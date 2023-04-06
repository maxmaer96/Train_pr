package com.example.mvp.models;

public class Chocolate extends Food {
    public enum Type {white, black, milk;} // какие типы шоколада бывают
    public Type type;// а это собственно тип шоколада

    public Chocolate(int id, int kkal, String title, Type type) {
        super(id, kkal, title);
        this.type = type;
    }

    public Chocolate(int kkal, String title, Type type) {
        super(kkal, title);
        this.type = type;
    }

    @Override
    public String getDescription() {
        String typeString = "";
        switch (this.type)
        {
            case white:
                typeString = "белый";
                break;
            case black:
                typeString = "черный";
                break;
            case milk:
                typeString = "молочный";
                break;
        }
        return String.format("Шоколад %s", typeString);
    }

    @Override
    public void setDescription(String description) {
        String chocolateType = description.split(" ")[1];
        switch (chocolateType)
        {
            case "белый":
                this.type = Type.white;
                break;
            case "черный":
                this.type = Type.black;
                break;
            case "молочный":
                this.type = Type.milk;
                break;
        }

    }
}