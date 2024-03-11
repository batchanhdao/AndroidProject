package com.example.recycle_view.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycle_view.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private List<Cat> cats;
    private CatItemListener catItemListener;

    public CatAdapter(List<Cat> cats) {
        this.cats = cats;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = this.cats.get(position);

        if (cat == null) return;

        holder.img.setImageResource(cat.getImg());
        holder.tv.setText(cat.getName());
    }

    @Override
    public int getItemCount() {
        if (this.cats != null) {
            return this.cats.size();
        }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tv;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);
        }

        private void initView(View view) {
            this.img = view.findViewById(R.id.imgId);
            this.tv = view.findViewById(R.id.nameId);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener != null) {
                catItemListener.onClickItem(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        public void onClickItem(View view, int position);
    }
}
