package com.dur4n.ticketsea.ui.createEvent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.event.EventMockRepository;
import com.dur4n.ticketsea.data.repository.ticket.TicketMockTMPRepository;
import com.dur4n.ticketsea.databinding.FragmentCreateEventBinding;
import com.dur4n.ticketsea.ui.BottomNavigationFragment;
import com.dur4n.ticketsea.ui.BottomNavigationFragmentDirections;
import com.dur4n.ticketsea.ui.about.AboutUsFragment;
import com.dur4n.ticketsea.ui.infoEvent.PurchaseOrderAdapter;
import com.dur4n.ticketsea.ui.ticketInfo.TicketInfoFragment;
import com.dur4n.ticketsea.ui.ticketInfo.TicketInfoFragmentDirections;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class CreateEventFragment extends Fragment implements View.OnClickListener, CreateEventContract.View, TicketLotAdapter.OnManageTicketListener{

    private Event currentEvent;
    private FragmentCreateEventBinding binding;
    private TicketLotAdapter ticketLotAdapter;
    CreateEventContract.Presenter presenter;

    static Event savedEvent;

    static String origin;

    static Boolean eventManage;

    public static Fragment newInstance(Bundle o, Boolean flag) {
        CreateEventFragment fragment = new CreateEventFragment();
        if (o != null){
            fragment.setArguments(o);
        }
        eventManage = flag;
        return fragment;
    }
    public static Fragment newInstanceWithEvent(Event event, Boolean flag, String originId) {
        CreateEventFragment fragment = new CreateEventFragment();
        fragment.currentEvent = event;
        eventManage = flag;
        origin = BottomNavigationFragment.origin;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new CreateEventPresenter(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentCreateEventBinding.inflate(inflater);
        binding.btAddPicture.setOnClickListener(this);
        binding.btAddTicketLot.setOnClickListener(this);

        if(eventManage){
            savedEvent = currentEvent;
            binding.tvCreateEventHeader.setText("Update Event");
            binding.tieName.setError("Change name to clone event");
            binding.setEvent(savedEvent);
            // cargar rv tickets
            if(origin == "eventItemView")
                TicketMockTMPRepository.getInstance().setListTicketsForUpdate(currentEvent.getNombre());
            //loadTicketOfEvent(currentEvent.getNombre());
            //ini edit
            binding.btCreateEvent.setOnClickListener(listener ->{
                updateEvent();
            });
        }else{
            if(savedEvent != null && origin == "editTickets")
                binding.setEvent(savedEvent);
            binding.tvCreateEventHeader.setText("Create Event");
            binding.btCreateEvent.setOnClickListener(listener -> {
                createEvent();
            });
        }

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_connect_wallet:
                Toast.makeText(getActivity(), "Se ha pulsado conectar wallet",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_user_porfolio:
                NavHostFragment.findNavController(this).navigate(R.id.action_showCurrentEventsFragment2_to_aboutUsFragment);
                return true;
            default:
                return false;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        savedEvent = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvTicketLot();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btAddPicture): {
                addPicture();
                break;
            }
            case (R.id.btAddTicketLot): {
                savedEvent = getCurrentEvent();
                createTicket();
            }
        }
    }

    private void updateEvent(){
        Event event = getCurrentEvent();
        if (event == null)
            return;
        update(event);
    }

    private void createEvent() {
        Event event = getCurrentEvent();
        if (event == null)
            return;

        create(getCurrentEvent());
    }

    private Event getCurrentEvent(){
        // cojer valeros de los tie
        String name = Objects.requireNonNull(binding.tieName.getText()).toString();
        long date = binding.cvDeadline.getDate();
        Log.i("log", "event date: " + date);
        Double comission;
        try {
            comission = Double.parseDouble(binding.tieCommission.getText().toString());

        }catch (NumberFormatException e){
            binding.tieCommission.setError("need a valid comission");
            return new Event();
        }
        String description = binding.tieDescription.getText().toString();



        // cojer lista de tickets del repositorio temporal
        ArrayList<Ticket> tickets = TicketMockTMPRepository.getInstance().getTickets(); //TODO MVP
        HashMap<String, Ticket> hashMapTickets = new HashMap<>();
        for (Ticket ticket : tickets){
            hashMapTickets.put(ticket.getReferenceCode(), ticket);
        }
        // crear nuevo evento y añadirlo al repositorio de eventos
        //TODO need to add owner address
        Event newEvent = new Event(name,date,description,comission, hashMapTickets);

        return newEvent;
    }

    private void addPicture() {

    }

    private void initRvTicketLot() {
        //1. Será inicializar el Adapter
        // old - ticketLotAdapter = new TicketLotAdapter(this);
        ticketLotAdapter = new TicketLotAdapter(new ArrayList<>(), this, getActivity());
        //2. OBLIGATORIAMENTE se debe indicar que diseñó tendrá el recyclerview
        //TODO cambiar el diseño
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //3. Asigno el layout al recyclerview
        binding.rvTickets.setLayoutManager(linearLayoutManager);
        //4. Asigno a la vista sus datos (modelo)
        binding.rvTickets.setAdapter(ticketLotAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.listTickets();
    }

    //region CreateEventContract.View

    @Override
    public void hideProcess() {

    }

    @Override
    public void showProcess() {

    }


    //region request
    @Override
    public void create(Event event) {
        presenter.create(event);
    }

    @Override
    public void update(Event event) {
        presenter.update(event);
    }

    @Override
    public void listTickets() {

    }

    @Override
    public void loadTicketOfEvent(String eventName) {
        presenter.loadTicketOfEvent(eventName);
    }

    @Override
    public void showData(ArrayList<Ticket> tickets) {
        ticketLotAdapter.update(tickets);
    }
    @Override
    public void createTicket() {
        //navigate
        //NavHostFragment.findNavController(this).navigate(R.id.action_createEventFragment_to_createTicketFragment);
        BottomNavigationFragmentDirections.ActionBottomNavigationFragmentToCreateTicketFragment action = BottomNavigationFragmentDirections.actionBottomNavigationFragmentToCreateTicketFragment(null, false, getCurrentEvent());
        //CreateEventFragmentDirections.ActionCreateEventFragmentToCreateTicketFragment action = CreateEventFragmentDirections.actionCreateEventFragmentToCreateTicketFragment(null);
        NavHostFragment.findNavController(CreateEventFragment.this).navigate(action);
    }
    @Override
    public void editTicket(Ticket ticket) {
        //navigate con bundle
        savedEvent = getCurrentEvent();
        BottomNavigationFragmentDirections.ActionBottomNavigationFragmentToCreateTicketFragment action = BottomNavigationFragmentDirections.actionBottomNavigationFragmentToCreateTicketFragment(ticket, eventManage, getCurrentEvent());
        NavHostFragment.findNavController(CreateEventFragment.this).navigate(action);
    }
    @Override
    public void deleteTicket(Ticket ticket) {
        presenter.delete(ticket);
    }

    @Override
    public void showNameError(String message) {
        binding.tieName.setError(message);
    }

    @Override
    public void showComissionError(String message) {
        binding.tieCommission.setError(message);
    }

    @Override
    public void showDescriptionError(String message) {
        binding.tieDescription.setError(message);
    }

    @Override
    public void showDateError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTicketError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetTicketData() {
        presenter.resetTicketData();
    }
    //endregion
    //region responses
    @Override
    public void onListSuccess(ArrayList<Ticket> tickets) {
        showData(tickets);
    }

    @Override
    public void onCreateSuccess(String message) {
        //Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        savedEvent = null;
        resetTicketData();

        showEventNotification(message);

    }

    private void showEventNotification(String message) {
        //Ejemplo de notificacion (hay que crear el canal de notificacion antes de esto)
        //1- Crear un bundle para pasarle un objeto a la notificacion
        Bundle bundle = new Bundle();
        bundle.putSerializable(Event.TAG, getCurrentEvent());
        //2- Crear un pendingIntent y pasarle el grafo de navegacion, el destino, el argument...
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.bottomNavigationFragment)
                .setArguments(bundle)
                .createPendingIntent();

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(getActivity(), "Notifications")
                    .setSmallIcon(R.drawable.ic_action_create_event)
                    .setContentTitle(getString(R.string.eventNotification))
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
        }

        //4- Añadir la notificacion al notificationManager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000), builder.build());
        //NavHostFragment.findNavController(this).navigateUp();
        Snackbar.make(getView(), "Event created successfuly", Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSuccess(Ticket ticket, String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        ticketLotAdapter.remove(ticket);
    }

    @Override
    public void onResetSuccess(String message) {

    }
    //endregion
    //region events
    @Override
    public void onEditTicket(Ticket ticket) {
        //savedEvent = null;
        editTicket(ticket);
    }

    @Override
    public void onDeleteTicket(Ticket ticket) {
        savedEvent = null;
        deleteTicket(ticket);
    }

    @Override
    public void onEventNameEmpty(String message) {
        showNameError(message);
    }

    @Override
    public void onEventNameTooLong(String message) {
        showNameError(message);
    }

    @Override
    public void onEventComissionEmpty(String message) {
        showComissionError(message);
    }

    @Override
    public void onEventComissionBig(String message) {
        showComissionError(message);
    }

    @Override
    public void onEventComissionNoNumeric(String message) {
        showComissionError(message);
    }

    @Override
    public void onEventDescriptionEmpty(String message) {
        showDescriptionError(message);
    }

    @Override
    public void onEventDescriptionTooLong(String message) {
        showDescriptionError(message);
    }

    @Override
    public void onEventDateOld(String message) {
        showDateError(message);
    }

    @Override
    public void onEventTicketEmpty(String message) {
        showTicketError(message);
    }

    @Override
    public void onSucessUpdate(String message) {
        //TODO
    }
    //endregion
}