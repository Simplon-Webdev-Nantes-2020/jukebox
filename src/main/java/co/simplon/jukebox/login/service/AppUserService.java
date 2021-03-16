package co.simplon.jukebox.login.service;

import co.simplon.jukebox.common.InvalidEntryException;
import co.simplon.jukebox.login.dto.JwtTokens;
import co.simplon.jukebox.login.dto.NewUserDto;
import co.simplon.jukebox.login.dto.RegistrationDto;
import co.simplon.jukebox.login.dto.NewPasswordDto;
import co.simplon.jukebox.login.model.AppUser;
import org.springframework.security.core.AuthenticationException;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    JwtTokens signin(String username, String password) throws AuthenticationException;
    JwtTokens signup(RegistrationDto user) throws InvalidEntryException;
    JwtTokens refreshJwtToken(String token);
    List<AppUser> findAllUsers(String name, String email);
    Optional<AppUser> findById(Long id);
    Optional<AppUser> findByName(String name);
    AppUser insert(NewUserDto user);
    AppUser update(Long id, AppUser user);
    void delete(Long id);
    void changePassword(Long id, NewPasswordDto password);
}
