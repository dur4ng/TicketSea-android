 package com.dur4n.ticketsea.ui.infoEvent;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Order;
import com.dur4n.ticketsea.databinding.FragmentInfoEventBinding;
import com.dur4n.ticketsea.ui.event.EventAdapter;
import com.dur4n.ticketsea.ui.ticketInfo.TicketInfoFragment;
import com.dur4n.ticketsea.ui.ticketInfo.TicketInfoFragmentDirections;

import java.util.ArrayList;

 public class InfoEventFragment
         extends
            Fragment
         implements
            View.OnClickListener,
            InfoEventContract.View,
            PurchaseOrderAdapter.OnManageInfoEventPurchaseOrdersListener,
            SaleOrderAdapter.OnManageInfoEventSaleOrdersListener
 {

    private FragmentInfoEventBinding binding;

    private SaleOrderAdapter saleOrderAdapter;
    private PurchaseOrderAdapter purchaseOrderAdapter;

    private InfoEventContract.Presenter presenter;

    Event event;

    //region live cycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                InfoEventFragmentDirections.ActionInfoEventFragmentToBottomNavigationFragment action = InfoEventFragmentDirections.actionInfoEventFragmentToBottomNavigationFragment(1, null, false, "eventInfo");
                NavHostFragment.findNavController(InfoEventFragment.this).navigate(action);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        presenter = new InfoEventPresenter(this);
    }

    //TODO create menu

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binding = FragmentInfoEventBinding.inflate(inflater, container, false);

        Bundle bundle = this.getArguments();
        if ( bundle != null) {
            event = (Event)bundle.getSerializable("event");
        }

        //Toast.makeText(getActivity(), event.getNombre(), Toast.LENGTH_SHORT).show();

        binding.tvEventDescription.setText(event.toString());

        binding.btBuy.setOnClickListener(this);
        binding.btSell.setOnClickListener(this);
        binding.btOwnerAddress.setOnClickListener(this);

        return binding.getRoot();
    }

     @Override
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
         initRvPurchaseOrder();
         initRvSaleOrder();
     }

     @Override
     public void onStart() {
         super.onStart();
         presenter.load();
     }

     //endregion

     //region simple navigation onclick
     @Override
     public void onClick(View view) {
         switch(view.getId()){
             case (R.id.btBuy): {
                 showTicketOperation();
                 break;
             }

             case (R.id.btOwnerAddress): {
                 copyOwnerEventAddress();
                 break;
             }

         }
     }

     private void showTicketOperation() {
         NavHostFragment.findNavController(this).navigate(R.id.action_infoEventFragment_to_ticketOperationFragment);
     }
     //endregion
     private void copyOwnerEventAddress() {
     }
     //region init RecyclerView
     private void initRvPurchaseOrder() {
         //1. Será inicializar el Adapter
         //purchaseOrderAdapter = new PurchaseOrderAdapter();
         purchaseOrderAdapter = new PurchaseOrderAdapter(new ArrayList<>(), this);

         //2. OBLIGATORIAMENTE se debe indicar que diseñó tendrá el recyclerview
         //TODO cambiar el diseño
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

         //3. Asigno el layout al recyclerview
         binding.rvPurchaseOrder.setLayoutManager(linearLayoutManager);

         //4. Asigno a la vista sus datos (modelo)
         binding.rvPurchaseOrder.setAdapter(purchaseOrderAdapter);
     }
     private void initRvSaleOrder(){
        //1. Será inicializar el Adapter
         //saleOrderAdapter = new SaleOrderAdapter();
         saleOrderAdapter = new SaleOrderAdapter(new ArrayList<>(), this);

         //2. OBLIGATORIAMENTE se debe indicar que diseñó tendrá el recyclerview
         //TODO cambiar layout
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

         //3. Asigno el layout al recyclerview
         binding.rvSaleOrder.setLayoutManager(linearLayoutManager);

         //4. Asigno a la vista sus datos (modelo)
         binding.rvSaleOrder.setAdapter(saleOrderAdapter);
     }
     //endregion

     //region vista-presenter
     @Override
     public void hideProcess() {

     }

     @Override
     public void showProcess() {

     }

     @Override
     public void onSuccessPurchaseOrdersList(ArrayList<Order> list) {

     }

     @Override
     public void onSuccessSaleOrdersList(ArrayList<Order> saleOrders) {

     }

     @Override
     public void showPurchaseOrders(ArrayList<Order> purchaseOrders) {
        purchaseOrderAdapter.update(purchaseOrders);
     }

     @Override
     public void showNoPurchaseOrder() {

     }

     @Override
     public void showSaleOrders(ArrayList<Order> saleOrders) {
        saleOrderAdapter.update(saleOrders);
     }

     @Override
     public void showNoSaleOrders() {

     }
     @Override
     public void onFailure(String message) {

     }
     //endregion

 }