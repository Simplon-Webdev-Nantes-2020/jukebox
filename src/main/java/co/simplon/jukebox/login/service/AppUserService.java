package co.simplon.jukebox.login.service;

import co.simplon.jukebox.login.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    List<AppUser> findAllUsers(String name, String email);
    Optional<AppUser> findById(Long id);
    Optional<AppUser> findByName(String name);
    AppUser insert(AppUser user);
    AppUser update(Long id, AppUser user);
    void delete(Long id);
}
