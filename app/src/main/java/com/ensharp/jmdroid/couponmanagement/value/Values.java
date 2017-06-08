package com.ensharp.jmdroid.couponmanagement.value;


/**
 * Created by jimin on 2017-02-06.
 */
public class Values {
    private static Values ourInstance = new Values();

    public static Values getInstance() {
        return ourInstance;
    }

    private Values() {
    }

    public int selectedItemNumber = 0;
}