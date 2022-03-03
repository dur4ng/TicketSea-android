package com.dur4n.ticketsea.ui.event;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.EventComparator;
import com.dur4n.ticketsea.data.model.user.User;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder>{

    /**
     * Mock Data
     * @param parent
     * @param viewType
     * @return
     */
    private ArrayList<Event> eventList;

    private Context context;
    private OnManageEventListener listener;

    private ShowCurrentEventsFragment fragment;

    public interface OnManageEventListener{
        //v1
        //pulsación corta -> navega a información evento
        //void OnNavegateEvent(Event event);

        //v2
        //pulsación larga
        void onDelete(Event event);
        //pulsación corta
        void onEdit(Event event);
    }
/*
    public EventAdapter(ShowCurrentEventsFragment fragment) {

        this.eventList = new ArrayList<Event>();
        eventList.add(new Event("Real Madrid vs Barca", "x042314dfd08lk2j340098","27/10/2021","","Clásico partido de fútbol"));
        eventList.add(new Event("Real Madrid vs Barca", "x042314dfd08lk2j340098","27/10/2021","","Clásico partido de fútbol"));
        eventList.add(new Event("Real Madrid vs Barca", "x042314dfd08lk2j340098","27/10/2021","","Clásico partido de fútbol"));
        eventList.add(new Event("Real Madrid vs Barca", "x042314dfd08lk2j340098","27/10/2021","","Clásico partido de fútbol"));
        this.fragment = fragment;



    }
*/
    public EventAdapter(ArrayList<Event> list, OnManageEventListener listener) {
        this.eventList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(eventList.get(position).getNombre());
        //viewHolder.tvOwnerAddress.setText(eventList.get(position).getOwnerAddress());

        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        Date date=new Date(eventList.get(position).getDate());
        String dateText = df2.format(date);
        viewHolder.tvDate.setText(dateText);

        //viewHolder.ivPicture.setImageIcon(eventList.get(position).getPicture());
        viewHolder.tvDescription.setText(eventList.get(position).getDescription());

        /*
        viewHolder.btInfoEvent.setOnClickListener(view -> {
            NavHostFragment.findNavController(fragment).navigate(R.id.action_showCurrentEventsFragment2_to_infoEventFragment);
            Log.i("xx", "Button pressed!");
        });
        */

        viewHolder.bind(eventList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void update(ArrayList<Event> list) {
        this.eventList.clear();
        this.eventList.addAll(list);
        notifyDataSetChanged();
    }

    public void order(){
        Collections.sort(eventList, new EventComparator());
        notifyDataSetChanged();
    }
    public void orderReverse() {
        Collections.sort(eventList, new EventComparator());
        Collections.reverse(eventList);
        notifyDataSetChanged();
    }
}


