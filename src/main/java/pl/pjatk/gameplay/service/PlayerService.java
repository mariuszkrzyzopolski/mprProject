package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.CustomErrorException;
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
        if (player.getNickname().isEmpty() || player.getHealth()<=0 || player.getAttack()<0 || player.getMana()<0){
            throw new CustomErrorException("Bad Object property");
        }
        return playerRepository.save(player);
    }
}
