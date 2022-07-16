package src;

public class Wizard extends Enemy implements Magical {

    public Wizard(String n, int mHp){
        super(n, mHp);
    }

    /**
     * attacks the hero
     * @param h the instance of hero
     * @return the string representation of the attack
     */
    @Override
    String attack(Hero h) {
        int rand = (int) (Math.random() * (2-1) + 1);
        if (rand == 1){
            return this.magicMissile(h);
        }
        return this.fireball(h);
    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String magicMissile(Entity e) {
        int damage = (int) (Math.random() * (8-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " strikes " + e.getName() + " for " + damage + " damage.";
    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String fireball(Entity e) {
        int damage = (int) (Math.random() * (7-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " fireballed " + e.getName() + " for " + damage + " damage.";
    }
}
