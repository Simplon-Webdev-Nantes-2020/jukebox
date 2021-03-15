package co.simplon.jukebox.login.repository;

import co.simplon.jukebox.login.model.Authority;
import co.simplon.jukebox.login.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Override
    List<Authority> findAll();

    Optional<Authority> findByAuthority(Role role);
}
