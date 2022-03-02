package com.dur4n.ticketsea.data.model;

import java.util.Comparator;

public class EventComparator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        return ((Event)o).getDescription().compareTo(((Event)t1).getDescription());
    }


}
