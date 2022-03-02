package com.dur4n.ticketsea.ui.createEvent;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;

import java.util.ArrayList;

public class CreateEventPresenter implements CreateEventContract.Presenter{

    CreateEventContract.View view;
    CreateEventContract.Interactor interactor;

    public CreateEventPresenter(CreateEventContract.View view){
        this.view = view;
        this.interactor = new CreateEventInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }

    @Override
    public void create(Event event) {
        interactor.create(event);
    }

    @Override
    public void update(Event event) {
        interactor.update(event);
    }

    @Override
    public void loadTicketOfEvent(String eventName) {
        interactor.loadTicketOfEvent(eventName);
    }

    @Override
    public void delete(Ticket ticket) {
        interactor.delete(ticket);
    }

    @Override
    public void listTickets() {
        interactor.listTickets();
    }

    @Override
    public void resetTicketData() {
        interactor.resetTicketData();
    }

    @Override
    public void onCreateSuccess(String message) {
        view.onCreateSuccess(message);
    }

    @Override
    public void onListSuccess(ArrayList<Ticket> tickets) {
        view.onListSuccess(tickets);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onDeleteSuccess(Ticket ticket, String message) {
        view.onDeleteSuccess(ticket, message);
    }

    @Override
    public void onResetSuccess(String message) {
        view.onResetSuccess(message);
    }

    @Override
    public void onEventNameEmpty(String message) {
        view.onEventNameEmpty(message);
    }

    @Override
    public void onEventNameTooLong(String message) {
        view.onEventNameTooLong(message);
    }

    @Override
    public void onEventComissionEmpty(String message) {
        view.onEventComissionEmpty(message);
    }

    @Override
    public void onEventComissionBig(String message) {
        view.onEventComissionBig(message);
    }

    @Override
    public void onEventComissionNoNumeric(String message) {
        view.onEventComissionNoNumeric(message);
    }

    @Override
    public void onEventDescriptionEmpty(String message) {
        view.onEventDescriptionEmpty(message);
    }

    @Override
    public void onEventDescriptionTooLong(String message) {
        view.onEventDescriptionTooLong(message);
    }

    @Override
    public void onEventDateOld(String message) {
        view.onEventDateOld(message);
    }

    @Override
    public void onEventTicketEmpty(String message) {
        view.onEventTicketEmpty(message);
    }

    @Override
    public void onSucessUpdate(String message) {
        view.onSucessUpdate(message);
    }
}
