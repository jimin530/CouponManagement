package com.ensharp.jmdroid.couponmanagement.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ensharp.jmdroid.couponmanagement.R;

public class UnusedActivity extends BaseActivity {

    LinearLayout mainView;
    ImageView iv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView = (LinearLayout) findViewById(R.id.mainView);
        View view = getLayoutInflater().inflate(R.layout.activity_unused, null);
        mainView.addView(view);

        iv_main = (ImageView) findViewById(R.id.iv_main);

        /*Values.getInstance().realm.beginTransaction();
        RealmResults<TBCoupon> getCoupon = Values.getInstance().realm.where(TBCoupon.class).findAll();
        Values.getInstance().realm.commitTransaction();

        Bitmap bitmap = BitmapController.byteArrayToBitmap(getCoupon.get(0).getCouponImage());
        iv_main.setImageBitmap(bitmap);*/
    }
}
