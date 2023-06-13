package com.example.budgetproass.ui.chi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budgetproass.R;
import com.example.budgetproass.adapter.ItemClickListener;
import com.example.budgetproass.adapter.ChiRecyclerviewAdapter;
import com.example.budgetproass.dialogChi.ChiDeleteDialog;
import com.example.budgetproass.dialogChi.ChiDetailDialog;
import com.example.budgetproass.dialogChi.ChiUpdateDialog;
import com.example.budgetproass.entity.Chi;


import java.util.List;

public class KhoanChiFragment extends Fragment {

    private KhoanChiViewModel mViewModel;
    private RecyclerView mRv;
    private ChiRecyclerviewAdapter mAdapter;

    public static KhoanChiFragment newInstance() {
        return new KhoanChiFragment();
    }

    public KhoanChiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView3);
        mAdapter = new ChiRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanChiFragment currentFragment = this;
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi Chi = mAdapter.getItem(position);
                ChiDetailDialog dialog = new ChiDetailDialog(getActivity(),currentFragment,Chi);
                dialog.show();
            }
        });
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi Chi = mAdapter.getItem(position);
                ChiUpdateDialog dialog = new ChiUpdateDialog(getActivity(),currentFragment,Chi);
                dialog.show();
            }
        });
        mAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi Chi = mAdapter.getItem(position);
                ChiDeleteDialog dialog = new ChiDeleteDialog(getActivity(),currentFragment,Chi);
                dialog.show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_chi_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanChiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> Chis) {
                mAdapter.setList(Chis);
            }
        });
    }

}