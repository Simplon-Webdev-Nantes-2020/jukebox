package co.simplon.jukebox.artiste.service;

import java.util.List;
import java.util.Optional;

import co.simplon.jukebox.artiste.model.Artist;

public interface ArtistService {

	Optional<Artist> findById(Long id);
	List<Artist> findAll();
	Artist insert(Artist artist);
	Artist update(Long id, Artist artist);
	void delete(Long id);
}
