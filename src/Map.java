package src;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Map {
    private char[][] map;
    private boolean[][] revealed;
    private static Map instance = null;

    /**
     * Constructor for map initializes this.map and this.revealed
     */
    public Map(){
        this.map = new char[5][5];
        this.revealed = new boolean[5][5];
    }


    /**
     * gets the instance of the map class
     * @return a new instance if the instance already doesn't exist
     */
    public static Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }

    /**
     *  this method loads the appropriate map to teh char[][] map variable
     * @param mapNum what map to load from (1,2,3)
     * @throws FileNotFoundException
     */
    public void loadMap(int mapNum) throws FileNotFoundException {
        String pathName = "Group Project - 277/Map1.txt" + mapNum + ".txt";
        this.map = new char[5][5];
        this.revealed = new boolean[5][5];

        Scanner read = new Scanner( new File (pathName));
        int count = 0;
        while(read.hasNext()) {
            String line = read.nextLine();
            char[] k = line.toCharArray();
            int c = 0;
            for (char i : k) {
                if (i == ' ') {
                    continue;
                } else {
                    this.map[count][c] = i;
                }
                c++;
            }
            count++;
        }
    }



    /**
     * returns a point of the list return into parameter
     * @param p a point of char to return
     * @return a point at the list
     */
    public char getCharAtLoc(Point p){
        return this.map[(int)p.getX()][(int)p.getY()];
    }

    /**
     * iterates over the map and figures out the point where the starting point of maze is located
     * @return a point where the maze starts
     */
    public Point findStart(){
        int row = 0;
        Point point = new Point();

        for (char[] i : this.map)
        {
            int column = 0;
            for (char k : i)
            {
                if(k == 's'){
                    point = new Point(row, column);
                    break;
                }
                column++;
            }
            row++;
        }
        return point;
    }

    /**
     * determines if the door has been visited yet or not
     * @param p a point in char map
     */
    public void reveal(Point p){
        int x = (int) p.getX();
        int y = (int) p.getY();
        this.revealed[x][y] = true;
    }

    /**
     * removes a char at location point
     * @param p a point in char map
     */
    public void removeCharAtLoc(Point p){
        int x = (int) p.getX();
        int y = (int) p.getY();
        this.map[x][y] = 'n';
    }

    /**
     * Changes the char[][] map to string and returns it
     * @param p the current location of the hero
     * @return the string representaion of the char[][] map
     */
    public String mapToString(Point p){
        int row = 0;
        char[][] checker = new char[5][5];
        for (char[] i : this.map)
        {
            int column = 0;
            for (char ignored : i)
            {
                Point point = new Point(row, column);
                if(point.equals(p)){
                    checker[row][column] = '*';
                }else if(this.revealed[row][column]){
                    checker[row][column] = this.map[row][column];
                }else{
                    checker[row][column] = 'x';
                }
                column++;
            }
            row++;
        }

        StringBuilder r = new StringBuilder();
        for (char[] i : checker)
        {
            for (char k : i)
            {
                r.append(k).append(" ");
            }
            r.append(" \n");
        }
        return r.toString();
    }

}

