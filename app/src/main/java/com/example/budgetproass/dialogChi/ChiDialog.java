package com.example.budgetproass.dialogChi;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;

import com.example.budgetproass.R;

import com.example.budgetproass.adapter.ChiSpinnerAdapter;
import com.example.budgetproass.entity.LoaiChi;
import com.example.budgetproass.entity.Chi;

import com.example.budgetproass.ui.chi.KhoanChiFragment;
import com.example.budgetproass.ui.chi.KhoanChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ChiDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode;
    private ChiSpinnerAdapter mAdapter;



    public ChiDialog(final Context context, KhoanChiFragment fragment, Chi ...Chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chi,null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        spType = view.findViewById(R.id.spType);
        etNote = view.findViewById(R.id.etNote);
        mAdapter = new ChiSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
        spType.setAdapter(mAdapter);


        if (Chi != null && Chi.length>0){
            etId.setText(""+Chi[0].tid);
            etName.setText(Chi[0].ten);
            etAmount.setText(""+Chi[0].sotien);
            etNote.setText(Chi[0].ghichu);
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
                        Chi lt = new Chi();
                        lt.ten = etName.getText().toString();
                        lt.sotien = Float.parseFloat(etAmount.getText().toString());
                        lt.ghichu = etNote.getText().toString();
                        lt.ltid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                        if (mEditMode){
                            lt.tid= Integer.parseInt(etId.getText().toString());
                        }else{
                            mViewModel.insert(lt);
                            Toast.makeText(context, "Khoản Chi đã được lưu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }

}
