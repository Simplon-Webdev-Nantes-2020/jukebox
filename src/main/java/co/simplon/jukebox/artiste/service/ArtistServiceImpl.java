package co.simplon.jukebox.artiste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.jukebox.artiste.model.Artist;
import co.simplon.jukebox.artiste.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistRepository repository;
	
	@Override
	public List<Artist> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Artist> findById (Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Artist insert(Artist artist) {
		return repository.save(artist);
	}
	
	@Override
	public Artist update(Artist artist) {
		return repository.save(artist);
	}
	
	@Override
	public void delete(Long id) {
		Optional<Artist> artist = this.findById(id);
		if (artist.isPresent()) {
			repository.delete(artist.get());
		}
	}

}
