package com.suhuamo.web.constant;

/**
 * @author suhuamo
 * @date 2023-07-15
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 数据库字段
 */
public interface MysqlConstant {
    /**
     * 升序
     */
    String SORT_ORDER_ASC = "ASC";

    /**
     * 降序
     */
    String SORT_ORDER_DESC = " DESC";
    /**
     * 创建时间字段--java参数字段
     */
    String CREATE_Time_FILED = "createTime";
    /**
     * 更新时间字段--java参数字段
     */
    String UPDATE_Time_FILED = "updateTime";
    /**
     * 逻辑删除字段--java参数字段
     */
    String DELETE_FLAG_FILED = "deleteFlag";

}
