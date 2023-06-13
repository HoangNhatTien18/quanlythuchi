package com.example.budgetproass.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetproass.entity.LoaiChi;
import com.example.budgetproass.entity.LoaiThu;

import java.util.List;


@Dao
public interface LoaiChiDao {
    @Query("SELECT * FROM loaichi")
    LiveData<List<LoaiChi>> findAll();

    @Insert
    void insert(LoaiChi loaichi);

    @Update
    void update(LoaiChi loaichi);
    @Delete
    void delete(LoaiChi loaichi);

}
