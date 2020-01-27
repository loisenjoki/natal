package com.luisa.smartnatal.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luisa.smartnatal.Data.Patients;
import com.luisa.smartnatal.R;

import java.util.HashMap;
import java.util.Map;

public class AddPatient extends AppCompatActivity {
    String age;
    String blood_group;
    String hemoglobin_levels;
    String hiv_status;
    String id_number;
    String name;
    String rhesus;
    AppCompatEditText EtIDnumber,etmothersname,etHBLevels,etBloodgrp,etAge,etHIVTest,etResus;
    AppCompatButton btnsubmit;
    //firebase database
    private DatabaseReference mDatabase;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("wPJfhboFaDdNZFQOhnkU");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnsubmit = findViewById(R.id.cirRegisterButton);
        etAge = findViewById(R.id.etAge);
        etBloodgrp = findViewById(R.id.etBloodgrp);
        etHBLevels = findViewById(R.id.etHBLevels);
        etHIVTest = findViewById(R.id.etHIVTest);
        etmothersname = findViewById(R.id.etmothersname);
        etResus = findViewById(R.id.etResus);
        EtIDnumber = findViewById(R.id.EtIDnumber);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = etAge.getText().toString();
                blood_group = etBloodgrp.getText().toString();
                hemoglobin_levels = etHBLevels.getText().toString();
                hiv_status = etHIVTest.getText().toString();
                id_number = EtIDnumber.getText().toString();
                name = etmothersname.getText().toString();
                rhesus = etResus.getText().toString();

                addPatients(id_number,name,age);
                Log.e("ddd","we are here");

            }
        });


    }
    private void addPatients(String UserId, String Name, String Age) {
        Patients patients = new Patients(Name, Age);



        ref = database.getReference().child(name);
        ref.setValue("loise");
    }
}
