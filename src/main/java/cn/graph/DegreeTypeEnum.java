package cn.graph;

/**
 * 度的类型枚举
 * 度、入度、出度
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/8/26 11:13
 */
public enum DegreeTypeEnum {

    /**
     * 度
     */
    DEGREE(0, "度"),

    /**
     * 入度
     */
    IN_DEGREE(1, "入度"),

    /**
     * 出度
     */
    OUT_DEGREE(2, "出度");

    private Integer value;
    private String description;

    DegreeTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
