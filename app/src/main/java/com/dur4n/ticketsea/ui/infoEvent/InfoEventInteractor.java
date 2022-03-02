package com.dur4n.ticketsea.ui.infoEvent;

import com.dur4n.ticketsea.data.model.Order;
import com.dur4n.ticketsea.data.repository.event.EventMockRepository;
import com.dur4n.ticketsea.data.repository.orders.purchase.PurchaseOrderMockRepository;
import com.dur4n.ticketsea.data.repository.orders.sale.SaleOrderMockRepository;

import java.util.ArrayList;

public class InfoEventInteractor implements InfoEventContract.OnListernerIntercator{

    InfoEventContract.OnListernerIntercator listener;

    public InfoEventInteractor(InfoEventContract.OnListernerIntercator listener) {
        this.listener = listener;
    }

    public void load() {
        // load Mock data
        PurchaseOrderMockRepository.getInstance(this).getList();
        SaleOrderMockRepository.getInstance(this).getList();
    }

    @Override
    public void onSuccessPurchaseOrdersList(ArrayList<Order> list) {
        listener.onSuccessPurchaseOrdersList(list);
    }

    @Override
    public void onSuccessSaleOrdersList(ArrayList<Order> saleOrders) {
        listener.onSuccessSaleOrdersList(saleOrders);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
