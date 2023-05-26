package com.suhuamo.web.component.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhuamo.web.common.util.DataUtil;
import com.suhuamo.web.component.service.WebService;

import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/06
 * 自定义父类服务实现层,包含基本的类型转换，分页查询接口， T 为 entity 类， V 为 vo 类，D 为 dto 类， M 为mapper接口类
 */
public class WebServiceImpl<M extends BaseMapper<T>, T, V, D> extends ServiceImpl<M, T> implements WebService<T, V, D> {


    /**
     * 该服务类对应的pojo类型对应的mapper接口
     */
    @Setter
    protected M baseMapper;
    @Setter
    protected T demoEntity;
    @Setter
    private V demoEntityVO;
    @Setter
    private D demoEntityDTO;

    /**
     *  将pojo类转换为vo类
     * @param entity
     * @return V
     */
    @Override
    public V poToVO(T entity) {
        // 创建一个VO类用于接受值
        V entityVO = getEntityVO();
        DataUtil.pojoTransfer(entity, entityVO);
        return entityVO;
    }

    /**
     *  将pojo类转换为vo类
     * @param entityList
     * @return List<V>
     */
    @Override
    public List<V> poToVO(List<T> entityList) {
        return entityList.stream().map(this::poToVO).collect(Collectors.toList());
    }

    /**
     *  将dto类转换为pojo类
     * @param dtoDemo
     * @return V
     */
    @Override
    public T dtoToPO(D dtoDemo) {
        // 创建一个entity类用于接受值
        T entity = getEntity();
        DataUtil.pojoTransfer(dtoDemo, entity);
        return entity;
    }

    /**
     *  将dto类转换为vo类
     * @param dtoDemoList
     * @return List<T>
     */
    @Override
    public List<T> dtoToPO(List<D> dtoDemoList) {
        return dtoDemoList.stream().map(this::dtoToPO).collect(Collectors.toList());
    }

    /**
     *  进行分页查找，返回vo类型的分页结果
     * @param page
     * @param queryWrapper
     * @return Page<V>
     */
    @Override
    public Page<V> pageVO(Page<T> page, Wrapper<T> queryWrapper) {
        // 先使用pojo类得到分页结果
        Page<T> entityPage = baseMapper.selectPage(page, queryWrapper);
        // 将pojo类的分页结果进行转换为vo类
        IPage<V> voPage = entityPage.convert(this::poToVO);
        // 可继续添加其他信息

        return (Page<V>) voPage;
    }

    /**
     *  进行分页查找，返回vo类型的分页结果
     * @param page
     * @return Page<V>
     */
    @Override
    public Page<V> pageVO(Page<T> page) {
        return pageVO(page, Wrappers.emptyWrapper());
    }

    /**
     *  返回一个实例化过后的entity对象
     * @param
     * @return V
     */
    private T getEntity() {
        // 借助 demoEntity 创建对象
        T entity = null;
        try {
            entity = (T) demoEntity.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     *  返回一个实例化过后的VO对象
     * @param
     * @return V
     */
    private V getEntityVO() {
        // 借助 demoEntityVO 创建对象
        V entityVO = null;
        try {
            entityVO = (V) demoEntityVO.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return entityVO;
    }

    /**
     *  返回一个实例化过后的DTO对象
     * @param
     * @return V
     */
    private D getEntityDTO() {
        // 借助 demoEntityDTO 创建对象
        D entityDTO = null;
        try {
            entityDTO = (D) demoEntityDTO.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return entityDTO;
    }
}
