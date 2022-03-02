package com.dur4n.ticketsea.ui.createEvent;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Ticket;
import com.google.android.material.textfield.TextInputLayout;

public class TicketLotViewHolder extends RecyclerView.ViewHolder {
    ImageView ivLogoEventLot;
    TextView tvName;
    TextInputLayout tieTicketLotNumber;
    Button btEditTicketLot;

    public TicketLotViewHolder(@NonNull View itemView){
        super(itemView);
        ivLogoEventLot = itemView.findViewById(R.id.ivLogoEventLot);
        tvName = itemView.findViewById(R.id.tvNameTicketLot);
        tieTicketLotNumber = itemView.findViewById(R.id.tieTicketLotNumber);
        btEditTicketLot = itemView.findViewById(R.id.btEditTicketLot);
    }
    //TODO bind function
    public void bind(Ticket ticket, TicketLotAdapter.OnManageTicketListener listener){
        // apply edit and delete events
        itemView.setOnClickListener(v -> {
            listener.onEditTicket(ticket);
        });

        itemView.setOnLongClickListener(v -> {
            listener.onDeleteTicket(ticket);
            return true;
        });
    }
}
