package com.ensharp.jmdroid.couponmanagement.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.realm.controll.DBController;
import com.ensharp.jmdroid.couponmanagement.util.uri.UriController;
import com.ensharp.jmdroid.couponmanagement.vo.CouponVO;

public class AddActivity extends AppCompatActivity {

    EditText et_coupon_title;
    EditText et_coupon_content;
    ImageView iv_selected_image;

    String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_coupon_title = (EditText) findViewById(R.id.et_coupon_title);
        et_coupon_content = (EditText) findViewById(R.id.et_coupon_content);
        iv_selected_image = (ImageView) findViewById(R.id.iv_selected_image);
    }

    public void onChangeCouponImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        //requestCode 100
        startActivityForResult(photoPickerIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = imageReturnedIntent.getData();
                        iv_selected_image.setImageURI(selectedImage);
                        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            selectedImagePath = UriController.getRealPathFromURI_API19(this, selectedImage);
                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                    selectedImagePath = UriController.getRealPathFromURI_API11to18(this, selectedImage);
                        } else {
                            selectedImagePath = UriController.getRealPathFromURI_BelowAPI11(this, selectedImage);
                        }*/
                        selectedImagePath = UriController.getRealPathFromURI(this, selectedImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public void onGallery(View view) {
        onChangeCouponImage();
    }

    public void onBackToMainFromAdd(View view) {
        onBackPressed();
    }

    public void onRegist(View view) {
        if (et_coupon_title.getText().toString().length() == 0) {
            Toast.makeText(this, "쿠폰 이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            try {
                long time = System.currentTimeMillis();
                DBController dbController = new DBController();
                CouponVO couponVO = new CouponVO(
                        false,
                        0,
                        et_coupon_title.getText().toString(),
                        et_coupon_content.getText().toString(),
                        String.valueOf(time),
                        "유효기간",
                        "사용처",
                        selectedImagePath,
                        "바코드숫자",
                        selectedImagePath
                );
                dbController.insertData(couponVO);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "저장 오류", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}
