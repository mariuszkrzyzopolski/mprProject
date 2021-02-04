package pl.pjatk.gameplay.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;
import pl.pjatk.gameplay.service.DamageService;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private DamageService damageService;
    private PlayerService playerService;

    public PlayerController(PlayerService playerService, DamageService damageService) {
        this.playerService = playerService;
        this.damageService = damageService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        return ResponseEntity.ok(playerService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable long id) {
        return ResponseEntity.ok(findPlayer(id));
    }

    @PostMapping
    public ResponseEntity<Player> save(@RequestBody Player player) {
        return ResponseEntity.ok(playerService.save(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        playerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/damage/{damage}")
    public ResponseEntity<Player> damage(@PathVariable long id, @PathVariable int damage){
        return ResponseEntity.ok(playerService.save(damageService.damagePlayer(findPlayer(id),damage)));
    }

    @PutMapping("/{id}/poison")
    public ResponseEntity<Player> poison(@PathVariable long id){
        return ResponseEntity.ok(playerService.save(damageService.poison(findPlayer(id))));
    }

    @PutMapping("/{id}/debuff")
    public ResponseEntity<Player> debuff(@PathVariable long id){
        return ResponseEntity.ok(playerService.save(damageService.debuff(findPlayer(id))));
    }

    @PutMapping("/{id}/buff")
    public ResponseEntity<Player> buff(@PathVariable long id){
        return ResponseEntity.ok(playerService.save(damageService.buff(findPlayer(id))));
    }

    @PutMapping("/{id}/disarm")
    public ResponseEntity<Player> disarm(@PathVariable long id){
        return ResponseEntity.ok(playerService.save(damageService.disarm(findPlayer(id))));
    }

    private Player findPlayer(long id){
        Optional<Player> byId = playerService.findById(id);
        if (byId.isPresent()) {
            if(byId.get().getHealth()<=0){
                playerService.delete(id);
                throw new CustomErrorException("You are dead! Game Over!");
            }
            else {
                return byId.get();
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
