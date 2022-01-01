package com.lee.service;

import com.lee.bean.LoginForm;
import com.lee.bean.Student;
import com.lee.bean.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring_conf/application-context.xml")
public class StudentTest {
    @Autowired
    private StudentService service;

    @Test
    public void testSelectList() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setName("dmm");
//        admin.setClazzName("三班");
        List<Student> users = service.selectList(null);
        for (Student user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLogin() {
        // 5.使用代理对象执行方法
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("张三");
        loginForm.setPassword("123456");
        System.out.println(service.login(loginForm));
    }
    @Test
    public void testFindBySno() {
        // 5.使用代理对象执行方法
        System.out.println(service.findBySno("1"));
    }

    @Test
    public void testInsert() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setSno("8");
        admin.setName("Admmm");
        admin.setAddress("北京");
        admin.setEmail("1929614205@qq.com");
        admin.setGender('女');
        admin.setPassword("123456");
        admin.setTelephone("99945678910");
        admin.setIntroduction("我是sb222222222");
        admin.setClazzName("三班");
        System.out.println(service.insert(admin));

        testSelectList();
    }

    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setId(12);
        admin.setAddress("上海");
        admin.setPassword("123456");
        admin.setIntroduction("我是Admmm");
        admin.setClazzName("二班");
        System.out.println(service.update(admin));

        testSelectList();
    }

    @Test
    public void testUpdatePassword() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setId(12);
        admin.setPassword("123456789");
        admin.setTelephone("9876543210");
        System.out.println(service.updatePassword(admin));

        testSelectList();
    }

    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(service.deleteById(new Integer[]{12}));

        testSelectList();
    }
}
