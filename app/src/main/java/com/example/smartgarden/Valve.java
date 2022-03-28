package com.example.smartgarden;

public class Valve {
    private String name;
    private String description;
    private int iconId;

    Valve(String name, String description, int iconId){
        this.name = name;
        this.description = description;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIconId() {
        return iconId;
    }
}
