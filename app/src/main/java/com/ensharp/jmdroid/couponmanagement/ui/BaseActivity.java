package com.ensharp.jmdroid.couponmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.value.Values;

public class BaseActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_base);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.getMenu().getItem(Values.getInstance().selectedItemNumber).setChecked(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Values.getInstance().selectedItemNumber = 0;
    }

    public void onAdd(View view) {
        Toast.makeText(this, "추가", Toast.LENGTH_SHORT).show();

    }
}
