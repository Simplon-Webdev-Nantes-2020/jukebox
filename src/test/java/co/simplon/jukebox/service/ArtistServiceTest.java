package co.simplon.jukebox.service;

import co.simplon.jukebox.model.Artist;
import co.simplon.jukebox.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {

    @Mock
    ArtistRepository repository;

    @InjectMocks
    ArtistService service = new ArtistServiceImpl();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("find all artists")
    @Test
    public void findAllTest() {
        //GIVEN
        List<Artist> inputArtists = new ArrayList<>();
        inputArtists.add(new Artist("Gaston","mybio",5));
        inputArtists.add(new Artist("Marguerite","Il était une fois",3));
        inputArtists.add(new Artist("Marie","la petite maison",3));
        when(repository.findAll()).thenReturn(inputArtists);

        //WHEN
        List<Artist> outputArtists = service.findAll("");

        //THEN
        assertThat(outputArtists.size()).isEqualTo(3);

    }

    @DisplayName("find all artists by name")
    @Test
    public void findAllByNameTest() {

        //GIVEN
        List<Artist> inputArtists = new ArrayList<>();
        final String str = "Ma";
        inputArtists.add(new Artist("Marguerite","Il était une fois",3));
        inputArtists.add(new Artist("Marie","la petite maison",3));
        when(repository.findByNameContaining(str)).thenReturn(inputArtists);

        //WHEN
        List<Artist> outputArtists = service.findAll(str);

        //THEN
        assertThat(outputArtists.size()).isEqualTo(2);

    }

    @DisplayName("find artist by id")
    @Test
    public void findByIdTest() {

        //GIVEN
        long idArtist = 50;
        Optional<Artist> inputArtist = Optional.of(new Artist("Indo","c'est ma bio",50));
        inputArtist.get().setId(idArtist);
        when(repository.findById(idArtist)).thenReturn(inputArtist);

        //WHEN
        Optional<Artist> outputArtist = service.findById(idArtist);
        Artist artist = outputArtist.orElseThrow();

        //THEN
        assertThat(artist.getId()).isEqualTo(idArtist);
        assertThat(artist.getName()).isEqualTo("Indo");

    }

    @DisplayName("insert artist")
    @Test
    public void insertTest() {

        //GIVEN
        long idArtist = 50;
        Artist inputArtist = new Artist("Indo","c'est ma bio",50);
        Artist outputArtist = new Artist("Indo","c'est ma bio",50);
        outputArtist.setId(idArtist);
        when(repository.save(inputArtist)).thenReturn(outputArtist);

        //WHEN
        outputArtist = service.insert(inputArtist);

        //THEN
        assertThat(outputArtist.getId()).isEqualTo(idArtist);
        assertThat(outputArtist.getName()).isEqualTo("Indo");

    }

    @DisplayName("update artist")
    @Test
    public void updateTest() {

        //GIVEN
        long idArtist = 50;
        Artist artist = new Artist("Indo","c'est ma bio",50);
        artist.setId(idArtist);
        when(repository.findById(idArtist)).thenReturn(Optional.of(artist));
        when(repository.save(artist)).thenReturn(artist);

        //WHEN
        Artist outputArtist = service.update(idArtist, artist);

        //THEN
        assertThat(outputArtist.getId()).isEqualTo(idArtist);
        assertThat(outputArtist.getName()).isEqualTo("Indo");

    }

}
