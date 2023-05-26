package com.suhuamo.web.component.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhuamo.web.component.R;
import com.suhuamo.web.component.service.WebService;
import lombok.Setter;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/09
 * 自定义父类控制器，包含一些基本的增删改查接口
 */
public class WebController<T, D, S extends WebService> {

    /**
     * 该控制器类对应的pojo类型对应的service接口
     */
    @Setter
    protected S baseService;

    /**
     * 查询所有数据
     *
     * @param
     * @return R
     */
    @GetMapping("/all")
    public R all(){
        return R.ok(baseService.poToVO(baseService.list()));
    }


    /**
     * 查询所有数据，可选择使用各种匹配查询,通过传递的值来选择匹配方式
     *
     * @param condition 匹配查询的内容，Map<String, List<Pair<String, Object>>> ，第一个String为匹配模式，like、eq等，
     *                  List<Pair<String, Object>>为匹配的内容，first为项，second为值
     * @return R
     */
    @GetMapping("/allByCondition")
    public R allByCondition(@RequestParam Map<String, List<Pair<String, Object>>> condition) {
        return R.ok(baseService.poToVO(baseService.list(conditionWarpper(condition))));
    }

    /**
     * 通过分页查询获取数据，可选择使用模糊查询
     *
     * @param pageNum 页码
     * @param pageSize 页面数据大小
     * @param condition 模糊查询的条件-
     *                     Map<String, List<Pair<String, Object>>>第一个String为匹配模式，like、eq等，
     *                     List<Pair<String, Object>>为匹配的内容，first为项，second为值
     * @return R
     */
    @GetMapping("/list")
    public R list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam("condition") Map<String, List<Pair<String, Object>>> condition){
        // 如果为空，则代表无模糊查询
        if(condition.isEmpty()) {
            return R.ok(baseService.pageVO(new Page<T>(pageNum,pageSize)));
        // 否侧获取值进行模糊查询
        } else {
            return R.ok(baseService.pageVO(new Page<T>(pageNum,pageSize), conditionWarpper(condition)));
        }
    }

    /**
     *  根据id查询数据
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.ok(baseService.poToVO(baseService.getById(id)));
    }

    /**
     * 添加数据
     *
     * @param entityDTO
     * @return R
     */
    @PostMapping("/add")
    public R add(@RequestBody D entityDTO){
        T entity =  (T)baseService.dtoToPO(entityDTO);
        baseService.save(entity);
        return R.ok(entity);
    }

    /**
     * 修改数据，根据id修改其他属性
     *
     * @param entityDTO
     * @return T
     */
    @PutMapping("/update")
    public R update(@RequestBody D entityDTO){
        T entity = (T) baseService.dtoToPO(entityDTO);
        baseService.updateById(entity);
        return R.ok();
    }

    /**
     *  删除数据,可进行批量删除,使用,隔开
     * @param ids
     * @return RespMessage
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") String ids){
        baseService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }

    /**
     *  创建模糊查询相关的Wrapper
     * @param condition
     * @return QueryWrapper<T>
     */
    private QueryWrapper<T> conditionWarpper(@RequestParam("condition") Map<String, List<Pair<String, Object>>> condition) {
        QueryWrapper<T> tQueryWrapper = new QueryWrapper<>();
        condition.forEach((ops, dataList) -> {
            switch (ops) {
                case "like":
                    dataList.stream().forEach(e -> {
                        tQueryWrapper.like(e.getFirst(), e.getSecond());
                    });
                    break;
                case "eq":
                    dataList.stream().forEach(e -> {
                        tQueryWrapper.eq(e.getFirst(), e.getSecond());
                    });
                    break;
                default:
                    break;
            }
        });
        return tQueryWrapper;
    }


}
