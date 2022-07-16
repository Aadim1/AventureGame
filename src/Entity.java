package src;

public abstract class Entity{
    private final String name;
    private int hp;
    private final int maxHp;

    /**
     * initializes the information of the Entity
     * @param n the name of the hero/enemy
     * @param mHp max hp for hero/enemy
     */
    public Entity(String n, int mHp){
        this.hp = mHp;
        this.maxHp = mHp;
        this.name = n;
    }

    /**
     * gets the name of the entity and
     * @return the name of the entity
     */
    public String getName(){
        return this.name;
    }

    /**
     * gets the HP of the entity and
     * @return the HP of the entity
     */
    public int getHp(){
        return hp;
    }

    /**
     * heals the entity to full
     */
    public void heal(){
        this.hp = maxHp;
    }

    /**
     * the entity takes d amount of damage
     */
    public void takeDamage(int d){
        if(this.hp > 0) {
            this.hp -= d;
        }
        if(this.hp <= 0){
            this.hp = 0;
        }
    }

    /**
     * gets the information of entity and
     * @return  the basic information of Entity
     */
    @Override
    public String toString(){
        return name + " " + hp + "/" + maxHp;
    }
}
