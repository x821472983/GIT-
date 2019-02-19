package com.base.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AdvancedQuery {
    @ApiModelProperty("字段名称")
    private String fieldName;
    @ApiModelProperty("关系运算符(>,<,=,like,>=,<=)")
    private String relationOperator;
    @ApiModelProperty("关系运算符的临时运算符,用来转义符号,like_start,like_end")
    private String tempOperator;
    @ApiModelProperty("字段值")
    private String fieldValue;
    @ApiModelProperty("字段类别(如果为日期需要转化为Date)")
    private String fieldType = "string";
    @ApiModelProperty("逻辑运算符(and,or)")
    private String logicalOperator = "AND";
    @ApiModelProperty("排序(升序,降序)")
    private String sort;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRelationOperator() {
        return relationOperator;
    }

    public void setRelationOperator(String relationOperator) {
        this.relationOperator = relationOperator;
    }

    public String getTempOperator() {
        return tempOperator;
    }

    public void setTempOperator(String tempOperator) {
        this.tempOperator = tempOperator;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "AdvancedQuery{" +
                "fieldName='" + fieldName + '\'' +
                ", relationOperator='" + relationOperator + '\'' +
                ", tempOperator='" + tempOperator + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", logicalOperator='" + logicalOperator + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
