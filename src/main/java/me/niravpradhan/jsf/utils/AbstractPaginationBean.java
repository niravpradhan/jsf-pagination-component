package me.niravpradhan.jsf.utils;

import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPaginationBean<E> implements Paginable<E>, Serializable {

    private int count;
    private int startIndex;
    private int endIndex;
    private int noOfRows;
    private int pageNo;
    private int noOfPages;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void getAll() {
        List<E> list = getSubList(startIndex, endIndex);
        setList(list);
    }

    @Override
    public void next() {
        startIndex += noOfRows;
        endIndex += noOfRows;
        if (endIndex > count) {
            endIndex = count;
        }
        if (startIndex > count) {
            startIndex = ((count - 1) / noOfRows) * noOfRows;
        }

        calculatePages();

        List<E> list = getSubList(startIndex, endIndex);
        setList(list);
    }

    @Override
    public void previous() {
        startIndex -= noOfRows;
        if (startIndex < 0) {
            startIndex = 0;
        }
        endIndex = startIndex + noOfRows;
        calculatePages();
        List<E> list = getSubList(startIndex, endIndex);
        setList(list);
    }

    @Override
    public void first() {
        startIndex = 0;
        endIndex = noOfRows;
        calculatePages();
        List<E> list = getSubList(startIndex, endIndex);
        setList(list);
    }

    @Override
    public void last() {
        startIndex = ((count - 1) / noOfRows) * noOfRows;
        endIndex = count;
        calculatePages();
        List<E> list = getSubList(startIndex, endIndex);
        setList(list);
    }

    @Override
    public void onShowEntriesChange(ValueChangeEvent event) {
        Object obj = event.getNewValue();
        if (Objects.nonNull(obj)) {
            noOfRows = Integer.parseInt(obj.toString());
        }
        first();
    }

    @Override
    public void onPageNoChange(ValueChangeEvent event) {
        Object obj = event.getNewValue();
        if (Objects.nonNull(obj)) {
            pageNo = Integer.parseInt(obj.toString());
            if (pageNo == 1) {
                startIndex = 0;
                endIndex = startIndex + noOfRows;
            } else {
                startIndex = pageNo * noOfRows;
                startIndex = startIndex - noOfRows;
                endIndex = startIndex + noOfRows;
                if (endIndex > count) {
                    endIndex = count;
                }
            }
            List<E> list = getSubList(startIndex, endIndex);
            setList(list);
        }
    }

    protected void calculatePages() {
        pageNo = endIndex / noOfRows;
        int rem = endIndex % noOfRows;
        if (rem != 0) {
            pageNo += 1;
        }
        noOfPages = count / noOfRows;
        rem = count % noOfRows;
        if (rem != 0) {
            noOfPages += 1;
        }
    }
}
