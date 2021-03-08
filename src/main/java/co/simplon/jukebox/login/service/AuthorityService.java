package co.simplon.jukebox.login.service;

import co.simplon.jukebox.login.model.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAll();
}
