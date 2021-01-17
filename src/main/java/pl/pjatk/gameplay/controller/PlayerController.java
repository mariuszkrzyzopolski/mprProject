package pl.pjatk.gameplay.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Optional<Player>> findById(@PathVariable long id) {
        Optional<Player> byId = playerService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Player> save(@RequestBody Player player) {
        return ResponseEntity.ok(playerService.save(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        playerService.delete(id);
        return ResponseEntity.ok().build();
    }

//    @PutMapping("/{id_obr}/{id_atk}")
//    public ResponseEntity<Optional<Player>> attack(@PathVariable long id_obr, @PathVariable long id_atk) {
//        return ResponseEntity.ok(damageService.attack(id_obr, id_atk));
//    }
}
