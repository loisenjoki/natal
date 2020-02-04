package com.luisa.smartnatal.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.*

import com.luisa.smartnatal.Data.Patients
import com.luisa.smartnatal.Data.User
import com.luisa.smartnatal.R
import com.luisa.smartnatal.Utils.Utils

class AddPatientActivity : AppCompatActivity() {
    private lateinit var age: String
    private lateinit var blood_group: String
    private lateinit var hemoglobin_levels: String
    private lateinit var hiv_status: String
    private lateinit var id_number: String
    private lateinit var name: String
    private lateinit var phoneNum: String
    private lateinit var residence: String
    private lateinit var height: String
    private lateinit var weight: String
    private lateinit var rhesus: String
    //Widgets
    private lateinit var bloodpressure: String
    private lateinit var etphoneNum: AppCompatEditText
    private lateinit var etresidence: AppCompatEditText
    private lateinit var etheight: AppCompatEditText
    private lateinit var etweight: AppCompatEditText
    private lateinit var etbloodpressure: AppCompatEditText
    private lateinit var EtIDnumber: AppCompatEditText
    private lateinit var etmothersname: AppCompatEditText
    private lateinit var etHBLevels: AppCompatEditText
    private lateinit var etBloodgrp: AppCompatEditText
    private lateinit var etAge: AppCompatEditText
    private lateinit var etHIVTest: AppCompatEditText
    private lateinit var etResus: AppCompatEditText
    private lateinit var btnsubmit: AppCompatButton
    private lateinit var btnback: ImageView
    //firebase database
    //val is immutable whele var can be assigned multiple times
    private lateinit var mDatabase: DatabaseReference
        private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var userListener: ValueEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("patients")
        btnsubmit = findViewById(R.id.cirRegisterButton)
        etAge = findViewById(R.id.etAge)
        etBloodgrp = findViewById(R.id.etBloodgrp)
        etHBLevels = findViewById(R.id.etHBLevels)
        etHIVTest = findViewById(R.id.etHIVTest)
        etmothersname = findViewById(R.id.etmothersname)
        etResus = findViewById(R.id.etResus)
        EtIDnumber = findViewById(R.id.EtIDnumber)
        etheight = findViewById(R.id.etheight)
        etphoneNum = findViewById(R.id.Etphone)
        etweight = findViewById(R.id.etweight)
        etbloodpressure = findViewById(R.id.etBloodPresure)
        etresidence = findViewById(R.id.location)

        btnsubmit.setOnClickListener {
            addPatient()
        }

        btnback = findViewById(R.id.backbtn)
        btnback.setOnClickListener {
             intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }



    private fun addPatient() {

        validateForm()





        SavePatientsData(name,age,id_number,blood_group,hemoglobin_levels,rhesus,hiv_status,"", "LwXol6llnjcCWlcuL4l5QXDphUo612")

    }




    private fun SavePatientsData( Name:String, Age:String, IDNumber:String, Bloodgrp:String, Hemaglobin:String,
                             Rhesus:String, HIVStatus:String,CardNum:String,userID:String) {


        val cardnum = "SNT" + Utils.RandomNum() + "/2020"

        val patients = Patients("Loise ULE", "258u44","23423432dd44","haimoB+","AB+",
                "Rhesus","Neg(-)",cardnum)


        databaseReference.child(userID).setValue(patients)

       /* intent = Intent(applicationContext,HomeActivity::class.java)
        startActivity(intent)*/




    }

    private fun validateForm(): Boolean {
        var valid = true

        age = etAge.text!!.toString()
        if (TextUtils.isEmpty(age)) {
            etAge.setError("Required.")
            valid = false
        } else {
            etAge.setError(null)
        }
        blood_group = etBloodgrp.text!!.toString()
        if (TextUtils.isEmpty(blood_group)) {
            etBloodgrp.setError("Required.")
            valid = false
        } else {
            etBloodgrp.setError(null)
        }
        hemoglobin_levels = etHBLevels.text!!.toString()
        if (TextUtils.isEmpty(hemoglobin_levels)) {
            etHBLevels.setError("Required.")
            valid = false
        } else {
            etHBLevels.setError(null)
        }
        hiv_status = etHIVTest.text!!.toString()
        if (TextUtils.isEmpty(hiv_status)) {
            etHIVTest.setError("Required.")
            valid = false
        } else {
            etHIVTest.setError(null)
        }
        id_number = EtIDnumber.text!!.toString()

        name = etmothersname.text!!.toString()
        if (TextUtils.isEmpty(name)) {
            etmothersname.setError("Required.")
            valid = false
        } else {
            etmothersname.setError(null)
        }

        weight = etweight.text!!.toString()
        if (TextUtils.isEmpty(weight)) {
            etweight.setError("Required.")
            valid = false
        } else {
            etweight.setError(null)
        }
        phoneNum = etphoneNum.text!!.toString()
        if (TextUtils.isEmpty(phoneNum)) {
            etphoneNum.setError("Required.")
            valid = false
        } else {
            etphoneNum.setError(null)
        }
        height = etheight.text!!.toString()
        if (TextUtils.isEmpty(height)) {
            etheight.setError("Required.")
            valid = false
        } else {
            etheight.setError(null)
        }
        bloodpressure = etbloodpressure.text!!.toString()
        if (TextUtils.isEmpty(bloodpressure)) {
            etbloodpressure.setError("Required.")
            valid = false
        } else {
            etbloodpressure.setError(null)
        }
        residence = etresidence.text!!.toString()
        if (TextUtils.isEmpty(residence)) {
            etresidence.setError("Required.")
            valid = false
        } else {
            etresidence.setError(null)
        }




        rhesus = etResus.text!!.toString()

        return valid
    }


}
