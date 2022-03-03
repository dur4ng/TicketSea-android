package com.dur4n.ticketsea.ui.preferences.settings;

import android.content.Context;

import com.dur4n.ticketsea.ui.base.IBasePresenter;
import com.dur4n.ticketsea.ui.base.exports.OnExportTicketsCSV;
import com.dur4n.ticketsea.ui.base.exports.OnExportTicketsJson;
import com.dur4n.ticketsea.ui.base.exports.OnExportTicketsQR;
import com.dur4n.ticketsea.ui.base.exports.OnExportTicketsXML;
import com.dur4n.ticketsea.ui.base.tickets.OnRepositoryListTicketCallback;

public interface SettingsContract {
    interface View extends InteractorListener{
        void exportTicketsJson(Context context);
        void exportTicketsCSV(Context context);
        void exportTicketsXML(Context context);
        void exportTicketsQR(Context context);
    }
    interface Presenter extends IBasePresenter, InteractorListener {
        void exportTicketsJson(Context context);
        void exportTicketsCSV(Context context);
        void exportTicketsXML(Context context);
        void exportTicketsQR(Context context);
    }

    interface InteractorListener extends Callback{
        void exportTicketsJson(Context context);
        void exportTicketsCSV(Context context);
        void exportTicketsXML(Context context);
        void exportTicketsQR(Context context);
    }
    interface Callback extends OnExportTicketsJson, OnExportTicketsQR, OnExportTicketsCSV, OnExportTicketsXML, OnRepositoryListTicketCallback {
        void onFailure(String message);
    }
    interface Repository{
        void exportTicketsJson(InteractorListener listener, Context context);
        void exportTicketsCSV(InteractorListener listener, Context context);
        void exportTicketsXML(InteractorListener listener, Context context);
        void exportTicketsQR(InteractorListener listener, Context context);
    }

}
