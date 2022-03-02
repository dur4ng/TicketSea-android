package com.dur4n.ticketsea.data.repository.orders.purchase;

import com.dur4n.ticketsea.data.Enums;
import com.dur4n.ticketsea.data.model.Order;
import com.dur4n.ticketsea.ui.base.orders.IRepositoryOrder;
import com.dur4n.ticketsea.ui.base.orders.purchase.OnBasePurchaseOrderCallback;
import com.dur4n.ticketsea.ui.infoEvent.InfoEventContract;

import java.util.ArrayList;

public class PurchaseOrderMockRepository implements IRepositoryOrder {
    public static PurchaseOrderMockRepository instance;
    private OnBasePurchaseOrderCallback callBack;
    private ArrayList<Order> lista;

    private PurchaseOrderMockRepository() {
        this.lista = new ArrayList<>();
        instance();
    }

    public static PurchaseOrderMockRepository getInstance(OnBasePurchaseOrderCallback callBack){
        if (instance == null){
            instance = new PurchaseOrderMockRepository();
        }
        instance.callBack=callBack;
        return instance;
    }

    private void instance(){
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        lista.add(new Order(Enums.OrderType.Purchase, "20", "3"));
    }
    @Override
    public void getList() {
        if (callBack instanceof InfoEventContract.PurchaseOrder.OnPurchaseOrderCallBack){
            ((InfoEventContract.PurchaseOrder.OnPurchaseOrderCallBack) callBack).onSuccessPurchaseOrdersList(lista);
        }
    }
    @Override
    public void create() {

    }
}
