package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.CustomErrorException;
import pl.pjatk.gameplay.model.Room;
import pl.pjatk.gameplay.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public List<Room> findall(){
        return roomRepository.findAll();
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public Room save(Room room) {
        if(room.getDescription().isEmpty() || room.getListOfRooms().isEmpty() || room.getTitle().isEmpty()){
            throw new CustomErrorException("Bad Object property");
        }
        return roomRepository.save(room);
    }
}
