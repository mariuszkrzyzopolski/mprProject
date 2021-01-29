package pl.pjatk.gameplay.model;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "statname")
    private String statname;
    @Column(name = "statvalue")
    private int statvalue;
    @Column(name = "uses")
    private int uses;

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

    public String getStatname() {
        return statname;
    }

    public void setStatname(String statname) {
        this.statname = statname;
    }

    public int getStatvalue() {
        return statvalue;
    }

    public void setStatvalue(int statvalue) {
        this.statvalue = statvalue;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public Item(Long id, String name, String statname, int statvalue, int uses) {
        this.id = id;
        this.name = name;
        this.statname = statname;
        this.statvalue = statvalue;
        this.uses = uses;
    }

    public Item(){
    }
}
