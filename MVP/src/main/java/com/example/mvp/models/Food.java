package com.example.mvp.models;

public class Food {
    public int kkal; // количество калорий
    public String title; // название
    public String description;
    public Integer id = null; // добавили идентификатор, по умолчанию равен null

    public Food(int id, int kkal, String title) {
        this.id=id;
        this.setKkal(kkal);
        this.setTitle(title);
    }

    public Food(int kkal, String title) {
        this.setKkal(kkal);
        this.setTitle(title);
    }

    @Override
    public String toString() {
        return String.format("%s: %s ккал", this.getTitle(), this.getKkal());
    }

    public int getKkal() {
        return kkal;
    }

    public void setKkal(int kkal) {
        this.kkal = kkal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {this.description =description;}
}
