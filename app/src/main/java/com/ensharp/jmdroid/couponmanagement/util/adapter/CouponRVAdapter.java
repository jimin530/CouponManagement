package com.ensharp.jmdroid.couponmanagement.util.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensharp.jmdroid.couponmanagement.R;
import com.ensharp.jmdroid.couponmanagement.ottobus.OttoBus;
import com.ensharp.jmdroid.couponmanagement.realm.controll.DBController;
import com.ensharp.jmdroid.couponmanagement.realm.table.TBCoupon;
import com.ensharp.jmdroid.couponmanagement.ui.DetailActivity;
import com.ensharp.jmdroid.couponmanagement.util.bitmap.BitmapController;
import com.ensharp.jmdroid.couponmanagement.value.Values;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimin on 2017. 6. 9..
 */

public class CouponRVAdapter extends RecyclerView.Adapter<CouponRVAdapter.ViewHolder> {
    Context context;
    List<TBCoupon> tmpCouponList = new ArrayList<>();
    DBController dbController = new DBController();

    public CouponRVAdapter(Context context, List<TBCoupon> tmpCouponList) {
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
        return tmpCouponList.size();
    }

    // 레이아웃을 만들어서 Holer에 저장
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_coupon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (Values.getInstance().selectedItemNumber == 0) {
            viewHolder.fl_cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("timeKey", tmpCouponList.get(position).getRegistrationDate());
                    context.startActivity(intent);
                }
            });
        }
        /*try {
            viewHolder.iv_coupon_thumbnail.setImageBitmap(BitmapController.pathToBitmap(tmpCouponList.get(position).getCouponImage()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }*/
        try {
            viewHolder.iv_coupon_thumbnail.setImageBitmap(BitmapController.pathTOThumnail(tmpCouponList.get(position).getCouponImage(), 180, 180));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        viewHolder.tv_coupon_title.setText(tmpCouponList.get(position).getTitle());
        viewHolder.tv_coupon_content.setText(tmpCouponList.get(position).getContent());
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dbController.deleteData(tmpCouponList.get(position).getRegistrationDate());
                                if (Values.getInstance().selectedItemNumber == 0) {
                                    OttoBus.getInstance().getBusDeleteToUnused().post(context);
                                } else if (Values.getInstance().selectedItemNumber == 1) {
                                    OttoBus.getInstance().getBusDeleteToused().post(context);
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                return;
                            }
                        });

                dialog = builder.create();
                dialog.show();
            }
        });
    }

    // 뷰 재활용을 위한 viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fl_cell;

        ImageView iv_coupon_thumbnail;
        TextView tv_coupon_title;
        TextView tv_coupon_content;
        ImageButton btn_delete;

        // 뷰로부터 컴포넌트를 획득
        public ViewHolder(View itemView) {
            super(itemView);
            fl_cell = (FrameLayout) itemView.findViewById(R.id.fl_cell);
            iv_coupon_thumbnail = (ImageView) itemView.findViewById(R.id.iv_coupon_thumbnail);
            tv_coupon_title = (TextView) itemView.findViewById(R.id.tv_coupon_title);
            tv_coupon_content = (TextView) itemView.findViewById(R.id.tv_coupon_content);
            btn_delete = (ImageButton) itemView.findViewById(R.id.btn_delete);
        }
    }
}
