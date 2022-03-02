package com.dur4n.ticketsea.ui.preferences.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.databinding.FragmentSettingsBinding;
import com.dur4n.ticketsea.ui.event.ShowCurrentEventsFragment;


public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater);
        binding.btnAboutUs.setOnClickListener(l -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_bottomNavigationFragment_to_aboutUsFragment2);
        });
        binding.btnLanguage.setOnClickListener(l -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_bottomNavigationFragment_to_languagesFragment);
        });
        return binding.getRoot();
    }

    public static Fragment newInstance(Bundle o) {
        SettingsFragment fragment = new SettingsFragment();
        if (o != null){
            fragment.setArguments(o);
        }
        return fragment;
    }


}