package com.lee.service;

import com.lee.bean.Grade;

import java.util.List;

public interface GradeService {
    /**
     * 根据年级名称查询指定/全部年级信息列表
     * @return
     */
    List<Grade> selectList(Grade grade);

    /**
     * 查询所有年级信息列表(用于在班级管理页面中获取年级信息)
     * @return
     */
    List<Grade> selectAll();

    /**
     * 根据年级名称查询指定的年级信息
     * @param gradeName
     * @return
     */
    Grade findByName(String gradeName);

    /**
     * 添加年级
     * @param grade
     * @return
     */
    int insert(Grade grade);

    /**
     * 更新年级
     * @param grade
     * @return
     */
    int update(Grade grade);

    /**
     * 删除年级
     * @param ids
     * @return
     */
    int deleteById(Integer[] ids);
}
