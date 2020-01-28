package com.luisa.smartnatal.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.luisa.smartnatal.Data.Patients
import com.luisa.smartnatal.R

import java.util.HashMap

class AddPatientActivity : AppCompatActivity() {
    private lateinit var age: String
    private lateinit var blood_group: String
    private lateinit var hemoglobin_levels: String
    private lateinit var hiv_status: String
    private lateinit var id_number: String
    private lateinit var name: String
    private lateinit var rhesus: String
    private lateinit var EtIDnumber: AppCompatEditText
    private lateinit var etmothersname: AppCompatEditText
    private lateinit var etHBLevels: AppCompatEditText
    private lateinit var etBloodgrp: AppCompatEditText
    private lateinit var etAge: AppCompatEditText
    private lateinit var etHIVTest: AppCompatEditText
    private lateinit var etResus: AppCompatEditText
    private lateinit var btnsubmit: AppCompatButton
    //firebase database
    //val is immutable whele var can be assigned multiple times
    private lateinit var mDatabase: DatabaseReference
        private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mFirebaseDatabase = mFirebaseInstance.getReference("patients")



        btnsubmit = findViewById(R.id.cirRegisterButton)
        etAge = findViewById(R.id.etAge)
        etBloodgrp = findViewById(R.id.etBloodgrp)
        etHBLevels = findViewById(R.id.etHBLevels)
        etHIVTest = findViewById(R.id.etHIVTest)
        etmothersname = findViewById(R.id.etmothersname)
        etResus = findViewById(R.id.etResus)
        EtIDnumber = findViewById(R.id.EtIDnumber)

        btnsubmit.setOnClickListener {
            age = etAge.text!!.toString()
            blood_group = etBloodgrp.text!!.toString()
            hemoglobin_levels = etHBLevels.text!!.toString()
            hiv_status = etHIVTest.text!!.toString()
            id_number = EtIDnumber.text!!.toString()
            name = etmothersname.text!!.toString()
            rhesus = etResus.text!!.toString()

               addPatients(name,age)
            Log.e("ddd", "we are here")
        }


    }

    private fun addPatients( Name: String, Age: String) {
        val patients = Patients("Loise", "25")
        mFirebaseDatabase.child("2").setValue(patients)

        intent = Intent(applicationContext,HomeActivity::class.java)
        startActivity(intent)




    }
}
