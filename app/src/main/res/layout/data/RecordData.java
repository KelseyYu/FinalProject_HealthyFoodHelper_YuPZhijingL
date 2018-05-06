package com.healthy.food.helper.data;

import java.util.List;

/**
 * @author bsl
 * @package com.healthy.food.helper.data
 * @filename RecordData
 * @date 18-4-24
 * @email don't tell you
 * @describe TODO
 */

public class RecordData {

    private String staple;

    private String food;

    private List<String> largest;

    private String times;

    private String feeling;

    private String img;

    private String date;

    // the setter
    public void setStaple(String staple) {
        this.staple = staple;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setLargest(List<String> largest) {
        this.largest = largest;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // the getter
    public String getStaple() {
        return staple;
    }

    public String getFood() {
        return food;
    }

    public List<String> getLargest() {
        return largest;
    }

    public String getTimes() {
        return times;
    }

    public String getFeeling() {
        return feeling;
    }

    public String getImg() {
        return img;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "RecordData{" +
                "staple='" + staple + '\'' +
                ", food='" + food + '\'' +
                ", largest=" + largest +
                ", times='" + times + '\'' +
                ", feeling='" + feeling + '\'' +
                ", img='" + img + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
