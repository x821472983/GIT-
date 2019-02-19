package com.base.em;

public enum EnumQueryType {
    LOGICAL_AND("AND","AND","且"),
    LOGICAL_OR("OR","OR","或"),
    RELATION_LIKE("LIKE","like","包含"),
    RELATION_LIKESTART("LIKE_START","like","以..开始"),
    RELATION_LIKEEND("LIKE_END","like","以..结尾"),
    RELATION_GREATER("GREATER",">","大于"),
    RELATION_LESS("LESS","<","小于"),
    RELATION_NOTGREATER("NOT_GREATER","<=","小于等于"),
    RELATION_NOTLESS("NOT_LESS",">=","大于等于"),
    RELATION_QUERY("QUERY","=","等于"),
    RELATION_NOT_QUERY("NOT_QUERY","!=","不等于"),
    RELATION_IN("IN","in","IN"),
    PLACEHOLDER("PLACEHOLDER","","占位"),
    RELATION_IS_NULL("IS_NULL","is null","为空"),
    RELATION_IS_NOT_NULL("IS_NOT_NULL","is not null","不为空");




    private String tag;
    private String logical;
    private String info;
    public static String getLogicalByTag(String tag){
        for(EnumQueryType em:EnumQueryType.values()){
            if(em.getTag().equals(tag)){
                return em.getLogical();
            }
        }
        return null;
    }
    private EnumQueryType(String tag,String logical,String info){
        this.tag=tag;
        this.logical=logical;
        this.info=info;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLogical() {
        return logical;
    }

    public void setLogical(String logical) {
        this.logical = logical;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "EnumQueryType{" +
                "tag='" + tag + '\'' +
                ", logical='" + logical + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
