package com.dur4n.ticketsea.ui.about;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.databinding.FragmentAboutUsBinding;
import com.dur4n.ticketsea.ui.event.EventAdapter;

import java.util.ArrayList;


public class AboutUsFragment extends Fragment implements View.OnClickListener{

    private FragmentAboutUsBinding binding;
    private TicketAdapter adapter;

    public static Fragment newInstance(Bundle o) {
        AboutUsFragment fragment = new AboutUsFragment();
        if (o != null){
            fragment.setArguments(o);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState)
    {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvTicket();
    }

    @Override
    public void onClick(View view) {

    }

    private void initRvTicket() {
        //1. Será inicializar el Adapter
        adapter = new TicketAdapter(this);

        //2. OBLIGATORIAMENTE se debe indicar que diseñó tendrá el recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        //3. Asigno el layout al recyclerview
        binding.rvTicketPorfolio.setLayoutManager(linearLayoutManager);

        //4. Asigno a la vista sus datos (modelo)
        binding.rvTicketPorfolio.setAdapter(adapter);
    }
}