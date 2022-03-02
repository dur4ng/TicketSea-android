package com.dur4n.ticketsea.ui.createTicket;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.databinding.FragmentCreateTicketBinding;
import com.dur4n.ticketsea.ui.BottomNavigationFragmentDirections;
import com.dur4n.ticketsea.ui.createEvent.CreateEventFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class CreateTicketFragment extends Fragment implements CreateTicketContract.View{

    private FragmentCreateTicketBinding binding;
    private CreateTicketContract.Presenter presenter;

    Ticket ticket = null;

    Event event = null;
    Boolean eventManage = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CreateTicketPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateTicketBinding.inflate(inflater, container, false);

        //binding.btAddTicket.setOnClickListener(this);
        //binding.btAddPicture.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventManage = CreateTicketFragmentArgs.fromBundle(getArguments()).getEventManage();
        event = CreateTicketFragmentArgs.fromBundle(getArguments()).getEvent();
        if(CreateTicketFragmentArgs.fromBundle(getArguments()).getTicket()!=null){
            binding.tvCreateTicketHeader.setText("Edit Ticket");
            // initView
            initView(CreateTicketFragmentArgs.fromBundle(getArguments()).getTicket());
            // init button with a edit event
            initEditButton();
        }
        else {
            binding.tvCreateTicketHeader.setText("Create Ticket");
            //init button with a create event
            initCreateButton();
        }
    }

    private void initView(Ticket ticket) {
        binding.tieRefereceCode.setText(ticket.getReferenceCode());
        binding.tiePrice.setText(ticket.getPrice());
    }
    private void initEditButton() {
        binding.btAddTicket.setOnClickListener(v -> {
            getTicket();
            presenter.editTicket(CreateTicketFragmentArgs.fromBundle(getArguments()).getTicket(), ticket);
        });
    }
    private void initCreateButton() {
        binding.btAddTicket.setOnClickListener(v -> {
            Boolean state = getTicket();
            if (state)
                presenter.create(ticket);
        });

    }
    private boolean getTicket(){
        String referenceCode = binding.tieRefereceCode.getText().toString();
        String price = binding.tiePrice.getText().toString();
        try {
            Double priceDouble = Double.parseDouble(price);

        }catch (NumberFormatException e){
            showPriceError("Need a valid number");
            return false;
        }

        Integer count = null;
        try {
            count = Integer.parseInt(binding.tieCount.getText().toString());
        } catch (NumberFormatException e){
            showCountError("need a valid count of the ticket protoype");
            return false;
        }
        ticket = new Ticket();
        ticket.setReferenceCode(referenceCode);
        ticket.setPrice(price);
        ticket.setCount(count);
        return true;
    }

    /*@Override
    public void onClick(View view) {
        switch(view.getId()){
            case (R.id.btAddTicket): {
                addTicket();
                break;
            }
            case (R.id.btAddPicture): {
                addPicture();
                break;
            }
        }
    }*/

    private void addTicket() {
        String referenceCode = binding.tieRefereceCode.getText().toString();
        String price = binding.tiePrice.getText().toString();
        Integer count = null;
        try {
            count = Integer.parseInt(binding.tieCount.getText().toString());
        } catch (NumberFormatException e){
            showCountError("need a valid count of the ticket protoype");
            return;
        }

        Ticket newTicket = new Ticket();

        create(newTicket);

        //NavHostFragment.findNavController(this).navigate(R.id.action_createTicketFragment_to_createEventFragment);
    }
    private void addPicture() {
    }
//region CreateTicektContract.View
    @Override
    public void hideProcess() {

    }

    @Override
    public void showProcess() {

    }

    @Override
    public void create(Ticket ticket) {
        presenter.create(ticket);
    }

    @Override
    public void editTicket(Ticket oldTicket, Ticket newTicket) {

    }

    @Override
    public void showReferenceCodeError(String message) {
        binding.tieRefereceCode.setError(message);
    }

    @Override
    public void showPriceError(String message) {
        binding.tiePrice.setError(message);
    }

    @Override
    public void showCountError(String message) {
        binding.tieCount.setError(message);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreateSuccess(String message) {

        //Ejemplo de notificacion (hay que crear el canal de notificacion antes de esto)
        //1- Crear un bundle para pasarle un objeto a la notificacion
        Bundle bundle = new Bundle();
        bundle.putSerializable(Ticket.TAG,  getTicket());
        //2- Crear un pendingIntent y pasarle el grafo de navegacion, el destino, el argument...
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.bottomNavigationFragment)
                .setArguments(bundle)
                .createPendingIntent();

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(getActivity(), "Notifications")
                    .setSmallIcon(R.drawable.ic_action_ticket)
                    .setContentTitle(getString(R.string.ticketCreated))
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
        }

        //4- Añadir la notificacion al notificationManager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000), builder.build());

        //NavHostFragment.findNavController(this).navigateUp();
        Snackbar.make(getView(), "Ticket created successfuly", Snackbar.LENGTH_SHORT).show();

        CreateTicketFragmentDirections.ActionCreateTicketFragmentToBottomNavigationFragment action = CreateTicketFragmentDirections.actionCreateTicketFragmentToBottomNavigationFragment(2, event, eventManage);
        NavHostFragment.findNavController(CreateTicketFragment.this).navigate(action);
    }

    @Override
    public void onEditSuccess(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        //NavHostFragment.findNavController(this).navigateUp();
        //NavHostFragment.findNavController(this).navigate(R.id.action_createTicketFragment_to_bottomNavigationFragment);

        CreateTicketFragmentDirections.ActionCreateTicketFragmentToBottomNavigationFragment action = CreateTicketFragmentDirections.actionCreateTicketFragmentToBottomNavigationFragment(2, event, eventManage);
        //BottomNavigationFragmentDirections.ActionBottomNavigationFragmentToCreateTicketFragment action = BottomNavigationFragmentDirections.actionBottomNavigationFragmentToCreateTicketFragment(null);
        //CreateEventFragmentDirections.ActionCreateEventFragmentToCreateTicketFragment action = CreateEventFragmentDirections.actionCreateEventFragmentToCreateTicketFragment(null);
        NavHostFragment.findNavController(CreateTicketFragment.this).navigate(action);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReferenceCodeEmpty(String message) {
        showReferenceCodeError(message);
    }

    @Override
    public void onReferenceCodeTooLong(String message) {
        showReferenceCodeError(message);
    }

    @Override
    public void onPriceEmpty(String message) {
        showPriceError(message);
    }

    @Override
    public void onPriceNoNumeric(String message) {
        showPriceError(message);
    }

    @Override
    public void onCountEmpty(String message) {
        showCountError(message);
    }

    @Override
    public void onCountNoValid(String message) {
        showCountError(message);
    }
    //endregion
}