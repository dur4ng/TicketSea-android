package com.dur4n.ticketsea.ui.about;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dur4n.ticketsea.R;

public class TicketViewHolder extends RecyclerView.ViewHolder {
    ImageView ivLogoEventLot;
    Button btEditTicketLot;
    TextView tvCodRef;

    public TicketViewHolder(@NonNull View itemView) {
        super(itemView);
        ivLogoEventLot = itemView.findViewById(R.id.ivLogoEventLot);
        btEditTicketLot = itemView.findViewById(R.id.btEditTicketLot);
        tvCodRef = itemView.findViewById(R.id.tvCodRef);
    }
}
