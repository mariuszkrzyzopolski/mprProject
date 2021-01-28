package pl.pjatk.gameplay.model;


import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "title")
    private String Title;

    @Column(name = "description")
    private String Description;

    @Column(name = "corridors")
    private String ListOfRooms;

    public Room(String title, String description, String listOfRooms) {
        Title = title;
        Description = description;
        ListOfRooms = listOfRooms;
    }

    public Room(){

    }

    public List<String> getListOfRooms() {
        return Arrays.asList(ListOfRooms.split("-"));
    }

    public void setListOfRooms(String listOfRooms) {
        ListOfRooms = listOfRooms;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
