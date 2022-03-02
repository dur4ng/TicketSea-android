package com.dur4n.ticketsea.data.repository.orders.sale;

import com.dur4n.ticketsea.data.Enums;
import com.dur4n.ticketsea.data.model.Order;
import com.dur4n.ticketsea.ui.base.orders.IRepositoryOrder;
import com.dur4n.ticketsea.ui.base.orders.sale.OnBaseSaleOrderCallback;
import com.dur4n.ticketsea.ui.infoEvent.InfoEventContract;

import java.util.ArrayList;

public class SaleOrderMockRepository implements IRepositoryOrder {
    public static SaleOrderMockRepository instance;
    private OnBaseSaleOrderCallback callBack;
    private ArrayList<Order> lista;

    private SaleOrderMockRepository() {
        this.lista = new ArrayList<>();
        instance();
    }

    public static SaleOrderMockRepository getInstance(OnBaseSaleOrderCallback callBack){
        if (instance == null){
            instance = new SaleOrderMockRepository();
        }
        instance.callBack=callBack;
        return instance;
    }

    private void instance(){
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
        lista.add(new Order(Enums.OrderType.Sale, "20", "3"));
    }
    @Override
    public void getList() {
        if (callBack instanceof InfoEventContract.SaleOrder.OnSaleOrderCallBack){
            ((InfoEventContract.SaleOrder.OnSaleOrderCallBack) callBack).onSuccessSaleOrdersList(lista);
        }
    }

    @Override
    public void create() {

    }
}
