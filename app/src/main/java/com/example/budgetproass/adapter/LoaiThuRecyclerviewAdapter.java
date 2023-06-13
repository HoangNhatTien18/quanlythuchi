package com.example.budgetproass.adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetproass.R;
import com.example.budgetproass.entity.LoaiThu;

import java.util.List;

public class LoaiThuRecyclerviewAdapter extends RecyclerView.Adapter<LoaiThuRecyclerviewAdapter.LoaiThuViewHolder> {
    private LayoutInflater mlayoutInflater;
    private List<LoaiThu> mList;


    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public static ItemClickListener itemDeleteClickListener;

    public LoaiThuRecyclerviewAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiThuRecyclerviewAdapter.itemEditClickListener = itemEditClickListener;
    }
    public void setOnItemDeleteClickListener(ItemClickListener itemDeleteClickListener) {
        LoaiThuRecyclerviewAdapter.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiThuRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recyclerview_loai_thu_item, parent,false);

        return new LoaiThuViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull LoaiThuViewHolder holder, int position) {
        if (mList !=null){
            holder.tvName.setText(mList.get(position).ten );
            holder.position = position;
        }
    }



    @Override
    public int getItemCount() {
        if(mList ==null)
            return 0;
        return mList.size();
    }



    public LoaiThu getItem(int position){
        if(mList == null || position>=mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<LoaiThu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }




    public static class LoaiThuViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView ivEdit, ivView, ivDelete;
        public int position;
        public CardView cv;
        public LoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            cv = (CardView) itemView;
            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemViewClickListener != null){
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemEditClickListener != null){
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemDeleteClickListener != null){
                        itemDeleteClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
