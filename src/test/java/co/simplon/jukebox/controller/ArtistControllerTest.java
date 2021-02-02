package co.simplon.jukebox.controller;

import co.simplon.jukebox.model.Artist;
import co.simplon.jukebox.service.ArtistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("controller_artist")
@WebMvcTest(controllers = ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService service;

    @DisplayName("Artist list")
    @Test
    public void getAllArtistTest() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(get("/jukebox/artists"))

                //THEN le statut de la réponse http est OK.
                .andExpect(status().isOk());
    }

    @DisplayName("get one artist")
    @Test
    public void getArtistByIdTest() throws Exception {

        //GIVEN
        long idArtist = 1;
        Optional<Artist> artist = Optional.of(new Artist("Indo","c'est ma bio",50));
        artist.get().setId(idArtist);
        when(service.findById(idArtist)).thenReturn(artist);

        //WHEN
        mockMvc.perform(get("/jukebox/artists/{id}", idArtist))

                .andDo(print()) //pour les test affiche le résultat de la requete dans le log

                //THEN le statut de la réponse http est OK.
                .andExpect(status().isOk())

                //THEN le type de contenu est json
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                //THEN existence d'une réponse json.
                .andExpect(jsonPath("$").exists())

                //THEN l'attribut 'id' a pour valeur 1
                .andExpect(jsonPath("$.id").value("1"))

                //THEN l'attribut 'name' a pour valeur 'Indo'
                .andExpect(jsonPath("$.name").value("Indo"))

                //THEN l'attribut 'bio' a pour valeur "c'est ma bio"
                .andExpect(jsonPath("$.bio").value("c'est ma bio"))

                //THEN l'attribut 'fanNumber' a pour valeur 20
                .andExpect(jsonPath("$.fanNumber").value("50"));
    }

    @DisplayName("Add artist")
    @Test
    public void addArtistTest() throws Exception {

        //GIVEN
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Artist("Indo","c'est ma bio",50));

        Artist artistOut = new Artist("Indo","c'est ma bio",50);
        artistOut.setId(1);
        when(service.insert(isA(Artist.class))).thenReturn(artistOut);

        //WHEN
        mockMvc.perform(post("/jukebox/artists")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                        )
//                .andDo(print()) //pour les test affiche le résultat de la requete dans le log

                //THEN statut de la réponse http est OK.
                .andExpect(status().isOk())

                //Assert le type de contenu de réponse.
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                //THEN
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

    @DisplayName("Update artist")
    @Test
    public void updateArtistTest() throws Exception {

        //GIVEN
        long idArtist = 1;
        Artist artist = new Artist("Indo","c'est ma bio",50);
        artist.setId(idArtist);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(artist);

        when(service.update(isA(Long.class), isA(Artist.class))).thenReturn(artist);

        //WHEN
        mockMvc.perform(put("/jukebox/artists/{id}", idArtist)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                        )

                //THEN statut de la réponse http est OK.
                .andExpect(status().isOk())

                //Assert le type de contenu de réponse.
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                //THEN
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

    @DisplayName("Delete artist")
    @Test
    public void deleteArtist() throws Exception {

        //GIVEN
        long idArtist = 1;
        Optional<Artist> artist = Optional.of(new Artist("Indo","c'est ma bio",50));
        artist.get().setId(idArtist);
        when(service.findById(idArtist)).thenReturn(artist);
        when(service.insert(artist.get())).thenReturn(artist.get());
        when(service.update(idArtist, artist.get())).thenReturn(artist.get());
        doNothing().when(service).delete(idArtist);

        //WHEN
        mockMvc.perform(delete("/jukebox/artists/{id}", idArtist).accept(MediaType.APPLICATION_JSON))

                //THEN delete succesfull
                .andExpect(status().is2xxSuccessful());
    }

}
