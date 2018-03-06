package com.sofivanhanen.yarnie.Data;

import java.util.Date;

/**
 * Created by sofvanh on 02/02/18.
 */

public class Pattern {

    private boolean free;
    private int id;
    private String name;
    private int projects_count;
    private Date published;
    private int yardage;

    private int value;

    // Setters for testing purposes
    public void setFree(boolean free) { this.free = free; }
    public boolean getFree() { return free; }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setProjects_count(int projects) { this.projects_count = projects; }
    public int getProjects_count() { return projects_count; }

    public void setPublished(Date date) { this.published = date; }
    public Date getPublished() { return published; };

    public void setYardage(int yardage) { this.yardage = yardage; }
    public int getYardage() { return yardage; }

    public int getValue() {
        return 1;
        // TODO: If value not calculated, calculate it here, save, and return
    }

}
