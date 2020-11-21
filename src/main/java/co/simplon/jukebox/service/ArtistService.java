package co.simplon.jukebox.service;

import java.util.List;
import java.util.Optional;

import co.simplon.jukebox.model.Artist;

public interface ArtistService {

	Optional<Artist> findById(Long id);
	List<Artist> findAll(String search);
	Artist insert(Artist artist);
	Artist update(Long id, Artist artist);
	void delete(Long id);
}
