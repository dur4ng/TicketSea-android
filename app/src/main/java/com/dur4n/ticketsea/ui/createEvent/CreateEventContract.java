package com.dur4n.ticketsea.ui.createEvent;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.event.EventMockRepository;
import com.dur4n.ticketsea.data.repository.event.IEventRepository;
import com.dur4n.ticketsea.ui.base.IBasePresenter;
import com.dur4n.ticketsea.ui.base.IProgressView;
import com.dur4n.ticketsea.ui.base.event.OnRepositoryErrorCallback;
import com.dur4n.ticketsea.ui.base.event.OnRepositoryListEventCreateCallback;
import com.dur4n.ticketsea.ui.base.event.OnRepositoryUpdateEventCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryCreateTicketCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryDeleteTicketCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryEditTicketCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryListTicketCallback;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryResetTicketCallback;

import java.util.ArrayList;
import java.util.List;

public interface CreateEventContract {
    interface View extends OnInteractorListener, IProgressView {
        void create(Event event);
        void update(Event event);
        void listTickets(); //
        void loadTicketOfEvent(String eventName);
        void showData(ArrayList<Ticket> tickets);
        void createTicket();
        void editTicket(Ticket ticket); //
        void deleteTicket(Ticket ticket); //

        // errors
        void showNameError(String message);
        void showComissionError(String message);
        void showDescriptionError(String message);
        void showDateError(String message);
        void showTicketError(String message);

        void resetTicketData();
    }
    interface Presenter extends IBasePresenter, OnInteractorListener {
        void create(Event event);
        void update(Event event);
        void loadTicketOfEvent(String eventName);
        void delete(Ticket ticket);
        void listTickets();

        void resetTicketData();
    }
    interface Interactor {
        void create(Event event);
        void delete(Ticket ticket);
        void update(Event event);
        void loadTicketOfEvent(String eventName);
        void listTickets();
        void resetTicketData();
    }
    interface OnInteractorListener extends  OnEventCallBack, OnTicketCallBack{

    }
    interface Repository{
        void create(CreateEventContract.OnEventCallBack callBack, Event event);
        void update(OnInteractorListener listener, Event event);
        void listTickets(CreateEventContract.OnEventCallBack callBack);
    }
    interface RepositoryTicketTMP{
        void resetTicketData(OnInteractorListener listener);
        void delete(OnInteractorListener listener, Ticket ticket);
        void setListTickets(OnInteractorListener listener, List<Ticket> ticketList);
        void setListTicketsForUpdate(String eventName);
    }
    // interface that group all responses from ticket repository
    interface OnEventCallBack
            extends
            OnRepositoryListEventCreateCallback,
            OnRepositoryListTicketCallback,
            OnRepositoryDeleteTicketCallback,
            OnRepositoryErrorCallback,
            OnRepositoryUpdateEventCallback
    {
        void onFailure(String message);
    }

    interface OnTicketCallBack extends OnRepositoryResetTicketCallback, OnRepositoryDeleteTicketCallback {
        void onFailure (String message);
    }
}
