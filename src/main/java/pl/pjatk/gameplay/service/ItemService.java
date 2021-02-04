package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Item;
import pl.pjatk.gameplay.repository.ItemRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ItemService {
    private ItemRepository itemRepository;

    public List<Item> findall(){
        return itemRepository.findAll();
    }

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Item save(Item item) {
        if(item.getUses()<=0 || item.getName().isEmpty() || item.getStatname().isEmpty()){
            throw new CustomErrorException("Bad Object property");
        }
        return itemRepository.save(item);
    }

    public Item use(Item item){
        item.setUses(item.getUses()-1);
        return item;
    }
}