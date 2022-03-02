package com.dur4n.ticketsea.ui.base.orders.sale;

import com.dur4n.ticketsea.data.model.Order;
import com.dur4n.ticketsea.ui.infoEvent.InfoEventContract;

import java.util.ArrayList;

public interface OnRepositoryListSaleOrderCallback {
    void onSuccessSaleOrdersList(ArrayList<Order> saleOrders);
}
