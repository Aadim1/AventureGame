package src;

public interface Archer {
    static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";
    static final int NUM_ARCHER_MENU_ITEMS = 2;

    /** to attack its opponent with a arrow */
    String arrow(Entity e);
    /** to attack its opponent with a fireArrow */
    String fireArrow(Entity e);
}
