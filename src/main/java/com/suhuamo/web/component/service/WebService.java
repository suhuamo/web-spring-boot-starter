package com.suhuamo.web.component.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/04
 * 自定义父类服务接口层,包含基本的类型转换，分页查询接口， T 为 entity 类， V 为 vo 类，D 为 dto 类
 */
public interface WebService<T, V, D> extends IService<T> {
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
    T dtoToPO(D dtoDemo);
    /**
     *  将dto类转换为vo类
     * @param dtoDemoList
     * @return List<T>
     */
    List<T> dtoToPO(List<D> dtoDemoList);
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
