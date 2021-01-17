package pl.pjatk.gameplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
