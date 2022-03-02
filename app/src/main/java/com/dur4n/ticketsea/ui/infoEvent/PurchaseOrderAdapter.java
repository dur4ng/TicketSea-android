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

public class PurchaseOrderAdapter extends RecyclerView.Adapter<PurchaseOrderAdapter.ViewHolder> {

    private ArrayList<Order> purchaseOrderList;

    private Context context;
    private OnManageInfoEventPurchaseOrdersListener listener;

    private InfoEventFragment fragment;

    public interface OnManageInfoEventPurchaseOrdersListener {

    }
    /*
    public PurchaseOrderAdapter() {
        this.purchaseOrderList = new ArrayList<Order>();
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));
        purchaseOrderAdapter.add(new Order(Enums.OrderType.Purchase, "20", "3"));

    }
    */

    public PurchaseOrderAdapter(
            ArrayList<Order> purchaseOrderList,
            OnManageInfoEventPurchaseOrdersListener listener
    ){
        this.purchaseOrderList = purchaseOrderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchaseorder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvSolAmount.setText(purchaseOrderList.get(position).getSolCount());
        viewHolder.tvTicketAmount.setText(purchaseOrderList.get(position).getTicketCount());

        //viewHolder.bind(purchaseOrderList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return purchaseOrderList.size();
    }

    public void update(ArrayList<Order> list) {
        this.purchaseOrderList.clear();
        this.purchaseOrderList.addAll(list);
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
