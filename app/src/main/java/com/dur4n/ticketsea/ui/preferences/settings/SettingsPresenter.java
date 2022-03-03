package com.dur4n.ticketsea.ui.preferences.settings;

import android.content.Context;

import com.dur4n.ticketsea.data.model.Ticket;

import java.util.ArrayList;

public class SettingsPresenter implements SettingsContract.Presenter {

    SettingsInteractor interactor;
    SettingsContract.View view;

    public SettingsPresenter(SettingsContract.View view) {
        this.interactor = new SettingsInteractor(this);
        this.view = view;
    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }

    @Override
    public void OnSuccess(String message) {
        view.OnSuccess(message);
    }

    @Override
    public void onListSuccess(ArrayList<Ticket> tickets) {

    }

    @Override
    public void exportTicketsJson(Context context) {
        interactor.exportTicketsJson(context);
    }

    @Override
    public void exportTicketsCSV(Context context) {
        interactor.exportTicketsCSV(context);
    }

    @Override
    public void exportTicketsXML(Context context) {
        interactor.exportTicketsXML(context);
    }

    @Override
    public void exportTicketsQR(Context context) {
        interactor.exportTicketsQR(context);
    }
    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }
}
