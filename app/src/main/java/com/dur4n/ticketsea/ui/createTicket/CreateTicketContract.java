package com.dur4n.ticketsea.ui.createTicket;

import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.ui.base.IBasePresenter;
import com.dur4n.ticketsea.ui.base.IProgressView;
import com.dur4n.ticketsea.ui.base.event.OnRepositoryErrorCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryCreateTicketCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryEditTicketCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryOnErrorTicketCallback;

public interface CreateTicketContract {
    interface View extends OnInteractorListener, IProgressView {
        void create(Ticket ticket);
        void editTicket(Ticket oldTicket, Ticket newTicket);

        void showReferenceCodeError(String message);
        void showPriceError(String message);
        void showCountError(String message);
    }
    interface Presenter extends IBasePresenter, OnInteractorListener {
        void create(Ticket ticket);
        void editTicket(Ticket oldTicket, Ticket newTicket);
    }
    interface Interactor extends OnInteractorListener{
        void create(Ticket ticket);
        void editTicket(Ticket oldTicket, Ticket newTicket);
    }
    interface OnInteractorListener extends OnTicketCallBack {

    }
    interface Repository {
        void create(OnInteractorListener callBack, Ticket ticket);
        void editTicket(OnInteractorListener callback,Ticket oldTicket, Ticket newTicket);
    }
    // interface that group all responses from ticket repository
    interface OnTicketCallBack
            extends
            OnRepositoryCreateTicketCallback,
            OnRepositoryEditTicketCallback,
            OnRepositoryOnErrorTicketCallback
    {
        void onFailure(String message);
    }

}
