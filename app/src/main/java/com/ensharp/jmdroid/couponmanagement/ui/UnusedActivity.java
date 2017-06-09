package com.ensharp.jmdroid.couponmanagement.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.realm.table.TBCoupon;
import com.ensharp.jmdroid.couponmanagement.util.adapter.CouponRVAdapter;
import com.ensharp.jmdroid.couponmanagement.value.Values;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetItem();
        rv_unused.setAdapter(couponRVAdapter);
    }

    public void resetItem() {
        List<TBCoupon> tmpCouponList = new ArrayList<>();
        for (int i = 0; i < Values.getInstance().couponList.size(); i++) {
            if (!Values.getInstance().couponList.get(i).isUsed()) {
                tmpCouponList.add(Values.getInstance().couponList.get(i));
            }
        }
        Collections.reverse(tmpCouponList);
        couponRVAdapter = new CouponRVAdapter(this, tmpCouponList);
    }
}
