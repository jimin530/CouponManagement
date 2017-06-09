package com.ensharp.jmdroid.couponmanagement.ottobus;

import com.squareup.otto.Bus;

public class OttoBus {
    private static OttoBus ourInstance = new OttoBus();

    public static OttoBus getInstance() {
        return ourInstance;
    }

    private OttoBus() {
    }

    // 삭제 알림
    Bus busDeleteToUnused = new Bus();
    Bus busDeleteToused = new Bus();

    public Bus getBusDeleteToUnused() {
        return busDeleteToUnused;
    }

    public void setBusDeleteToUnused(Bus busDeleteToUnused) {
        this.busDeleteToUnused = busDeleteToUnused;
    }

    public Bus getBusDeleteToused() {
        return busDeleteToused;
    }

    public void setBusDeleteToused(Bus busDeleteToused) {
        this.busDeleteToused = busDeleteToused;
    }
}
