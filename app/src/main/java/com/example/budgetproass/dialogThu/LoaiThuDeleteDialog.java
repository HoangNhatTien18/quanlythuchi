package com.example.budgetproass.dialogThu;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.ui.thu.LoaiThuFragment;
import com.example.budgetproass.ui.thu.LoaiThuViewModel;

public class LoaiThuDeleteDialog {
    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId,tvNamee;


    public LoaiThuDeleteDialog(final Context context, LoaiThuFragment fragment, LoaiThu loaiThu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_delete_loai_thu,null);
        tvId = view.findViewById(R.id.tvId);
        tvNamee = view.findViewById(R.id.tvNamee);
        tvId.setText(""+loaiThu.lid);
        tvNamee.setText(loaiThu.ten);

        AlertDialog.Builder builder= new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.delete(loaiThu);
                        Toast.makeText(context, "Loại thu đã được xoá", Toast.LENGTH_SHORT).show();
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
