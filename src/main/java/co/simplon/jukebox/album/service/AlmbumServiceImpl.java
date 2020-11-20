package co.simplon.jukebox.album.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.jukebox.album.model.Album;
import co.simplon.jukebox.album.repository.AlbumRepository;


@Service
public class AlmbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepository repository;
	
	@Override
	public List<Album> findAll(String search) {
		if (! "".equals(search))
			return repository.findByTitleContaining(search);
		else
			return repository.findAll();
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
			
			Album albumToUpdate = optionalAlbum.get(); 
			albumToUpdate.setTitle(album.getTitle());
			if (album.getReleasedate() != null)
				albumToUpdate.setReleasedate(album.getReleasedate());
			if (album.getFanNumber() != null)
				albumToUpdate.setFanNumber(album.getFanNumber());
			return repository.save(albumToUpdate);
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
