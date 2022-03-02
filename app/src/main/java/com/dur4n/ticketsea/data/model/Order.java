package com.dur4n.ticketsea.data.model;

import com.dur4n.ticketsea.data.Enums;

public class Order {
    Enums.OrderType orderType;
    String ticketCount;
    String solCount;

    public Order(Enums.OrderType orderType, String ticketCount, String solCount) {
        this.orderType = orderType;
        this.ticketCount = ticketCount;
        this.solCount = solCount;
    }

    public Enums.OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(Enums.OrderType orderType) {
        this.orderType = orderType;
    }

    public String getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getSolCount() {
        return solCount;
    }

    public void setSolCount(String solCount) {
        this.solCount = solCount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderType=" + orderType +
                ", ticketCount='" + ticketCount + '\'' +
                ", solCount='" + solCount + '\'' +
                '}';
    }
}
