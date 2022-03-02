package com.dur4n.ticketsea.ui.infoEvent;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dur4n.ticketsea.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView tvSolAmount;
    TextView tvTicketAmount;

    public OrderViewHolder(@NonNull View itemView){
        super(itemView);
        tvSolAmount=itemView.findViewById(R.id.tvSolAmount);
        tvTicketAmount=itemView.findViewById(R.id.tvTicketAmount);
    }
}
