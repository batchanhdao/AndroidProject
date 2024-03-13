package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test1.model.SpinnerAdapter;
import com.example.test1.model.Tour;
import com.example.test1.model.TourAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TourAdapter.CatItemListener, SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
        this.setOnClick();
        this.setOnQuery();
    }

    private Spinner spinner;
    private int[] images = {R.drawable.maybay, R.drawable.oto, R.drawable.xemay};
    private RecyclerView recyclerView;
    private TourAdapter catAdapter;
    private EditText nameET, descriptionET;
    private Button addBtn, updateBtn;
    private int selectedItemIndex;
    private SearchView searchView;


    // click search
    private void setOnQuery() {
        this.searchView.setOnQueryTextListener(this);
    }


//    click add/update
    private void setOnClick() {
        this.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour cat = new Tour();
//                get data
                int i = spinner.getSelectedItemPosition();
                String nameText = nameET.getText().toString();
                String descText = descriptionET.getText().toString();

                try {
//                    check and save data
                    int img = images[i];
                    cat.setImg(img);
                    cat.setName(nameText);
                    cat.setDescription(descText);

                    catAdapter.add(cat);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour cat = new Tour();
                int i = spinner.getSelectedItemPosition();
                String nameText = nameET.getText().toString();
                String descText = descriptionET.getText().toString();

                try {
                    int img = images[i];

                    cat.setImg(img);
                    cat.setName(nameText);
                    cat.setDescription(descText);

                    catAdapter.update(selectedItemIndex, cat);
                    addBtn.setEnabled(true);
                    updateBtn.setEnabled(false);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    ánh xạ view
    private void initView() {
        this.spinner = findViewById(R.id.imgSpinnerId);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        this.spinner.setAdapter(spinnerAdapter);
        this.nameET = findViewById(R.id.nameId);
        this.descriptionET = findViewById(R.id.descriptionId);
        this.recyclerView = findViewById(R.id.recycleViewId);
        this.addBtn = findViewById(R.id.addBtn);
        this.updateBtn = findViewById(R.id.updateBtn);
        this.updateBtn.setEnabled(false);
        this.catAdapter = new TourAdapter(this);
//        event click list data
        this.catAdapter.setClickListener(this);
        this.searchView = findViewById(R.id.seachId);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
//        call method show data from TourAdapter
        this.recyclerView.setAdapter(this.catAdapter);
    }

//    click 1 object
    @Override
    public void onItemClick(View view, int position) {
        this.addBtn.setEnabled(false);
        this.updateBtn.setEnabled(true);
        this.selectedItemIndex = position;
        Tour cat = catAdapter.getCat(position);

        this.reRenderForm(cat);
    }

    private void reRenderForm(Tour cat) {
        this.nameET.setText(cat.getName());
        this.descriptionET.setText(cat.getDescription());

        int img = cat.getImg();
        int indexOfImgInSpinner = 0;

        for (int i = 0; i < images.length; i++) {
            if (img == images[i]) {
                indexOfImgInSpinner = i;
            }
        }
        spinner.setSelection(indexOfImgInSpinner, true);
    }

//    logic search
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        filter(query);
        return false;
    }

    private void filter(String query) {
        List<Tour> filteredCats = new ArrayList<>();

        for (Tour cat : catAdapter.getBackupCats()) {
            if (cat.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCats.add(cat);
            }
        }

        if (filteredCats.isEmpty()) {
            Toast.makeText(this, "No data matched", Toast.LENGTH_SHORT).show();
        }
        this.catAdapter.filterCats(filteredCats);
    }

}