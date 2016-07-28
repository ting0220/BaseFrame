package com.example.zhaoting.baseframe.bean;

/**
 * Created by zhaoting on 16/7/28.
 */
public class CursorBean {

    /**
     * total : 1
     * limit : 10
     * offset : 0
     */

    private int total;
    private int limit;
    private int offset;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
