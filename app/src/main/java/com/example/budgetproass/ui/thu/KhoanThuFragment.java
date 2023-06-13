package com.example.budgetproass.ui.thu;

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
import com.example.budgetproass.adapter.ThuRecyclerviewAdapter;
import com.example.budgetproass.dialogThu.ThuDeleteDialog;
import com.example.budgetproass.dialogThu.ThuDetailDialog;
import com.example.budgetproass.dialogThu.ThuUpdateDialog;
import com.example.budgetproass.entity.Thu;

import java.util.List;

public class KhoanThuFragment extends Fragment {

    private KhoanThuViewModel mViewModel;
    private RecyclerView mRv;
    private ThuRecyclerviewAdapter mAdapter;

    public static KhoanThuFragment newInstance() {
        return new KhoanThuFragment();
    }

    public KhoanThuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView2);
        mAdapter = new ThuRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanThuFragment currentFragment = this;
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuDetailDialog dialog = new ThuDetailDialog(getActivity(),currentFragment,thu);
                dialog.show();
            }
        });
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuUpdateDialog dialog = new ThuUpdateDialog(getActivity(),currentFragment,thu);
                dialog.show();
            }
        });
        mAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuDeleteDialog dialog = new ThuDeleteDialog(getActivity(),currentFragment,thu);
                dialog.show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_thu_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanThuViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
            }
        });
    }

}