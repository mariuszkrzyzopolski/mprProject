package pl.pjatk.gameplay.player.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Item;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.ItemRepository;
import pl.pjatk.gameplay.service.ItemService;

import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void findAll(){
        when(itemRepository.findAll()).thenReturn(List.of(new Item()));

        List<Item> all = itemService.findall();

        assertThat(all).isNotEmpty();
    }

    @Test
    void findById() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(new Item()));

        Optional<Item> item = itemService.findById(1L);

        assertThat(item).isNotEmpty();
    }

    @Test
    void save() {
        Item item = new Item(1L,"testowy","health",5,3,"");
        when(itemRepository.save(item)).thenReturn(item);

        Item savedItem = itemService.save(item);

        assertThat(savedItem).isEqualTo(item);
    }

    @Test
    void shouldNotSave() {
        Item item = new Item(1L,"","health",5,3,"");

        assertThatThrownBy(()-> itemService.save(item)).isInstanceOf(CustomErrorException.class);
    }

    @Test
    void shouldUseAdd() throws NoSuchMethodException {
        Item item = new Item(2L,"testowy2","health",5,3,"");
        Player player = new Player("test",1L,50,50,50);

        AbstractMap.SimpleEntry<Item, Player> result = itemService.use(item,player);

        assertThat(result.getValue().getHealth()).isEqualTo(55);
        assertThat(result.getKey().getUses()).isEqualTo(2);
    }

    @Test
    void shouldUseSub() throws NoSuchMethodException {
        Item item = new Item(1L,"testowy","mana",-5,3,"");
        Player player = new Player("test",1L,50,50,50);

        AbstractMap.SimpleEntry<Item, Player> result = itemService.use(item,player);

        assertThat(result.getValue().getMana()).isEqualTo(45);
        assertThat(result.getKey().getUses()).isEqualTo(2);
    }

    @Test
    void shouldNotUse() {
        Item item = new Item(1L,"testowy","false",-5,3,"");
        Player player = new Player("test",1L,50,50,50);

        assertThatThrownBy(()-> itemService.use(item,player)).isInstanceOf(NoSuchMethodException.class);
    }
}
