package com.suhuamo.web.component;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/04
 * 自定义父类服务接口层,包含基本的类型转换，分页查询接口， T 为 entity 类， V 为 vo 类，D 为 dto 类
 */
public interface WebService<T, V> extends IService<T> {

    /**
     *  进行数据校验
     * @param entity
     * @return void
     */
    void validData(T entity);

    /**
     *  解析数据并生成对应的 QueryWrapper
     * @param entity
     * @return QueryWrapper<T>
     */
    QueryWrapper<T> anaQueryWrapper(T entity);

    /**
     *  将pojo类转换为vo类
     * @param entity
     * @return V
     */
    V poToVO(T entity);

    /**
     *  将pojo类转换为vo类
     * @param entityList
     * @return List<V>
     */
    List<V> poToVO(List<T> entityList);
    /**
     *  将dto类转换为pojo类
     * @param dtoDemo
     * @return V
     */
    T dtoToPO(Object dtoDemo);
    /**
     *  将dto类转换为vo类
     * @param dtoDemoList
     * @return List<T>
     */
    List<T> dtoToPO(List<Object> dtoDemoList);
    /**
     *  进行分页查找，返回vo类型的分页结果
     * @param page
     * @param queryWrapper
     * @return Page<V>
     */
    Page<V> pageVO(Page<T> page, Wrapper<T> queryWrapper);
    /**
     *  进行分页查找，返回vo类型的分页结果
     * @param page
     * @return Page<V>
     */
    Page<V> pageVO(Page<T> page);
}
