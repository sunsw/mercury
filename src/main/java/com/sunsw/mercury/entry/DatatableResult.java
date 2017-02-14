package com.sunsw.mercury.entry;

/**
 * datatable返回结果
 * Created by sunsw on 2016/7/5.
 */
public class DatatableResult<T> extends AjaxResult {

    private String draw;
    private Long recordsTotal;
    private Long recordsFiltered;

    public DatatableResult(Object data) {
        super(data);
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
