package com.example.budgetproass.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetproass.entity.LoaiChi;
import com.example.budgetproass.repository.LoaiChiResponsitory;

import java.util.List;

public class LoaiChiViewModel extends AndroidViewModel {
    private LoaiChiResponsitory mLoaiChiResponsitory;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiViewModel(@NonNull Application application) {
        super(application);
        mLoaiChiResponsitory = new LoaiChiResponsitory(application);
        mAllLoaiChi = mLoaiChiResponsitory.getAllLoaiChi();

    }

    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }
    public void insert(LoaiChi loaiChi){
        mLoaiChiResponsitory.insert(loaiChi);
    }
    public void delete(LoaiChi loaiChi){
        mLoaiChiResponsitory.delete(loaiChi);
    }
    public void update(LoaiChi loaiChi){
        mLoaiChiResponsitory.update(loaiChi);
    }
}