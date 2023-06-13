package com.example.budgetproass.dialogThu;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.ui.thu.LoaiThuFragment;
import com.example.budgetproass.ui.thu.LoaiThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiThuUpdateDialog {
    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId,etName;
    private boolean mEditMode;

    public LoaiThuUpdateDialog(final Context context, LoaiThuFragment fragment, LoaiThu ...loaiThu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_empty_loai_thu,null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        if (loaiThu != null && loaiThu.length>0){
            etId.setText(""+loaiThu[0].lid);
            etName.setText(loaiThu[0].ten);
            mEditMode = true;
        }else {
            mEditMode = false;
        }
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
                        LoaiThu lt = new LoaiThu();
                        lt.ten = etName.getText().toString();
                        if (mEditMode){
                            lt.lid=Integer.parseInt(etId.getText().toString());
                            mViewModel.update(lt);
                            Toast.makeText(context,"Loại thu đã được cập nhật",Toast.LENGTH_SHORT).show();
                        } else {
                            mViewModel.insert(lt);

                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }

}
