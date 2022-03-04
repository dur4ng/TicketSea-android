package com.dur4n.ticketsea.data.repository.event;

import com.dur4n.ticketsea.data.dao.EventDAO;
import com.dur4n.ticketsea.data.dao.TicketDAO;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.ticket.TicketLocalRepository;
import com.dur4n.ticketsea.data.repository.ticket.TicketMockTMPRepository;
import com.dur4n.ticketsea.data.room.LocalDB;
import com.dur4n.ticketsea.ui.createEvent.CreateEventContract;
import com.dur4n.ticketsea.ui.event.ShowCurrentEventsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EventLocalRepository implements ShowCurrentEventsContract.Repository, CreateEventContract.Repository{

    private static EventLocalRepository instance;
    private ArrayList<Event> lista;

    private EventDAO eventDAO;

    private EventLocalRepository() {
        this.lista = new ArrayList<>();
        eventDAO = LocalDB.getDatabase().eventDao();
    }

    private void instance() {
        HashMap<String, Ticket> hashMapTickets = new HashMap<>();
        //lista.add(new Event("Real Madrid vs Barca", "x042314dfd08lk2j340098",1220227200L,1.0,"Clásico partido de fútbol"));
        //lista.add(new Event("Skrillex concert", "x042314dfd08lk2j340098",1220227200L,1.0,"Skrillex concert in ULTRA miami festival"));
        //lista.add(new Event("DEFCON", "x042314dfd08lk2j340098",1220227200L,1.0,"Biggest Hacking conference in USA"));
        lista.add(new Event("DiscoLatino", 1220227200L,"Best concert of music latina",1.0, hashMapTickets));
        lista.add(new Event("DiscoLatino", 1220227200L,"Best concert of music latina",1.0, hashMapTickets));
        lista.add(new Event("DiscoLatino", 1220227200L,"Best concert of music latina",1.0, hashMapTickets));
        lista.add(new Event("DiscoLatino", 1220227200L,"Best concert of music latina",1.0, hashMapTickets));
    }

    public static EventLocalRepository getInstance(){
        if (instance == null){
            instance = new EventLocalRepository();
        }
        return instance;
    }

    public void add(Event newEvent){

        lista.add(newEvent);
    }
    public List<Event> getList() {
        List<Event> eventList = null;
        try {
            eventList = LocalDB.databaseWriteExecutor.submit(() -> eventDAO.select()).get();
        } catch (ExecutionException e) {
            eventList = new ArrayList<>();
        } catch (InterruptedException e) {
            eventList = new ArrayList<>();
        }

        return eventList;
    }

    public Event getEventById(Integer id) {
        Event event = null;
        try {
            event = LocalDB.databaseWriteExecutor.submit(() -> eventDAO.getEventById(id)).get();
        } catch (Exception e) {
            event = null;
        }

        return event;
    }

    @Override
    public void getList(ShowCurrentEventsContract.OnEventCallBack callBack) {
        try {
            lista = (ArrayList<Event>) LocalDB.databaseWriteExecutor.submit(() -> eventDAO.select()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callBack.onSuccess(lista);
    }
    @Override
    public void deleted(Event event) {
        //TODO
    }
    @Override
    public void undo(Event event) {
        //TODO
    }
//region createEventContract.View

    @Override
    public void create(CreateEventContract.OnEventCallBack callBack, Event event) {

        // name event empty
        if(event.getNombre() == null){
            callBack.onEventNameEmpty("need a valid name");
            return;
        }
        // name event too long
        if (event.getNombre().length() >= 15){
            callBack.onEventNameTooLong("need a valid name");
            return;
        }

        // comission rate empty
        if (event.getComission() == null){
            callBack.onEventComissionEmpty("Need a comission value");
            return;
        }
        // mayor o igual a 100
        if (event.getComission() >= 100){
            callBack.onEventComissionBig("Can't have a 100% of comission");
            return;
        }
        //TODO no numérico \ this probably just need to do it in the view
        //if (event.getComission())

        // description empty
        if (event.getDescription().equals("")){
            callBack.onEventDescriptionEmpty("Need a description");
            return;
        }
        // too long
        if (event.getDescription().length() >= 30){
            callBack.onEventDescriptionTooLong("Too long description");
            return;
        }

        // date past date
        if (event.getDate() > System.currentTimeMillis()){
            callBack.onEventDateOld("can't make a event in the past");
            return;
        }

        // empty tickets
        if(event.getTickets().size() <= 0){
            callBack.onEventTicketEmpty("A event need tickets");
            return;
        }

        Event currentEvent = null;
        try {
           LocalDB.databaseWriteExecutor.submit(()->eventDAO.insert(event)).get();
           currentEvent = LocalDB.databaseWriteExecutor.submit(()->eventDAO.findByName(event.getNombre())).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (Map.Entry<String, Ticket> entry : event.getTickets().entrySet()) {
            Ticket value = entry.getValue();
            if(value.getCount() == null){
                callBack.onFailure("set the count of each prototype ticket");
                return;
            }else {
                for (int i = 1; i<=value.getCount();i++){
                    Ticket ticketToInsert = new Ticket();
                    ticketToInsert.setEventId(currentEvent.id);
                    ticketToInsert.setEventName(currentEvent.getNombre());
                    ticketToInsert.setReferenceCode(value.getReferenceCode());
                    ticketToInsert.setPrice(value.getPrice());
                    ticketToInsert.setCount(value.getCount());

                    // need create a ticket using the room constructor ticket
                    TicketLocalRepository.getInstance().create(ticketToInsert);
                }
            }


        }
        callBack.onCreateSuccess("event created");
    }

    @Override
    public void update(CreateEventContract.OnInteractorListener listener, Event event) {
        //Borrar evento

        //Create new event

        //build new event for update it
        // cojer lista de tickets del repositorio temporal
        ArrayList<Ticket> tickets = TicketMockTMPRepository.getInstance().getTickets();
        HashMap<String, Ticket> hashMapTickets = new HashMap<>();
        for (Ticket ticket : tickets){
            hashMapTickets.put(ticket.getReferenceCode(), ticket);
        }
        event.setTickets(hashMapTickets);

        //create(listener, event);

        // name event empty
        if(event.getNombre() == null){
            listener.onEventNameEmpty("need a valid name");
            return;
        }
        // name event too long
        if (event.getNombre().length() >= 15){
            listener.onEventNameTooLong("need a valid name");
            return;
        }

        // comission rate empty
        if (event.getComission() == null){
            listener.onEventComissionEmpty("Need a comission value");
            return;
        }
        // mayor o igual a 100
        if (event.getComission() >= 100){
            listener.onEventComissionBig("Can't have a 100% of comission");
            return;
        }
        //TODO no numérico \ this probably just need to do it in the view
        //if (event.getComission())

        // description empty
        if (event.getDescription().equals("")){
            listener.onEventDescriptionEmpty("Need a description");
            return;
        }
        // too long
        if (event.getDescription().length() >= 30){
            listener.onEventDescriptionTooLong("Too long description");
            return;
        }

        // date past date
        if (event.getDate() > System.currentTimeMillis()){
            listener.onEventDateOld("can't make a event in the past");
            return;
        }

        // empty tickets
        if(event.getTickets().size() <= 0){
            listener.onEventTicketEmpty("A event need tickets");
            return;
        }

        Event currentEvent = null;
        try {
            LocalDB.databaseWriteExecutor.submit(()->eventDAO.deleteEventByEventName(event.getNombre()));
            LocalDB.databaseWriteExecutor.submit(()->eventDAO.insert(event)).get();
            currentEvent = LocalDB.databaseWriteExecutor.submit(()->eventDAO.findByName(event.getNombre())).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (Map.Entry<String, Ticket> entry : event.getTickets().entrySet()) {
            Ticket value = entry.getValue();
            if(value.getCount() == null){
                listener.onFailure("set the count of each prototype ticket");
                return;
            }else {
                for (int i = 1; i<=value.getCount();i++){
                    Ticket ticketToInsert = new Ticket();
                    ticketToInsert.setEventId(currentEvent.id);
                    ticketToInsert.setEventName(currentEvent.getNombre());
                    ticketToInsert.setReferenceCode(value.getReferenceCode());
                    ticketToInsert.setPrice(value.getPrice());
                    ticketToInsert.setCount(value.getCount());

                    // need create a ticket using the room constructor ticket
                    TicketLocalRepository.getInstance().create(ticketToInsert);
                }
            }


        }

        listener.onCreateSuccess("event edited");


    }

    @Override
    public void listTickets(CreateEventContract.OnEventCallBack callBack) {

    }
}
