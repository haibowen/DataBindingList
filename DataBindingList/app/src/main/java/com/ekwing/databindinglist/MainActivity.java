package com.ekwing.databindinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ekwing.databindinglist.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    List<UserCenter> mDataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        initData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvList.setLayoutManager(layoutManager);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(mDataList);
        mBinding.rvList.setAdapter(recyclerViewAdapter);
    }

    private void initData() {
        for (int i = 0; i <10 ; i++) {

            UserCenter userCenter=new UserCenter("海波","");
            UserCenter userCenter1=new UserCenter("java","");
            mDataList.add(userCenter);
            mDataList.add(userCenter1);

        }
    }
}
