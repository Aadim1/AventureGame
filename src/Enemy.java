package src;

public abstract class Enemy extends Entity{
    /**
     * calls the super class
     * @param n the name of the enemy
     * @param mHp maximum HP of the enemy
     */
    public Enemy(String n, int mHp) {
        super(n, mHp);
    }

    /**
     * attack its opponent
     * @param h the instance of hero class
     */
    abstract String attack(Hero h);
}
