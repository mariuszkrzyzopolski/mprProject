package pl.pjatk.gameplay.player.service;

import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.model.Obstacle;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.DamageService;

import static org.assertj.core.api.Assertions.assertThat;


public class DamageServiceTest {
    private DamageService damageService = new DamageService();

    @Test
    void shouldDamagePlayer(){
        //get
        Player player = new Player("wa", (long) 1,100, 100,10);
        //when
        damageService.damagePlayer(player,10);
        //then
        assertThat(player.getHealth()).isEqualTo(90);
    }

    @Test
    void shouldDamageObstacle(){
        //get
        Obstacle obstacle = new Obstacle(1L,"testowa",10,50);
        //when
        damageService.damageObstacle(obstacle,10);
        //then
        assertThat(obstacle.getCondition()).isEqualTo(40);
    }

    @Test
    void shouldBuffPlayer(){
        //get
        Player player = new Player("wa", (long) 1,100, 100,10);
        //when
        damageService.buff(player);
        //then
        assertThat(player.getAttack()).isEqualTo(200);
    }

    @Test
    void shouldDebuffPlayer(){
        //get
        Player player = new Player("wa", (long) 1,100, 100,10);
        //when
        damageService.debuff(player);
        //then
        assertThat(player.getAttack()).isNotEqualTo(200);
        assertThat(player.getAttack()).isEqualTo(50);
    }

    @Test
    void shouldPoison(){
        Player player = new Player("wa", (long) 1,100, 100,10);
        damageService.poison(player);

        assertThat(player.getHealth()).isEqualTo(21);
        assertThat(player.getAttack()).isEqualTo(50);
    }

    @Test
    void shouldPoisonPlayerButNotKill(){
        //get
        Player player = new Player("wa", (long) 1,100, 1,10);
        //when
        damageService.poison(player);
        //then
        assertThat(player.getHealth()).isGreaterThan(0);
        assertThat(player.getAttack()).isGreaterThan(0);
    }

    @Test
    void shouldDisarmPlayer(){
        //get
        Player player = new Player("wa", (long) 1,100, 1,10);
        //when
        damageService.disarm(player);
        //then
        assertThat(player.getAttack()).isZero();
    }


}
