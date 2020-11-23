package co.simplon.jukebox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.jukebox.model.Playlist;
import co.simplon.jukebox.repository.PlaylistRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	private PlaylistRepository repository;
	
	@Override
	public List<Playlist> findAll(String search) {
		if (! "".equals(search))
			return repository.findByNameIgnoreCaseContaining(search);
		else
			return repository.findAll();
	}
	
	@Override
	public Optional<Playlist> findById (Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Playlist insert(Playlist PlayList) {
		return repository.save(PlayList);
	}
	
	@Override
	public Playlist update(Long id, Playlist PlayList) {
		Optional<Playlist> optionalPlayList = this.findById(id);
		if(optionalPlayList.isPresent()) {
			return repository.save(PlayList);
		}
		return null;
	}
	
	@Override
	public void delete(Long id) {
		Optional<Playlist> PlayList = this.findById(id);
		if (PlayList.isPresent()) {
			repository.delete(PlayList.get());
		}
	}

}
