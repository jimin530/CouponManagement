package com.ensharp.jmdroid.couponmanagement.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.util.adapter.CouponRVAdapter;
import com.ensharp.jmdroid.couponmanagement.value.Values;

public class UnusedActivity extends BaseActivity {

    LinearLayout mainView;
    RecyclerView rv_unused;
    CouponRVAdapter couponRVAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView = (LinearLayout) findViewById(R.id.mainView);
        View view = getLayoutInflater().inflate(R.layout.activity_unused, null);
        mainView.addView(view);

        rv_unused = (RecyclerView) findViewById(R.id.rv_unused);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_unused.setLayoutManager(linearLayoutManager);
        couponRVAdapter = new CouponRVAdapter(this, Values.getInstance().couponList);
        rv_unused.setAdapter(couponRVAdapter);
        /*Values.getInstance().realm.beginTransaction();
        RealmResults<TBCoupon> getCoupon = Values.getInstance().realm.where(TBCoupon.class).findAll();
        Values.getInstance().realm.commitTransaction();

        Bitmap bitmap = BitmapController.byteArrayToBitmap(getCoupon.get(0).getCouponImage());
        iv_main.setImageBitmap(bitmap);*/
    }
}
