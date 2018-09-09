package me.niravpradhan.jsf.utils;

import javax.faces.event.ValueChangeEvent;
import java.util.List;

public interface Paginable<E> {

    void setList(List<E> list);

    List<E> getList();

    List<E> getSubList(int startIndex, int endIndex);

    int count();

    void getAll();

    void next();

    void previous();

    void first();

    void last();

    void onShowEntriesChange(ValueChangeEvent event);

    void onPageNoChange(ValueChangeEvent event);
}
