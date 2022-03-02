package com.dur4n.ticketsea.data.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dur4n.ticketsea.data.dao.EventDAO;
import com.dur4n.ticketsea.data.dao.TicketDAO;
import com.dur4n.ticketsea.data.dao.UserDAO;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.model.user.User;

//1.Definición de la configuración de la bd
@Database(entities = {Event.class, Ticket.class, User.class}, version = 10)
public abstract class LocalDB extends RoomDatabase {
    // 2. Crear los métodos de obtención de los DAO.
    // (Se crea una interfaz con las etiquetas de queries para room,
    // se crea un método abstracto que hace de puente entre room y la interfaz)
    public abstract EventDAO eventDao();
    public abstract TicketDAO ticketDAO();
    public abstract UserDAO userDAO();

    private static volatile LocalDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocalDB getDatabase() {
        return INSTANCE;

    }

    static LocalDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDB.class, "ticketSea")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDB.class, "ticketSea")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
