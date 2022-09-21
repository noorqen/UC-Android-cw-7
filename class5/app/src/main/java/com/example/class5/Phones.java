package com.example.class5;

import java.io.Serializable;

//$1. add [implements Serializable] ser: to move object from one place to another
public class Phones implements Serializable {
    //1. identifying the data by their type
    //#1. take these as KEYS for the firebase data. has to be the same
    private String phoneName;
    private double phonePrice;
    //#2. from int to string for img and edit the rest below
    //make the ImageView comments in //itemAdapter and //itemsDetails
    private String phoneImg;

    //#3. generate an empty constructor: [select None]
    public Phones() {
    }

    //2. generating a Constructor + a Getter and Setter with all the attributes selected
    public Phones(String phoneName, double phonePrice, String phoneImg) {
        this.phoneName = phoneName;
        this.phonePrice = phonePrice;
        this.phoneImg = phoneImg;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public double getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(double phonePrice) {
        this.phonePrice = phonePrice;
    }

    public String getPhoneImg() {
        return phoneImg;
    }

    public void setPhoneImg(String phoneImg) {
        this.phoneImg = phoneImg;
    }
}
