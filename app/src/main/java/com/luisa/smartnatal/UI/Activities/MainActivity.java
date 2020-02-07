package com.luisa.smartnatal.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luisa.smartnatal.NFC.NFCReadFragment;
import com.luisa.smartnatal.NFC.callback.Listener;
import com.luisa.smartnatal.R;

public class MainActivity extends AppCompatActivity implements Listener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private FloatingActionButton mBtWrite;
    private Button mBtRead;
    private NFCReadFragment mNfcReadFragment;
    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    //Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsersDatabaseReference;
    private DatabaseReference mUserReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private NfcAdapter mNfcAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.activity_main);


        //Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");
        initViews();
        initNFC();
    }


    private void initViews() {

        mBtWrite =  findViewById(R.id.btnwrite);
        mBtRead =   findViewById(R.id.btnread);


        //adding a new patiend
        mBtWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddPatientActivity.class));
            }
        });

        mBtRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReadFragment();
            }
        });
    }

    private void showReadFragment() {

        mNfcReadFragment = (NFCReadFragment) getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);

        if (mNfcReadFragment == null) {

            mNfcReadFragment = NFCReadFragment.newInstance();
        }
        mNfcReadFragment.show(getFragmentManager(),NFCReadFragment.TAG);

    }


    private void initNFC() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

    }

    @Override
    public void onDialogDisplayed() {
        isDialogDisplayed  = true;
    }

    @Override
    public void onDialogDismissed() {
        isDialogDisplayed = false;
        isWrite = false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected,tagDetected,ndefDetected};

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if(mNfcAdapter!= null)
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mNfcAdapter!= null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Log.d(TAG, "wwwwww: "+">>>main");


        Log.d(TAG, "onNewIntent: "+intent.getAction());

        if(tag != null) {
            Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
            Ndef ndef = Ndef.get(tag);

            if (isDialogDisplayed) {



                    mNfcReadFragment = (NFCReadFragment)getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);

            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Log.e("user is Registerd","Logged In");
        } else {

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
    }




}

