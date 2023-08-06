package com.yscz.test.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yscz.test.pojo.Dept;
import com.yscz.test.pojo.dto.dept.DeptAddDTO;
import com.yscz.test.pojo.dto.dept.DeptQueryDTO;
import com.yscz.test.pojo.dto.dept.DeptUpdateDTO;
import com.yscz.test.pojo.vo.DeptVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suhuamo
 * @date 2023-08-06
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 */
public interface DeptService extends IService<Dept>{

    /**
     * 校验添加数据的参数
     * @param deptAddDTO
     * @return void
     */
    void validAddData(DeptAddDTO deptAddDTO);

    /**
     * 校验更新数据的参数
     * @param deptUpdateDTO
     * @return void
     */
    void validUpdateData(DeptUpdateDTO deptUpdateDTO);

    /**
     * 校验查询数据的参数
     * @param deptQueryDTO
     * @return void
     */
    void validQueryData(DeptQueryDTO deptQueryDTO);

    /**
     * 根据查询数据的参数解析Wrapper
     * @param deptQueryDTO
     * @return Wrapper
     */
    Wrapper getQueryWrapper(DeptQueryDTO deptQueryDTO);

    /**
     * 将分页的数据vo化
     * @param deptPage
     * @return Page<DeptVO>
     */
    Page<DeptVO> pageToVo(Page<Dept> deptPage);

    /**
     * 校验指定id的数据是否存在
     * @param id
     * @return Boolean
     */
    Boolean idExists(Integer id);

    /**
     * 将addDto对象转换为po对象--标准化
     * @param deptAddDTO
     * @return Dept
     */
    Dept addDtoToPo(DeptAddDTO deptAddDTO);

    /**
     * 将updateDto对象转换为po对象--标准化
     * @param deptUpdateDTO
     * @return Dept
     */
    Dept updateDtoToPo(DeptUpdateDTO deptUpdateDTO);

    /**
     * 将po对转换为vo对象--可视化
     * @param dept
     * @return DeptVO
     */
    DeptVO poToVo(Dept dept);


}
