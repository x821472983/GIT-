package com.base.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class QueryParams {
    @ApiModelProperty(value="表名")
    private String table;
    @ApiModelProperty(value="主键字段")
    private String field_pk;
    @ApiModelProperty(value="字段")
    private String field="*";
    @ApiModelProperty(value="字段值")
    private String field_value;
    @ApiModelProperty(value="条件")
    private String where="";
    @ApiModelProperty(value="排序")
    private String order="";
    @ApiModelProperty(value="默认排序")
    private String default_order;
    @ApiModelProperty(value="表连接信息")
    private String join="";
    @ApiModelProperty(value="当前页面")
    private int curr_page=1;
    @ApiModelProperty(value="起始记录")
    private int index_count;
    @ApiModelProperty(value="分页大小")
    private int page_size=10;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getField_pk() {
        return field_pk;
    }

    public void setField_pk(String field_pk) {
        this.field_pk = field_pk;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField_value() {
        return field_value;
    }

    public void setField_value(String field_value) {
        this.field_value = field_value;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDefault_order() {
        return default_order;
    }

    public void setDefault_order(String default_order) {
        this.default_order = default_order;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public int getCurr_page() {
        return curr_page;
    }

    public void setCurr_page(int curr_page) {
        this.curr_page = curr_page;
    }

    public int getIndex_count() {
        return index_count;
    }

    public void setIndex_count(int index_count) {
        this.index_count = index_count;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    @Override
    public String toString() {
        return "QueryParams{" +
                "table='" + table + '\'' +
                ", field_pk='" + field_pk + '\'' +
                ", field='" + field + '\'' +
                ", field_value='" + field_value + '\'' +
                ", where='" + where + '\'' +
                ", order='" + order + '\'' +
                ", default_order='" + default_order + '\'' +
                ", join='" + join + '\'' +
                ", curr_page=" + curr_page +
                ", index_count=" + index_count +
                ", page_size=" + page_size +
                '}';
    }
}
