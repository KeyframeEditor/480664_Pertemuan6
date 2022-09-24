package com.example.tugasmasnanang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnSubmit;
    EditText teksTanggal;
    EditText teksWaktu;
    String spinnerText;
    EditText textAlasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Begin Rule++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define elements
        btnSubmit = findViewById(R.id.btnSubmit);
        teksTanggal = findViewById(R.id.teksTanggal);
        teksWaktu = findViewById(R.id.teksWaktu);
        Spinner spinnerPresensi = findViewById(R.id.spinnerPresensi);
        textAlasan = findViewById(R.id.textFieldAlasan);


        //Spinner Rules
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        spinnerPresensi.setAdapter(adapter);
        if (spinnerPresensi != null){
            spinnerPresensi.setOnItemSelectedListener(this);
        }

//        Submit Button Rules
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

//        Edit Text Rules
        teksTanggal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    teksTanggal.setEnabled(false);
                    DialogFragment dateFragment = new DatePickerFragment();
                    dateFragment.show(getSupportFragmentManager(), "date-picker");
                }
                else{
                    teksTanggal.setEnabled(true);
                }
            }
        });

        teksWaktu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    teksWaktu.setEnabled(false);
                    DialogFragment dateFragment = new TimePickerFragment();
                    dateFragment.show(getSupportFragmentManager(), "time-picker");
                }
                else{
                    teksWaktu.setEnabled(true);
                }

            }
        });
//        End Rule++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    //    Alert Dialog Rule
    public void showAlertDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Konfirmasi");
        alertBuilder.setMessage("Apakah kamu yakin data yang akan kamu kirim sudah sesuai?");

        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Absen Berhasil", Toast.LENGTH_SHORT).show();
                teksTanggal.setText("");
                teksWaktu.setText("");
                textAlasan.setText("");
                Spinner spinner = findViewById(R.id.spinnerPresensi);
                spinner.setSelection(0);
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        alertBuilder.show();
    }

    //    Date Fragment Rule
    public void processDatePickerResult(int day, int month, int year) {
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month + 1);
        String year_string = Integer.toString(year);

        String dateMessage = day_string + "/" + month_string + "/" + year_string;
        teksTanggal.setText(dateMessage);
    }

    //    Time Fragment Rule
    public void processTimePickerResult(int minute, int hour) {
        String minute_string = Integer.toString(minute);
        String hour_string = Integer.toString(hour);

        String dateMessage = minute_string + ":" + hour_string;
        teksWaktu.setText(dateMessage);
    }

//    spinner rules
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerText = adapterView.getItemAtPosition(i).toString();
        showSpinnerText();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showSpinnerText(){
        if (!Objects.equals(spinnerText, "Hadir Tepat Waktu")){
            textAlasan.setVisibility(View.VISIBLE);
        }
        else{
            textAlasan.setVisibility(View.INVISIBLE);
        }
    }
}