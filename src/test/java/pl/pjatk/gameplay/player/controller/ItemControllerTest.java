package pl.pjatk.gameplay.player.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.model.Item;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.ItemService;
import pl.pjatk.gameplay.service.PlayerService;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ItemService itemService;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/item")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldMatchContent() throws Exception {
        Item item = new Item(2L,"test","health",10,3,"poison");
        itemService.save(item);
        mockMvc.perform(get("/item/2"))
                .andDo(print())
                .andExpect(content().string(equalTo("{\"id\":2,\"name\":\"test\",\"statname\":\"health\",\"statvalue\":10,\"aditionalEfect\":\"poison\",\"uses\":3}")));
    }

    @Test
    void shouldPostItem() throws Exception {
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"test\",\"statname\":\"health\",\"statvalue\":10,\"aditionalEfect\":\"poison\",\"uses\":3}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string((equalTo("{\"id\":2,\"name\":\"test\",\"statname\":\"health\",\"statvalue\":10,\"aditionalEfect\":\"poison\",\"uses\":3}"))));
    }

    @Test
    void shouldHealPlayer() throws Exception{
        Item item = new Item(1L,"test","health",5,3,"");
        itemService.save(item);
        Player player = new Player("s2s",3L,10,10,10);
        playerService.save(player);
        mockMvc.perform(put("/item/1/player/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"test\",\"statname\":\"health\",\"statvalue\":5,\"aditionalEfect\":\"\",\"uses\":2}")));
    }

    @Test
    void shouldRunOutOfUsed() throws Exception{
        Item item = new Item(1L,"test","health",5,1,"");
        itemService.save(item);
        Player player = new Player("s2s",1L,10,10,10);
        playerService.save(player);
        mockMvc.perform(put("/item/1/player/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(equalTo("You run out from uses of that item! Item disappear!")));
    }
}
