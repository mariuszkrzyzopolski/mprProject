package pl.pjatk.gameplay.controller;

import java.util.AbstractMap;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Item;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.ItemService;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private ItemService itemService;
    private PlayerService playerService;

    public ItemController(ItemService itemService,PlayerService playerService) {
        this.playerService = playerService;
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

    @PutMapping("/{id}/player/{playerId}")
    public ResponseEntity<Item> use(@PathVariable long id,@PathVariable long playerId) throws NoSuchMethodException {
        Optional<Item>itemFind = itemService.findById(id);
        Optional<Player>playerFind = playerService.findById(playerId);
        if(itemFind.isPresent() && playerFind.isPresent()){
            AbstractMap.SimpleEntry<Item, Player> pair = itemService.use(itemFind.get(),playerFind.get());
            itemService.save(pair.getKey());
            playerService.save(pair.getValue());
            if(itemFind.get().getUses()<=0) {
                itemService.delete(id);
                throw new CustomErrorException("You run out from uses of that item! Item disappear!");
            }else{
                return ResponseEntity.ok(itemService.save(itemFind.get()));
            }
        }else{
            throw new NoSuchElementException();
        }

    }
}
