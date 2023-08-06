package com.yscz.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhuamo.web.common.ResponseResult;
import com.suhuamo.web.enums.CodeEnum;
import com.yscz.test.pojo.Dept;
import com.yscz.test.pojo.dto.dept.DeptAddDTO;
import com.yscz.test.pojo.dto.dept.DeptQueryDTO;
import com.yscz.test.pojo.dto.dept.DeptUpdateDTO;
import com.yscz.test.pojo.vo.DeptVO;
import com.yscz.test.service.DeptService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author suhuamo
 * @date 2023-08-06
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 新增 dept
     * @param deptAddDTO
     * @return ResponseResult<String>
     */
    @PostMapping
    public ResponseResult<String> addDept(@RequestBody DeptAddDTO deptAddDTO) {
        // 1. 校验数据
        deptService.validAddData(deptAddDTO);
        // 2. 转换类型
        Dept dept = deptService.addDtoToPo(deptAddDTO);
        // 3. 插入数据
        deptService.save(dept);
        // 4. 转换为vo返回给前端
        System.out.println("deptAddDTO = " + deptAddDTO);
        System.out.println("dept = " + dept);
        return ResponseResult.ok(deptService.poToVo(dept));
    }

    /**
     * 根据 id 删除 dept
     * @param id
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> deleteDeptById(@PathVariable("id") Integer id) {
        // 1. 校验 id 是否存在
        if(deptService.idExists(id)) {
            return ResponseResult.error(CodeEnum.NOT_FOUND_ERROR);
        }
        // 2. 删除数据
        deptService.removeById(id);
        return ResponseResult.ok(true);
    }

    /**
     * 根据 id 修改 dept
     * @param deptUpdateDTO
     * @return ResponseResult<String>
     */
    @PutMapping
    public ResponseResult<String> updateDeptById(@RequestBody DeptUpdateDTO deptUpdateDTO) {
        // 1. 校验数据
        deptService.validUpdateData(deptUpdateDTO);
        // 2. 转换类型
        Dept dept = deptService.updateDtoToPo(deptUpdateDTO);
        // 3. 更新数据库
        deptService.updateById(dept);
        // 4. 返回vo给前端
        return ResponseResult.ok(deptService.poToVo(dept));
    }

    /**
     * 查询所有 dept, 可由传入参数限制条件
     * @param
     * @return ResponseResult<List<Dept>>
     */
    @GetMapping
    public ResponseResult<Page<DeptVO>> getAllDeptList(DeptQueryDTO deptQueryDTO) {
        // 1. 校验数据
        deptService.validQueryData(deptQueryDTO);
        // 2. 根据数据解析 Wrapper
        Wrapper queryWrapper = deptService.getQueryWrapper(deptQueryDTO);
        // 3. 进行查询
        Page<Dept> deptPage = deptService.page(new Page<>(deptQueryDTO.getCurrent(), deptQueryDTO.getPageSize()), queryWrapper);
        // 4. 返回vo给前端
        return ResponseResult.ok(deptService.pageToVo(deptPage));
    }

    /**
     * 根据 id 查询 dept
     * @param id
     * @return ResponseResult<Dept>
     */
    @GetMapping("/{id}")
    public ResponseResult<DeptVO> getDeptById(@PathVariable("id") Integer id) {
        // 1. 校验数据
        if(deptService.idExists(id)) {
            return ResponseResult.error(CodeEnum.NOT_FOUND_ERROR);
        }
        // 2. 查询数据
        Dept dept = deptService.getById(id);
        // 3. 返回vo给前端
        return ResponseResult.ok(deptService.poToVo(dept));
    }
}
