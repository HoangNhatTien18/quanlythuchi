package com.example.budgetproass.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budgetproass.entity.Chi;
import com.example.budgetproass.entity.LoaiChi;
import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.entity.Thu;

@Database(entities = { LoaiChi.class, Chi.class},version = 3)
public abstract class AppDatabase2 extends RoomDatabase {
    public abstract LoaiChiDao loaiChiDao();
    public abstract ChiDao chiDao();


    public static AppDatabase2 INSTANCE;
    private static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute();
        }
    };

    public static AppDatabase2 getDatabase(final Context context){
        if (INSTANCE ==null){
            synchronized (AppDatabase2.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase2.class,"personal_db1")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }
    public static class PopulateData extends AsyncTask<Void,Void,Void>{
        private LoaiChiDao loaiChiDao;
        private ChiDao chiDao;


        public PopulateData(AppDatabase2 db){
            loaiChiDao = db.loaiChiDao();
            chiDao = db.chiDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String[] chidaos = new String[]{"Luong","Thuong","Co phieu"};
            for(String it: chidaos){
                LoaiChi lt = new LoaiChi();
                lt.ten=it;
                loaiChiDao.insert(lt);
            }
            Chi chi = new Chi();
            chi.ten = "Lương tháng 1";
            chi.sotien=3000;
            chi.ltid=2;
            chi.ghichu="";
            chiDao.insert(chi);
            Log.d("Budgetpro","insert data");
            return null;

        }
    }
}
