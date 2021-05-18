package com.nekolines.vehical.mvptest_applicationlist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nekolines.vehical.mvptest_applicationlist.R;
import com.nekolines.vehical.mvptest_applicationlist.contract.MainContract;
import com.nekolines.vehical.mvptest_applicationlist.presenter.MainPresenter;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static MainPresenter mainPresenter;
    private ListView appsListView;
    private MainActivity that;
    private int initNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        that = this;
        initView(savedInstanceState);


    }

    private void initView(Bundle savedInstanceState) {
        try {
            initNum = savedInstanceState.getInt("rec");
        }catch (Exception e){

        }

        initNum +=1;

        if(mainPresenter == null){
            mainPresenter = new MainPresenter();
            mainPresenter.setNum(initNum);
        }
        mainPresenter.bindView(this);

        appsListView = findViewById(R.id.AppsListView);

        Button button =findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                that.recreate();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rec",initNum);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.getApps();
    }

    @Override
    public void updateApps(List<ResolveInfo> applist) {
        appsListView.setAdapter(new CommonAdapter<ResolveInfo>(this,R.layout.adapter_apps_item,applist) {
            @Override
            protected void convert(ViewHolder viewHolder, ResolveInfo item, int position) {
                viewHolder.setText(R.id.title, String.valueOf(item.loadLabel(that.getPackageManager())));
                viewHolder.setText(R.id.packagename,item.activityInfo.name);
                viewHolder.setImageDrawable(R.id.icon,item.loadIcon(that.getPackageManager()));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.bindView(null);
    }

    public void showToast(int intdd) {
        Toast.makeText(that,""+intdd,Toast.LENGTH_SHORT).show();
    }
}
