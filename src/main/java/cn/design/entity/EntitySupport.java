package cn.design.entity;

import java.io.Serializable;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 14:20
 */
public interface EntitySupport extends Serializable {

    /**
     * 数据库主键
     *
     * @return id
     */
    Integer getId();
}
