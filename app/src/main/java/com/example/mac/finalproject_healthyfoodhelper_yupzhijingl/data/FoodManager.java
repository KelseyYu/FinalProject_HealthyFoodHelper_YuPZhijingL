package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data;



import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;

import java.util.ArrayList;
import java.util.List;


public class FoodManager implements FoodGetter {

    private int[] imgIds    = {
            R.mipmap.bacon, R.mipmap.banana, R.mipmap.chips, R.mipmap.chocolate,
            R.mipmap.hamburger, R.mipmap.milktea, R.mipmap.rice, R.mipmap.spaghetti,

    };

    private String[] names  = {
            "Bacon", "Banana", "Chips", "Chocolate",
            "Hamburger", "Milk Tea", "Rice", "Spaghetti"

    };

    private String[] carlors  = {
            "27", "91", "153", "586",
            "520", "170", "116", "371"

    };

    private String[] nutris   = {
            "Protein: 1.85 g\n" +
                    "Fat: 2.09 g\n" +
                    "Carbohydrate: 0.07 g\n", "K: 256 mg\n" +
            "Mg: 43mg\n", "Protein: 1.84 g\n" +
            "Fat: 10.49 g\n" +
            "Carbohydrate: 13.93 g\n", "Fat: 40.10g\n" +
            "Carbohydrate: 53.40g\n" +
            "Protein: 4.30g\n",
            "Protein: 26 g\n" +
                    "Fat: 26 g\n" +
                    "Carbohydrate: 46 g\n", "Protein: 0.95 g\n" +
            "Fat: 0.84 g\n" +
            "Carbohydrate: 1.51 g\n", "Protein: 2.6 g\n" +
            "Fat: 0.33 g\n" +
            "Carbohydrate: 24.86 g\n", "Protein: 13.04 g\n" +
            "Fat: 1.31 g\n" +
            "Carbohydrate: 74.67 g\n"
    };


    // get food list
    public List<Food> getFoodList() {
        List<Food> foodList = new ArrayList<Food>();

        for(int i = 0; i < imgIds.length; i ++) {
            Food food = new Food(imgIds[i], names[i], carlors[i], nutris[i]);
            foodList.add(food);
        }

        return foodList;
    }

}
