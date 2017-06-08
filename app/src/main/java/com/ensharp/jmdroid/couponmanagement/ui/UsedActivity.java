package com.ensharp.jmdroid.couponmanagement.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ensharp.jmdroid.couponmanagement.R;

public class UsedActivity extends BaseActivity {

    LinearLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainView = (LinearLayout) findViewById(R.id.mainView);
        View view = getLayoutInflater().inflate(R.layout.activity_used, null);
        mainView.addView(view);
    }
}
