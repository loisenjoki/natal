package com.luisa.smartnatal.UI.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.luisa.smartnatal.Data.Date;
import com.luisa.smartnatal.R;

import java.util.List;

public class AppointmentAdaptor  extends RecyclerView.Adapter<AppointmentAdaptor.MyViewHolder> {

    private List<Date> dateList;

    public AppointmentAdaptor(List<Date> dateLists) {
        this.dateList = dateLists;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,time;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.testing);
            title = (TextView) view.findViewById(R.id.appoTime);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_row_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Date date = dateList.get(position);
        holder.title.setText(date.getName());
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

}
