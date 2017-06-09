package com.ensharp.jmdroid.couponmanagement.value;


import com.ensharp.jmdroid.couponmanagement.realm.table.TBCoupon;

import io.realm.Realm;
import io.realm.RealmResults;

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

    public int selectedItemNumber;

    public Realm realm;
    public RealmResults<TBCoupon> couponList;
}