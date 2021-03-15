package co.simplon.jukebox.login;

import co.simplon.jukebox.common.InternalException;
import co.simplon.jukebox.login.dto.JwtTokens;
import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.login.model.Authority;
import co.simplon.jukebox.login.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

  /**
   * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
   * microservices environment, this key would be kept on a config-server.
   */
  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length:1800}")
  private long tokenValidity;

  @Value("${security.jwt.refreshtoken.expire-length:82800}")
  private long resfreshTokenValidity;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public JwtTokens createTokens(String username, Set<Authority> roles) {
    final String token = createToken(username, roles, false);
    final String refreshToken = createToken(username, roles, true);

    return new JwtTokens(token, refreshToken);
  }
  private String createToken(String username, Set<Authority> roles, boolean refreshToken) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

    long validityLength = (refreshToken?resfreshTokenValidity:tokenValidity) * 1000;
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityLength);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new InternalException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
/*
  public Jws<Claims> validateJwtRefreshToken(String token) {
    JwtParser parser = Jwts.parser().setSigningKey(secretKey);
    Jws<Claims> claims = parser.parseClaimsJws(token);

    Optional<AppUser> user = repository.findByUsername(claims.getBody().getSubject());

    return parser.require(USER_SECRET, user.getUserSecret()).parseClaimsJws(token);
  }
*/
}
