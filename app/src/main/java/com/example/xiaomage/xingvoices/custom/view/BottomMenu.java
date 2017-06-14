package com.example.xiaomage.xingvoices.custom.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.main.OnBottomMenuItemClickListener;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import static android.view.View.GONE;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.*;

/**
 * Created by xiaomage on 2017/5/14.
 *
 */

public class BottomMenu extends PopupWindow {

    TextView mTvCancel;
    TextView mTvComment;
    TextView mTvCollection;
    TextView mTvShare;
    TextView mTvLookupPic;
    TextView mTvAddToBlacklist;

    private int mModel = 0;
    private Context mContext;
    private OnBottomMenuItemClickListener mOnBottomMenuItemClickListener;

    public void setOnBottomMenuItemClickListener(OnBottomMenuItemClickListener onBottomMenuItemClickListener) {
        mOnBottomMenuItemClickListener = onBottomMenuItemClickListener;
    }

    public BottomMenu(Context context) {

        super(context);

        mContext = context;

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        View contentView = LayoutInflater.from(context).inflate(R.layout.main_bottom_menu, null);

        setChildrenClickListener(contentView);

        setContentView(contentView);

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

    private void setChildrenClickListener(View contentView){

        mTvCancel = (TextView)contentView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mOnBottomMenuItemClickListener){
                    return;
                }
                BottomMenu.this.dismiss();
            }
        });

        mTvComment = (TextView)contentView.findViewById(R.id.tv_to_comment);
        mTvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mOnBottomMenuItemClickListener){
                    return;
                }
                mOnBottomMenuItemClickListener.onBottomItemClick(COMMENT);
                BottomMenu.this.dismiss();
            }
        });

        mTvCollection = (TextView)contentView.findViewById(R.id.tv_collection);
        mTvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mOnBottomMenuItemClickListener){
                    return;
                }
                mOnBottomMenuItemClickListener.onBottomItemClick(COLLECTION);
                BottomMenu.this.dismiss();
            }
        });

        mTvShare = (TextView)contentView.findViewById(R.id.tv_to_share);
        mTvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mOnBottomMenuItemClickListener){
                    return;
                }
                mOnBottomMenuItemClickListener.onBottomItemClick(SHARE);
                BottomMenu.this.dismiss();
            }
        });

        mTvLookupPic = (TextView)contentView.findViewById(R.id.tv_lookup_pic);
        mTvLookupPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mOnBottomMenuItemClickListener){
                    return;
                }
                mOnBottomMenuItemClickListener.onBottomItemClick(LOOK_UP_PIC);
                BottomMenu.this.dismiss();
            }
        });

        mTvAddToBlacklist = (TextView)contentView.findViewById(R.id.tv_add_to_blacklist);
        mTvAddToBlacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mOnBottomMenuItemClickListener){
                    return;
                }
                mOnBottomMenuItemClickListener.onBottomItemClick(ADD_TO_BLACK_LIST);
                BottomMenu.this.dismiss();
            }
        });
    }

    public void setModel(int model) {
        mModel = model;
        mTvCollection.setText(BaseUtil.getString(R.string.bottom_menu_cancel_collection));
        mTvAddToBlacklist.setVisibility(GONE);
    }
}
