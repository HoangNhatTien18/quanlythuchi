package com.example.budgetproass.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetproass.dao.AppDatabase;
import com.example.budgetproass.dao.LoaiThuDao;
import com.example.budgetproass.dao.ThuDao;
import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.entity.ThongKeLoaiThu;
import com.example.budgetproass.entity.Thu;

import java.util.List;

public class ThuResponsitory {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;

    public ThuResponsitory(Application application){
        this.mThuDao = AppDatabase.getDatabase(application).thuDao();
        mAllThu = mThuDao.findAll();
    }




    public LiveData<List<Thu>> getAllThu(){
        return mAllThu;
    }


    public LiveData<Float> sumTongThu(){
        return mThuDao.sumTongThu();
    }
    public LiveData<List<ThongKeLoaiThu>> sumByLoaiThu(){
        return mThuDao.sumByLoaiThu();
    }




    public void insert(Thu thu){
        new InsertAsyncTask(mThuDao).execute(thu);
    }
    public void delete(Thu thu){
        new DeleteAsyncTask(mThuDao).execute(thu);
    }
    public void update(Thu thu){
        new UpdateAsyncTask(mThuDao).execute(thu);
    }




    class InsertAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public InsertAsyncTask(ThuDao thuDao) {
            this.mThuDao = thuDao;
        }

        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.insert(Thus[0]);
            return null;
        }
    }



    class DeleteAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public DeleteAsyncTask(ThuDao ThuDao) {
            this.mThuDao = ThuDao;
        }

        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.delete(Thus[0]);
            return null;
        }
    }



    class UpdateAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public UpdateAsyncTask(ThuDao ThuDao) {
            this.mThuDao = ThuDao;
        }

        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.update(Thus[0]);
            return null;
        }
    }
}
