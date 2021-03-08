package co.simplon.jukebox.login.service;

import co.simplon.jukebox.login.model.Authority;
import co.simplon.jukebox.login.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    AuthorityRepository repo;

    @Override
    public List<Authority> findAll() {
        return repo.findAll();
    }
}
