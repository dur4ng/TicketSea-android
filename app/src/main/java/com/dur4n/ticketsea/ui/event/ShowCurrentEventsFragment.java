package com.dur4n.ticketsea.ui.event;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.user.User;
import com.dur4n.ticketsea.databinding.FragmentShowCurrentEventsBinding;
import com.dur4n.ticketsea.ui.BottomNavigationFragment;
import com.dur4n.ticketsea.ui.BottomNavigationFragmentDirections;
import com.dur4n.ticketsea.ui.about.AboutUsFragment;
import com.dur4n.ticketsea.ui.createTicket.CreateTicketFragment;
import com.dur4n.ticketsea.ui.createTicket.CreateTicketFragmentDirections;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ShowCurrentEventsFragment extends Fragment implements View.OnClickListener, ShowCurrentEventsContract.View, EventAdapter.OnManageEventListener{

    private FragmentShowCurrentEventsBinding binding;
    private EventAdapter adapter;
    private ShowCurrentEventsContract.Presenter presenter;

    public static Fragment newInstance(Bundle o) {
        ShowCurrentEventsFragment fragment = new ShowCurrentEventsFragment();
        if (o != null){
            fragment.setArguments(o);
        }
        return fragment;
    }

    //region liveCycleFragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("TicketSea");
        //2. Se inicializa el presenter
        presenter = new ShowCurrentEventsPresenter(this);
    }

    //TODO create menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.showcurrenteventsfragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
            case R.id.action_order_event:
                adapter.order();
                Toast.makeText(getActivity(), "sorted A-Z", Toast.LENGTH_SHORT).show();
                //Snackbar.make(this, "m", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.action_order_eventReverse:
                adapter.orderReverse();
                Toast.makeText(getActivity(), "sorted Z-A", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return false;
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShowCurrentEventsBinding.inflate(inflater, container, false);
        //Para añadir un evento a un botón del fragment hay que implementar View.OnClickListener al fragment
        binding.fabCreateEvent.setOnClickListener(this);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvEvent();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    //endregion

    //region simple navegation event
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case (R.id.fabCreateEvent):{
                showCreateEvent();
                break;
            }

        }
    }

    private void showCreateEvent() {

    }
    //endregion

    //region recyclerView
    private void initRvEvent() {
        //1. Será inicializar el Adapter
        adapter = new EventAdapter(new ArrayList<>(), this);

        //2. OBLIGATORIAMENTE se debe indicar que diseñó tendrá el recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        //3. Asigno el layout al recyclerview
        binding.rvEvent.setLayoutManager(linearLayoutManager);

        //4. Asigno a la vista sus datos (modelo)
        binding.rvEvent.setAdapter(adapter);
    }
    //endregion

    //region ProcessView
    @Override
    public void hideProcess() {

    }

    @Override
    public void showProcess() {

    }
    //endregion

    @Override
    public void onSuccess(ArrayList<Event> list) {

    }
/*
Version navega a información del evento
    @Override
    public void OnNavegateEvent(Event event) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("event", event);
        NavHostFragment.findNavController(this).navigate(R.id.action_bottomNavigationFragment_to_infoEventFragment, bundle);
        Toast.makeText(getActivity(), event.getNombre(), Toast.LENGTH_SHORT).show();

    }
 */
    @Override
    public void showData(ArrayList<Event> eventList) {
        adapter.update(eventList);

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void onDelete(Event event) {
        //TODO show a dialog to confirm the delete
    }

    @Override
    public void onEdit(Event event) {
        //ShowCurrentEventsFragmentDirections.ActionShowCurrentEventsFragment2ToCreateEventFragment action = ShowCurrentEventsFragmentDirections.actionShowCurrentEventsFragment2ToCreateEventFragment(event);
        //NavHostFragment.findNavController(ShowCurrentEventsFragment.this).navigate(action);
        BottomNavigationFragmentDirections.ActionBottomNavigationFragmentToCreateTicketFragment action1 = BottomNavigationFragmentDirections.actionBottomNavigationFragmentToCreateTicketFragment(null, true, event);
        NavHostFragment.findNavController(ShowCurrentEventsFragment.this).navigate(action1);
        CreateTicketFragmentDirections.ActionCreateTicketFragmentToBottomNavigationFragment action2 = CreateTicketFragmentDirections.actionCreateTicketFragmentToBottomNavigationFragment(2, event,true, "eventItemView");
        NavHostFragment.findNavController(ShowCurrentEventsFragment.this).navigate(action2);
    }
}