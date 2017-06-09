package com.ensharp.jmdroid.couponmanagement.util.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.realm.table.TBCoupon;
import com.ensharp.jmdroid.couponmanagement.util.bitmap.BitmapController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimin on 2017. 6. 9..
 */

public class UsedCouponRVAdapter extends RecyclerView.Adapter<UsedCouponRVAdapter.ViewHolder> {
    Context context;
    List<TBCoupon> tmpCouponList = new ArrayList<>();

    public UsedCouponRVAdapter(Context context, List<TBCoupon> tmpCouponList) {
        this.context = context;
        this.tmpCouponList = tmpCouponList;
    }

    // item 추가 메소드
    public void addItems(List<TBCoupon> items) {
        if (null != items) {
            this.tmpCouponList.addAll(items);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < tmpCouponList.size(); i++) {
            if (tmpCouponList.get(i).isUsed()) {
                count++;
            }
        }
        //return tmpCouponList.size();
        return count;
    }

    // 레이아웃을 만들어서 Holer에 저장
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_coupon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        if (tmpCouponList.get(position).isUsed()) {

            try {
                viewHolder.iv_coupon_thumbnail.setImageBitmap(BitmapController.pathToBitmap(tmpCouponList.get(position).getCouponImage()));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            viewHolder.tv_coupon_title.setText(tmpCouponList.get(position).getTitle());
            viewHolder.tv_coupon_content.setText(tmpCouponList.get(position).getContent());
            viewHolder.btn_detail.setVisibility(View.INVISIBLE);

        }
    }

    // 뷰 재활용을 위한 viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_coupon_thumbnail;
        TextView tv_coupon_title;
        TextView tv_coupon_content;
        ImageButton btn_detail;

        // 뷰로부터 컴포넌트를 획득
        public ViewHolder(View itemView) {
            super(itemView);
            iv_coupon_thumbnail = (ImageView) itemView.findViewById(R.id.iv_coupon_thumbnail);
            tv_coupon_title = (TextView) itemView.findViewById(R.id.tv_coupon_title);
            tv_coupon_content = (TextView) itemView.findViewById(R.id.tv_coupon_content);
            btn_detail = (ImageButton) itemView.findViewById(R.id.btn_detail);
        }
    }
}
