package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data;

import java.io.Serializable;



public class Food implements Serializable {

    private int imgId;

    private String name;

    private String carloriesPerServing;

    private String nutriion;

    // constructor
    public Food(int imgId, String name, String carloriesPerServing, String nutriion) {
        this.imgId      = imgId;
        this.name       = name;
        this.nutriion   = nutriion;
        this.carloriesPerServing = carloriesPerServing;
    }


    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarloriesPerServing() {
        return carloriesPerServing;
    }

    public void setCarloriesPerServing(String carloriesPerServing) {
        this.carloriesPerServing = carloriesPerServing;
    }

    public String getNutriion() {
        return nutriion;
    }

    public void setNutriion(String nutriion) {
        this.nutriion = nutriion;
    }

    @Override
    public String toString() {
        return "Food{" +
                "imgId=" + imgId +
                ", name='" + name + '\'' +
                ", carloriesPerServing='" + carloriesPerServing + '\'' +
                ", nutriion='" + nutriion + '\'' +
                '}';
    }
}
