package com.dur4n.ticketsea.ui.about;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Ticket;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketViewHolder> {

    private ArrayList<Ticket> ticketList;
    private AboutUsFragment fragment;
    public TicketAdapter(AboutUsFragment fragment){
        this.ticketList = new ArrayList<Ticket>();
        Ticket mock = new Ticket();
        mock.setReferenceCode("sadfasdf");
        mock.setEventName("evento");
        mock.setPrice("12");

        ticketList.add(mock);

        this.fragment = fragment;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        holder.tvCodRef.setText(ticketList.get(position).getReferenceCode());
        holder.btEditTicketLot.setText(ticketList.get(position).getEventName());
        //holder.ivLogoEventLot.setImageIcon();
        holder.btEditTicketLot.setOnClickListener(view -> {
            NavHostFragment.findNavController(fragment).navigate(R.id.action_bottomNavigationFragment_to_ticketInfoFragment);
            Log.i("xx", "Button pressed!");
        });
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
}
