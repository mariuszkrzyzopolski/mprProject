package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Obstacle;
import pl.pjatk.gameplay.model.Player;


@Service
public class DamageService {
    public Player damagePlayer(Player p, int damage) {
        p.setHealth(p.getHealth() - damage);
        return p;
    }

    public Obstacle damageObstacle(Obstacle o, int damage){
        o.setCondition(o.getCondition()-damage);
        return o;
    }

    public Player buff(Player p){
        p.setAttack(p.getAttack() * 2);
        return p;
    }
    public Player debuff(Player p) {
        p.setAttack(p.getAttack() / 2);
        return p;
    }
    public Player poison(Player p){
        if(p.getHealth() > 1){
            p.setHealth((p.getHealth() / 5) + 1);
            p.setAttack(p.getAttack() / 2);
        }
        return p;
    }
    public Player disarm(Player p){
        p.setAttack(0);
        return p;
    }
}