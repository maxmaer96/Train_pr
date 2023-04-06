package com.example.mvp.models;

import java.util.ArrayList;

public class Cookie extends Food // булочка
{
    public Boolean withSugar;// с сахаром ли?
    public Boolean withPoppy;// или маком?
    public Boolean withSesame;// а может с кунжутом?

    public Cookie(int id, int kkal, String title, Boolean withSugar, Boolean withPoppy, Boolean withSesame) {
        super(id, kkal, title);
        this.withSugar = withSugar;
        this.withPoppy = withPoppy;
        this.withSesame = withSesame;
    }

    public Cookie(int kkal, String title, Boolean withSugar, Boolean withPoppy, Boolean withSesame) {
        super(kkal, title);
        this.withSugar = withSugar;
        this.withPoppy = withPoppy;
        this.withSesame = withSesame;
    }

    @Override
    public String getDescription() {
        ArrayList<String> items = new ArrayList<>();
        if (this.withSugar)
            items.add("с сахаром");
        if (this.withPoppy)
            items.add("с маком");
        if (this.withSesame)
            items.add("с кунжутом");

        return String.format("Булочка %s", String.join(", ", items));
    }

    @Override
    public void setDescription(String description) {
//        String[] descriptionArray = description.split(" ")[0].split(",");
        String[] descriptionArray = description.substring(description.indexOf(' ')).split(",");
        for (String descriptionItem : descriptionArray) {
            if (!this.withSugar) {
                this.withSugar = descriptionItem.trim().equals("с сахаром");
            }
            if (!this.withPoppy) {
                this.withPoppy = descriptionItem.trim().equals("с маком");
            }
            if (!this.withSesame) {
                this.withSesame = descriptionItem.trim().equals("с кунжутом");
            }
        }
    }
}