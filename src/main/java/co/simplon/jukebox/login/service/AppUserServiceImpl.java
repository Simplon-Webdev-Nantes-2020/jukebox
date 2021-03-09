package co.simplon.jukebox.login.service;

import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.login.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{
    @Autowired
    AppUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AppUser> findAllUsers(String name, String email) {
        if (! "".equals(name))
            return  repository.findByUsernameStartingWith(name);
        else if (! "".equals(email))
            return repository.findByEmailStartingWith(email);
        return repository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<AppUser> findByName(String name) {
        return repository.findByUsername(name);
    }

    @Override
    public AppUser insert(AppUser user) {
        user.setPassword(passwordEncoder.encode("password"));
        user.setCreatedDate(LocalDateTime.now());
        return repository.save(user);
    }

    @Override
    public AppUser update(Long id, AppUser user) {
        Optional<AppUser> optionalUser = this.findById(id);
        if(optionalUser.isPresent()) {
            user.setPassword(optionalUser.get().getPassword());
            return repository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<AppUser> user = this.findById(id);
        user.ifPresent(appUser -> repository.delete(appUser));
    }
    
}
