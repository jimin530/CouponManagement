package com.ensharp.jmdroid.couponmanagement.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.util.adapter.UnusedCouponRVAdapter;
import com.ensharp.jmdroid.couponmanagement.value.Values;

public class UnusedActivity extends BaseActivity {

    LinearLayout mainView;
    RecyclerView rv_unused;
    UnusedCouponRVAdapter unusedCouponRVAdapter;
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
        unusedCouponRVAdapter = new UnusedCouponRVAdapter(this, Values.getInstance().couponList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv_unused.setAdapter(unusedCouponRVAdapter);
    }
}
