package com.luisa.smartnatal.NFC;

import android.app.DialogFragment;
import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luisa.smartnatal.Data.Patients;
import com.luisa.smartnatal.UI.Activities.MainActivity;
import com.luisa.smartnatal.NFC.callback.Listener;
import com.luisa.smartnatal.R;

import java.io.IOException;

public class NFCReadFragment extends DialogFragment {

    public static final String TAG = NFCReadFragment.class.getSimpleName();
    private FirebaseDatabase database;
    private DatabaseReference refdb;

    private String CardNumber;

    public static NFCReadFragment newInstance() {

        return new NFCReadFragment();
    }

    private TextView mTvMessage;
    private Listener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_read,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mTvMessage =  view.findViewById(R.id.tv_message);
        database = FirebaseDatabase.getInstance();
        refdb = database.getReference();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MainActivity)context;
        mListener.onDialogDisplayed();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }

    public void onNfcDetected(Ndef ndef){

        readFromNFC(ndef);
    }

    private void readFromNFC(Ndef ndef) {

        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            CardNumber = new String(ndefMessage.getRecords()[0].getPayload());
            Log.d(TAG, "readFromNFC: "+CardNumber);
            mTvMessage.setText(CardNumber);
            ndef.close();

            getPatient(CardNumber);

        } catch (IOException | FormatException e) {
            e.printStackTrace();

        }
    }

    private void getPatient(final String CardNumber) {

        refdb.child("patients").orderByChild("card_num")
                .equalTo(CardNumber)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("values", dataSnapshot.toString());

              Patients patients = dataSnapshot.getValue(Patients.class);

              //  String Height = ;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error",databaseError.toString());

            }
        });



    }
}
