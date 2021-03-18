package co.simplon.jukebox.login;

import co.simplon.jukebox.common.InternalException;
import co.simplon.jukebox.login.dto.JwtTokens;
import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.login.model.Authority;
import co.simplon.jukebox.login.repository.AppUserRepository;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
     * microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String tokenSecretKey;

    private static final String USER_SECRET = "userSecret";


    @Value("${security.jwt.token.expire-length:1800}")
    private long tokenValidity;

    @Value("${security.jwt.refreshtoken.expire-length:82800}")
    private long refreshTokenValidity;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AppUserRepository userRepo;

    @PostConstruct
    protected void init() {
        tokenSecretKey = Base64.getEncoder().encodeToString(tokenSecretKey.getBytes());
    }

    /**
     * create 2 tokens : access and refresh
     *
     * @param user
     * @return
     */
    public JwtTokens createTokens(AppUser user) {
        generateSecretCode(user);
        final String token = createToken(user, false);
        final String refreshToken = createToken(user, true);
        return new JwtTokens(token, refreshToken);
    }

    /**
     * create one token
     *
     * @param user
     * @param refreshToken : true it's a creation of refresh token
     * @return
     */
    private String createToken(AppUser user, boolean refreshToken) {

        String username = user.getUsername();
        String email = user.getEmail();
        Set<Authority> roles = user.getAuthorities();

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("email", email);
        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).collect(Collectors.toList()));
        if (refreshToken) {
            claims.put(USER_SECRET, user.getSecretCode());
        }
        long validityLength = (refreshToken ? refreshTokenValidity : tokenValidity) * 1000;
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityLength);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, tokenSecretKey)
                .compact();
    }

    private void generateSecretCode(AppUser user) {
        LocalDateTime time = LocalDateTime.now();
        user.setSecretCode(user.getUsername() + time);
    }


    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * extract username from claims of token
     *
     * @param token
     * @return
     */
    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * extract token from header http
     * @param req
     * @return
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InternalException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * verify the validity of the refresh token
     * @param token
     * @return
     */
    public Jws<Claims> validateJwtRefreshToken(String token) {
        JwtParser parser = Jwts.parser().setSigningKey(tokenSecretKey);
        Jws<Claims> claims = parser.parseClaimsJws(token);

        String username = claims.getBody().getSubject();
        AppUser user = userRepo.findByUsername(username).orElseThrow();

        return parser.require(USER_SECRET, user.getSecretCode()).parseClaimsJws(token);
    }

    /**
     * create new access token from refresh token
     * @param jws
     * @return
     */
    public String createTokenFromClaims(Jws<Claims> jws) {
        long validityLength = tokenValidity * 1000;
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityLength);
        return Jwts.builder()
                .setClaims(jws.getBody())
                .signWith(SignatureAlgorithm.HS512, tokenSecretKey)
                .setExpiration(validity)
                .setIssuedAt(new Date())
                .compact();
    }

}
