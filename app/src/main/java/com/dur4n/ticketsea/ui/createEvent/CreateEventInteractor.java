package com.dur4n.ticketsea.ui.createEvent;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.event.EventLocalRepository;
import com.dur4n.ticketsea.data.repository.event.EventMockRepository;
import com.dur4n.ticketsea.data.repository.ticket.TicketMockTMPRepository;

import java.util.ArrayList;

public class CreateEventInteractor implements CreateEventContract.Interactor, CreateEventContract.OnInteractorListener{

    public CreateEventContract.OnInteractorListener listener;

    public CreateEventInteractor(CreateEventContract.OnInteractorListener listener){
        this.listener = listener;
    }


    @Override
    public void create(Event event) {
        //EventMockRepository.getInstance().create(listener, event);
        EventLocalRepository.getInstance().create(listener, event);
    }

    @Override
    public void delete(Ticket ticket) {
        TicketMockTMPRepository.getInstance().delete(listener, ticket);
    }

    @Override
    public void update(Event event) {
        EventLocalRepository.getInstance().update(listener, event);
    }

    @Override
    public void loadTicketOfEvent(String eventName) {
        TicketMockTMPRepository.getInstance().setListTicketsForUpdate(eventName);
    }

    @Override
    public void listTickets() {
        TicketMockTMPRepository.getInstance().listTickets(listener);
    }

    @Override
    public void resetTicketData() {
        TicketMockTMPRepository.getInstance().resetTicketData(listener);
    }

    @Override
    public void onCreateSuccess(String message) {
        listener.onCreateSuccess(message);
    }
    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
    @Override
    public void onListSuccess(ArrayList<Ticket> tickets) {
        listener.onListSuccess(tickets);
    }
    @Override
    public void onDeleteSuccess(Ticket ticket, String message) {
        listener.onDeleteSuccess(ticket, message);
    }
    @Override
    public void onResetSuccess(String message) {
        listener.onResetSuccess(message);
    }

    @Override
    public void onEventNameEmpty(String message) {
        listener.onEventNameEmpty(message);
    }

    @Override
    public void onEventNameTooLong(String message) {
        listener.onEventNameTooLong(message);
    }

    @Override
    public void onEventComissionEmpty(String message) {
        listener.onEventComissionEmpty(message);
    }

    @Override
    public void onEventComissionBig(String message) {
        listener.onEventComissionBig(message);
    }

    @Override
    public void onEventComissionNoNumeric(String message) {
        listener.onEventComissionNoNumeric(message);
    }

    @Override
    public void onEventDescriptionEmpty(String message) {
        listener.onEventDescriptionEmpty(message);
    }

    @Override
    public void onEventDescriptionTooLong(String message) {
        listener.onEventDescriptionTooLong(message);
    }

    @Override
    public void onEventDateOld(String message) {
        listener.onEventDateOld(message);
    }

    @Override
    public void onEventTicketEmpty(String message) {
        listener.onEventTicketEmpty(message);
    }

    @Override
    public void onSucessUpdate(String message) {
        listener.onSucessUpdate(message);
    }
}
