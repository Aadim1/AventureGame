package src;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class EnemyGenerator{
    public HashMap<String, Integer> enemies;


    /**
     * Constructor for EnemyGenerator, it read through the file and creates a HashMap of Enemy and their basehp
     * @throws FileNotFoundException when the file ./Enemies.txt does not exist
     */
    public EnemyGenerator() throws FileNotFoundException {
        this.enemies = new HashMap<>();
        Scanner scan = new Scanner( new File("Group Project - 277/Enemies.txt"));
        while (scan.hasNext()){
            String line = scan.nextLine();
            String[] arr =line.split(",", 2);
            String enemyName = arr[0];
            int enemyHP = Integer.parseInt(arr[1]);
            this.enemies.put(enemyName, enemyHP);
        }
    }

    /**
     * generates a random enemy for the hero to fight
     * @param level the hero current level
     * @return the randomly generated enemy
     */
    public Enemy generateEnemy(int level){
        Object[] values = this.enemies.keySet().toArray();
        String randomEnemyGenerator = (String) values[new Random().nextInt(values.length)];
        int baseHp = this.enemies.get(randomEnemyGenerator) * level;
        int randomEnemy = (int) (Math.random() * ((3-1) + 2));
        if (randomEnemy == 1){
            return new Warrior(randomEnemyGenerator + " Warrior!", baseHp);
        }else if (randomEnemy == 3){
            return new Wizard(randomEnemyGenerator + " Wizard!", baseHp);
        }
        return new Ranger(randomEnemyGenerator + " Ranger!", baseHp);
    }

}
