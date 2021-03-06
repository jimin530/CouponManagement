package com.ensharp.jmdroid.couponmanagement.realm.controll;

import android.content.Context;

import com.ensharp.jmdroid.couponmanagement.realm.table.TBCoupon;
import com.ensharp.jmdroid.couponmanagement.value.Values;
import com.ensharp.jmdroid.couponmanagement.vo.CouponVO;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jimin on 2017. 6. 9..
 */

public class DBController {
    public DBController() {
    }

    public void initDB(Context context) {
        // Realm 초기화
        Realm.init(context);
        // Realm 인스턴스 얻기
        Values.getInstance().realm = Realm.getDefaultInstance();
        Values.getInstance().couponList = Values.getInstance().realm.where(TBCoupon.class).findAll();
    }

    // Insert
    public void insertData(CouponVO couponVO) {
        try {
            Values.getInstance().realm.beginTransaction();
            TBCoupon tbCoupon = Values.getInstance().realm.createObject(TBCoupon.class);
            tbCoupon.setUsed(couponVO.isUsed());
            tbCoupon.setCategory(couponVO.getCategory());
            tbCoupon.setTitle(couponVO.getTitle());
            tbCoupon.setContent(couponVO.getContent());
            tbCoupon.setRegistrationDate(couponVO.getRegistrationDate());
            tbCoupon.setExpirationDate(couponVO.getExpirationDate());
            tbCoupon.setUsedPlace(couponVO.getUsedPlace());
            tbCoupon.setCouponImage(couponVO.getCouponImage());
            tbCoupon.setBarcodeNumber(couponVO.getBarcodeNumber());
            tbCoupon.setBarcodeImage(couponVO.getBarcodeImage());
        } catch (Exception e) {
            e.printStackTrace();
            // Log.i(TAG, ">>>>>   Primary Key :  겹침");
        } finally {
            Values.getInstance().realm.commitTransaction();
        }
    }

    // Delete
    public void deleteData(String timeKey) {
        Values.getInstance().realm.beginTransaction();
        TBCoupon tbCoupon = Values.getInstance().realm.where(TBCoupon.class).equalTo("registrationDate", timeKey).findFirst();
        tbCoupon.deleteFromRealm();
        Values.getInstance().realm.commitTransaction();
    }

    // select
    public String getCoupon(String timeKey) {
        Values.getInstance().realm.beginTransaction();
        RealmResults<TBCoupon> coupon = Values.getInstance().realm.where(TBCoupon.class).equalTo("registrationDate", timeKey).findAll();
        Values.getInstance().realm.commitTransaction();

        return coupon.get(0).getCouponImage();
    }

    // update to used
    public void updateToUsed(String timeKey) {
        Values.getInstance().realm.beginTransaction();
        TBCoupon tbCoupon = Values.getInstance().realm.where(TBCoupon.class).equalTo("registrationDate", timeKey).findFirst();
        tbCoupon.setUsed(true);
        Values.getInstance().realm.commitTransaction();
    }
}
