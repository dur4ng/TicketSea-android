package com.dur4n.ticketsea.ui.infoEvent;

import com.dur4n.ticketsea.data.model.Order;

import java.util.ArrayList;

public class InfoEventPresenter implements InfoEventContract.Presenter, InfoEventContract.OnListernerIntercator{

    InfoEventContract.View view;
    InfoEventInteractor interactor;

    public InfoEventPresenter(InfoEventContract.View view) {
        this.view = view;
        this.interactor = new InfoEventInteractor(this);
    }

    //region peticiones de la vista al presenter
    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void load() {
        interactor.load();
    }
    //endregion

    //region callbacks del interactor al presenter
    @Override
    public void onSuccessPurchaseOrdersList(ArrayList<Order> list) {
        if (list.size()==0){
            view.showNoPurchaseOrder();
        } else {
            view.showPurchaseOrders(list);

        }
        view.hideProcess();
    }

    @Override
    public void onSuccessSaleOrdersList(ArrayList<Order> saleOrders) {
        if (saleOrders.size()==0){
            view.showNoSaleOrders();
        } else {
            view.showSaleOrders(saleOrders);

        }
        view.hideProcess();
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }
    //endregion
}
