package com.lee.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.bean.Grade;
import com.lee.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeController {

    // 注入业务对象
    @Autowired
    private GradeService gradeService;

    // 存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到年级信息管理页面
     * @return
     */
    @GetMapping("/goGradeListView")
    public String goGradeListView() {
        return "grade/gradeList";
    }
    /**
     * 分页查询:根据年级名称获取指定/所有年级信息列表
     * @param page
     * @param rows
     * @param gradeName
     * @return
     */
    @PostMapping("/getGradeList")
    @ResponseBody
    public Map<String, Object> getGradeList(Integer page, Integer rows, String gradeName) {
        Grade grade = new Grade();
        grade.setName(gradeName);

        // 设置每页的记录数
        PageHelper.startPage(page, rows);
        // 根据年级名称获取指定或全部年级信息列表
        List<Grade> list = gradeService.selectList(grade);
        // 封装信息列表
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        // 获取总记录数
        long total = pageInfo.getTotal();
        // 获取当前页数据列表
        List<Grade> gradeList = pageInfo.getList();
        // 存储数据对象
        result.put("total", total);
        result.put("rows", gradeList);

        return result;
    }

    /**
     * 添加年级信息
     * @param grade
     * @return
     */
    @PostMapping("/addGrade")
    @ResponseBody
    public Map<String, Object> addGrade(Grade grade) {
        // 判断年级名是否已存在
        Grade name = gradeService.findByName(grade.getName());
        if (name == null) {
            if (gradeService.insert(grade) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
            }
        } else {
            result.put("success", false);
            result.put("msg", "该年级名称已存在! 请修改后重试!");
        }
        return result;
    }

    /**
     * 根据id修改指定的年级信息
     * @param grade
     * @return
     */
    @PostMapping("/editGrade")
    @ResponseBody
    public Map<String, Object> editGrade(Grade grade) {
        // 需排除用户只修改年级名以外的信息
        Grade g = gradeService.findByName(grade.getName());
        if (g != null) {
            if (!(grade.getId().equals(g.getId()))) {
                result.put("success", false);
                result.put("msg", "该年级名称已存在! 请修改后重试!");
                return result;
            }
        }
        // 添加操作
        if (gradeService.update(grade) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    /**
     * 根据id删除指定的年级信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteGrade")
    @ResponseBody
    public Map<String, Object> deleteGrade(@RequestParam(value = "ids[]", required = true) Integer[] ids) {
        if (gradeService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
