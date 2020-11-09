package co.simplon.jukebox.artiste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	 * Test si serveur opérationnel
	 * Ce mapping existe uniquement pour la formation
	 * Sur un serveur de production, cela n'a pas lieu dêtre
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/artist/hello")
	ResponseEntity<Artist> getArtistToto() {
		Artist hello = new Artist();
		hello.setName("Hello");
		hello.setBio("Comment allez-vous ?");
		hello.setFanNumber(100);
		return ResponseEntity.ok().body(hello);
	}

	/**
	 * Liste de tous les artistes
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/artist")
	List<Artist> getAllArtiste() {
		return service.findAll();
	}

	/**
	 * recherche d'un artiste par son id
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/artist/{id}")
	ResponseEntity<Artist> getArtistById(@PathVariable(value="id") long id) {
		Optional<Artist> artist = service.findById(id);
		if (artist.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(artist.get());
	}
	
	/**
	 * creation d'un artiste
	 * @param artist
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/artist")
	Artist addArtist(@Valid @RequestBody Artist artist){
		return service.insert(artist);
	}
	
	@CrossOrigin
	@PutMapping("/artist/{id}")
	ResponseEntity<Artist> updateArtiste(@PathVariable(value="id") long id, @Valid @RequestBody Artist artist){
		Artist updatedArtiste = service.update(id, artist);
		if(updatedArtiste == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(updatedArtiste);
	}

	@CrossOrigin
	@DeleteMapping("/artist/{id}")
	ResponseEntity<Artist> deleteArtist(@PathVariable(value="id") long id){
		Optional<Artist> artist = service.findById(id);
		if(artist.isEmpty())
			return ResponseEntity.notFound().build();
		
		service.delete(artist.get().getId());
		return ResponseEntity.ok().build();
	}


}
