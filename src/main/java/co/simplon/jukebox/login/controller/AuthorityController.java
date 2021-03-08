package co.simplon.jukebox.login.controller;

import co.simplon.jukebox.login.model.Authority;
import co.simplon.jukebox.login.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/roles")
public class AuthorityController {

    @Autowired
    private AuthorityService service;

    @GetMapping
    public List<Authority> getAllRoles() {
        return service.findAll();
    }
}
