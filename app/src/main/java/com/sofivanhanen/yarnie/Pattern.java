package com.sofivanhanen.yarnie;

/**
 * Created by sofvanh on 02/02/18.
 */

public class Pattern {

    private boolean free;
    private int id;
    private String name;
    private int yardage;
    private int yardage_max;

    public boolean getFree() { return free; }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setYardage(int yardage) { this.yardage = yardage; }
    public int getYardage() { return yardage; }

    public int getYardage_max() { return yardage_max; }

}
