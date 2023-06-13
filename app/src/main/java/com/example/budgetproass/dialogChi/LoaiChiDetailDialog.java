package com.example.budgetproass.dialogChi;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.LoaiChi;
import com.example.budgetproass.ui.chi.LoaiChiFragment;
import com.example.budgetproass.ui.chi.LoaiChiViewModel;

public class LoaiChiDetailDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId,tvNamee;


    public LoaiChiDetailDialog(final Context context, LoaiChiFragment fragment, LoaiChi loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_loai_chi,null);
        tvId = view.findViewById(R.id.tvId);
        tvNamee = view.findViewById(R.id.tvNamee);
        tvId.setText(""+loaiChi.lid);
        tvNamee.setText(loaiChi.ten);

        AlertDialog.Builder builder= new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
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
