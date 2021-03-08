package co.simplon.jukebox.login.controller;

import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.login.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
public class AppUserController {

    @Autowired
    private AppUserService service;

    @GetMapping
    public List<AppUser> getAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping("/{id}")
    ResponseEntity<AppUser> getAlbumById(@PathVariable(value="id") long id) {
        Optional<AppUser> appUser = service.findById(id);
        if (appUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(appUser.get());
    }


}
