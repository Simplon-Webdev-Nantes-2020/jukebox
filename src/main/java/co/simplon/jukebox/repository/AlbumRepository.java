package co.simplon.jukebox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.model.Album;


public interface AlbumRepository extends JpaRepository<Album, Long>{

	public List<Album> findByTitleIgnoreCaseContaining(String title) ;
	public List<Album> findByArtistId(Long artistId);
}
