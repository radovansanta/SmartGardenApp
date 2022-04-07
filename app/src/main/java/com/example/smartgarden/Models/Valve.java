package com.example.smartgarden.Models;

public class Valve {
    private Integer id;
    private String name;
    private String description;
    private int icon;
    private boolean state;

    public Valve(Integer id,String name, String description, int icon, boolean state){
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIconId() {
        return icon;
    }

    public boolean getState() {return state;}

    public Valve getValve() {
        return new Valve(id, name, description,icon,state);
    }
}
