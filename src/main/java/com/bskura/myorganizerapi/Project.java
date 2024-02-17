package com.bskura.myorganizerapi;

public class Project {
    private String name;
    private String description;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString(){
        return "Name: " + name + ", Description: " + description;
    }
}
