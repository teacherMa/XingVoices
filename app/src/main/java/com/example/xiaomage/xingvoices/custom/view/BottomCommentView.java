package com.example.xiaomage.xingvoices.custom.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.utils.Constants;

/**
 * Created by xiaomage on 2017/5/23.
 *
 */

public class BottomCommentView extends PopupWindow {

    private Context mContext;

    private ImageView mIvToVoiceCom;
    private Button mIvSendCom;
    private EditText mEtComContent;

    public BottomCommentView(Context context) {
        super(context);
        mContext = context;

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.main_bottom_com,null);

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

    private void setChildrenClickListener(View contentView){

        mIvToVoiceCom = (ImageView)contentView.findViewById(R.id.iv_to_voice_com);
        mIvToVoiceCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mIvSendCom = (Button)contentView.findViewById(R.id.iv_send_com);
        mIvSendCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mEtComContent = (EditText)contentView.findViewById(R.id.et_voice_com_content);
        mEtComContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
