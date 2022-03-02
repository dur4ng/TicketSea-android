package com.dur4n.ticketsea.ui.createEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.ui.event.EventViewHolder;

import java.util.ArrayList;

public class TicketLotAdapter extends RecyclerView.Adapter<TicketLotViewHolder> {

    interface OnManageTicketListener {
        void onEditTicket(Ticket ticket);
        void onDeleteTicket(Ticket ticket);
    }

    //TODO we dont have tickets, we have ticketsLots
    private ArrayList<Ticket> ticketList;
    private OnManageTicketListener listener;
    Context context;
    CreateEventFragment fragment;

    //region adapter
    public TicketLotAdapter(ArrayList<Ticket> ticketList, OnManageTicketListener listener, Context context){
        this.ticketList = ticketList;
        this.listener = listener;
        this.context = context;
    }
    public TicketLotAdapter(CreateEventFragment fragment) {
        this.ticketList = new ArrayList<>();
        Ticket mock = new Ticket();
        mock.setReferenceCode("sadfasdf");
        mock.setEventName("evento");
        mock.setPrice("12");

        ticketList.add(mock);

        this.fragment = fragment;
    }

    @NonNull
    @Override
    public TicketLotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventlot_item, parent,false);
        return new TicketLotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketLotViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(ticketList.get(position).getReferenceCode());
        viewHolder.bind(ticketList.get(position), listener);
        //Old version
        /*
        viewHolder.btEditTicketLot.setOnClickListener(view -> {
            NavHostFragment.findNavController(fragment).navigate(R.id.action_createEventFragment_to_createTicketFragment);
        });
        */

    }
    @Override
    public int getItemCount() {
        return ticketList.size();
    }
    //endregion

    //region actions
    public void update(ArrayList<Ticket> ticketList){
        this.ticketList.clear();
        this.ticketList.addAll(ticketList);
        notifyDataSetChanged();
    }
    public void remove(Ticket ticket){
        this.ticketList.remove(ticket);
        notifyDataSetChanged();
    }
    //endregion
}
