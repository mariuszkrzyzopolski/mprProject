package pl.pjatk.gameplay.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.Item;
import pl.pjatk.gameplay.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.ok(itemService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Item>> findById(@PathVariable long id) {
        Optional<Item> byId = itemService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId);
        } else {
            throw new NoSuchElementException();
        }
    }

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.save(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }
}
