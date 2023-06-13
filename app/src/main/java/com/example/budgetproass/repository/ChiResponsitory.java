package com.example.budgetproass.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetproass.dao.AppDatabase;
import com.example.budgetproass.dao.AppDatabase2;
import com.example.budgetproass.dao.ChiDao;
import com.example.budgetproass.dao.ThuDao;
import com.example.budgetproass.entity.Chi;
import com.example.budgetproass.entity.ThongKeLoaiChi;
import com.example.budgetproass.entity.ThongKeLoaiThu;

import java.util.List;

public class ChiResponsitory {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    public ChiResponsitory(Application application){
        this.mChiDao = AppDatabase2.getDatabase(application).chiDao();
        mAllChi = mChiDao.findAll();
    }




    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;
    }

    public LiveData<Float> sumTongChi(){
        return mChiDao.sumTongChi();
    }
    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(){
        return mChiDao.sumByLoaiChi();
    }

    public void insert(Chi Chi){
        new InsertAsyncTask(mChiDao).execute(Chi);
    }
    public void delete(Chi Chi){
        new DeleteAsyncTask(mChiDao).execute(Chi);
    }
    public void update(Chi Chi){
        new UpdateAsyncTask(mChiDao).execute(Chi);
    }




    class InsertAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public InsertAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.insert(Chis[0]);
            return null;
        }
    }



    class DeleteAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public DeleteAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.delete(Chis[0]);
            return null;
        }
    }



    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public UpdateAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.update(Chis[0]);
            return null;
        }
    }
}
