package com.example.test1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.CatViewHolder>{

    private Context context;
    private List<Tour> cats;
    private List<Tour> backupCats;
    private CatItemListener catItemListener;

    public TourAdapter(Context context) {
        this.context = context;
        if (cats == null) {
            this.cats = new ArrayList<>();
            this.backupCats = new ArrayList<>();
        }
    }

    public TourAdapter(Context context, List<Tour> cats) {
        this.context = context;
        this.cats = cats;
    }

    public void setClickListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    public List<Tour> getCats() {
        return this.cats;
    }

    public List<Tour> getBackupCats() {
        return this.backupCats;
    }

//    search
    public void filterCats(List<Tour> filteredCats) {
        this.cats = filteredCats;
        notifyDataSetChanged();
    }

//    get to update
    public Tour getCat(int position) {
        return this.cats.get(position);
    }

//    select view
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.item_work, parent, false);
        return new CatViewHolder(itemView);
    }

//    show data theo view
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Tour cat = this.cats.get(position);

        if (cat == null) return;

        holder.imgIV.setImageResource(cat.getImg());
        holder.nameTV.setText(cat.getName());
        holder.descriptionTV.setText(cat.getDescription());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac muon xoa " + cat.getName() + " khong?");
                builder.setIcon(R.drawable.maybay);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cats.remove(holder.getAdapterPosition());
                        backupCats.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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

    public void add(Tour cat) {
        this.cats.add(cat);
        this.backupCats.add(cat);
        notifyDataSetChanged();
    }

    public void update(int position, Tour cat) {
        this.cats.set(position, cat);
        this.backupCats.set(position, cat);
        notifyDataSetChanged(); // refreshing the RecycleView
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView catCV;
        private ImageView imgIV;
        private TextView nameTV, descriptionTV;
        private Button deleteBtn;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);

            itemView.setOnClickListener(this);
        }

        private void initView(View view) {
            this.catCV = view.findViewById(R.id.catCardViewId);
            this.imgIV = view.findViewById(R.id.imgId);
            this.nameTV = view.findViewById(R.id.nameId);
            this.descriptionTV = view.findViewById(R.id.descId);
            this.deleteBtn = view.findViewById(R.id.deleteBtnId);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener != null) {
                catItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }
}
