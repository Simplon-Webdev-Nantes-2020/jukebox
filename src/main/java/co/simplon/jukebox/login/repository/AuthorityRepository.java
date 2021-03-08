package co.simplon.jukebox.login.repository;

import co.simplon.jukebox.login.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Override
    List<Authority> findAll();

}
