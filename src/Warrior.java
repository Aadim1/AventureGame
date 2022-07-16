package src;

public class Warrior extends Enemy implements Fighter{
    public Warrior(String n, int mHp){
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
            return this.sword(h);
        }
        return this.axe(h);
    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String sword(Entity e) {
        int damage = (int) (Math.random() * (5-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " slashes " + e.getName() + " for " + damage + " damage.";
    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String axe(Entity e) {
        int damage = (int) (Math.random() * (3-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " axed " + e.getName() + " for " + damage + " damage.";
    }
}
