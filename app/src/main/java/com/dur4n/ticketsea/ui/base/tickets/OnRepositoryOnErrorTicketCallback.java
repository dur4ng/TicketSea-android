package com.dur4n.ticketsea.ui.base.tickets;

public interface OnRepositoryOnErrorTicketCallback {
    void onReferenceCodeEmpty(String message);
    void onReferenceCodeTooLong(String message);

    void onPriceEmpty(String message);
    void onPriceNoNumeric(String message);

    void onCountEmpty(String message);
    void onCountNoValid(String message);
}
