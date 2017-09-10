package com.xjcrepe.sgpsi.map;

import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * Created by LiXijun on 2017/9/10.
 */

public class MainPresenter implements MainContract.Presenter {

    @Nullable
    private MainContract.View view;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void bindView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void fetchPsiReadingsList() {

    }

    @Override
    public void onUserSelectedPsiReadings() {

    }
}
