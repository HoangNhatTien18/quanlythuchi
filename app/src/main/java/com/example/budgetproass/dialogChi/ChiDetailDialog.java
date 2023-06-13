package com.example.budgetproass.dialogChi;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.Chi;
import com.example.budgetproass.ui.chi.KhoanChiFragment;
import com.example.budgetproass.ui.chi.KhoanChiViewModel;

public class ChiDetailDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId,tvNamee,tvAmount,tvNote;


    public ChiDetailDialog(final Context context, KhoanChiFragment fragment, Chi Chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_chi,null);
        tvId = view.findViewById(R.id.tvId);
        tvNamee = view.findViewById(R.id.tvNamee);
        tvAmount = view.findViewById(R.id.tvAmount);
        tvNote = view.findViewById(R.id.tvNote);
        tvId.setText(""+Chi.ltid);
        tvAmount.setText(""+Chi.sotien);
        tvNote.setText(Chi.ghichu);
        tvNamee.setText(Chi.ten);



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
