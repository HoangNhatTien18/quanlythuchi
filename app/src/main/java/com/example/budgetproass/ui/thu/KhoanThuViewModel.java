package com.example.budgetproass.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.entity.Thu;
import com.example.budgetproass.repository.LoaiThuResponsitory;
import com.example.budgetproass.repository.ThuResponsitory;

import java.util.List;

public class KhoanThuViewModel extends AndroidViewModel {
    private ThuResponsitory mThuResponsitory;
    private LoaiThuResponsitory mLoaiThuResponsitory;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public KhoanThuViewModel(@NonNull Application application) {
        super(application);
        mThuResponsitory = new ThuResponsitory(application);
        mAllThu = mThuResponsitory.getAllThu();
        mLoaiThuResponsitory = new LoaiThuResponsitory(application);
        mAllLoaiThu = mLoaiThuResponsitory.getAllLoaiThu();

    }

    public LiveData<List<Thu>> getAllThu(){
        return mAllThu;
    }
    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }
    public void insert(Thu thu){
        mThuResponsitory.insert(thu);
    }
    public void delete(Thu thu){
        mThuResponsitory.delete(thu);
    }
    public void update(Thu thu){
        mThuResponsitory.update(thu);
    }


}