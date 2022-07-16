package src;

public interface Magical {
    String MAGIC_MENU = "1. Magic Missile\n2. Fireball";
    int NUM_MAGIC_MENU_ITEMS = 2;

    /** to attack its opponent with a magic missile */
    String magicMissile(Entity e);


    /** to attack its opponent with a fireball */
    String fireball(Entity e);
}
