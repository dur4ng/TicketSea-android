package com.dur4n.ticketsea.ui.event;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    TextView tvOwnerAddress;
    TextView tvDate;
    //ImageView ivPicture;
    TextView tvDescription;
    Button btInfoEvent;

    // objeto View es una dependencia de un fragmnet
    // cargamos un componente para darle estilo a cada elemento de mi adapter lista
    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvOwnerAddress = itemView.findViewById(R.id.tvOwnerAddress);
        tvDate = itemView.findViewById(R.id.tvDate);
        //ivPicture=itemView.findViewById(R.id.ivPicture);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        //btInfoEvent = itemView.findViewById(R.id.btInfoEvent);
    }

    public void bind(Event event, EventAdapter.OnManageEventListener listener) {
        //itemView.setOnClickListener(view -> listener.OnNavegateEvent(event));
        itemView.setOnClickListener(view -> listener.onEdit(event));
        itemView.setOnLongClickListener(view -> {
            listener.onDelete(event);
            return true;
        });
    }
}
