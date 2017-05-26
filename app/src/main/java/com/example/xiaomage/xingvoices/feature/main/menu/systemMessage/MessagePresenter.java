package com.example.xiaomage.xingvoices.feature.main.menu.systemMessage;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.Resp.myVoiceCommentResp.MyVoiceCommentResp;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import java.util.List;

public class MessagePresenter extends BasePresenter<MessageContract.View, MainRepository> implements MessageContract.Presenter {

    public MessagePresenter(@NonNull MainRepository repository, @NonNull MessageContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        requestComment(1);
    }

    @Override
    public void requestComment(int num) {
        OnResultCallback<List<MyVoiceCommentResp>> callback = new OnResultCallback<List<MyVoiceCommentResp>>() {
            @Override
            public void onSuccess(List<MyVoiceCommentResp> resultValue, int code) {
                if (getView() == null) {
                    return;
                }
                getView().requestSuccess(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if (getView() == null) {
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().requestMyVoiceComments(callback,num);
    }
}
