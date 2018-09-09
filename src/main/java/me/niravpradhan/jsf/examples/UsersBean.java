package me.niravpradhan.jsf.examples;

import me.niravpradhan.jsf.utils.AbstractPaginationBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class UsersBean extends AbstractPaginationBean<User> {

    private List<User> list;

    @Override
    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public List<User> getList() {
        return list;
    }

    @Override
    public List<User> getSubList(int startIndex, int endIndex) {
        return Repository.getSubList(startIndex, endIndex);
    }

    @PostConstruct
    public void init() {
        // set total no of rows in table
        setCount(Repository.count());
        // set no of rows per page
        setNoOfRows(5);
        // set the start Index
        setStartIndex(0);
        // set the end index
        setEndIndex(getNoOfRows());

        // set the no of pages in table
        calculatePages();

        // get the list based on startIndex and endIndex
        list = getSubList(getStartIndex(), getEndIndex());
    }

}
