package com.dur4n.ticketsea.data.repository.ticket;

import com.dur4n.ticketsea.data.dao.TicketDAO;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.event.EventMockRepository;
import com.dur4n.ticketsea.data.room.LocalDB;
import com.dur4n.ticketsea.ui.createEvent.CreateEventContract;
import com.dur4n.ticketsea.ui.createTicket.CreateTicketContract;
import com.dur4n.ticketsea.ui.event.ShowCurrentEventsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TicketMockTMPRepository implements CreateTicketContract.Repository, CreateEventContract.RepositoryTicketTMP{
    private static TicketMockTMPRepository instance;
    private static ArrayList<Ticket> tickets;
    private static TicketDAO ticketDAO;

    private TicketMockTMPRepository() {
        this.tickets = new ArrayList<>();
    }

    public static TicketMockTMPRepository getInstance(){
        if (instance == null){
            instance = new TicketMockTMPRepository();
            ticketDAO = LocalDB.getDatabase().ticketDAO();
        }
        return instance;
    }

    public void listTickets(CreateEventContract.OnEventCallBack callBack) {
        callBack.onListSuccess(tickets);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
//region responses

    @Override
    public void create(CreateTicketContract.OnInteractorListener callBack, Ticket ticket) {
        if(ticket.getReferenceCode().equals("")){
            callBack.onReferenceCodeEmpty("A ticket need a reference code");
        }
        if(ticket.getReferenceCode().length() > 10){
            callBack.onReferenceCodeTooLong("The reference code size must be less than 10");
        }
        if(ticket.getPrice().equals("")){
            callBack.onPriceEmpty("Must have a price");
        }
        else{
            tickets.add(ticket);

            callBack.onCreateSuccess("Success ticket creation");
        }
    }

    @Override
    public void editTicket(CreateTicketContract.OnInteractorListener callback, Ticket oldTicket, Ticket newTicket) {
        if(newTicket.getReferenceCode().equals("")){
            callback.onReferenceCodeEmpty("A ticket need a reference code");
        }
        if(newTicket.getReferenceCode().length() > 10){
            callback.onReferenceCodeTooLong("The reference code size must be less than 10");
        }
        if(newTicket.getPrice().equals("")){
            callback.onPriceEmpty("Must have a price");
        }else {
            tickets.set(tickets.indexOf(oldTicket), newTicket);
            callback.onEditSuccess("susccess edit operation ");
        }
    }

    @Override
    public void resetTicketData(CreateEventContract.OnInteractorListener listener) {
        instance = null;
        listener.onResetSuccess("reset");
    }

    @Override
    public void delete(CreateEventContract.OnInteractorListener listener, Ticket ticket) {
        tickets.remove(ticket);
        listener.onDeleteSuccess(ticket,"deleted");
    }

    @Override
    public void setListTickets(CreateEventContract.OnInteractorListener listener, List<Ticket> ticketList) {
        tickets.clear();
        tickets.addAll(ticketList);

        listener.onListSuccess(tickets);
    }

    @Override
    public void setListTicketsForUpdate(String eventName) {
        //query al localTicketR
        List<Ticket> ticketList = null;
        try {
            ticketList = LocalDB.databaseWriteExecutor.submit(()->ticketDAO.getAllTicketsEvent(eventName)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ticketList != null){
            tickets.clear();
            tickets.addAll(ticketList);
        }
    }


    //endregion
}
