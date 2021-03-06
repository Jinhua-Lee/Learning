package com.ee.page;

import lombok.Data;

/**
 * 分页信息实体类
 *
 * @author Jinhua
 */
@Data
public class PageBean {
    /**
     * 当前页数
     */
    private int currPage;

    /**
     * 每页显示记录数
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 构造函数
     * 四个参数中这三个需要手动注入，总页数是计算出来的
     * 只提供 getter 方法， 不提供 setter 方法 构造函数行使了 setter 方法的功能
     *
     * @param currPagePara   当前页
     * @param pageSizePara   每页大小
     * @param totalCountPara 总记录数
     */
    public PageBean(int currPagePara, int pageSizePara, int totalCountPara) {
        currPage = currPagePara;
        pageSize = pageSizePara;
        totalCount = totalCountPara;

        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }

        if (currPage <= 0) {
            currPage = 1;
        }
        if (currPage >= totalPage + 1) {
            currPage = totalPage;
        }
    }

}