package co.simplon.jukebox.login.service;

import co.simplon.jukebox.common.InvalidEntryException;
import co.simplon.jukebox.login.JwtTokenProvider;
import co.simplon.jukebox.login.dto.JwtTokens;
import co.simplon.jukebox.login.dto.NewPasswordDto;
import co.simplon.jukebox.login.dto.NewUserDto;
import co.simplon.jukebox.login.dto.RegistrationDto;
import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.login.model.Authority;
import co.simplon.jukebox.login.model.Role;
import co.simplon.jukebox.login.repository.AppUserRepository;
import co.simplon.jukebox.login.repository.AuthorityRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository repository;
    private final AuthorityRepository repoAuthority;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AppUserServiceImpl(AppUserRepository repository, AuthorityRepository repoAuthority,
                              PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                              JwtTokenProvider jwtTokenProvider) {
        this.repository = repository;
        this.repoAuthority = repoAuthority;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public JwtTokens signin(String username, String password) throws InvalidEntryException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        AppUser user = repository.findByUsername(username).orElseThrow();
        return jwtTokenProvider.createTokens(user);
    }

    @Override
    public JwtTokens signup(RegistrationDto inputUser) throws InvalidEntryException {

        // verify that user not exist
        if (repository.existsByUsername(inputUser.getUsername()))
            throw new InvalidEntryException("User", "User already exist");

        // default role = USER
        Optional<Authority> roleUser = repoAuthority.findByAuthority(Role.ROLE_USER);

        // user in database
        AppUser appUser = new AppUser(inputUser.getUsername(),inputUser.getEmail());
        appUser.setPassword(passwordEncoder.encode(inputUser.getPassword()));
        appUser.setAuthorities(Collections.singleton(roleUser.orElseThrow()));
        appUser.setCreatedDate(LocalDateTime.now());
        appUser.setActive(true);
        repository.save(appUser);

        // JWT
        return jwtTokenProvider.createTokens(appUser);
    }

    @Override
    public JwtTokens refreshJwtToken(String refreshToken) {
        Jws<Claims> claims = jwtTokenProvider.validateJwtRefreshToken(refreshToken);
        String newToken = jwtTokenProvider.createTokenFromClaims(claims);
        return new JwtTokens(newToken, refreshToken);
    }

    @Override
    public List<AppUser> findAllUsers(String name, String email) {
        if (! "".equals(name))
            return  repository.findByUsernameStartingWith(name);
        else if (! "".equals(email))
            return repository.findByEmailStartingWith(email);
        return repository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<AppUser> findByName(String name) {
        return repository.findByUsername(name);
    }

    @Override
    public AppUser insert(NewUserDto userDto) {
        AppUser user = new AppUser(userDto.getUsername(), userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreatedDate(LocalDateTime.now());
        return repository.save(user);
    }

    @Override
    public AppUser update(Long id, AppUser user) {
        Optional<AppUser> optionalUser = this.findById(id);
        if(optionalUser.isPresent()) {
            user.setPassword(optionalUser.get().getPassword());
            return repository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<AppUser> user = this.findById(id);
        user.ifPresent(appUser -> repository.delete(appUser));
    }

    @Override
    public void changePassword(Long id, NewPasswordDto password) {
        AppUser user = repository.findById(id).orElseThrow();

        if (!password.isForcePwdChange()) {
            if (passwordEncoder.matches(password.getNewPassword(), user.getPassword()))
                throw new InvalidEntryException("User password", "Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(password.getNewPassword()));
        repository.save(user);
    }


}
