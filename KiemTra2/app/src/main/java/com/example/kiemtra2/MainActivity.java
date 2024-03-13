package com.example.kiemtra2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.kiemtra2.databinding.ActivityMainBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;
    private JobAdapter jobAdapter;

    private int position;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        jobAdapter = new JobAdapter(new ArrayList<>(), new ArrayList<>());
        binding.rvJob.setAdapter(jobAdapter);
        binding.btnAdd.setOnClickListener(v -> {
            addJob();
        });
        binding.btnDate.setOnClickListener(v -> {
            showDatePicker();
        });
        binding.btnUpdate.setOnClickListener(v -> {
            updateJob(position);
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchJob(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchJob(newText);
                return false;
            }
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            binding.date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    void addJob() {
        if (!validateFields()) {
            return;
        }
        try {
            String name = binding.name.getText().toString();
            String description = binding.description.getText().toString();
            Boolean isMale = binding.rbMale.isChecked();
            Long time = format.parse(binding.date.getText().toString()).getTime();
            Job job = new Job(name, description, isMale, time);
            jobAdapter.addJob(job);
        } catch (ParseException e) {
            Log.e("TAG", "addJob: ", e);
            e.printStackTrace();
        }

    }

    void updateJob(int position) {
        if (!validateFields()) {
            return;
        }
        try {
            String name = binding.name.getText().toString();
            String description = binding.description.getText().toString();
            Boolean isMale = binding.rbMale.isChecked();
            Long time = format.parse(binding.date.getText().toString()).getTime();
            Job job = new Job(name, description, isMale, time);
            jobAdapter.updateJob(job, position);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    void searchJob(String s) {
        jobAdapter.searchJob(s);
    }

    void setJobInputFromAdapter(Job job, int position) {
        this.position = position;
        binding.name.setText(job.getName());
        binding.description.setText(job.getDescription());
        binding.rbMale.setChecked(job.getMale());
        binding.rbFemale.setChecked(!job.getMale());
        binding.date.setText(format.format(job.getTime()));
    }

    private boolean validateFields() {
        String name = binding.name.getText().toString();
        String description = binding.description.getText().toString();
        String date = binding.date.getText().toString();
        if (name.isEmpty() || description.isEmpty() || date.isEmpty()) {
            return false;
        }
        return true;
    }

}