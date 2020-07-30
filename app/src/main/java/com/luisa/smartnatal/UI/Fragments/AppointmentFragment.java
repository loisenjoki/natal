package com.luisa.smartnatal.UI.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.threeten.bp.format.DateTimeFormatter;

import com.google.android.material.textfield.TextInputLayout;
import com.luisa.smartnatal.Data.Date;
import com.luisa.smartnatal.R;
import com.luisa.smartnatal.UI.Adaptors.AppointmentAdaptor;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class AppointmentFragment extends Fragment implements OnDateLongClickListener, OnDateSelectedListener, OnMonthChangedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");

    private MaterialCalendarView widget;
    private  TextView textView;
  private   RecyclerView recyclerView;
    List<Date> dateList;
   AppointmentAdaptor appointmentAdaptor;
    View view;
    private TextInputLayout etname, ettime;
    private  Button btnsubmint;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentFragment newInstance(String param1, String param2) {
        AppointmentFragment fragment = new AppointmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.appointment_fragment, container, false);
        widget  = view.findViewById(R.id.calendarView);
        textView = view.findViewById(R.id.textViewcalender);
        recyclerView = view.findViewById(R.id.recyclerview);
        dateList = new ArrayList<>();
        appointmentAdaptor = new AppointmentAdaptor(dateList);
        // Set layout manager to position the items
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(appointmentAdaptor);

        widget.setOnDateChangedListener(this);
        widget.setOnDateLongClickListener(this);
        widget.setOnMonthChangedListener( this);
        return view;
    }



    @Override
    public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date) {


        final String text = String.format("%s is available",FORMATTER.format(date.getDate()));
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();



    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
           //Toast.makeText(getActivity(),String.valueOf( date.getDay()), Toast.LENGTH_SHORT).show();
        textView.setText(selected ? FORMATTER.format(date.getDate()) : "No Selection");
        appointmentDialog();


    }
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }
    private void appointmentDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.appointment_dialog, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        etname = dialogView.findViewById(R.id.appointmentname);
        ettime = dialogView.findViewById(R.id.apptime);
        btnsubmint = (Button) dialogView.findViewById(R.id.addnewappointment);
        btnsubmint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appointname = etname.getEditText().getText().toString();
                String time = ettime.getEditText().getText().toString();
                preparedates(appointname,time);

                alertDialog.dismiss();
            }
        });

    }

    private void preparedates(String mydate, String mytime){
        Toast.makeText(getActivity(), mydate, Toast.LENGTH_SHORT).show();
        Date datess = new Date(mydate,mytime);
        dateList.add(datess);
        appointmentAdaptor.notifyDataSetChanged();
    }
}