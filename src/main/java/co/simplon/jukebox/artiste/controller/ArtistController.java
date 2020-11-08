package co.simplon.jukebox.artiste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import co.simplon.jukebox.artiste.model.Artist;
import co.simplon.jukebox.artiste.service.ArtistService;

@RestController
@RequestMapping("/jukebox")
public class ArtistController {

	@Autowired
	
	ArtistService service;
	
	/**
	 * Liste de tous les artistes
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/artist)")
	List<Artist> getAllArtiste() {
		return service.findAll();
	}

	/**
	 * recherche d'un artiste par son id
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/artist/{id})")
	ResponseEntity<Artist> getArtistById(@PathVariable(value="id") long id) {
		Optional<Artist> artist = service.findById(id);
		if (artist.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(artist.get());
	}
	
	@CrossOrigin
	@PostMapping("/artist")
	Artist addArtist(@Valid @RequestBody Artist artist){
		return service.insert(artist);
	}

}
