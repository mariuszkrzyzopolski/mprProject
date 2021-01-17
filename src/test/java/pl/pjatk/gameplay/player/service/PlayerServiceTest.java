package pl.pjatk.gameplay.player.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void findAll(){
        when(playerRepository.findAll()).thenReturn(List.of(new Player()));

        List<Player> all = playerService.findall();

        assertThat(all).isNotEmpty();
    }

    @Test
    void findById() {
        when(playerRepository.findById(1L)).thenReturn(Optional.of(new Player()));

        Optional<Player> player = playerService.findById(1L);

        assertThat(player).isNotEmpty();
    }

//    @Test
//    void save() {
//        Player player = new Player();
//        when(playerRepository.save(player));
//    }
}
