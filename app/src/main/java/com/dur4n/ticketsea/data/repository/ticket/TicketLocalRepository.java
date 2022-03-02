package com.dur4n.ticketsea.data.repository.ticket;

import com.dur4n.ticketsea.data.dao.EventDAO;
import com.dur4n.ticketsea.data.dao.TicketDAO;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.event.EventLocalRepository;
import com.dur4n.ticketsea.data.room.LocalDB;
import com.dur4n.ticketsea.ui.createEvent.CreateEventContract;
import com.dur4n.ticketsea.ui.createTicket.CreateTicketContract;
import com.dur4n.ticketsea.ui.event.ShowCurrentEventsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class TicketLocalRepository implements CreateTicketContract.Repository{
    private static TicketLocalRepository instance;
    private ArrayList<Ticket> lista;

    private TicketDAO ticketDAO;

    private TicketLocalRepository() {
        this.lista = new ArrayList<>();
        ticketDAO = LocalDB.getDatabase().ticketDAO();
    }

    public static TicketLocalRepository getInstance(){
        if (instance == null){
            instance = new TicketLocalRepository();
        }
        return instance;
    }


    @Override
    public void create(CreateTicketContract.OnInteractorListener callBack, Ticket ticket) {
        try {
            LocalDB.databaseWriteExecutor.submit(()->ticketDAO.insert(ticket)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void create(Ticket ticket) {
        try {
            LocalDB.databaseWriteExecutor.submit(()->ticketDAO.insert(ticket)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void editTicket(CreateTicketContract.OnInteractorListener callback, Ticket oldTicket, Ticket newTicket) {
        for (Ticket ticketToEdit: lista
        ) {
            if (ticketToEdit.getReferenceCode().equals(oldTicket.getReferenceCode())){
                    /*
                list.remove(dependencyToEdit);
                list.add(dependency);
                     */
                try {
                    lista = (ArrayList<Ticket>) LocalDB.databaseWriteExecutor.submit(()->ticketDAO.update(newTicket)).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                callback.onEditSuccess(ticketToEdit.getReferenceCode() + " editado correctamente");
                return;
            }
            callback.onFailure("Ticket no encontrado");
        }

    }
    public void editTicket(Ticket oldTicket, Ticket newTicket) {
        for (Ticket ticketToEdit: lista
        ) {
            if (ticketToEdit.getReferenceCode().equals(oldTicket.getReferenceCode())){
                try {
                    lista = (ArrayList<Ticket>) LocalDB.databaseWriteExecutor.submit(()->ticketDAO.update(newTicket)).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                return;
            }
        }

    }
}
