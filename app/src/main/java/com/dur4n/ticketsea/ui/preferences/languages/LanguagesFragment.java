package com.dur4n.ticketsea.ui.preferences.languages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dur4n.ticketsea.MainActivity;
import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.databinding.FragmentLanguagesBinding;
import com.dur4n.ticketsea.ui.utils.LocaleHelper;


public class LanguagesFragment extends Fragment {

    FragmentLanguagesBinding binding;
    Context context;
    Resources resources;

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
        // Inflate the layout for this fragment
        binding = FragmentLanguagesBinding.inflate(inflater);

        binding.btnEnglish.setOnClickListener(l -> {
            context = LocaleHelper.setLocale(getActivity(), "en");
        });

        binding.btnSpanish.setOnClickListener(l -> {
            context = LocaleHelper.setLocale(getActivity(), "es");
        });

        return binding.getRoot();

    }
}