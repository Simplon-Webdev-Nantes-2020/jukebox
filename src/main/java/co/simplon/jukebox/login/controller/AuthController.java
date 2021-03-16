package co.simplon.jukebox.login.controller;

import co.simplon.jukebox.login.dto.JwtTokens;
import co.simplon.jukebox.login.dto.RegistrationDto;
import co.simplon.jukebox.login.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AppUserService service;

    @CrossOrigin
    @PostMapping("/signin")
    public ResponseEntity<JwtTokens> login(@RequestParam String username, @RequestParam String password) {
        JwtTokens tokens = service.signin(username, password);
        return ResponseEntity.ok().body(tokens);
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtTokens> signup(@RequestBody RegistrationDto user) {
        JwtTokens tokens = service.signup(user);
        return ResponseEntity.ok().body(tokens);
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<JwtTokens> refreshToken(@RequestBody String refreshToken) {
        JwtTokens tokens = service.refreshJwtToken(refreshToken);
        return ResponseEntity.ok().body(tokens);
    }
}
