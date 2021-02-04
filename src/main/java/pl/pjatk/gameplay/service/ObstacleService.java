package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Obstacle;
import pl.pjatk.gameplay.repository.ObstacleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ObstacleService {
    private ObstacleRepository obstacleRepository;

    public ObstacleService(ObstacleRepository obstacleRepository){
        this.obstacleRepository = obstacleRepository;
    }

    public List<Obstacle> findall(){
        return obstacleRepository.findAll();
    }

    public Optional<Obstacle> findById(Long id) {
        return obstacleRepository.findById(id);
    }

    public void delete(Long id) {
        obstacleRepository.deleteById(id);
    }

    public Obstacle save(Obstacle obstacle) {
        if(obstacle.getCondition()<=0 || obstacle.getDamage()<0 || obstacle.getName().isEmpty()){
            throw new CustomErrorException("Bad Object property");
        }
        return obstacleRepository.save(obstacle);
    }
}