package com.dur4n.ticketsea.ui.event;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.repository.event.EventLocalRepository;
import com.dur4n.ticketsea.data.repository.event.EventMockRepository;

import java.util.ArrayList;

public class ShowCurrentEventsInteractor implements ShowCurrentEventsContract.OnEventCallBack{

    public ShowCurrentEventsContract.OnInteractorListener listener;

    public ShowCurrentEventsInteractor(ShowCurrentEventsContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void load() {
        // load Mock data
        //EventMockRepository.getInstance().getList(listener);
        EventLocalRepository.getInstance().getList(listener);
    }

    //region repository response
    @Override
    public void onSuccess(ArrayList<Event> list) {
        listener.onSuccess(list);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }

    @Override
    public void onDeleteSuccess(String message) {
        listener.onDeleteSuccess(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }
    //endregion
}
