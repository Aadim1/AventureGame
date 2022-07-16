package src;

import java.awt.*;
import java.io.FileNotFoundException;

public class Hero extends Entity implements Fighter, Magical, Archer{

    private Point loc;
    private int level;
    private int gold;
    private int keys;
    private int potions;

    /**
     * Initializes the hero class
     * @param n the name of the hero
     * @throws FileNotFoundException when the file map doesn't exist
     */
    public Hero(String n) throws FileNotFoundException {
        super(n, 25);
        this.gold = 10;
        this.potions = 1;
        this.keys = 0;
        this.level = 0;
        this.levelUp();
        this.loc = Map.getInstance().findStart();
        Map.getInstance().reveal(this.loc);
    }

    /**
     * gets the description of hero(name,hp,level,gold) in string
     * @return description of hero(name,hp,level,gold) in string
     */
    @Override
    public String toString(){
        return super.getName() + "\nHP: " + super.getHp() + "/25" + "\nLevel: " + this.level + "\nGold: " + this.gold +
                "\nP: " + this.potions + " K: " + this.keys;
    }

    /**
     * gets the current location of the hero
     * @return the current location of the hero
     */
    public Point getLocation(){
        return this.loc;
    }

    /**
     * hero's level increases as map changes 
     * @throws FileNotFoundException when the file map doesn't exist
     */
    public void levelUp() throws FileNotFoundException {
        this.level++;
        if (this.level <= 3){
            Map.getInstance().loadMap(this.level);
        }else{
            int britisha = this.level;
            while (true){
                britisha -= 3;
                if (britisha <= 3){
                    Map.getInstance().loadMap(britisha);
                    break;
                }
            }
        }
        this.loc = Map.getInstance().findStart();
        Map.getInstance().reveal(this.loc);
    }

    /**
     * gets the current level of the hero
     * @return the current level of the hero
     */
    public int getLevel(){
        return this.level;
    }

    /**
     * takes the hero to one location to east if its in bound
     * @return x if east is out of bound or the value in char
     */
    public char goEast(){try{
            Map map = Map.getInstance();
            int x = (int) this.loc.getX();
            int y = (int) this.loc.getY() + 1;
            this.loc = new Point(x, y);
            char z = map.getCharAtLoc(this.loc);
            map.reveal(this.loc);
            return z;
        }catch (IndexOutOfBoundsException e){
            int x = (int) this.loc.getX();
            int y = (int) this.loc.getY() - 1;
            this.loc = new Point(x, y);
            return 'x';
        }
    }

    /**
     * takes the hero to one location to west if its in bound
     * @return x if west is out of bound or the value in char
     */
    public char goWest(){
        try{
            Map map = Map.getInstance();
            int x = (int) this.loc.getX();
            int y = (int) this.loc.getY() - 1;
            this.loc = new Point(x, y);
            char z = map.getCharAtLoc(this.loc);
            map.reveal(this.loc);
            return z;
        }catch (IndexOutOfBoundsException e){
            int x = (int) this.loc.getX();
            int y = (int) this.loc.getY() + 1;
            this.loc = new Point(x, y);
            return 'x';
        }
    }

    /**
     * takes the hero to one location to south if its in bound
     * @return x if south is out of bound or the value in char
     */
    public char goSouth(){
        try{
            Map map = Map.getInstance();
            int x = (int) this.loc.getX() + 1;
            int y = (int) this.loc.getY();
            this.loc = new Point(x, y);
            char z = map.getCharAtLoc(this.loc);
            map.reveal(this.loc);
            return z;
        }catch (IndexOutOfBoundsException e){
            int x = (int) this.loc.getX() - 1;
            int y = (int) this.loc.getY();
            this.loc = new Point(x, y);
            return 'x';
        }
    }

    /**
     * takes the hero to one location to north if its in bound
     * @return x if north is out of bound or the value in char
     */
    public char goNorth(){try{
            Map map = Map.getInstance();
            int x = (int) this.loc.getX() - 1;
            int y = (int) this.loc.getY();
            this.loc = new Point(x, y);
            char z = map.getCharAtLoc(this.loc);
            map.reveal(this.loc);
            return z;
        }catch (IndexOutOfBoundsException e){
            int x = (int) this.loc.getX() + 1;
            int y = (int) this.loc.getY();
            this.loc = new Point(x, y);
            return 'x';
        }
    }

    /**
     * gets the attack menu that the hero can choose from
     * @return the attack menu that the hero can choose from
     */
    public String getAttackMenu(){
        return "1. Physical\n2. Magical\n3. Ranged";
    }

    /**
     * number of choices that the hero can make
     * @return the number of choices that the hero can make
     */
    public int getNumAttackmenuitems(){
        return 3;
    }

    /**
     * gets the appropriate menu from the user choice
     * @param choice the choice that the user made between, Warrior, Fighter, Magic
     * @return the appropriate menu from the user choice
     */
    public String getSubAttackMenu(int choice){
        if (choice == 1){
            return FIGHTER_MENU;
        }else if (choice == 2){
            return MAGIC_MENU;
        }
        return ARCHER_MENU;
    }

    /**
     * number of choices that the hero can make from the appropriate menu from the user choice
     * @param choice the choice that the user made between, Warrior, Fighter, Magic
     * @return number of choices that the hero can make from the appropriate menu from the user choice
     */
    public int getNumSubAttackMenuitems(int choice){
        if (choice == 1){
            return NUM_FIGHTER_MENU_ITEMS;
        }else if (choice == 2){
            return NUM_MAGIC_MENU_ITEMS;
        }
        return NUM_ARCHER_MENU_ITEMS;
    }

    /**
     * the hero attacks the enemy using the choice and subchoice that the user specified
     * @param e the instance of the Enemy
     * @param choice the choice that the use chose between Physical, Magical, and Ranged
     * @param subChoice the choice that the user made between the types of attack
     * @return the string representation of the attack that the hero just did to the Enemy
     */
    public String attack(Enemy e, int choice, int subChoice){
        if (choice == 1){
            if (subChoice == 1){
                return this.sword(e);
            }
            return this.axe(e);
        }else if (choice == 2){
            if (subChoice == 1){
                return this.magicMissile(e);
            }
            return this.fireball(e);
        }else {
            if (subChoice == 1) {
                return this.arrow(e);
            }
            return this.fireArrow(e);
        }
    }

    /**
     * gets the amount of gold the hero have and
     * @return the amount of gold the hero have
     */
    public int getGold(){
        return this.gold;
    }

    /**
     * collects the gold into hero
     * @param g the amount of gold to be added to this.gold
     */
    public void collectGold(int g){
        this.gold += g;
    }

    /**
     * spends a particular amount of gold
     * @param g he amounts of gold to be subtracted to this.gold
     * @return true if there is enough gold to spend, false otherwise
     */
    public boolean spendGold(int g){
        if(this.gold > 0){
            this.gold -=g;
            return true;
        }
        return false;
    }

    /**
     * See if the user has a key
     * @return true if the user have a key false otherwise
     */
    public boolean hasKey(){
        return this.keys > 0;
    }

    /**
     * Adds a key to the hero
     */
    public void pickUpKey(){

        this.keys++;
    }

    /**
     * uses a key
     * @return true if the hero has key and can use it false otherwise
     */
    public boolean useKey(){
        if (this.hasKey()){
            this.keys--;
            return true;
        }
        return false;
    }

    /**
     * see if the hero has potion
     * @return true if the hero has potion false otherwise
     */
    public boolean hasPotion(){
        return this.potions > 0;
    }

    /**
     * Picks up one potion and adds it to this.potions
     */
    public void pickUpPotion(){
        // Hero picks up the potion
        this.potions++;
    }

    /**
     * Hero uses a potion to heal itself
     * @return true if the hero can use potion, false otherwise
     */
    public boolean usePotion(){
        if(hasPotion()){
            this.potions--;
            this.heal();
            return true;
        }
        return false;
    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String sword(Entity e) {
        int damage = (int) (Math.random() * (3-1) + 1);
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
        int damage = (int) (Math.random() * (2-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " axed " + e.getName() + " for " + damage + " damage.";
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
        int damage = (int) (Math.random() * (5-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " throws fire arrow at " + e.getName() + " for " + damage + " damage.";
    }

    /**
     * attack its opponent
     * @param e the instance of its opponent
     * @return the string representation of the attack
     */
    @Override
    public String magicMissile(Entity e) {
        int damage = (int) (Math.random() * (7-1) + 1);
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
        int damage = (int) (Math.random() * (4-1) + 1);
        if (this.getHp() > 20){
            damage += 3;
        }else if (this.getHp() > 10){
            damage += 2;
        }
        e.takeDamage(damage);
        return this.getName() + " fireballed " + e.getName() + " for " + damage + " damage.";
    }
}
