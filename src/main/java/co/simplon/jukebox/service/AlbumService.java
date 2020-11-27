package co.simplon.jukebox.service;

import java.util.List;
import java.util.Optional;

import co.simplon.jukebox.model.Album;


public interface AlbumService {

	Optional<Album> findById(Long id);
	List<Album> findAll(String search);
	List<Album> findByArtist(Long id);
	Album insert(Album album);
	Album update(Long id, Album album);
	void delete(Long id);
}
