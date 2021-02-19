package co.simplon.jukebox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/admin")
public class AdminController {

	/**
	 * Test si serveur opérationnel
	 * Ce mapping existe uniquement pour la formation
	 * Sur un serveur de production, cela n'a pas lieu dêtre
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/hello")
	ResponseEntity<String> helloWord() {
		return ResponseEntity.ok().body("Hello World");
	}
}
