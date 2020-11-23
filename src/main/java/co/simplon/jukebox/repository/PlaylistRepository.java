package co.simplon.jukebox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jukebox.model.PlayList;


public interface PlayListRepository extends JpaRepository<PlayList, Long>{

	public List<PlayList> findByNameIgnoreCaseContaining(String name) ;
}
