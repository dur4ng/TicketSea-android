package com.dur4n.ticketsea.ui.base.event;

import com.dur4n.ticketsea.data.model.Event;

import java.util.ArrayList;

public interface OnRepositoryListEventCallback {
    void onSuccess(ArrayList<Event> list);
}
