package com.egdfrm.unit.common;

import java.util.List;

/**
 * Created by tyq on 17/1/5.
 */
public class Pagination {

    private Integer offset;//起始记录
    private Integer endset;//结束记录
    private Integer limit;//每页显示条数
    private Long total;//总记录数
    private Object rows;//数据集

    public Pagination(){

    }

    public Pagination(int offset,int limit){
        this.offset = offset;
        this.limit = limit;
        if(offset == 0){
            this.endset = 0;
        }else {
            this.endset = offset+limit-1;
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public Integer getEndset() {
        return endset;
    }

    public void setEndset(Integer endset) {
        this.endset = endset;
    }
}
