package com.dur4n.ticketsea.ui.event;

import com.dur4n.ticketsea.data.model.Event;

import java.util.ArrayList;

public class ShowCurrentEventsPresenter implements ShowCurrentEventsContract.Presenter, ShowCurrentEventsContract.OnInteractorListener{

    private ShowCurrentEventsContract.View view;
    private ShowCurrentEventsInteractor interactor;

    public ShowCurrentEventsPresenter(ShowCurrentEventsContract.View view) {
        this.view = view;
        this.interactor = new ShowCurrentEventsInteractor(this);
    }

    //region orders for the interactor
    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void deleted(Event event) {

    }

    @Override
    public void undo(Event event) {

    }
    //endregion

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    //region responses from the interactor
    @Override
    public void onSuccess(ArrayList<Event> list) {
        if (list.size()==0){
            view.showNoData();
        } else {
            view.showData(list);

        }
        view.hideProcess();
    }

    @Override
    public void onDeleteSuccess(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }

    @Override
    public void onFailure(String message) {

    }
    //endregion
}
