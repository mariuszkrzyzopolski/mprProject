package pl.pjatk.gameplay.player.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PlayerServiceTestIntegr {

    @Autowired
    private PlayerService playerService;

    @BeforeEach
    void clear() {
        playerService.delete();
    }

    @Test
    void shouldBeEmpty() {
        List<Player> all = playerService.findall();
        assertThat(all).isEmpty();
    }

    @Test
    void shouldAddPlayerToDB(){
        Player player = new Player("ss",1L,100,10,10);
        Player save = playerService.save(player);
        assertThat(save.getId()).isNotNull();
    }

    @Test
    void shouldFindAllPlayers(){
        Player player = new Player("ss",1L,100,10,10);
        Player player2 = new Player("ss",2L,100,10,10);
        playerService.save(player);
        playerService.save(player2);
        List<Player> all = playerService.findall();
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldFindPlayer() {
        Player player = new Player("ss",1L,100,10,10);
        playerService.save(player);
        assertThat(playerService.findById(1L)).isNotEmpty();
    }

    @Test
    void shouldDeletePlayer() {
        //Need use player.getId() method because db do not reusing IDs
        Player player = playerService.save(new Player("ss",1L,100,10,10));
        Player player2 = playerService.save(new Player("s2",2L,100,10,10));
        playerService.delete(player2.getId());
        assertThat(playerService.findall()).isNotEmpty();
        assertThat(playerService.findById(player.getId())).isNotEmpty();
        assertThat(playerService.findById(player2.getId())).isEmpty();
    }

    @Test
    void shouldDeleteAllPlayer() {
        playerService.save(new Player("ss",1L,100,10,10));
        playerService.save(new Player("s2",2L,100,10,10));
        playerService.delete();
        assertThat(playerService.findall()).isEmpty();
    }

    @Test
    void shouldNotSave() {
        Player player = new Player("",1L,5,5,5);

        assertThatThrownBy(()-> playerService.save(player)).isInstanceOf(CustomErrorException.class);
        assertThat(playerService.findall()).isEmpty();
    }
}
