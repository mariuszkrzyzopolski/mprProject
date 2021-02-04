package pl.pjatk.gameplay.controller;


import org.hibernate.ObjectDeletedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Obstacle;
import pl.pjatk.gameplay.service.DamageService;
import pl.pjatk.gameplay.service.ObstacleService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/obstacle")
public class ObstacleController {
    private ObstacleService obstacleService;
    private DamageService damageService;

    public ObstacleController(ObstacleService obstacleService, DamageService damageService){
        this.obstacleService = obstacleService;
        this.damageService = damageService;
    }

    @GetMapping
    public ResponseEntity<List<Obstacle>> findAll() {
        return ResponseEntity.ok(obstacleService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Obstacle>> findById(@PathVariable long id) {
        Optional<Obstacle> byId = obstacleService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId);
        } else {
            throw new NoSuchElementException();
        }
    }

    @PostMapping
    public ResponseEntity<Obstacle> save(@RequestBody Obstacle obstacle) {
        return ResponseEntity.ok(obstacleService.save(obstacle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        obstacleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/damage/{damage}")
    public ResponseEntity<Obstacle> damage(@PathVariable long id, @PathVariable int damage){
        Optional<Obstacle> byId = obstacleService.findById(id);
        if (byId.isPresent()) {
            Obstacle obstacle = damageService.damageObstacle(byId.get(),damage);
            if (obstacle.getCondition()<=0){
                obstacleService.delete(id);
                throw new CustomErrorException("Obstacle destroyed!");
            } else {
                return ResponseEntity.ok(obstacleService.save(obstacle));
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
