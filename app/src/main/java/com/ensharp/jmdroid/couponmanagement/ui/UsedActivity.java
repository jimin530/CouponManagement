package com.ensharp.jmdroid.couponmanagement.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.util.adapter.UsedCouponRVAdapter;
import com.ensharp.jmdroid.couponmanagement.value.Values;

public class UsedActivity extends BaseActivity {

    LinearLayout mainView;
    RecyclerView rv_used;
    UsedCouponRVAdapter usedCouponRVAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView = (LinearLayout) findViewById(R.id.mainView);
        View view = getLayoutInflater().inflate(R.layout.activity_used, null);
        mainView.addView(view);

        rv_used = (RecyclerView) findViewById(R.id.rv_used);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_used.setLayoutManager(linearLayoutManager);
        usedCouponRVAdapter = new UsedCouponRVAdapter(this, Values.getInstance().couponList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv_used.setAdapter(usedCouponRVAdapter);
    }
}