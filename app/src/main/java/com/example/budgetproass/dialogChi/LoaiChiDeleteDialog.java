package com.example.budgetproass.dialogChi;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.LoaiChi;

import com.example.budgetproass.ui.chi.LoaiChiFragment;
import com.example.budgetproass.ui.chi.LoaiChiViewModel;

public class LoaiChiDeleteDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId,tvNamee;


    public LoaiChiDeleteDialog(final Context context, LoaiChiFragment fragment, LoaiChi loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_delete_loai_chi,null);
        tvId = view.findViewById(R.id.tvId);
        tvNamee = view.findViewById(R.id.tvNamee);
        tvId.setText(""+loaiChi.lid);
        tvNamee.setText(loaiChi.ten);

        AlertDialog.Builder builder= new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.delete(loaiChi);
                        Toast.makeText(context, "Loại Chi đã được xoá", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                });

        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }

}
