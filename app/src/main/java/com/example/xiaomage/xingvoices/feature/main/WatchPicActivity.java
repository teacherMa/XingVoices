package com.example.xiaomage.xingvoices.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class WatchPicActivity extends AppCompatActivity {
    private static final String PATH = "path";

    @BindView(R.id.iv_main_watch_pic)
    ImageView mIvMainWatchPic;
    @BindView(R.id.iv_back_to_main)
    ImageView mIvBackToMain;

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, WatchPicActivity.class);
        intent.putExtra(PATH, url);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_watch_pic);

        ButterKnife.bind(this);

        String path = getIntent().getStringExtra(PATH);
        BaseUtil.load(path).into(mIvMainWatchPic);
    }

    @OnClick(R.id.iv_back_to_main)
    public void onViewClicked() {
        onBackPressed();
    }
}
