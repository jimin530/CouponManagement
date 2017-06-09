package com.ensharp.jmdroid.couponmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.realm.controll.DBController;
import com.ensharp.jmdroid.couponmanagement.value.Values;

public class BaseActivity extends AppCompatActivity {

    DBController dbController = new DBController();

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

        Log.i("확인", ">>>>>   Coupon.size :  " + Values.getInstance().couponList.size());
        //dbController.deleteData(0);
        //Log.i("확인", ">>>>>   Coupon.size :  " + Values.getInstance().couponList.size());

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
