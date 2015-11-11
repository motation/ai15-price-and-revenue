package de.hawhamburg.microservices.core.price.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ole on 07.11.2015.
 */
public class WrappedList {
    private List list;
    private String name;

    private WrappedList(Class classToWrap){
        this.list = new ArrayList<>();
        this.name = classToWrap.getSimpleName() + "List";
    }

    public static WrappedList getWrappedList(Class classToWrap, Collection collection){
        WrappedList wrappedList = new WrappedList(classToWrap);
        wrappedList.addAll(collection);
        return wrappedList;
    }

    private boolean addAll(Collection collection){
        return list.addAll(collection);
    }

    public String getName() {
        return name;
    }

    public List getList() {
        return list;
    }
}
