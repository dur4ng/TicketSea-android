package com.dur4n.ticketsea.data.repository.event;

import android.os.Build;
import android.util.Log;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.ui.createEvent.CreateEventContract;
import com.dur4n.ticketsea.ui.event.ShowCurrentEventsContract;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class EventMockRepository implements ShowCurrentEventsContract.Repository, CreateEventContract.Repository  {

    private static EventMockRepository instance;
    private ArrayList<Event> lista;

    private EventMockRepository() {
        this.lista = new ArrayList<>();
        instance();
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

    public static EventMockRepository getInstance(){
        if (instance == null){
            instance = new EventMockRepository();
        }
        return instance;
    }

    public void add(Event newEvent){
        lista.add(newEvent);
    }

    @Override
    public void getList(ShowCurrentEventsContract.OnEventCallBack callBack) {
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
        if(event.getNombre().equals("")){
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

        lista.add(event);
        callBack.onCreateSuccess("event created");
    }

    @Override
    public void update(CreateEventContract.OnInteractorListener listener, Event event) {

    }

    @Override
    public void listTickets(CreateEventContract.OnEventCallBack callBack) {

    }


    //endregion
}
