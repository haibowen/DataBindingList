package com.ekwing.databindinglist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import com.ekwing.databindinglist.databinding.IteListMvvmBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<UserCenter> mDataList;

    public RecyclerViewAdapter(List<UserCenter> mDataList) {
        this.mDataList = mDataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        IteListMvvmBinding binding;
        public ViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding=(IteListMvvmBinding)binding;
        }

        public IteListMvvmBinding getBinding() {
            return binding;
        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        IteListMvvmBinding binding= DataBindingUtil.inflate((LayoutInflater.from(parent.getContext())), R.layout.ite_list_mvvm,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        UserCenter userCenter=mDataList.get(position);
        holder.getBinding().setUsercenter(userCenter);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
