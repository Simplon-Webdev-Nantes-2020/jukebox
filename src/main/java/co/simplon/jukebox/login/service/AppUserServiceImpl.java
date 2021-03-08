package co.simplon.jukebox.login.service;

import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.login.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{
    @Autowired
    AppUserRepository repository;

    @Override
    public List<AppUser> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return repository.findById(id);
    }


}
