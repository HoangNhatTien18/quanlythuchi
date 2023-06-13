package com.example.budgetproass.dialogThu;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;

import com.example.budgetproass.R;
import com.example.budgetproass.adapter.ThuSpinnerAdapter;
import com.example.budgetproass.entity.LoaiThu;
import com.example.budgetproass.entity.Thu;
import com.example.budgetproass.ui.thu.KhoanThuFragment;
import com.example.budgetproass.ui.thu.KhoanThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ThuUpdateDialog {
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode;
    private ThuSpinnerAdapter mAdapter;



    public ThuUpdateDialog(final Context context, KhoanThuFragment fragment, Thu ...thu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_update_thu,null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        spType = view.findViewById(R.id.spType);
        etNote = view.findViewById(R.id.etNote);
        mAdapter = new ThuSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
        spType.setAdapter(mAdapter);


        if (thu != null && thu.length>0){
            etId.setText(""+thu[0].tid);
            etName.setText(thu[0].ten);
            etAmount.setText(""+thu[0].sotien);
            etNote.setText(thu[0].ghichu);
            mEditMode = true;
        }else{
            mEditMode= false;
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
                        Thu lt = new Thu();
                        lt.ten = etName.getText().toString();
                        lt.sotien = Float.parseFloat(etAmount.getText().toString());
                        lt.ghichu = etNote.getText().toString();
                        lt.ltid = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                        if (mEditMode){
                            lt.tid= Integer.parseInt(etId.getText().toString());
                            mViewModel.update(lt);
                            Toast.makeText(context, "Khoản thu đã được cập nhật", Toast.LENGTH_SHORT).show();
                        }else{
                            mViewModel.insert(lt);
                            Toast.makeText(context, "Khoản thu đã được thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }

}
