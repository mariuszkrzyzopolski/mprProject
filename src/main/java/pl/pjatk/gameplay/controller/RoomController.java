package pl.pjatk.gameplay.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.Room;
import pl.pjatk.gameplay.service.RoomService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok(roomService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Room>> findById(@PathVariable long id) {
        Optional<Room> byId = roomService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId);
        } else {
            throw new NoSuchElementException();
        }
    }

    @PostMapping
    public ResponseEntity<Room> save(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.save(room));
    }
}
