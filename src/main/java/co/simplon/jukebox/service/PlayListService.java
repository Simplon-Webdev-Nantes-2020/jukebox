package co.simplon.jukebox.service;

import java.util.List;
import java.util.Optional;

import co.simplon.jukebox.model.PlayList;


public interface PlayListService {

	Optional<PlayList> findById(Long id);
	List<PlayList> findAll(String search);
	PlayList insert(PlayList PlayList);
	PlayList update(Long id, PlayList PlayList);
	void delete(Long id);
}
