package co.simplon.jukebox.album.service;

import java.util.List;
import java.util.Optional;

import co.simplon.jukebox.album.model.Album;


public interface AlbumService {

	Optional<Album> findById(Long id);
	List<Album> findAll(String search);
	Album insert(Album album);
	Album update(Long id, Album album);
	void delete(Long id);
}
