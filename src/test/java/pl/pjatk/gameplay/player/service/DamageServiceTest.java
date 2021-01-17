package pl.pjatk.gameplay.player.service;

import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.DamageService;

import static org.assertj.core.api.Assertions.assertThat;


public class DamageServiceTest {
    private DamageService damageService = new DamageService();

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
    void shouldPoisonPlayer(){
        //get
        Player player = new Player("wa", (long) 1,100, 1,10);
        //when
        damageService.poison(player);
        //then
        assertThat(player.getHealth()).isGreaterThan(0);
        assertThat(player.getAttack()).isGreaterThan(0);
    }

    @Test
    void shouldDissarmPlayer(){
        //get
        Player player = new Player("wa", (long) 1,100, 1,10);
        //when
        damageService.dissarm(player);
        //then
        assertThat(player.getAttack()).isZero();
    }


}
