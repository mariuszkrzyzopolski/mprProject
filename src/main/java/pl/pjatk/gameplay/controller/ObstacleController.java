package pl.pjatk.gameplay.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.Obstacle;
import pl.pjatk.gameplay.service.ObstacleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/obstacle")
public class ObstacleController {
    private ObstacleService obstacleService;

    public ObstacleController(ObstacleService obstacleService){
        this.obstacleService = obstacleService;
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
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Obstacle> save(@RequestBody Obstacle obstacle) {
        return ResponseEntity.ok(obstacleService.save(obstacle));
    }
}
