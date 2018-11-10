package com.example.basicAuth.model;

public class Player {
    private String name;
    private Integer alter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlter() {
        return alter;
    }

    public void setAlter(Integer alter) {
        this.alter = alter;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", alter=" + alter +
                '}';
    }
}
