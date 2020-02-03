package com.luisa.smartnatal.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

import android.os.Bundle
import android.util.Log
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

        btnsubmit.setOnClickListener {
            age = etAge.text!!.toString()
            blood_group = etBloodgrp.text!!.toString()
            hemoglobin_levels = etHBLevels.text!!.toString()
            hiv_status = etHIVTest.text!!.toString()
            id_number = EtIDnumber.text!!.toString()
            name = etmothersname.text!!.toString()
            rhesus = etResus.text!!.toString()


         val userListener =   object : ValueEventListener{
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            //getting user values
                            val user = dataSnapshot.getValue(User::class.java)
                            if (user==null) {
                                Toast.makeText(baseContext,
                                        "Error: could not fetch user.",
                                        Toast.LENGTH_SHORT).show()
                            } else{

                                val userID = "LwXol6njcCWlcuL4l5QXDphUo612"

                                if (user!=null){
                                    //addPatients(name,age,id_number,blood_group,hemoglobin_levels,rhesus,hiv_status,"", "LwXol6njcCWlcuL4l5QXDphUo612")
                                }
                                Log.e("ddd", "we are here")}


                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.w("Error", "getUser:onCancelled", databaseError.toException())

                        }

                    }

                    databaseReference.addValueEventListener(userListener)
                    this.userListener = userListener


        }


    }

    private fun addPatients( Name:String, Age:String, IDNumber:String, Bloodgrp:String, Hemaglobin:String,
                             Rhesus:String, HIVStatus:String,CardNum:String,userID:String) {


        val key = databaseReference.child("posts").push().key
        if (key == null) {
            Log.w("Error", "Couldn't get push key for posts")
            return
        }
        val cardnum = "SNT" + Utils.RandomNum() + "/2020"

        val patients = Patients("Loise", "258u","23423432","haimoA","A+",
                "Rhesus","Neg(-)",cardnum)


        databaseReference.child(userID).setValue(patients)

        intent = Intent(applicationContext,HomeActivity::class.java)
        startActivity(intent)




    }
}
