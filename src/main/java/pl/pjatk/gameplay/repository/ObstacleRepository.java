package pl.pjatk.gameplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.model.Obstacle;

@Repository
public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
}
