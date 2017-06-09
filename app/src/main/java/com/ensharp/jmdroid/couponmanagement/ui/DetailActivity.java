package com.ensharp.jmdroid.couponmanagement.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.realm.controll.DBController;
import com.ensharp.jmdroid.couponmanagement.util.bitmap.BitmapController;

public class DetailActivity extends AppCompatActivity {

    ImageView iv_coupon_view;

    DBController dbController;
    String timeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        timeKey = getIntent().getStringExtra("timeKey");

        dbController = new DBController();
        iv_coupon_view = (ImageView) findViewById(R.id.iv_coupon_view);
        String path = dbController.getCoupon(timeKey);
        iv_coupon_view.setImageBitmap(BitmapController.pathToBitmap(path));
    }

    public void onBackToMainFromDetail(View view) {
        onBackPressed();
    }

    public void onChangeUsed(View view) {
        try {
            dbController.updateToUsed(timeKey);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "오류 발생", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
