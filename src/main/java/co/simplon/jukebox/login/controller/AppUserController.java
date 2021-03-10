package co.simplon.jukebox.login.controller;

import co.simplon.jukebox.login.dto.PasswordsDto;
import co.simplon.jukebox.login.model.AppUser;
import co.simplon.jukebox.login.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
public class AppUserController {

    @Autowired
    private AppUserService service;

    @CrossOrigin
    @GetMapping
    public List<AppUser> getAllUsers(
            @RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="email", defaultValue="") String email) {
        return service.findAllUsers(name,email);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity<AppUser> getUserById(@PathVariable(value="id") long id) {
        Optional<AppUser> appUser = service.findById(id);
        if (appUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(appUser.get());
    }

    @CrossOrigin
    @PostMapping
    AppUser addUser(@Valid @RequestBody AppUser user){
        return service.insert(user);
    }

    @PutMapping("/{id}")
    ResponseEntity<AppUser> updateUser(@PathVariable(value="id") long id, @Valid @RequestBody AppUser user){
        AppUser updatedUser = service.update(id, user);
        if(updatedUser == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AppUser> deleteUser(@PathVariable(value="id") long id){
        Optional<AppUser> user = service.findById(id);
        if(user.isEmpty())
            return ResponseEntity.notFound().build();

        service.delete(user.get().getId());
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/password")
    ResponseEntity<AppUser> changePassword(
            @PathVariable(value="id") long id,
            @Valid @RequestBody PasswordsDto pwd){
        service.changePassword(id, pwd);
        return ResponseEntity.accepted().build();
    }

}
