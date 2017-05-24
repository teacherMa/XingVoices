package com.example.xiaomage.xingvoices.feature.record.publish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice.LocalVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.FileUtil;
import com.example.xiaomage.xingvoices.utils.Injection;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import butterknife.BindView;

public class PublishActivity extends BaseActivity<PublishPresenter> {

    private static final String ARG_VOICE = "local voice bean";

    public static Intent getNewIntent(Context context, LocalVoice localVoice){

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_VOICE,localVoice);

        Intent intent = new Intent(context,PublishActivity.class);
        intent.putExtras(bundle);

        return intent;
    }

    @BindView(R.id.record_publish_view)
    PublishView mRecordPublishView;

    LocalVoice mLocalVoice;
    private Uri picUri;
    private Uri uriTempFile;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected PublishPresenter createPresenter() {

        mLocalVoice=(LocalVoice) getIntent().getSerializableExtra(ARG_VOICE);
        mRecordPublishView.setLocalVoice(mLocalVoice);

        return new PublishPresenter(
                Injection.provideRecordRepository(),
                mRecordPublishView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.record_publish_frag;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.PicCode.GALLERY:
                if (resultCode == Activity.RESULT_OK) {
                    picUri = data.getData();

                    String filePath = BaseUtil.getRealFilePath(this,picUri);
                    mRecordPublishView.setOriginPic(filePath);

                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(picUri, "image/*");
                    //uritempFile为Uri类变量，实例化uritempFile
                    File file = new File(FileUtil.PATH_TEMP
                            .concat(String.valueOf(new Date().getTime())).concat(".png"));
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    mRecordPublishView.setCropPic(file.getPath());

                    uriTempFile = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uriTempFile);
                    intent.putExtra("scale", true);
                    intent.putExtra("aspectX", 1.67);
                    intent.putExtra("aspectY", 1);

                    intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
                    startActivityForResult(intent, Constants.PicCode.CROP_PIC);
                }
                break;
            case Constants.PicCode.CROP_PIC:
                if (resultCode == Activity.RESULT_OK) {
                    mRecordPublishView.loadCropPicture();
                }
                break;
            default:
                break;
        }
    }

}
