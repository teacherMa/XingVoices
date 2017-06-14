package com.example.xiaomage.xingvoices.feature.main.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.utils.AlertUtil;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class SettingActivity extends AppCompatActivity {

    public static Intent getIntent(Context context){
        return new Intent(context,SettingActivity.class);
    }

    @BindView(R.id.iv_back_to_menu)
    ImageView mIvBackToMenu;
    @BindView(R.id.rl_clear)
    RelativeLayout mRlClear;
    @BindView(R.id.rl_about_us)
    RelativeLayout mRlAboutUs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_setting_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back_to_menu)
    public void onMIvBackToMenuClicked() {
        onBackPressed();
    }

    @OnClick(R.id.rl_clear)
    public void onMRlClearClicked() {
        AlertUtil.showAlert(this, BaseUtil.getString(R.string.main_clear_rom),BaseUtil.getString(R.string.main_clear_success));
    }
}
