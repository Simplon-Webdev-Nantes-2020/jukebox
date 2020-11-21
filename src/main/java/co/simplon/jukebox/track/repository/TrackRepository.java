package co.simplon.jukebox.track.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.track.model.Track;


public interface TrackRepository extends JpaRepository<Track, Long>{

	public List<Track> findByTitleContaining(String title) ;
}
