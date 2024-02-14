package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class DataTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etime, edate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_time);
        initView();
        etime.setOnClickListener(this);
        edate.setOnClickListener(this);

    }

    //   -------------------- menu------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.mFile:
//                Toast.makeText(this, "Selected File",
//                        Toast.LENGTH_LONG).show();
//                break;
//            case R.id.mExit:
//                System.exit(0);
//                break;
//            case R.id.mEmail:
//                Toast.makeText(this, "Seelected Email",
//                        Toast.LENGTH_LONG).show();
//                break;
//            case R.id.mPhone:
//                Toast.makeText(this, "Selected phone",
//                        Toast.LENGTH_LONG).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //    ------------------end menu----------------------
    private void initView() {
        etime = findViewById(R.id.editTextTime);
        edate = findViewById(R.id.editTextDate);
    }

    @Override
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (view == etime) {
            TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {
                    etime.setText(h + ":" + m);

                }
            }, hh, mm, true);
            tpd.show();
        } else if (view == edate) {
            DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    edate.setText(d + "/" + (m + 1) + "/" + y);
                }
            }, year, month, day);
            dpd.show();
        }
    }
}