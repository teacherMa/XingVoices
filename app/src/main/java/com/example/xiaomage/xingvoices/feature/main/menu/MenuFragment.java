package com.example.xiaomage.xingvoices.feature.main.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MenuFragment extends BaseFragment<MenuPresenter> {

    public static MenuFragment getNewInstance(Context context){
        return new MenuFragment();
    }

    @BindView(R.id.main_menu_view)
    MenuView mMainMenuView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected MenuPresenter createPresenter() {
        return new MenuPresenter(
                Injection.provideMainRepository(),
                mMainMenuView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_menu_frag;
    }
}
