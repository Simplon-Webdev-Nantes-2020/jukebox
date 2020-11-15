package co.simplon.jukebox.artiste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.artiste.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long>{

	public List<Artist> findByNameContaining(String name) ;
}
