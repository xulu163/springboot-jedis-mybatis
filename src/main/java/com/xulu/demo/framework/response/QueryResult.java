package com.xulu.demo.framework.response;

import java.util.List;

/**
 * @author by xulu on 2018/4/2.
 */
public class QueryResult<T> {

    private Integer total;
    private List<T> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}