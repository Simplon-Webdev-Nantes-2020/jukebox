package co.simplon.jukebox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.jukebox.model.Album;
import co.simplon.jukebox.repository.AlbumRepository;


@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepository repository;
	
	@Override
	public List<Album> findAll(String search) {
		if (! "".equals(search))
			return repository.findByTitleIgnoreCaseContaining(search);
		else
			return repository.findAll();
	}
	
	@Override
	public List<Album> findByArtist(Long id) {
		return repository.findByArtistId(id);
	}
	
	@Override
	public Optional<Album> findById (Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Album insert(Album album) {
		return repository.save(album);
	}
	
	@Override
	public Album update(Long id, Album album) {
		Optional<Album> optionalAlbum = this.findById(id);
		if(optionalAlbum.isPresent()) {
			return repository.save(album);
		}
		return null;
	}
	
	@Override
	public void delete(Long id) {
		Optional<Album> album = this.findById(id);
		if (album.isPresent()) {
			repository.delete(album.get());
		}
	}

}
