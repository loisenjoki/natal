package com.luisa.smartnatal.UI.Activities

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
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
import com.luisa.smartnatal.NFC.NFCReadFragment
import com.luisa.smartnatal.NFC.NFCWriteFragment
import com.luisa.smartnatal.NFC.callback.Listener
import com.luisa.smartnatal.R
import com.luisa.smartnatal.Utils.AppUtils

class AddPatientActivity : AppCompatActivity() ,Listener {
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
    private lateinit var cardnum: String
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
    lateinit var patients: Patients
    //firebase database
    //val is immutable whele var can be assigned multiple times
    private lateinit var mDatabase: DatabaseReference
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var userListener: ValueEventListener? = null
    //NFC Declarations
    private lateinit var mNfcWriteFragment: NFCWriteFragment
    val TAG = NFCWriteFragment::class.java.simpleName
    private lateinit var mNfcAdapter : NfcAdapter
    private var isWrite = false
    private var isDialogDisplayed = false


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

         cardnum = "SNT" + AppUtils.RandomNum() + "/2020"

        //initNFC()
        mNfcWriteFragment  = NFCWriteFragment()


        btnsubmit.setOnClickListener {
            SavePatientsData()
            //showWriteFragment()
        }

        btnback = findViewById(R.id.backbtn)
        btnback.setOnClickListener {
             intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun SavePatientsData() {

      /* if (!AppUtils.validated(etmothersname, etAge,etBloodgrp,etHBLevels,etHIVTest,
                        etResus,etphoneNum,etresidence,etweight,etheight,etbloodpressure)) {
            return
        }*/
            if (::patients.isInitialized)

            patients = Patients(name, age,id_number,blood_group,hemoglobin_levels,hiv_status,
                    rhesus,cardnum,phoneNum,residence,weight,height,bloodpressure)
            //Log.d("USer", patients.toString())

            databaseReference.child(databaseReference.push().key!!).setValue(patients)

            intent = Intent(applicationContext,HomeActivity::class.java)
            startActivity(intent)



    }
    //initating NFC
    private fun initNFC() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

    }
    private fun showWriteFragment() {
        isWrite = true

        if (mNfcWriteFragment == null){
            mNfcWriteFragment = fragmentManager.findFragmentByTag(NFCWriteFragment.TAG) as NFCWriteFragment
            mNfcWriteFragment = NFCWriteFragment.newInstance()
        }
        mNfcWriteFragment.show(fragmentManager,NFCWriteFragment.TAG)
    }


    override fun onDialogDisplayed() {
        isDialogDisplayed = true


    }

    override fun onDialogDismissed() {
        isDialogDisplayed = false
        isWrite = false
    }
/*

    override fun onResume() {
        super.onResume()
        val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        val ndefDetected = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        val techDetected = IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)
        val nfcIntentFilter = arrayOf(techDetected, tagDetected, ndefDetected)
        //(Intent.FLAG_ACTIVITY_SINGLE_TOP
        val notifyIntent = Intent(this, AddPatientActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, notifyPendingIntent, nfcIntentFilter, null)

    }

     override fun onPause() {
        super.onPause()
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this)
    }

     override fun onNewIntent(intent: Intent) {
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

        Log.d(TAG, "onNewIntent: " + intent.action!!)

        if (tag != null) {
            Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show()
            val ndef = Ndef.get(tag)

            if (isDialogDisplayed) {

                if (isWrite) {

                    mNfcWriteFragment = fragmentManager.findFragmentByTag(NFCWriteFragment.TAG) as NFCWriteFragment
                    mNfcWriteFragment.onNfcDetected(ndef, cardnum)
                    Log.e("Card Number >>>>>>>>>>",cardnum)

                }
            }
        }
    }
*/


}
