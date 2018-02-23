package com.sofivanhanen.yarnie.Data;

/**
 * Created by sofvanh on 02/02/18.
 */

public class Pattern {

    private boolean free;
    private int id;
    private String name;
    private int yardage;

    // Setters for testing purposes
    public void setFree(boolean free) { this.free = free; }
    public boolean getFree() { return free; }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setName(String name) { this.name = name; };
    public String getName() { return name; }

    public void setYardage(int yardage) { this.yardage = yardage; }
    public int getYardage() { return yardage; }

}
