package co.simplon.jukebox.login.repository;

import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
    List<AppUser> findByUsernameStartingWith(String name) ;
    List<AppUser> findByEmailStartingWith(String email) ;

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
