package com.example.budgetproass.dialogThu;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.Thu;
import com.example.budgetproass.ui.thu.KhoanThuFragment;
import com.example.budgetproass.ui.thu.KhoanThuViewModel;

public class ThuDetailDialog {
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId,tvNamee,tvAmount,tvNote;


    public ThuDetailDialog(final Context context, KhoanThuFragment fragment, Thu thu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_thu,null);
        tvId = view.findViewById(R.id.tvId);
        tvNamee = view.findViewById(R.id.tvNamee);
        tvAmount = view.findViewById(R.id.tvAmount);
        tvNote = view.findViewById(R.id.tvNote);
        tvId.setText(""+thu.ltid);
        tvAmount.setText(""+thu.sotien);
        tvNote.setText(thu.ghichu);
        tvNamee.setText(thu.ten);



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
