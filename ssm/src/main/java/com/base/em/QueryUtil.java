package com.base.em;

import com.base.constants.ConstantsParameterType;
import com.base.pojo.AdvancedQuery;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.base.pojo.QueryParams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <h2>查询工具类</h2>
 *
 * @author Enzo
 * @ClassName: QueryUtil
 * @date 2018年1月11日 下午4:56:35
 */
public class QueryUtil {

    /**
     * <h3>查询参数过滤</h3>
     *
     * @param listAdvancedQuery
     * @return
     * @throws Exception
     * @author Enzo
     * @date 2018年1月11日 下午4:57:37
     */
    private static List<AdvancedQuery> filterQueryParams(List<AdvancedQuery> listAdvancedQuery) throws Exception {
        if (listAdvancedQuery != null) {
            for (int i = 0; i < listAdvancedQuery.size(); i++) {
                AdvancedQuery query = listAdvancedQuery.get(i);
                if (query.getFieldName() == null || "".equals(query.getFieldName())) {
                    listAdvancedQuery.remove(i);
                    i--;
                    continue;
                } else if (query.getFieldValue() == null || "".equals(query.getFieldValue())) {// 高级查询值不为空
                    listAdvancedQuery.remove(i);
                    i--;
                    continue;
                } else {
                    if (query.getTempOperator() == null || "".equals(query.getTempOperator())) {// 设置默认查询类型
                        if (query.getFieldType().toLowerCase().indexOf(ConstantsParameterType.PRAMETER_INT) > -1) {// 如果是int型數據，则默认等于号
                            query.setTempOperator(EnumQueryType.RELATION_QUERY.getTag());
                        } else if (query.getFieldType().toLowerCase()
                                .indexOf(ConstantsParameterType.PRAMETER_STRING) > -1) {// 如果是string型数据，则默认like
                            query.setTempOperator(EnumQueryType.RELATION_LIKE.getTag());
                        } else if (query.getFieldType().toLowerCase()
                                .indexOf(ConstantsParameterType.PRAMETER_VARCHAR) > -1) {// 如果是string型数据，则默认like
                            query.setTempOperator(EnumQueryType.RELATION_LIKE.getTag());
                        } else if (query.getFieldType().toLowerCase()
                                .indexOf(ConstantsParameterType.PRAMETER_DATETIME) > -1) {// 时间类型
                            query.setTempOperator(EnumQueryType.RELATION_LIKESTART.getTag());
                        } else if (query.getFieldType().toLowerCase()
                                .indexOf(ConstantsParameterType.PRAMETER_DATE) > -1) {// 日期类型
                            query.setTempOperator(EnumQueryType.RELATION_LIKESTART.getTag());
                        } else {// 不允许未知类型查询
                            throw new BusinessException(EnumError.QUERY_ERROR);
                        }
                    } else if (ConstantsParameterType.PRAMETER_DATE.equals(query.getFieldType())) {// 日期类型
                        if (EnumQueryType.RELATION_QUERY.getTag().equals(query.getTempOperator())) {// 等于
                            query.setTempOperator(EnumQueryType.RELATION_LIKESTART.getTag());
                        } else if (EnumQueryType.RELATION_NOT_QUERY.getTag().equals(query.getTempOperator())) {// 不等于
                            throw new BusinessException(EnumError.QUERY_ERROR_TIME);
                        }

                    }
                    if (EnumQueryType.getLogicalByTag(query.getTempOperator()) == null) {// 非系统登记的关系运算符
                        throw new BusinessException(EnumError.QUERY_ERROR);
                    }
                    if (EnumQueryType.getLogicalByTag(query.getLogicalOperator()) == null) {// 非系统登记的逻辑运算符
                        throw new BusinessException(EnumError.QUERY_ERROR);
                    }
                    query.setRelationOperator(EnumQueryType.getLogicalByTag(query.getTempOperator()));// 设置实际关系运算符
                }

            }
        }
        return listAdvancedQuery;
    }

    /**
     * <h3>查询参数转查询条件</h3>
     *
     * @param listAdvancedQuery
     * @return
     * @author Enzo
     * @date 2018年1月11日 下午5:02:07
     */
    private static String advancedQueryToSqlCondition(List<AdvancedQuery> listAdvancedQuery) throws Exception {
        StringBuffer condition = new StringBuffer("");
        if (listAdvancedQuery != null && listAdvancedQuery.size() > 0) {
            condition.append("(");
            int n = 0;
            for (AdvancedQuery query : listAdvancedQuery) {
                if (n > 0) {
                    condition.append(" " + query.getLogicalOperator() + " ");// 添加逻辑运算符and/or
                }
                condition.append(appendQueryItem(query));
                n++;
            }
            condition.append(") ");
        }
        return condition.toString();
    }

    /**
     * <h3>装高级查询项转化为查询sql条件</h3>
     *
     * @param query
     * @return
     * @throws Exception
     * @author Enzo
     * @date 2018年4月13日 下午5:19:36
     */
    private static StringBuffer appendQueryItem(AdvancedQuery query) throws Exception {
        String[] fieldName = query.getFieldName().split(",");
        StringBuffer queryItem = new StringBuffer("");
        if (fieldName.length > 1) {
            queryItem.append("(");
        }
        for (int i = 0; i < fieldName.length; i++) {
            if (i > 0) {
                queryItem.append(" or ");
            }
            queryItem.append(fieldName[i] + " " + query.getRelationOperator() + " ");
            if (EnumQueryType.RELATION_IS_NULL.getTag().equals(query.getTempOperator())) {// 为空

            } else if (EnumQueryType.RELATION_IS_NOT_NULL.getTag().equals(query.getTempOperator())) {// 非空

            } else if (EnumQueryType.RELATION_LIKE.getTag().equals(query.getTempOperator())) {// 模糊查询
                queryItem.append("'%" + query.getFieldValue() + "%'");
            } else if (EnumQueryType.RELATION_LIKESTART.getTag().equals(query.getTempOperator())) {// 以。。。开始
                queryItem.append("'" + query.getFieldValue() + "%'");
            } else if (EnumQueryType.RELATION_LIKEEND.getTag().equals(query.getTempOperator())) {// 以。。结尾
                queryItem.append("'%" + query.getFieldValue() + "'");
            } else if (EnumQueryType.RELATION_IN.getTag().equals(query.getTempOperator())) {// in
                queryItem.append("('" + query.getFieldValue() + "')");
            } else {
                if (ConstantsParameterType.PRAMETER_DATE.equals(query.getFieldType())) {// 日期类型
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar calendar = Calendar.getInstance();
                    Date time = null;
                    if (query.getFieldValue().length() == 10) {
                        try {
                            time = sdf.parse(query.getFieldValue());
                            calendar.setTime(time);
                        } catch (ParseException e) {
                            throw new BusinessException("时间格式转化异常");
                        }
                    }
                    if (EnumQueryType.RELATION_GREATER.getTag().equals(query.getTempOperator())) {// 大于
                        queryItem.append("'" + query.getFieldValue() + " 00:00:00'");
                    } else if (EnumQueryType.RELATION_LESS.getTag().equals(query.getTempOperator())) {// 小于
                        calendar.add(Calendar.DATE, 1);
                        queryItem.append("'" + sdf.format(calendar.getTime()) + " 00:00:00'");
                    } else if (EnumQueryType.RELATION_NOTLESS.getTag().equals(query.getTempOperator())) {// 大于等于
                        queryItem.append("'" + query.getFieldValue() + " 00:00:00'");
                    } else if (EnumQueryType.RELATION_NOTGREATER.getTag().equals(query.getTempOperator())) {// 小于等于
                        calendar.add(Calendar.DATE, 1);
                        queryItem.append("'" + sdf.format(calendar.getTime()) + " 00:00:00'");
                    } else if (EnumQueryType.RELATION_QUERY.getTag().equals(query.getTempOperator())) {// 等于
                        queryItem.append("'" + query.getFieldValue() + "'");
                    }

                } else if (ConstantsParameterType.PRAMETER_DATETIME.equals(query.getFieldType())) {// 时间类型

                } else {
                    queryItem.append("'" + query.getFieldValue() + "'");
                }
            }
        }
        if (fieldName.length > 1) {
            queryItem.append(")");
        }

        return queryItem;
    }

    /**
     * <h3>获得排序字段</h3>
     *
     * @param listAdvancedQuery
     * @return
     * @author Enzo
     * @date 2018年1月17日 下午1:51:32
     */
    private static String advancedQueryToSqlOrder(List<AdvancedQuery> listAdvancedQuery) {
        StringBuilder orderBy = new StringBuilder();
        if (listAdvancedQuery != null && listAdvancedQuery.size() > 0) {
            for (AdvancedQuery query : listAdvancedQuery) {
                if (query.getFieldName() == null || "".equals(query.getFieldName()) || query.getSort() == null
                        || "".equals(query.getSort())) {
                    continue;
                }
                if (orderBy.length() > 0) {
                    orderBy.append(",");
                }
                orderBy.append(query.getFieldName() + " " + query.getSort());
            }
        }
        return orderBy.toString();

    }

    /**
     * <h3>转化高级查询条件</h3>
     *
     * @param listAdvancedQuery
     * @return
     * @author Enzo
     * @date 2018年1月17日 下午8:39:19
     */
    private static QueryParams convertAdvanceQuery(List<AdvancedQuery> listAdvancedQuery) throws Exception {
        QueryParams params = new QueryParams();
        params.setOrder(advancedQueryToSqlOrder(listAdvancedQuery));
        filterQueryParams(listAdvancedQuery);
        params.setWhere(advancedQueryToSqlCondition(listAdvancedQuery));

        return params;
    }

    /**
     * <h3>转化高级查询条件</h3>
     *
     * @param baseModel
     * @return
     * @author Enzo
     * @date 2018年1月17日 下午8:39:19
     */
    public static QueryParams convertQueryParams(BaseModel baseModel) throws Exception {
        QueryParams oldQueryParams = baseModel.getQueryParams();
        baseModel.setQueryParams(convertAdvanceQuery(baseModel.getListAdvancedQuery()));
        if (oldQueryParams != null) {
            baseModel.getQueryParams().setCurr_page(oldQueryParams.getCurr_page());
            baseModel.getQueryParams().setPage_size(oldQueryParams.getPage_size());
        }
        return baseModel.getQueryParams();
    }


}
