package com.ensharp.jmdroid.couponmanagement.util.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by jimin on 2017. 6. 9..
 */

public class BitmapController {
    public BitmapController() {
    }

    // byte[]를 bitmap으로 변환
    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }

    // bitmap을 byte[]로 변환
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static Bitmap pathToBitmap(String path) {
        File file = new File(path);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            return bitmap;
        }
        return null;
    }

    // bitmap resizing
    public static Bitmap pathTOThumnail(String file, int width, int height) {
        //비트맵 옵션 설정을 위한 객체 생성
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        //아래 부분은 중요!
        //비트맵 객체를 생성 하지 않고, 해당 이미지의 정보만 추출시 사용한다,
        //out of memory를 예외를 예방 할수 있는 좋은 방법이다...
        bmpFactoryOptions.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        //비율 계산
        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) height);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth / (float) width);

        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                // 샘플 사이즈를 지정 한다
                // 샘플 사이즈 N으로 설정 하면 -> 이미지 사이즈의 1/N 이 된다.
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }
        //실제 Bitmap 객체 생성을 하기위해 False로 세팅
        bmpFactoryOptions.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        return bitmap;
    }
}
