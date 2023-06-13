package com.example.budgetproass.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.budgetproass.R;
import com.example.budgetproass.adapter.ThongKeLoaiChiRecyclerViewAdapter;
import com.example.budgetproass.adapter.ThongKeLoaiThuRecyclerViewAdapter;
import com.example.budgetproass.entity.ThongKeLoaiChi;
import com.example.budgetproass.entity.ThongKeLoaiThu;

import java.util.List;


public class ThongKeFragment extends Fragment {

    private ThongKeViewModel mThongKeViewModel;
    private EditText mEtTongThu,mEtTongchi;
    private RecyclerView rvThongKeLoaiThu,rvThongKeTongChi;

    private ThongKeLoaiThuRecyclerViewAdapter mThongKeLoaiThurAdapter;
    private ThongKeLoaiChiRecyclerViewAdapter mThongKeLoaiChiAdapter;



    public ThongKeFragment() {

    }


    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thong_ke,container,false);

        mEtTongThu = view.findViewById(R.id.etTongThu);
        mEtTongchi = view.findViewById(R.id.etTongChi);

        rvThongKeLoaiThu = view.findViewById(R.id.rvThongKeLoaiThu);
        rvThongKeTongChi = view.findViewById(R.id.rvThongKeTongChi);

        mThongKeViewModel= new ViewModelProvider(this).get(ThongKeViewModel.class);

        mThongKeLoaiThurAdapter = new ThongKeLoaiThuRecyclerViewAdapter(getActivity());
        mThongKeLoaiChiAdapter = new ThongKeLoaiChiRecyclerViewAdapter(getActivity());

        rvThongKeLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiThu.setAdapter(mThongKeLoaiThurAdapter);

        rvThongKeTongChi.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeTongChi.setAdapter(mThongKeLoaiChiAdapter);

        mThongKeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongThu.setText(""+tong);
            }
        });
        mThongKeViewModel.getTongChi().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongchi.setText(""+tong);
            }
        });
        mThongKeViewModel.getThongKeLoaiThus().observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                mThongKeLoaiThurAdapter.setList(thongKeLoaiThus);
            }
        });
        mThongKeViewModel.getThongKeLoaiChis().observe(getActivity(), new Observer<List<ThongKeLoaiChi>>() {
            @Override
            public void onChanged(List<ThongKeLoaiChi> thongKeLoaiChis) {
                mThongKeLoaiChiAdapter.setList(thongKeLoaiChis);
            }
        });

        return view;
    }
}