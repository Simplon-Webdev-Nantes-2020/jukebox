package co.simplon.jukebox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.model.Track;


public interface TrackRepository extends JpaRepository<Track, Long>{

	public List<Track> findByTitleIgnoreCaseContaining(String title) ;
}
