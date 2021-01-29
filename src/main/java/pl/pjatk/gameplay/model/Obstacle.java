package pl.pjatk.gameplay.model;

import javax.persistence.*;

@Entity
@Table(name = "obstacle")
public class Obstacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "damage")
    private int damage;
    @Column(name = "condition")
    private int condition;

    public Obstacle(Long id, String name, int damage, int condition) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.condition = condition;
    }

    public Obstacle() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }
}
