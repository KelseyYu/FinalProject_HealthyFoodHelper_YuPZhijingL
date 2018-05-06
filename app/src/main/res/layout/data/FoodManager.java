package com.healthy.food.helper.data;

import com.healthy.food.helper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bsl
 * @package com.healthy.food.helper.data
 * @filename FoodManager
 * @date 18-4-25
 * @email don't tell you
 * @describe TODO
 */

public class FoodManager implements FoodGetter {

    private int[] imgIds    = {
            R.mipmap.paw_left, R.mipmap.paw_code, R.mipmap.paw_left, R.mipmap.paw_code,
            R.mipmap.paw_left, R.mipmap.paw_code, R.mipmap.paw_left, R.mipmap.paw_code,
            R.mipmap.paw_left, R.mipmap.paw_code, R.mipmap.paw_left, R.mipmap.paw_code,
            R.mipmap.paw_left, R.mipmap.paw_code, R.mipmap.paw_left, R.mipmap.paw_code,
    };

    private String[] names  = {
            "hhh1", "hhh2", "hhh3", "hhh4",
            "hhh5", "hhh6", "hhh7", "hhh8",
            "hhh9", "hhh10", "hhh11", "hhh12",
            "hhh13", "hhh14", "hhh15", "hhh16"
    };

    private String[] carlors  = {
            "1000", "1001", "1002", "1003",
            "1004", "1005", "1006", "1007",
            "1008", "1009", "1010", "1011",
            "1012", "1013", "1014", "1015"
    };

    private String[] nutris   = {
            "2000", "2001", "2002", "2003",
            "2004", "2005", "2006", "2007",
            "2008", "2009", "2010", "2011",
            "2012", "2013", "2014", "2015"
    };

    // get food list
    public List<com.healthy.food.helper.data.Food> getFoodList() {
        List<com.healthy.food.helper.data.Food> foodList = new ArrayList<com.healthy.food.helper.data.Food>();

        for(int i = 0; i < imgIds.length; i ++) {
            com.healthy.food.helper.data.Food food = new com.healthy.food.helper.data.Food(imgIds[i], names[i], carlors[i], nutris[i]);
            foodList.add(food);
        }

        return foodList;
    }

}
