package com.example.budgetproass.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetproass.entity.ThongKeLoaiChi;
import com.example.budgetproass.entity.ThongKeLoaiThu;
import com.example.budgetproass.repository.ChiResponsitory;
import com.example.budgetproass.repository.ThuResponsitory;

import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuResponsitory mThuResponsitory;
    private ChiResponsitory mChiResponsitory;
    private LiveData<Float> mTongThu,mTongChi;
    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus;
    private LiveData<List<ThongKeLoaiChi>> mThongKeLoaiChis;

    public ThongKeViewModel(@NonNull Application application) {
        super(application);
        mThuResponsitory = new ThuResponsitory(application);
        mChiResponsitory = new ChiResponsitory(application);
        mTongThu = mThuResponsitory.sumTongThu();
        mTongChi = mChiResponsitory.sumTongChi();
        mThongKeLoaiThus = mThuResponsitory.sumByLoaiThu();
        mThongKeLoaiChis = mChiResponsitory.sumByLoaiChi();

    }

    public LiveData<Float> getTongThu() {

        return mTongThu;
    }
    public LiveData<Float> getTongChi() {

        return mTongChi;
    }

    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThus() {
        return mThongKeLoaiThus;
    }
    public LiveData<List<ThongKeLoaiChi>> getThongKeLoaiChis() {
        return mThongKeLoaiChis;
    }
}
