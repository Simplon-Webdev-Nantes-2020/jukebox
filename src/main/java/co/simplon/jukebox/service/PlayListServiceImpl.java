package co.simplon.jukebox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.jukebox.model.PlayList;
import co.simplon.jukebox.repository.PlayListRepository;

@Service
public class PlayListServiceImpl implements PlayListService {

	@Autowired
	private PlayListRepository repository;
	
	@Override
	public List<PlayList> findAll(String search) {
		if (! "".equals(search))
			return repository.findByNameIgnoreCaseContaining(search);
		else
			return repository.findAll();
	}
	
	@Override
	public Optional<PlayList> findById (Long id) {
		return repository.findById(id);
	}
	
	@Override
	public PlayList insert(PlayList PlayList) {
		return repository.save(PlayList);
	}
	
	@Override
	public PlayList update(Long id, PlayList PlayList) {
		Optional<PlayList> optionalPlayList = this.findById(id);
		if(optionalPlayList.isPresent()) {
			return repository.save(PlayList);
		}
		return null;
	}
	
	@Override
	public void delete(Long id) {
		Optional<PlayList> PlayList = this.findById(id);
		if (PlayList.isPresent()) {
			repository.delete(PlayList.get());
		}
	}

}
