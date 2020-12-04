package co.simplon.jukebox.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import co.simplon.jukebox.service.ArtistService;

@WebMvcTest(controllers = ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService service;

    @Test
    public void testGetArtists() throws Exception {
        mockMvc.perform(get("/jukebox/artists"))
            .andExpect(status().isOk());
    }

}
