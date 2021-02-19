package co.simplon.jukebox.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jukebox.model.Artist;
import co.simplon.jukebox.service.ArtistService;

@RestController
@RequestMapping("/jukebox")
public class ArtistController {

	@Autowired
	ArtistService service;
	
	/**
	 * Liste des artistes
	 * @param search : critère de recherche
	 * @return liste des artistes
	 */
	@CrossOrigin
	@GetMapping("/artists")
	public ResponseEntity<List<Artist>> getAllArtist(@RequestParam(value="search", defaultValue="") String search) {
		List<Artist> listArtist;
		try {
			listArtist = service.findAll(search);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listArtist);
	}

	/**
	 * recherche d'un artiste par son id
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/artists/{id}")
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
	@Secured({"ROLE_MANAGER"})
	@PostMapping("/artists")
	ResponseEntity<Artist> addArtist(@Valid @RequestBody Artist artist){
		return ResponseEntity.ok().body(service.insert(artist));
	}
	
	@CrossOrigin
	@Secured({"ROLE_MANAGER"})
	@PutMapping("/artists/{id}")
	ResponseEntity<Artist> updateArtist(@PathVariable(value="id") long id, @Valid @RequestBody Artist artist){
		Artist updatedArtiste = service.update(id, artist);
		if(updatedArtiste == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(updatedArtiste);
	}

	@CrossOrigin
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/artists/{id}")
	ResponseEntity<Artist> deleteArtist(@PathVariable(value="id") long id){
		Optional<Artist> artist = service.findById(id);
		if(artist.isEmpty())
			return ResponseEntity.notFound().build();
		
		service.delete(artist.get().getId());
		return ResponseEntity.accepted().build();
	}


}
