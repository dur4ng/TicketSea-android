package com.dur4n.ticketsea.ui.infoEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.Enums;
import com.dur4n.ticketsea.data.model.Order;

import java.util.ArrayList;

public class SaleOrderAdapter extends RecyclerView.Adapter<SaleOrderAdapter.ViewHolder> {

    //mock ordenes de venta
    private ArrayList<Order> saleOrderList;

    private Context context;
    private SaleOrderAdapter.OnManageInfoEventSaleOrdersListener listener;

    private InfoEventFragment fragment;

    public interface OnManageInfoEventSaleOrdersListener {

    }
    /*
    public SaleOrderAdapter() {
        this.saleOrderAdapter = new ArrayList<Order>();
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));
        saleOrderAdapter.add(new Order(Enums.OrderType.Sale, "20", "3"));

    }
     */

    public SaleOrderAdapter(
            ArrayList<Order> saleOrderList,
            OnManageInfoEventSaleOrdersListener listener)
    {
        this.saleOrderList = saleOrderList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public SaleOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saleorder_item, parent, false);
        return new SaleOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvSolAmount.setText(saleOrderList.get(position).getSolCount());
        viewHolder.tvTicketAmount.setText(saleOrderList.get(position).getTicketCount());

        //viewHolder.bind(saleOrderList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return saleOrderList.size();
    }

    public void update(ArrayList<Order> list) {
        this.saleOrderList.clear();
        this.saleOrderList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSolAmount;
        TextView tvTicketAmount;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvSolAmount=itemView.findViewById(R.id.tvSolAmount);
            tvTicketAmount=itemView.findViewById(R.id.tvTicketAmount);
        }
    }
}
