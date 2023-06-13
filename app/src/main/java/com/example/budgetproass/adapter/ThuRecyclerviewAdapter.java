package com.example.budgetproass.adapter;

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
import com.example.budgetproass.entity.Thu;

import java.util.List;

public class ThuRecyclerviewAdapter extends RecyclerView.Adapter<ThuRecyclerviewAdapter.ThuViewHolder> {
    private LayoutInflater mlayoutInflater;
    private List<Thu> mList;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public static ItemClickListener itemDeleteClickListener;

    public ThuRecyclerviewAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ThuRecyclerviewAdapter.itemEditClickListener = itemEditClickListener;
    }
    public void setOnItemDeleteClickListener(ItemClickListener itemDeleteClickListener) {
        ThuRecyclerviewAdapter.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ThuRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recyclerview_thu_item, parent,false);

        return new ThuViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, int position) {
        if (mList !=null){
            holder.tvName.setText(mList.get(position).ten );
            holder.tvAmount.setText(""+mList.get(position).sotien+"Đồng");
            holder.position = position;
        }
    }



    @Override
    public int getItemCount() {
        if(mList ==null)
            return 0;
        return mList.size();
    }



    public Thu getItem(int position){
        if(mList == null || position>=mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Thu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }




    public static class ThuViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName,tvAmount;
        public ImageView ivEdit, ivView, ivDelete;
        public int position;
        public CardView cv;
        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tvAmount);
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
