package com.lee.service;

import com.lee.bean.Admin;
import com.lee.bean.LoginForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring_conf/application-context.xml")
public class AdminTest {
    @Autowired
    private AdminService service;

    @Test
    public void testSelectList() {
        // 5.使用代理对象执行方法
        Admin admin = new Admin();
        admin.setName("admmm");
        List<Admin> users = service.selectList(null);
        for (Admin user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLogin() {
        // 5.使用代理对象执行方法
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("Admin");
        loginForm.setPassword("123456");
        System.out.println(service.login(loginForm));
    }

    @Test
    public void testFindByName() {
        // 5.使用代理对象执行方法
        System.out.println(service.findByName("Admin"));
    }

    @Test
    public void testInsert() {
        // 5.使用代理对象执行方法
        Admin admin = new Admin();
        admin.setName("Admmm");
        admin.setAddress("北京");
        admin.setEmail("1929614205@qq.com");
        admin.setGender('女');
        admin.setPassword("123456");
        admin.setTelephone("99945678910");
        System.out.println(service.insert(admin));

        testSelectList();
    }

    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Admin admin = new Admin();
        admin.setId(0);
        admin.setName("Admin");
        admin.setAddress("陕西西安");
        admin.setPassword("123456789");
        System.out.println(service.update(admin));

        testSelectList();
    }

    @Test
    public void testUpdatePassword() {
        // 5.使用代理对象执行方法
        Admin admin = new Admin();
        admin.setId(0);
        admin.setPassword("123456");
        admin.setTelephone("9876543210");
        System.out.println(service.updatePassword(admin));

        testSelectList();
    }

    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(service.deleteById(new Integer[]{164}));

        testSelectList();
    }
}
