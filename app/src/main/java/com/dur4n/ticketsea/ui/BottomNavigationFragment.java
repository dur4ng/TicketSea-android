package com.dur4n.ticketsea.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.databinding.FragmentBottomNavigationBinding;
import com.dur4n.ticketsea.ui.about.AboutUsFragment;
import com.dur4n.ticketsea.ui.createEvent.CreateEventFragment;
import com.dur4n.ticketsea.ui.createTicket.CreateTicketFragmentArgs;
import com.dur4n.ticketsea.ui.event.ShowCurrentEventsFragment;
import com.dur4n.ticketsea.ui.preferences.settings.SettingsFragment;


public class BottomNavigationFragment extends Fragment {

    FragmentBottomNavigationBinding binding;

    Integer currentFragmentFlag = 1;
    Event currentEvent = null;

    public static Boolean eventManage = false;

    public static String origin = "";

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
                             Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavigationBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    @Override
    public void onStart() {

        try {
            currentFragmentFlag = BottomNavigationFragmentArgs.fromBundle(getArguments()).getChildFragmentFlag();

        } catch (Exception e){
            currentFragmentFlag = 1;
        }
        try {
            currentEvent = BottomNavigationFragmentArgs.fromBundle(getArguments()).getEvent();
        }catch (Exception e){
            currentEvent = null;
        }
        try {
            eventManage = BottomNavigationFragmentArgs.fromBundle(getArguments()).getEventManage();
        }catch (Exception e){

        }
        try {
            origin = BottomNavigationFragmentArgs.fromBundle(getArguments()).getOrigin();
        } catch (Exception e){

        }
        if(origin == "eventItemView" || origin == "editTickets"){
            eventManage = true;
        }
        super.onStart();
        switch (currentFragmentFlag){
            case 1:
                loadFragment(ShowCurrentEventsFragment.newInstance(null));
                break;
            case 2:
                    loadFragment(CreateEventFragment.newInstanceWithEvent(currentEvent, eventManage, origin));
                break;
            case 3:
                loadFragment(AboutUsFragment.newInstance(null));
                break;
            default:
                break;
        }

    }

    private void initNavigation() {
        binding.mainMenuBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.action_events:
                    loadFragment(ShowCurrentEventsFragment.newInstance(null));
                    break;
                case R.id.action_create_event:
                        loadFragment(CreateEventFragment.newInstanceWithEvent(currentEvent, eventManage, origin));
                    break;
                case R.id.action_user_porfolio:
                    loadFragment(AboutUsFragment.newInstance(null));
                    break;
                case R.id.action_preferences:
                    loadFragment(SettingsFragment.newInstance(null));
            }
            return true;
        });
    }

    private void loadFragment(Fragment newFragment){
        if (newFragment!= null){
            getChildFragmentManager().beginTransaction().replace(R.id.appContent, newFragment).commit();
        }
    }
}