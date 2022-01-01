package com.lee.dao;

import com.lee.bean.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 班级
 */
@Repository
public interface ClazzMapper {
    /**
     * 根据班级名称获取指定/全部班级信息列表
     * @return
     */
    List<Clazz> selectList(Clazz clazz);

    /**
     * 查询所有班级列表信息(用于在学生管理页面中获取班级信息)
     * @return
     */
    List<Clazz> selectAll();

    /**
     * 获取指定名称的班级信息
     * @param clazzName
     * @return
     */
    Clazz findByName(String clazzName);

    /**
     * 添加班级
     * @param clazz
     * @return
     */
    int insert(Clazz clazz);

    /**
     * 更新班级
     * @param clazz
     * @return
     */
    int update(Clazz clazz);

    /**
     * 删除班级
     * @param ids
     * @return
     */
    int deleteById(Integer[] ids);
}
