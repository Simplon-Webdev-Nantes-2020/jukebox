package co.simplon.jukebox.login.service;

import co.simplon.jukebox.login.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    List<AppUser> findAllUsers();
    Optional<AppUser> findById(Long id);
}
