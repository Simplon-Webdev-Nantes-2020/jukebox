package co.simplon.jukebox.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.simplon.jukebox.model.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import co.simplon.jukebox.service.ArtistService;

import java.util.Optional;

@WebMvcTest(controllers = ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService service;

    /**
     * Liste des artistes
     */
    @Test
    public void getAllArtistTest() throws Exception {
        mockMvc.perform(get("/jukebox/artists"))
            .andExpect(status().isOk());
    }

    @Test
    public void getArtistByIdTest() throws Exception {

        //mock
        long idArtist = 1;
        Optional<Artist> artist = Optional.of(new Artist("Indo","c'est ma bio",50));
        artist.get().setId(idArtist);
        when(service.findById(idArtist)).thenReturn(artist);

        //test url
        mockMvc.perform(get("/jukebox/artists/" + idArtist))
                //Assert le statut de la réponse http est égal a OK.
                .andExpect(status().isOk())
                //Assert le type de contenue de réponse.
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //Assert l’existence d'une réponse json.
                .andExpect(jsonPath("$").exists())
                //Assert la valeur de l'attribut 'name' dans la réponse json.
                .andExpect(jsonPath("$.id").value("1"))
                //Assert la valeur de l'attribut 'name' dans la réponse json.
                .andExpect(jsonPath("$.name").value("Indo"))
                //Assert la valeur de l'attribut 'name' dans la réponse json.
                .andExpect(jsonPath("$.bio").value("c'est ma bio"))
                //Assert la valeur de l'attribut 'name' dans la réponse json.
                .andExpect(jsonPath("$.fanNumber").value("50"));
    }

}
