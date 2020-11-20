package co.simplon.jukebox.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.album.model.Album;


public interface AlbumRepository extends JpaRepository<Album, Long>{

	public List<Album> findByTitleContaining(String title) ;
}
