package com.ensharp.jmdroid.couponmanagement.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.realm.controll.DBController;
import com.ensharp.jmdroid.couponmanagement.util.bitmap.BitmapController;
import com.ensharp.jmdroid.couponmanagement.value.Values;

public class BaseActivity extends AppCompatActivity {

    DBController dbController = new DBController();
    BitmapController bitmapController = new BitmapController();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_unused:
                    Values.getInstance().selectedItemNumber = 0;
                    intent = new Intent(getBaseContext(), UnusedActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.navigation_used:
                    Values.getInstance().selectedItemNumber = 1;
                    intent = new Intent(getBaseContext(), UsedActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.navigation_option:
                    Values.getInstance().selectedItemNumber = 2;
                    intent = new Intent(getBaseContext(), OptionActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DB 인스턴스 얻기
        dbController.initDB(this);

        setContentView(R.layout.activity_base);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(Values.getInstance().selectedItemNumber).setChecked(true);

        Bitmap bitmap = getBitmapFromDrawable(this, R.drawable.img_sample);
        byte[] byteArray = bitmapController.bitmapToByteArray(bitmap);

        /*CouponVO couponVO = new CouponVO(
                false,
                0,
                "제목",
                "내용",
                "등록날짜",
                "유효기간",
                "사용처",
                byteArray,
                "바코드숫자",
                byteArray
        );
        dbController.insertData(couponVO);*/

        Log.i("확인", ">>>>>   List.size :  " + Values.getInstance().couponList.size());
    }

    public static Bitmap getBitmapFromDrawable(Context context, @DrawableRes int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable || drawable instanceof VectorDrawableCompat) {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return bitmap;
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Values.getInstance().selectedItemNumber = 0;
    }

    public void onAdd(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
