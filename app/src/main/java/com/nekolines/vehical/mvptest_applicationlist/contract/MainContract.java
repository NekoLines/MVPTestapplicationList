package com.nekolines.vehical.mvptest_applicationlist.contract;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.nekolines.vehical.mvptest_applicationlist.activity.MainActivity;

import java.util.List;

public interface MainContract {
    interface Model{
        List<ResolveInfo> app_list(PackageManager pm);
    }

    interface View{
        void updateApps(List<ResolveInfo> applist);
        void showToast(int info);
    }

    interface Presenter{
        void getApps();
        void bindView(MainActivity mainActivity);
        void setNum(int initNum);
    }
}
