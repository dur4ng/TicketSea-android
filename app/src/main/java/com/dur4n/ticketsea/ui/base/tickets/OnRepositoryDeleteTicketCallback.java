package com.dur4n.ticketsea.ui.base.tickets;

import com.dur4n.ticketsea.data.model.Ticket;

public interface OnRepositoryDeleteTicketCallback {
    void onDeleteSuccess(Ticket ticket, String message);
}
