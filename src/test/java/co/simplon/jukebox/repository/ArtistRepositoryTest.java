package co.simplon.jukebox.repository;

import co.simplon.jukebox.model.Artist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArtistRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private ArtistRepository repository;

    @DisplayName("find artist by name")
    @Test
    public void findByNameContainingTest( ) {
    	//GIVEN
    	Artist artistJulian = new Artist("Julian","my bio",120);
    	entityManager.persist(artistJulian);
    	Artist artistMary = new Artist("Mary","my bio",140);
    	entityManager.persist(artistMary);

    	//WHEN
    	List<Artist> artistSelect = repository.findByNameContaining("ul");

    	//THEN
    	assertThat(artistSelect.size()).isEqualTo(1);
    }
}
