package com.lee.service;

import com.lee.bean.Admin;
import com.lee.bean.LoginForm;

import java.util.List;

public interface AdminService {
    /**
     * 验证登录信息是否正确
     * @param loginForm
     * @return
     */
    Admin login(LoginForm loginForm);

    /**
     * 根据姓名查询指定/所有管理员信息列表
     * @return
     */
    List<Admin> selectList(Admin admin);

    /**
     * 通过姓名查询指定管理员信息
     * @param name
     * @return
     */
    Admin findByName(String name);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 更新管理员
     * @param admin
     * @return
     */
    int update(Admin admin);

    /**
     * 更新管理员密码
     * @param admin
     * @return
     */
    int updatePassword(Admin admin);

    /**
     * 删除管理员
     * @param ids
     * @return
     */
    int deleteById(Integer[] ids);
}
