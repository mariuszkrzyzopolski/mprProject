package pl.pjatk.gameplay.player.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Obstacle;
import pl.pjatk.gameplay.repository.ObstacleRepository;
import pl.pjatk.gameplay.service.ObstacleService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObstacleServiceTest {
    @Mock
    private ObstacleRepository obstacleRepository;

    @InjectMocks
    private ObstacleService obstacleService;

    @Test
    void findAll(){
        when(obstacleRepository.findAll()).thenReturn(List.of(new Obstacle()));

        List<Obstacle> all = obstacleService.findall();

        assertThat(all).isNotEmpty();
    }

    @Test
    void findById() {
        when(obstacleRepository.findById(1L)).thenReturn(Optional.of(new Obstacle()));

        Optional<Obstacle> obstacle = obstacleService.findById(1L);

        assertThat(obstacle).isNotEmpty();
    }

    @Test
    void save() {
        Obstacle Obstacle = new Obstacle(1L,"testowa",5,5);
        when(obstacleRepository.save(Obstacle)).thenReturn(Obstacle);

        Obstacle savedObstacle = obstacleService.save(Obstacle);

        assertThat(savedObstacle).isEqualTo(Obstacle);
    }

    @Test
    void shouldNotSave() {
        Obstacle obstacle = new Obstacle(1L,"",5,5);

        assertThatThrownBy(()-> obstacleService.save(obstacle)).isInstanceOf(CustomErrorException.class);
    }
}
