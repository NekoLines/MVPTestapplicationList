package com.nekolines.vehical.mvptest_applicationlist.presenter;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.nekolines.vehical.mvptest_applicationlist.activity.MainActivity;
import com.nekolines.vehical.mvptest_applicationlist.contract.MainContract;
import com.nekolines.vehical.mvptest_applicationlist.model.MainModel;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainModel model;
    private MainActivity view;
    private int Intdd=0;

    public MainPresenter() {
        this.model = new MainModel();
    }

    @Override
    public void getApps() {
        PackageManager pm = view.getPackageManager();
        List<ResolveInfo> list = model.app_list(pm);
        if(list!=null){
            view.updateApps(list);
            view.showToast(Intdd);
        }
    }

    @Override
    public void bindView(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void setNum(int initNum) {
        Intdd = initNum;
    }
}
