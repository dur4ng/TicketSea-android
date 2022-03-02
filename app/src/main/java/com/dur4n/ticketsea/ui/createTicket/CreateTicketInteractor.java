package com.dur4n.ticketsea.ui.createTicket;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.ticket.TicketMockTMPRepository;

public class CreateTicketInteractor implements CreateTicketContract.Interactor{

    public CreateTicketContract.OnInteractorListener listener;

    public CreateTicketInteractor(CreateTicketContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    //region CreateTicketContract.Interactor
    @Override
    public void create(Ticket ticket) {
        TicketMockTMPRepository.getInstance().create(listener, ticket);
    }

    @Override
    public void editTicket(Ticket oldTicket, Ticket newTicket) {
        TicketMockTMPRepository.getInstance().editTicket(listener, oldTicket, newTicket);
    }

    @Override
    public void onCreateSuccess(String message) {
        listener.onCreateSuccess(message);
    }

    @Override
    public void onEditSuccess(String message) {
        listener.onEditSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }

    @Override
    public void onReferenceCodeEmpty(String message) {
        listener.onReferenceCodeEmpty(message);
    }

    @Override
    public void onReferenceCodeTooLong(String message) {
        listener.onReferenceCodeTooLong(message);
    }

    @Override
    public void onPriceEmpty(String message) {
        listener.onPriceEmpty(message);
    }

    @Override
    public void onPriceNoNumeric(String message) {
        listener.onPriceNoNumeric(message);
    }

    @Override
    public void onCountEmpty(String message) {
        listener.onCountEmpty(message);
    }

    @Override
    public void onCountNoValid(String message) {
        listener.onCountNoValid(message);
    }
    //endregion
}
