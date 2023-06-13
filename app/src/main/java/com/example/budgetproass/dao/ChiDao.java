package com.example.budgetproass.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetproass.entity.Chi;
import com.example.budgetproass.entity.ThongKeLoaiChi;
import com.example.budgetproass.entity.ThongKeLoaiThu;

import java.util.List;


@Dao
public interface ChiDao {
    @Query("SELECT * FROM chi")
    LiveData<List<Chi>> findAll();


    @Query("SELECT sum(sotien) FROM chi")
    LiveData<Float> sumTongChi();

    @Query("SELECT b.lid, b.ten ,sum(a.sotien) as tong FROM chi a INNER JOIN loaichi b on a.ltid = b.lid"+
            " GROUP BY b.lid, b.ten")
    LiveData<List<ThongKeLoaiChi>> sumByLoaiChi();

    @Insert
    void insert(Chi chi);

    @Update
    void update(Chi chi);
    @Delete
    void delete(Chi chi);

}
