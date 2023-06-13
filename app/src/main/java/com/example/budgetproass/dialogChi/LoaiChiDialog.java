package com.example.budgetproass.dialogChi;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.LoaiChi;

import com.example.budgetproass.ui.chi.LoaiChiFragment;
import com.example.budgetproass.ui.chi.LoaiChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiChiDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId,etName;


    public LoaiChiDialog(final Context context, LoaiChiFragment fragment, LoaiChi ...loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loai_chi,null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        AlertDialog.Builder builder= new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiChi lt = new LoaiChi();
                        lt.ten = etName.getText().toString();
                        if (lt.ten.isEmpty()){
                            Toast.makeText(context, "Bạn chưa nhập Tên loại Chi", Toast.LENGTH_SHORT).show();
                        }else{
                            mViewModel.insert(lt);
                            Toast.makeText(context, "Loại Chi đã được lưu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }

}
