package com.dur4n.ticketsea.ui.event;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.repository.event.IEventRepository;
import com.dur4n.ticketsea.ui.base.event.OnRepositoryListEventDeleteCallback;
import com.dur4n.ticketsea.ui.base.event.OnRepositoryListEventUndoCallback;
import com.dur4n.ticketsea.ui.base.IBasePresenter;
import com.dur4n.ticketsea.ui.base.IProgressView;
import com.dur4n.ticketsea.ui.base.event.OnRepositoryListEventCallback;

import java.util.ArrayList;
import java.util.List;

public interface ShowCurrentEventsContract {
    /**
     * Interface for ShowCurrentEvent views
     * - Extends from IProgressView OnRepositoryListCallback
     * - Have the response of the repository
     * - Have methods for show a progress
     * - Have methods for manage the data of a RecyclerView
     */
    interface View extends OnRepositoryListEventCallback, IProgressView {
        void showData(ArrayList<Event> eventList);
        void showNoData();
    }

    /**
     * Interfaz una debe implementar el presenter
     */
    interface Presenter extends IBasePresenter {
        // cargar datos
        void load();
        //2. Cuando se realiza una pulsación larga se elimina
        void deleted(Event event);
        //3. Cuando el usuario pulsa la opción Undo del SnackBar
        void undo (Event event);
    }
    /**
     * Interfaz que debe implementar toda clase que quiere ser un Repositorio Lista
     */
    interface Repository {
        // cargar datos
        void getList(ShowCurrentEventsContract.OnEventCallBack callBack);
        //2. Cuando se realiza una pulsación larga se elimina
        void deleted(Event event);
        //3. Cuando el usuario pulsa la opción Undo del SnackBar
        void undo (Event event);
    }
    /**
     * Interfaz que debe implementar el Listener del Interactor.
     * Esta interfaz muestra las posibles alternativas de los casos de uso
     * - LISTAR ELEMENTOS (getList)
     * - ELIMINAR (delete)
     * - DESHACER (undo)
     */
    interface OnInteractorListener extends OnEventCallBack {

    }

    /**
     * Mi solución creacría una interfaz que extienda de todoas los callback
     */
    interface OnEventCallBack extends IEventRepository, OnRepositoryListEventCallback, OnRepositoryListEventDeleteCallback, OnRepositoryListEventUndoCallback {
        void onFailure(String message);
    }
}
