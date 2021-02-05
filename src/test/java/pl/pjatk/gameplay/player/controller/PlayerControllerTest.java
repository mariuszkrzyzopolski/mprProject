package pl.pjatk.gameplay.player.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerService playerService;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/player")).andDo(print()).andExpect(status().isOk());
    }


    @Test
    void shouldMatchContent() throws Exception {
        Player player = new Player("ss",1L,10,10,10);
        playerService.save(player);
        mockMvc.perform(get("/player/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"nickname\":\"ss\",\"attack\":10,\"health\":10,\"mana\":10}")));
    }

    @Test
    void shouldMatchAllContent() throws Exception {
        Player player = new Player("s2s",1L,10,10,10);
        playerService.save(player);
        Player player2 = new Player("s22",2L,20,10,10);
        playerService.save(player2);
        mockMvc.perform(get("/player"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"nickname\":\"s2s\",\"attack\":10,\"health\":10,\"mana\":10},{\"id\":2,\"nickname\":\"s22\",\"attack\":20,\"health\":10,\"mana\":10}]")));
    }

    @Test
    void shouldPostPlayer() throws Exception {
        mockMvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nickname\":\"a\", \"mana\":100,\"health\":200,\"attack\":20}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string((equalTo("{\"id\":2,\"nickname\":\"a\",\"attack\":20,\"health\":200,\"mana\":100}"))));

    }

    @Test
    void shouldDeletePlayer() throws Exception {
        Player player = new Player("s2s",1L,10,10,10);
        playerService.save(player);
        mockMvc.perform(delete("/player/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void shouldDamagePlayer() throws Exception {
        Player player = new Player("s2s",3L,10,10,10);
        playerService.save(player);
        mockMvc.perform(put("/player/3/damage/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string((equalTo("{\"id\":3,\"nickname\":\"s2s\",\"attack\":10,\"health\":5,\"mana\":10}"))));

    }

    @Test
    void shouldKillPlayer() throws Exception {
        Player player = new Player("s2s",2L,10,5,10);
        playerService.save(player);
        mockMvc.perform(put("/player/2/damage/5"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string((equalTo("You are dead! Game Over!"))));

    }



}
