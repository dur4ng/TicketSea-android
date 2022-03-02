package com.dur4n.ticketsea.ui.base.event;

public interface OnRepositoryErrorCallback {
    void onEventNameEmpty(String message);
    void onEventNameTooLong(String message);

    void onEventComissionEmpty(String message);
    void onEventComissionBig(String message);
    void onEventComissionNoNumeric(String message);

    void onEventDescriptionEmpty(String message);
    void onEventDescriptionTooLong(String message);

    void onEventDateOld(String message);

    void onEventTicketEmpty(String message);
}
