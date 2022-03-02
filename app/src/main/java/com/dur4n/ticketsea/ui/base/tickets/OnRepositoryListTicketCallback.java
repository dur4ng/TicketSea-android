package com.dur4n.ticketsea.ui.base.tickets;

import com.dur4n.ticketsea.data.model.Ticket;

import java.util.ArrayList;

public interface OnRepositoryListTicketCallback {
    void onListSuccess(ArrayList<Ticket> tickets);
}
