package com.uswop.pms.commons;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private boolean first; // 是否第一页

    private boolean last; // 是否最后一页

    private int pageCount; // 总页数

    private int count; // 总记录数

    private int next; // 下一页的页数

    private int previous; // 上一页的页数

    private int size; // 每页的记录条数

    private int number; // 当前页数

    private List contents; // 包含的记录集

    private int[] indexs;

    public Page() {
        this.first = true;
        this.last = true;
        this.pageCount = 0;
        this.count = 0;
        this.next = 0;
        this.previous = 0;
        this.size = 10;
        this.number = 0;
        this.contents = new ArrayList();
    }

    public Page(final int count, final int number, int size) {
        if (size < 1) {
            size = 10;
        }
        this.setPageCount(count / size);
        if (count % size > 0) {
            this.setPageCount(this.getPageCount() + 1);
        }
        if (number < 1) {
            this.setNumber(1);
        } else {
            if (number > this.getPageCount()) {
                this.setNumber(this.getPageCount());
            } else {
                this.setNumber(number);
            }
        }
        this.setCount(count);
        this.setSize(size);
        this.setFirst(this.getNumber() == 1);

        this.setLast(this.getNumber() >= this.getPageCount());
        if (this.getFirst()) {
            this.setPrevious(1);
        } else {
            this.setPrevious(this.getNumber() - 1);
        }
        if (this.getLast()) {
            this.setNext(this.getNumber());
        } else {
            this.setNext(this.getNumber() + 1);
        }

        indexs = new int[pageCount];
        for (int i = 0; i < pageCount; i++) {
            indexs[i] = i + 1;
        }
    }

    public boolean getFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean getLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public List getContents() {
        return contents;
    }

    public void setContents(List contents) {
        this.contents = contents;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getIndexs() {
        return indexs;
    }

    public void setIndexs(int[] indexs) {
        this.indexs = indexs;
    }
}

