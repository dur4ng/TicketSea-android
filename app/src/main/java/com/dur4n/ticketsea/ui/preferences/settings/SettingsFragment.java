package com.dur4n.ticketsea.ui.preferences.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.databinding.FragmentSettingsBinding;
import com.dur4n.ticketsea.ui.BottomNavigationFragment;
import com.dur4n.ticketsea.ui.event.ShowCurrentEventsFragment;
import com.dur4n.ticketsea.ui.utils.FileManager;
import com.google.gson.Gson;
import com.google.zxing.WriterException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.XMLFormatter;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class SettingsFragment extends Fragment implements SettingsContract.View {

    FragmentSettingsBinding binding;
    SettingsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SettingsPresenter(this);
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
        binding.btnExportTicketsJson.setOnClickListener(l -> {
            exportTicketsJson(getContext());
        });
        binding.btnExportTicketsCSV.setOnClickListener(l -> {
            exportTicketsCSV(getContext());
        });
        binding.btnExportTicketsXML.setOnClickListener(l -> {
            exportTicketsXML(getContext());
        });
        binding.btnExportTicketsQR.setOnClickListener(l -> {
            exportTicketsQR(getContext());
        });
        return binding.getRoot();
    }

    public static Fragment newInstance(Bundle o) {
        SettingsFragment fragment = new SettingsFragment();
        BottomNavigationFragment.origin = "settings";
        BottomNavigationFragment.eventManage = false;
        if (o != null){
            fragment.setArguments(o);
        }
        return fragment;
    }

    @Override
    public void exportTicketsJson(Context context) {
        presenter.exportTicketsJson(context);
    }

    @Override
    public void exportTicketsCSV(Context context) {
        presenter.exportTicketsCSV(context);
    }

    @Override
    public void exportTicketsXML(Context context) {
        presenter.exportTicketsXML(context);
    }

    @Override
    public void exportTicketsQR(Context context) {
        presenter.exportTicketsQR(context);
    }
    @Override
    public void OnSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListSuccess(ArrayList<Ticket> tickets) {

    }
    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}