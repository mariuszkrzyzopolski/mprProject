package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private DamageService damageService;

    public List<Player> findall(){
        return playerRepository.findAll();
    }

    public PlayerService(PlayerRepository playerRepository, DamageService damageService) {
        this.playerRepository = playerRepository;
        this.damageService = damageService;
    }

    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    public void delete(){
        playerRepository.deleteAll();
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

//    public Player attack(Long attackerId, Long defenderId) {
//        Player attacker = findById(attackerId).get();
//        Player defender = findById(defenderId).get();
//        defender = damageService.attack(attacker, defender);
//        playerRepository.save(defender);
//        return defender;
//    }
}
