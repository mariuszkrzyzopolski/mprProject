package pl.pjatk.gameplay.player.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
    void shouldGetError() {
        Player player = new Player("ss",10L,100,10,10);
        playerService.save(player);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> playerService.findById(10L));
    }

    @Test
    void shouldAttack(){
        Player def = new Player("ss",1L,10,100,10);
        Player att = new Player("ss",2L,20,100,10);
        playerService.save(def);
        playerService.save(att);

        playerService.attack(att.getId(),def.getId());

        Optional<Player> player = playerService.findById(2L);
        assertThat(player).isNotEmpty();
//        assertThat(player.getHealth()).isEqualTo(70);

    }


}
