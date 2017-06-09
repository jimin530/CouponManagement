package com.ensharp.jmdroid.couponmanagement.realm.table;

import io.realm.RealmObject;

/**
 * Created by jimin on 2017. 6. 8..
 */

public class TBCoupon extends RealmObject {
    boolean used; // 사용 여부
    int category; // 카테고리 (default : 0)
    String title; // 쿠폰 제목
    String content; // 쿠폰 내용
    String registrationDate; // 등록 날짜 (해당 앱)
    String expirationDate; // 유효기간
    String usedPlace; // 사용처
    String couponImage; // 쿠폰 전체 이미지
    String barcodeNumber; // 바코드 숫자
    String barcodeImage; // 바코드 이미지

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUsedPlace() {
        return usedPlace;
    }

    public void setUsedPlace(String usedPlace) {
        this.usedPlace = usedPlace;
    }

    public String getCouponImage() {
        return couponImage;
    }

    public void setCouponImage(String couponImage) {
        this.couponImage = couponImage;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public String getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(String barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
