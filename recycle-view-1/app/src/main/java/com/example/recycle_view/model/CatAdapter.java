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
    private Context context;

    public CatAdapter(List<Cat> cats, Context context) {
        this.cats = cats;
        this.context = context;
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), cat.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.cats != null) {
            return this.cats.size();
        }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv;
        private CardView cardView;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.initView(itemView);
        }

        private void initView(@NonNull View view) {
            this.img = view.findViewById(R.id.imgId);
            this.tv = view.findViewById(R.id.nameId);
            this.cardView = view.findViewById(R.id.cview);
        }
    }
}
