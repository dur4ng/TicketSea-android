package com.dur4n.ticketsea.ui.base.orders.purchase;

import com.dur4n.ticketsea.data.model.Order;
import com.dur4n.ticketsea.ui.infoEvent.InfoEventContract;

import java.util.ArrayList;

public interface OnRepositoryListPurchaseOrderCallback {
    void onSuccessPurchaseOrdersList(ArrayList<Order> list);
}
