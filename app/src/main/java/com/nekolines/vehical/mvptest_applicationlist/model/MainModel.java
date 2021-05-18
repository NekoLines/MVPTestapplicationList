package com.nekolines.vehical.mvptest_applicationlist.model;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;

import com.nekolines.vehical.mvptest_applicationlist.contract.MainContract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainModel implements MainContract.Model {
    private List<ResolveInfo> all_apps_list = null;

    @Override
    public List<ResolveInfo> app_list(PackageManager pm) {
        if(pm==null)return null;
        if(all_apps_list!=null) return all_apps_list;
        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        SystemClock.sleep(5000);
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 通过getPackageManager()的queryIntentActivities方法遍历,得到所有能打开的app的packageName
        List<ResolveInfo>  resolveinfoList = pm.queryIntentActivities(resolveIntent, 0);
        all_apps_list = resolveinfoList;
        return resolveinfoList;
    }

}
