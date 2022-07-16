package src;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("What is your name, traveler? ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        Hero hero = new Hero(name);
        EnemyGenerator enemyGenerator = new EnemyGenerator();
        Map map = Map.getInstance();
        // fight(hero, enemy);
        while (true) {
            int choice = mainMenu(hero);
            char x = '\0';
            if (choice == 1) {
                x = hero.goNorth();
            } else if (choice == 2) {
                x = hero.goSouth();
            } else if (choice == 3) {
                x = hero.goEast();
            } else if (choice == 4) {
                x = hero.goWest();
            } else {
                System.out.println("Game Over.");
                break;
            }

            if (x == 'x'){
                System.out.println("Can not go in there.");
            }
            if(x == 'n'){
                System.out.println("There was nothing here.");
            }else if (x == 's'){
                store(hero);
            }else if(x == 'f'){
                if(hero.hasKey()){
                    hero.useKey();
                    hero.levelUp();
                    System.out.println("You find a locked gate. Luckily you have a key! You proceed to the next area.");
                }else{
                    System.out.println("You find a locked gate. Unfortunately you do not have a key! Try finding the key and come back again.");
                }
            }else if(x == 'i'){
                int rand = (int) (Math.random() * (2-1) + 1);
                if (rand == 1){
                    hero.pickUpKey();
                    System.out.println("You found a key here.");
                }else{
                    System.out.println("You found a potion here.");
                    hero.pickUpPotion();
                }
                Map.getInstance().removeCharAtLoc(hero.getLocation());
            }else if(x == 'm'){
                Enemy enemy = enemyGenerator.generateEnemy(hero.getLevel());
                boolean alive = monsterRoom(hero, enemy);

                if (!alive){
                    if(hero.getHp() <= 0){
                        System.out.println("Game Over.");
                        break;
                    }
                }else{
                    int rand = (int) (Math.random() * (5-1) + 1);
                    int gold = rand + enemy.getHp();
                    System.out.println("You defeated the " + enemy.getName());
                    hero.collectGold(gold);
                    System.out.println("You find " + gold + " gold in the corpse.");
                }
            }
        }
    }

    /**
     * gets the main menu for user to choose, shows the hero details, map and
     * gives the user a choice to move one direction.
     * @param h a instance of the hero
     * @return the choice the user made
     */
    public static int mainMenu(Hero h){
        System.out.println(h + "\n" + Map.getInstance().mapToString(h.getLocation()));
        System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
        return CheckInput.getIntRange(1, 5);
    }

    /**
     * gives the hero the chance to fight the monster, run away or drink the potion then fight the monster
     * @param h an instance of the hero
     * @param e an instance of enemy
     * @return true if the hero is alive. False if the hero decided to run away, or the hero died.
     */
    public static boolean monsterRoom(Hero h, Enemy e) throws FileNotFoundException {
        int choice;
        System.out.println("You have encountered a " + e.toString() + "\n");
        while (true) {
            System.out.println("1. Fight\n2. Run Away");
            if(h.hasPotion()){
                System.out.println("3. Drink Potion");
                choice = CheckInput.getIntRange(1, 3);
            }else{
                choice = CheckInput.getIntRange(1, 2);
            }
            if (choice == 1) {
                return fight(h, e);
            } else if (choice == 2) {

                char x = '\0';
                do {
                    int rand = (int) (Math.random() * (3 - 1) + 1);
                    if (rand == 1) {
                        x = h.goEast();
                    } else if (rand == 2) {
                        x = h.goSouth();
                    } else if (rand == 3) {
                        x = h.goWest();
                    } else {
                        x = h.goNorth();
                    }
                } while (x == 'x');
                if (x == 'f') {
                    if (h.hasKey()) {
                        h.useKey();
                        h.levelUp();
                        System.out.println("You find a locked gate. Luckily you have a key! You proceed to the next area.");
                    } else {
                        System.out.println("You find a locked gate. Unfortunately you do not have a key! Try finding the key and come back again.");
                    }
                }
                return false;
            } else {
                h.usePotion();
            }
        }
    }

    /**
     * The hero fights with the monster.
     * @param h an instance of hero class
     * @param e an instance of enemy
     * @return true if the heros alive, false otherwise
     */
    public static boolean fight(Hero h, Enemy e){
        Map.getInstance().removeCharAtLoc(h.getLocation());
        while(h.getHp() > 0 && e.getHp() > 0) {
            System.out.println(h.getAttackMenu());
            int choice = CheckInput.getIntRange(1, h.getNumAttackmenuitems());
            System.out.println(h.getSubAttackMenu(choice));
            int subChoice = CheckInput.getIntRange(1, h.getNumSubAttackMenuitems(choice));
            System.out.println(h.attack(e, choice, subChoice));
            System.out.println(e.attack(h));
        }
        return h.getHp() > 0;
    }

    /**
     * The store menu where hero can valuable items in exchange for gold
     * @param h instance of a hero class
     */
    public static void store(Hero h){
        while (true) {
            System.out.println("Welcome to the store. What would you like to buy?\n1. Health Potion - 25g\n2. Key - 50g\n3. Nothing, just browsing...");
            int choice = CheckInput.getIntRange(1, 3);
            if (choice == 1) {
                if (h.getGold() > 25) {
                    h.spendGold(25);
                    h.pickUpPotion();
                    break;
                } else {
                    System.out.println("You do not have enough gold to buy this item");
                }
            } else if (choice == 2) {
                if (h.getGold() >= 50) {
                    h.spendGold(50);
                    h.pickUpKey();
                    break;
                } else {
                    System.out.println("You do not have enough gold to buy this item");
                }
            }else{
                break;
            }
        }
    }


}
