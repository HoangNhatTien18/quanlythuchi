package com.example.budgetproass.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetproass.dao.LoaiThuDao;
import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.repository.LoaiThuResponsitory;

import java.util.List;

public class LoaiThuViewModel extends AndroidViewModel {
    private LoaiThuResponsitory mLoaiThuResponsitory;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public LoaiThuViewModel(@NonNull Application application) {
        super(application);
        mLoaiThuResponsitory = new LoaiThuResponsitory(application);
        mAllLoaiThu = mLoaiThuResponsitory.getAllLoaiThu();

    }

    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }
    public void insert(LoaiThu loaiThu){
        mLoaiThuResponsitory.insert(loaiThu);
    }
    public void delete(LoaiThu loaiThu){
        mLoaiThuResponsitory.delete(loaiThu);
    }
    public void update(LoaiThu loaiThu){
        mLoaiThuResponsitory.update(loaiThu);
    }
}