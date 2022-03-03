package com.dur4n.ticketsea.ui.preferences.settings;

import android.content.Context;

import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.exports.ExportsRepository;

import java.util.ArrayList;

public class SettingsInteractor implements SettingsContract.InteractorListener{

    SettingsContract.InteractorListener listener;

    public SettingsInteractor(SettingsContract.Presenter listener) {
        this.listener = listener;
    }

    @Override
    public void OnSuccess(String message) {
        listener.OnSuccess(message);
    }

    @Override
    public void onListSuccess(ArrayList<Ticket> tickets) {

    }

    @Override
    public void exportTicketsJson(Context context) {
        ExportsRepository.getInstance().exportTicketsJson(this, context);
    }

    @Override
    public void exportTicketsCSV(Context context) {
        ExportsRepository.getInstance().exportTicketsCSV(this, context);
    }

    @Override
    public void exportTicketsXML(Context context) {
        ExportsRepository.getInstance().exportTicketsXML(this, context);
    }

    @Override
    public void exportTicketsQR(Context context) {
        ExportsRepository.getInstance().exportTicketsQR(this, context);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
