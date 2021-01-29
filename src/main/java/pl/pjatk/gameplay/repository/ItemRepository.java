package pl.pjatk.gameplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
