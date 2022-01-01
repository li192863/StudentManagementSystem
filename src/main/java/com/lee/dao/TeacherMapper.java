package com.lee.dao;

import com.lee.bean.LoginForm;
import com.lee.bean.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教师
 */
@Repository
public interface TeacherMapper {
    /**
     * 验证登录信息是否正确
     * @param loginForm
     * @return
     */
    Teacher login(LoginForm loginForm);

    /**
     * 根据教师与班级名查询指定/全部教师信息列表
     * @return
     */
    List<Teacher> selectList(Teacher teacher);

    /**
     * 根据工号查询指定教师信息
     * @param tno
     * @return
     */
    Teacher findByTno(String tno);

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    int insert(Teacher teacher);

    /**
     * 更新教师
     * @param teacher
     * @return
     */
    int update(Teacher teacher);

    /**
     * 更新教师密码
     * @param teacher
     * @return
     */
    int updatePassword(Teacher teacher);

    /**
     * 删除教师
     * @param ids
     * @return
     */
    int deleteById(Integer[] ids);
}
