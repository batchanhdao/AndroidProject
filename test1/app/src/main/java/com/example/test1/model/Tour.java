package com.example.test1.model;

public class Tour {
    private int img;
    private String name, description;

    public Tour(int img, String name, String description) {
        this.img = img;
        this.name = name;
        this.description = description;
    }

    public Tour() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
