package src;

interface Fighter {
    static final String FIGHTER_MENU = "1. Sword\n2. Axe";
    static final int NUM_FIGHTER_MENU_ITEMS = 2;

    /** to attack its opponent with a sword */
    String sword(Entity e);
    /** to attack its opponent with a axe */
    String axe(Entity e);
}
