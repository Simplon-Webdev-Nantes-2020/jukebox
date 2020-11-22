package co.simplon.jukebox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.jukebox.model.Track;
import co.simplon.jukebox.repository.TrackRepository;


@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackRepository repository;
	
	@Override
	public List<Track> findAll(String search) {
		if (! "".equals(search))
			return repository.findByTitleIgnoreCaseContaining(search);
		else
			return repository.findAll();
	}
	
	@Override
	public Optional<Track> findById (Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Track insert(Track track) {
		return repository.save(track);
	}
	
	@Override
	public Track update(Long id, Track track) {
		
		
		Optional<Track> optionalTrack = this.findById(id);
		
		if(optionalTrack.isPresent()) {
			
			Track trackToUpdate = optionalTrack.get(); 
			trackToUpdate.setTitle(track.getTitle());
			trackToUpdate.setDuration(track.getDuration());
			if (trackToUpdate.getPreview() != null)
				trackToUpdate.setPreview(track.getPreview());
			return repository.save(trackToUpdate);
		}
		
		return null;
	}
	
	@Override
	public void delete(Long id) {
		Optional<Track> track = this.findById(id);
		if (track.isPresent()) {
			repository.delete(track.get());
		}
	}

}
