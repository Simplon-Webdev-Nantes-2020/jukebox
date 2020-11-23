package co.simplon.jukebox.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import co.simplon.jukebox.model.PlayList;
import co.simplon.jukebox.service.PlayListService;

@RestController
@RequestMapping("/jukebox")
public class PlayListController {

	@Autowired
	PlayListService service;
	
	/**
	 * Liste des PlayListes
	 * @param search : critère de recherche
	 * @return liste des PlayListes
	 */
	@CrossOrigin
	@GetMapping("/playlists")
	public ResponseEntity<List<PlayList>> getAllPlayList(@RequestParam(value="search", defaultValue="") String search) {
		List<PlayList> listPlayList;
		try {
			listPlayList = service.findAll(search);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listPlayList);
	}

	/**
	 * recherche d'un PlayListe par son id
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/playlists/{id}")
	ResponseEntity<PlayList> getPlayListById(@PathVariable(value="id") long id) {
		Optional<PlayList> PlayList = service.findById(id);
		if (PlayList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(PlayList.get());
	}
	
	/**
	 * creation d'un PlayListe
	 * @param PlayList
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/playlists")
	ResponseEntity<PlayList> addPlayList(@Valid @RequestBody PlayList PlayList){
		return ResponseEntity.ok().body(service.insert(PlayList));
	}
	
	@CrossOrigin
	@PutMapping("/playlists/{id}")
	ResponseEntity<PlayList> updatePlayList(@PathVariable(value="id") long id, @Valid @RequestBody PlayList PlayList){
		PlayList updatedPlayListe = service.update(id, PlayList);
		if(updatedPlayListe == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(updatedPlayListe);
	}

	@CrossOrigin
	@DeleteMapping("/playlists/{id}")
	ResponseEntity<PlayList> deletePlayList(@PathVariable(value="id") long id){
		Optional<PlayList> PlayList = service.findById(id);
		if(PlayList.isEmpty())
			return ResponseEntity.notFound().build();
		
		service.delete(PlayList.get().getId());
		return ResponseEntity.accepted().build();
	}


}
