package com.example.mvp.models;

public class Fruit extends Food {
    public Boolean isFresh;// свежий ли фрукт

    public Fruit(int id, int kkal, String title, Boolean isFresh) {
        super(id, kkal, title);
        this.isFresh = isFresh;
    }

    public Fruit(int kkal, String title, Boolean isFresh) {
        super(kkal, title);
        this.isFresh = isFresh;
    }

    @Override
    public String getDescription() {
        String isFreshString = this.isFresh ? "свежий" : "не свежий";
        return String.format("Фрукт %s", isFreshString);
    }

    @Override
    public void setDescription(String description) {
        String isFreshString = description.split(" ")[1];
        this.isFresh = isFreshString.equals("свежий");
    }
}