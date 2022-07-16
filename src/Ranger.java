package src;

public class Ranger extends Enemy implements  Archer{
    public Ranger(String n, int mHp){
        super(n, mHp);
    }

    /**
     * attacks the hero
     * @param h the instance of hero
     * @return the string representation of the attack
     */
    @Override
    public String attack(Hero h) {
        int rand = (int) (Math.random() * (2-1) + 1);
        if (rand == 1){
            return this.arrow(h);
        }
        return this.fireArrow(h);

    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String arrow(Entity e) {
        int damage = (int) (Math.random() * (4-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " hits  " + e.getName() + " with an arrow for " + damage + " damage.";
    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String fireArrow(Entity e) {
        int damage = (int) (Math.random() * (6-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " throws fire arrow at " + e.getName() + " for " + damage + " damage.";
    }
}
