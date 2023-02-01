package com.example.projecttest4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttest4.MenuViewInterface;
import com.example.projecttest4.R;
import com.example.projecttest4.models.Schedule;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder2>{

    Context context;
    private ArrayList<Schedule> schedules;

    public ScheduleAdapter(Context context, ArrayList<Schedule> schedules) {
        this.context = context;
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_schedule, parent, false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder2 holder, int position) {
        Date scheduleDateSql = schedules.get(position).getDate();
        String scheduleDateString = scheduleDateSql.toString();
        holder.date.setText(scheduleDateString);
        holder.hours.setText(schedules.get(position).getShift());
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        public TextView date;
        public TextView hours;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateListTextView);
            hours = itemView.findViewById(R.id.hoursListTextView);
        }
    }
}
