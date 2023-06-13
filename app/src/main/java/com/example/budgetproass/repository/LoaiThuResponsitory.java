package com.example.budgetproass.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetproass.dao.AppDatabase;
import com.example.budgetproass.dao.LoaiThuDao;
import com.example.budgetproass.entity.LoaiThu;

import java.util.List;

public class LoaiThuResponsitory {
    private LoaiThuDao mLoaiThuDao;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public LoaiThuResponsitory(Application application){
        this.mLoaiThuDao = AppDatabase.getDatabase(application).loaiThuDao();
        mAllLoaiThu = mLoaiThuDao.findAll();
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }

    public void insert(LoaiThu loaiThu){
        new InsertAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    public void delete(LoaiThu loaiThu){
        new DeleteAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    public void update(LoaiThu loaiThu){
        new UpdateAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    class InsertAsyncTask extends AsyncTask<LoaiThu, Void, Void>{
        private LoaiThuDao mLoaiThuDao;
        public InsertAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.insert(loaiThus[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<LoaiThu, Void, Void>{
        private LoaiThuDao mLoaiThuDao;
        public DeleteAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.delete(loaiThus[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<LoaiThu, Void, Void>{
        private LoaiThuDao mLoaiThuDao;
        public UpdateAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.update(loaiThus[0]);
            return null;
        }
    }
}
