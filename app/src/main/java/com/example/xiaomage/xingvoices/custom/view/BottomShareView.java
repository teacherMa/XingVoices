package com.example.xiaomage.xingvoices.custom.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.PopupWindowCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.main.OnBottomShareItemClickListener;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.utils.Constants;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class BottomShareView extends PopupWindow {

    private Context mContext;

    private ImageView mIvDismiss;
    private ImageView mIvQqShare;
    private ImageView mIvWechatShare;
    private ImageView mIvSinaShare;

    private OnBottomShareItemClickListener mOnBottomShareItemClickListener;

    public BottomShareView(Context context) {
        super(context);
        mContext = context;

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.main_bottom_com, null);

        setContentView(contentView);

        setChildrenClickListener(contentView);

        setFocusable(true);
        setTouchable(true);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = ((MainActivity) mContext).getWindow().getAttributes();
                layoutParams.alpha = 1.0f;
                ((MainActivity) mContext).getWindow().setAttributes(layoutParams);
            }
        });

        this.setAnimationStyle(R.style.BottomMenuAnim);
        this.setBackgroundDrawable(new BitmapDrawable());
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        WindowManager.LayoutParams layoutParams = ((MainActivity) mContext).getWindow().getAttributes();
        layoutParams.alpha = 0.5f;
        ((MainActivity) mContext).getWindow().setAttributes(layoutParams);
    }

    private void setChildrenClickListener(View contentView) {

        mIvDismiss = (ImageView) contentView.findViewById(R.id.iv_dismiss);
        mIvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mIvQqShare = (ImageView) contentView.findViewById(R.id.iv_qq_share);
        mIvQqShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBottomShareItemClickListener.onBottomShareItemClick(Constants.BottomShareItem.QQ_SHARE);
                dismiss();
            }
        });

        mIvWechatShare = (ImageView) contentView.findViewById(R.id.iv_wechat);
        mIvWechatShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBottomShareItemClickListener.onBottomShareItemClick(Constants.BottomShareItem.WECHAT_SHARE);
                dismiss();
            }
        });

        mIvSinaShare = (ImageView) contentView.findViewById(R.id.iv_sina);
        mIvSinaShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBottomShareItemClickListener.onBottomShareItemClick(Constants.BottomShareItem.SINA_SHARE);
                dismiss();
            }
        });
    }

    public void setOnBottomShareItemClickListener(OnBottomShareItemClickListener onBottomShareItemClickListener) {
        mOnBottomShareItemClickListener = onBottomShareItemClickListener;
    }
}
