package pl.pjatk.gameplay.player.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    void shouldMatchConent() throws Exception {
        Player player = new Player("ss",1L,10,10,10);
        playerService.save(player);
        mockMvc.perform(get("/player/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("test")));
    }
}
