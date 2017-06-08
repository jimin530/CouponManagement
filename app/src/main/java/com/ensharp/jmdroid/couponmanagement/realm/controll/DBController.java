package com.ensharp.jmdroid.couponmanagement.realm.controll;

import android.content.Context;

import com.ensharp.jmdroid.couponmanagement.vo.CouponVO;
import com.ensharp.jmdroid.couponmanagement.realm.table.TBCoupon;
import com.ensharp.jmdroid.couponmanagement.value.Values;

import io.realm.Realm;

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
    public void deleteData(int index) {
        Values.getInstance().realm.beginTransaction();
        // 특정 index 값 삭제
        Values.getInstance().couponList.deleteFromRealm(index);
        // DB 초기화
        Values.getInstance().couponList.deleteAllFromRealm();
        Values.getInstance().realm.commitTransaction();
    }

    // select
    /*public void onGet(View view) {
        mRealm.beginTransaction();
        RealmResults<UserVO> userList = mRealm.where(UserVO.class).equalTo("name", "John").findAll();
        mRealm.commitTransaction();

        Log.i("최종 확인 : ", "이름 : " + userList.get(0).getName() + ", 나이 : " + userList.get(0).getAge());
    }*/
}
