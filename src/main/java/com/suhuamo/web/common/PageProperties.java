package com.suhuamo.web.common;

import lombok.Data;

/**
 * @author suhuamo
 * @date 2023-08-06
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 分页参数限制
 */
@Data
public class PageProperties {
    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = MysqlConstant.SORT_ORDER_ASC;
}
