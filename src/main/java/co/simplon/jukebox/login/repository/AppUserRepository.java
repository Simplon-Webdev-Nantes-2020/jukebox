package co.simplon.jukebox.login.repository;

import co.simplon.jukebox.login.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
    List<AppUser> findByUsernameStartingWith(String name) ;
    List<AppUser> findByEmailStartingWith(String email) ;

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("update AppUser u set u.secretCode = null where u.username = :username")
    int resetSecretCodeFor(String username);

    @Transactional
    @Modifying
    @Query("update AppUser u set u.secretCode = null")
    int resetAllSecretCode();

}
