package com.example.budgetproass.dialogThu;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.ui.thu.LoaiThuFragment;
import com.example.budgetproass.ui.thu.LoaiThuViewModel;

public class LoaiThuDetailDialog {
    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId,tvNamee;


    public LoaiThuDetailDialog(final Context context, LoaiThuFragment fragment, LoaiThu loaiThu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_loai_thu,null);
        tvId = view.findViewById(R.id.tvId);
        tvNamee = view.findViewById(R.id.tvNamee);
        tvId.setText(""+loaiThu.lid);
        tvNamee.setText(loaiThu.ten);

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
