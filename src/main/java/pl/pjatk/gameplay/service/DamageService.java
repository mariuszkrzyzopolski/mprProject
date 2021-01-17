package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.Optional;

@Service
public class DamageService {

//    private PlayerRepository playerRepository;

//    public DamageService(PlayerRepository playerRepository) {
//        this.playerRepository = playerRepository;
//    }
//
//    public Optional<Player> attack (long id_obr, long id_atk){
//        playerRepository.findById(id_atk);
//        playerRepository.findById(id_obr);
//
//        if (playerRepository.findById(id_atk).isPresent() && playerRepository.findById(id_obr).isPresent()) {
//            playerRepository.findById(id_obr).get().setHealth(playerRepository.findById(id_obr).get().getHealth() - playerRepository.findById(id_atk).get().getAttack());
//        }
//        return playerRepository.findById(id_obr);
//    }
    public Player attack(Player attacker, Player defender) {
        defender.setHealth(
            defender.getHealth() - attacker.getAttack()
        );
        return defender;
    }

    public int buff(Player p){
        p.setAttack(p.getAttack() * 2);
        return p.getAttack();
    }
    public int debuff(Player p) {
        p.setAttack(p.getAttack() / 2);
        return p.getAttack();
    }
    public Player poison(Player p){
        if(p.getHealth() > 1){
            p.setHealth((p.getHealth() / 5) + 1);
            p.setAttack(p.getAttack() / 2);
        }
        return p;
    }
    public int dissarm(Player p){
        p.setAttack(0);
        return p.getAttack();
    }
}