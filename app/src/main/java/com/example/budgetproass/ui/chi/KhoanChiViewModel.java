package com.example.budgetproass.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetproass.entity.LoaiChi;
import com.example.budgetproass.entity.Chi;
import com.example.budgetproass.repository.LoaiChiResponsitory;
import com.example.budgetproass.repository.ChiResponsitory;

import java.util.List;

public class KhoanChiViewModel extends AndroidViewModel {
    private ChiResponsitory mChiResponsitory;
    private LoaiChiResponsitory mLoaiChiResponsitory;
    private LiveData<List<Chi>> mAllChi;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public KhoanChiViewModel(@NonNull Application application) {
        super(application);
        mChiResponsitory = new ChiResponsitory(application);
        mAllChi = mChiResponsitory.getAllChi();
        mLoaiChiResponsitory = new LoaiChiResponsitory(application);
        mAllLoaiChi = mLoaiChiResponsitory.getAllLoaiChi();

    }

    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;
    }
    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }
    public void insert(Chi Chi){
        mChiResponsitory.insert(Chi);
    }
    public void delete(Chi Chi){
        mChiResponsitory.delete(Chi);
    }
    public void update(Chi Chi){
        mChiResponsitory.update(Chi);
    }

}