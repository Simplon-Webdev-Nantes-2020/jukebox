package co.simplon.jukebox.artiste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.artiste.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long>{

}
