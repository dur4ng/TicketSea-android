package com.dur4n.ticketsea.ui.createTicket;

import com.dur4n.ticketsea.data.model.Ticket;

public class CreateTicketPresenter implements CreateTicketContract.Presenter{

    private CreateTicketContract.View view;
    private CreateTicketContract.Interactor interactor;

    public CreateTicketPresenter(CreateTicketContract.View view){
        this.view = view;
        this.interactor = new CreateTicketInteractor(this);
    }

    @Override
    public void create(Ticket ticket) {
        interactor.create(ticket);
    }

    @Override
    public void editTicket(Ticket oldTicket, Ticket newTicket) {
        interactor.editTicket(oldTicket, newTicket);
    }

    @Override
    public void onCreateSuccess(String message) {
        view.onCreateSuccess(message);
    }

    @Override
    public void onEditSuccess(String message) {
        view.onEditSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onReferenceCodeEmpty(String message) {
        view.onReferenceCodeEmpty(message);
    }

    @Override
    public void onReferenceCodeTooLong(String message) {
        view.onReferenceCodeTooLong(message);
    }

    @Override
    public void onPriceEmpty(String message) {
        view.onPriceEmpty(message);
    }

    @Override
    public void onPriceNoNumeric(String message) {
        view.onPriceNoNumeric(message);
    }

    @Override
    public void onCountEmpty(String message) {
        view.onCountEmpty(message);
    }

    @Override
    public void onCountNoValid(String message) {
        view.onCountNoValid(message);
    }
}
