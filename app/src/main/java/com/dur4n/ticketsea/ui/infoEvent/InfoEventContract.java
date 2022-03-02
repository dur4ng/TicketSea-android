package com.dur4n.ticketsea.ui.infoEvent;

import com.dur4n.ticketsea.data.model.Order;
import com.dur4n.ticketsea.ui.base.IBasePresenter;
import com.dur4n.ticketsea.ui.base.IProgressView;
import com.dur4n.ticketsea.ui.base.orders.purchase.OnBasePurchaseOrderCallback;
import com.dur4n.ticketsea.ui.base.orders.purchase.OnRepositoryListPurchaseOrderCallback;
import com.dur4n.ticketsea.ui.base.orders.sale.OnRepositoryListSaleOrderCallback;
import com.dur4n.ticketsea.ui.base.orders.sale.OnBaseSaleOrderCallback;

import java.util.ArrayList;

public interface InfoEventContract {

    interface PurchaseOrder{
        interface View extends OnPurchaseOrderCallBack, IProgressView {
            void showPurchaseOrders(ArrayList<Order> purchaseOrders);
            void showNoPurchaseOrder();
        }
        interface Presenter extends IBasePresenter {
            void load();
        }
        interface OnInteractorListener extends OnPurchaseOrderCallBack {

        }
        interface OnPurchaseOrderCallBack extends OnRepositoryListPurchaseOrderCallback, OnBasePurchaseOrderCallback {
            void onFailure(String message);
        }
    }

    interface SaleOrder {
        interface View extends OnSaleOrderCallBack, IProgressView{
            void showSaleOrders(ArrayList<Order> saleOrders);
            void showNoSaleOrders();
        }
        interface Presenter extends IBasePresenter {
            void load();
        }
        interface OnInteractorListener extends OnSaleOrderCallBack {

        }
        interface OnSaleOrderCallBack extends OnRepositoryListSaleOrderCallback, OnBaseSaleOrderCallback {
            void onFailure(String message);
        }
    }

    interface View extends PurchaseOrder.View, SaleOrder.View{

    }
    interface Presenter extends PurchaseOrder.Presenter, SaleOrder.Presenter{

    }
    interface OnListernerIntercator extends PurchaseOrder.OnInteractorListener, SaleOrder.OnInteractorListener{

    }
}
