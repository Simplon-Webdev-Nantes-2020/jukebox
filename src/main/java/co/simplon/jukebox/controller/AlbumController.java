package co.simplon.jukebox.controller;

import co.simplon.jukebox.model.Album;
import co.simplon.jukebox.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/jukebox")
public class AlbumController {

	@Autowired
	AlbumService service;
	
	/**
	 * Liste des albums
	 * @param search : critère de recherche
	 * @return liste des albums
	 */
	@CrossOrigin
	@GetMapping("/albums")
	public ResponseEntity<List<Album>> getAllAlbum(@RequestParam(value="search", defaultValue="") String search) {
		List<Album> listAlbum;
		try {
			listAlbum = service.findAll(search);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listAlbum);
	}

	/**
	 * recherche d'un album par son id
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/albums/{id}")
	ResponseEntity<Album> getAlbumById(@PathVariable(value="id") long id) {
		Optional<Album> album = service.findById(id);
		if (album.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(album.get());
	}
	
	/**
	 * liste des albums d'un artist
	 * @return liste des albums
	 */
	@CrossOrigin
	@GetMapping("/artists/{id}/albums")
	public ResponseEntity<List<Album>> getAllAlbum(@PathVariable(value="id") long id) {
		List<Album> listAlbum;
		try {
			listAlbum = service.findByArtist(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listAlbum);
	}

	/**
	 * creation d'un album
	 * @param album
	 * @return
	 */
	@CrossOrigin
	@RolesAllowed({"MANAGER"})
	@PostMapping("/albums")
	ResponseEntity<Album> addAlbum(@Valid @RequestBody Album album){
		return ResponseEntity.ok().body(service.insert(album));
	}
	
	@CrossOrigin
	@RolesAllowed({"MANAGER"})
	@PutMapping("/albums/{id}")
	ResponseEntity<Album> updateAlbum(@PathVariable(value="id") long id, @Valid @RequestBody Album album){
		Album updatedAlbum = service.update(id, album);
		if(updatedAlbum == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(updatedAlbum);
	}

	@CrossOrigin
	@RolesAllowed({"ADMIN"})
	@DeleteMapping("/albums/{id}")
	ResponseEntity<Album> deleteAlbum(@PathVariable(value="id") long id){
		Optional<Album> album = service.findById(id);
		if(album.isEmpty())
			return ResponseEntity.notFound().build();
		
		service.delete(album.get().getId());
		return ResponseEntity.accepted().build();
	}


}
