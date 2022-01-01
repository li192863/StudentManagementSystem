package com.lee.service;

import com.lee.bean.LoginForm;
import com.lee.bean.Student;

import java.util.List;

public interface StudentService {
    /**
     * 验证登录信息是否正确
     * @param loginForm
     * @return
     */
    Student login(LoginForm loginForm);

    /**
     * 根据班级与学生名获取指定或全部学生信息列表
     * @return
     */
    List<Student> selectList(Student student);

    /**
     * 根据学号查询指定学生信息
     * @param sno
     * @return
     */
    Student findBySno(String sno);

    /**
     * 添加学生
     * @param student
     * @return
     */
    int insert(Student student);

    /**
     * 更新学生
     * @param student
     * @return
     */
    int update(Student student);

    /**
     * 更新学生密码
     * @param student
     * @return
     */
    int updatePassword(Student student);

    /**
     * 删除学生
     * @param ids
     * @return
     */
    int deleteById(Integer[] ids);
}
