package co.simplon.jukebox.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import co.simplon.jukebox.model.Artist;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArtistRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private ArtistRepository repository;
    
    @Test
    public void findByNameContainingTest( ) {
    	Artist artistJulian = new Artist("Julian","my bio",120);
    	entityManager.persist(artistJulian);
    	Artist artistMary = new Artist("Mary","my bio",140);
    	entityManager.persist(artistMary);
    	
    	List<Artist> artistSelect = repository.findByNameContaining("ul");
    	assertEquals(1, artistSelect.size());
    }
}
