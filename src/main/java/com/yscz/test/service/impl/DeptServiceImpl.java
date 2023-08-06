package com.yscz.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhuamo.web.enums.CodeEnum;
import com.suhuamo.web.exception.CustomException;
import com.yscz.test.pojo.Dept;
import com.yscz.test.mapper.DeptMapper;
import com.yscz.test.pojo.dto.dept.DeptAddDTO;
import com.yscz.test.pojo.dto.dept.DeptQueryDTO;
import com.yscz.test.pojo.dto.dept.DeptUpdateDTO;
import com.yscz.test.pojo.vo.DeptVO;
import com.yscz.test.service.DeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suhuamo
 * @date 2023-08-06
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 该服务类对应的 pojo 类型对应的 mapper 接口已注入 WebServiceImpl ，名称为 baseMapper
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    DeptMapper deptMapper;


    @Override
    public void validAddData(DeptAddDTO deptAddDTO) {

    }

    @Override
    public void validUpdateData(DeptUpdateDTO deptUpdateDTO) {
        // 1. id不能为空，可以使用注解校验
        if(deptUpdateDTO.getId() == null) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        // 2. 其他校验
    }

    @Override
    public void validQueryData(DeptQueryDTO deptQueryDTO) {

    }

    @Override
    public Wrapper getQueryWrapper(DeptQueryDTO deptQueryDTO) {
        // 1. 创建 Wrapper 对象
        LambdaQueryWrapper<Dept> deptLambdaQueryWrapper = Wrappers.lambdaQuery(Dept.class);
        // 2. 进行条件拼接
        return deptLambdaQueryWrapper;
    }

    @Override
    public Page<DeptVO> pageToVo(Page<Dept> deptPage) {
        return (Page<DeptVO>) deptPage.convert(this::poToVo);
    }

    @Override
    public Boolean idExists(Integer id) {
        // 1. 查询数据
        Dept dept = deptMapper.selectById(id);
        // 2. 返回结果
        if(dept == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Dept addDtoToPo(DeptAddDTO deptAddDTO) {
        // 1. 创建实体
        Dept dept = new Dept();
        // 2. 基础参数复制
        BeanUtils.copyProperties(deptAddDTO, dept);
        // 3. 其他业务参数处理后set
        return dept;
    }

    @Override
    public Dept updateDtoToPo(DeptUpdateDTO deptUpdateDTO) {
        // 1. 创建实体
        Dept dept = new Dept();
        // 2. 基础参数复制
        BeanUtils.copyProperties(deptUpdateDTO, dept);
        // 3. 其他业务参数处理后set
        return dept;
    }

    @Override
    public DeptVO poToVo(Dept dept) {
        // 1. 创建实体
        DeptVO deptVO = new DeptVO();
        // 2. 基础参数复制
        BeanUtils.copyProperties(dept, deptVO);
        // 3. 其他业务参数处理后set
        return deptVO;
    }
}
