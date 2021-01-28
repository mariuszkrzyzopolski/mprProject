package pl.pjatk.gameplay.model;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "attack")
    private int attack;
    @Column(name = "health")
    private int health;
    @Column(name = "mana")
    private int mana;


    public Player(String nickname, Long id, int attack, int health, int mana) {
        this.nickname = nickname;
        this.id = id;
        this.attack = attack;
        this.health = health;
        this.mana = mana;
    }

    public Player() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }


}
