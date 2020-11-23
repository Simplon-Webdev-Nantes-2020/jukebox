package co.simplon.jukebox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.model.Playlist;


public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

	public List<Playlist> findByNameIgnoreCaseContaining(String name) ;
}
