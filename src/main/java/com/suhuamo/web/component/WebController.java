package com.suhuamo.web.component;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhuamo.web.common.BaseResponse;
import com.suhuamo.web.common.DeleteDTO;
import com.suhuamo.web.common.PageRequest;
import com.suhuamo.web.enums.CodeEnum;
import com.suhuamo.web.exception.CustomException;

import lombok.Setter;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/09 自定义父类控制器，包含一些基本的增删改查接口,T 为 entity 类， V 为 vo 类，D 为 dto 类， S 为service接口类
 */
public class WebController<T, AD, UD, QD, V, S extends WebService> {

    /**
     * 该控制器类对应的pojo类型对应的service接口
     */
    @Setter
    protected S baseService;

    // region 增删改查

    /**
     * 添加数据
     *
     * @param entityAddDTO 上传数据
     * @return BaseResponse<V>
     */
    @PostMapping("/add")
    public BaseResponse<V> add(@RequestBody AD entityAddDTO) {
        if(entityAddDTO == null) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        T entity = (T)baseService.dtoToPO(entityAddDTO);
        // 进行校验
        baseService.validData(entity);
        Boolean result = baseService.save(entity);
        if(!result) {
            throw new CustomException(CodeEnum.OPERATION_ERROR);
        }
        V entityVo = (V)baseService.poToVO(entity);
        return BaseResponse.ok(entityVo);
    }

    /**
     * 删除数据
     *
     * @param deleteDTO 删除数据参数
     * @return BaseResponse<Boolean>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> delete(@RequestBody DeleteDTO deleteDTO) {
        // 校验数据
        if(deleteDTO == null || deleteDTO.getId() < 0) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        boolean result = baseService.removeById(deleteDTO.getId());
        if(!result) {
            throw new CustomException(CodeEnum.OPERATION_ERROR);
        }
        return BaseResponse.ok(result);
    }

    /**
     * 修改数据，根据id修改其他属性（可选，即不传的参数不改变）
     *
     * @param entityUpdateDTO
     * @return BaseResponse<Boolean>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody UD entityUpdateDTO) {
        if(entityUpdateDTO == null) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        T entity = (T)baseService.dtoToPO(entityUpdateDTO);
        // 检验数据
        baseService.validData(entity);
        boolean result = baseService.updateById(entity);
        if(!result) {
            throw new CustomException(CodeEnum.OPERATION_ERROR);
        }
        return BaseResponse.ok(result);
    }

    /**
     *  根据id查询数据（全部字段，建议设置管理员权限）
     * @param id
     * @return BaseResponse<T>
     */
    @GetMapping("/get")
    public BaseResponse<T> getById(Long id) {
        if(id < 0) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        T entity = (T)baseService.getById(id);
        if(entity == null) {
            throw new CustomException(CodeEnum.NOT_FOUND_ERROR);
        }
        return BaseResponse.ok(entity);
    }

    /**
     *  根据id查询包装类数据
     * @param id
     * @return BaseResponse<V>
     */
    @GetMapping("/get/vo")
    public BaseResponse<V> getVOById(Long id) {
        if(id < 0) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        T entity = (T)baseService.getById(id);
        if(entity == null) {
            throw new CustomException(CodeEnum.NOT_FOUND_ERROR);
        }
        V entityVO = (V)baseService.poToVO(entity);
        return BaseResponse.ok(entityVO);
    }

    /**
     *  分页获取数据（全部字段，建议设置管理员权限）
     * @param entityQueryDTO
     * @return BaseResponse<Page<T>>
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<T>> getListByPage(QD entityQueryDTO, PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        T entity = null;
        if(entityQueryDTO != null) {
            entity = (T)baseService.dtoToPO(entityQueryDTO);
        }
        Page<T> entityPage = (Page<T>) baseService.page(new Page<T>(current,size), baseService.anaQueryWrapper(entity));
        return BaseResponse.ok(entityPage);
    }

    /**
     *  分页获取包装类数据
     * @param entityQueryDTO
     * @return BaseResponse<Page<V>>
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<V>> getVOListByPage(QD entityQueryDTO, PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        T entity = null;
        if(entityQueryDTO != null) {
            entity = (T)baseService.dtoToPO(entityQueryDTO);
        }
        Page<V> entityVOPage = (Page<V>) baseService.pageVO(new Page<T>(current,size), baseService.anaQueryWrapper(entity));
        return BaseResponse.ok(entityVOPage);
    }

    // endregion
}
