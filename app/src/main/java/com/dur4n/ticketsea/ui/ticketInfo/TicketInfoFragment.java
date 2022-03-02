package com.dur4n.ticketsea.ui.ticketInfo;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.ui.createTicket.CreateTicketFragment;

public class TicketInfoFragment extends Fragment {

    Integer id = 3;

    public TicketInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                TicketInfoFragmentDirections.ActionTicketInfoFragmentToBottomNavigationFragment action = TicketInfoFragmentDirections.actionTicketInfoFragmentToBottomNavigationFragment(id, null, false);
                NavHostFragment.findNavController(TicketInfoFragment.this).navigate(action);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_info, container, false);
    }


}