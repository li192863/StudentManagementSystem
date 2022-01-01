package com.lee.dao;

import com.lee.bean.Admin;
import com.lee.bean.LoginForm;
import com.lee.bean.Student;
import com.lee.bean.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring_conf/application-context.xml")
public class TeacherTest {
    @Autowired
    private SqlSessionFactory factory;
    private SqlSession session;
    private StudentMapper mapper;

    @Before
    public void init() throws IOException {
        // 3.使用SqlSessionFactory生产SqlSession (使用工厂模式创建SqlSession)
        session = factory.openSession();
        // 4.使用SqlSession创建dao接口的代理对象 (使用代理模式创建dao接口实现类)
        mapper = session.getMapper(StudentMapper.class);
    }

    @After
    public void destroy() {
        session.commit(); // 提交事务
        // 6.释放资源
        session.close();
    }

    @Test
    public void testSelectList() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setName("dmm");
//        admin.setClazzName("三班");
        List<Student> users = mapper.selectList(null);
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
        System.out.println(mapper.login(loginForm));
    }

    @Test
    public void testFindBySno() {
        // 5.使用代理对象执行方法
        System.out.println(mapper.findBySno("1"));
    }

    @Test
    public void testInsert() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setSno("7");
        admin.setName("Admmm");
        admin.setAddress("北京");
        admin.setEmail("1929614205@qq.com");
        admin.setGender('女');
        admin.setPassword("123456");
        admin.setTelephone("99945678910");
        admin.setIntroduction("我是sb222222222");
        admin.setClazzName("三班");
        System.out.println(mapper.insert(admin));

        testSelectList();
    }
//
    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setId(11);
        admin.setAddress("上海");
        admin.setPassword("123456");
        admin.setIntroduction("我是Admmm");
        admin.setClazzName("二班");
        System.out.println(mapper.update(admin));

        testSelectList();
    }
//
    @Test
    public void testUpdatePassword() {
        // 5.使用代理对象执行方法
        Student admin = new Student();
        admin.setId(11);
        admin.setPassword("123456789");
        admin.setTelephone("9876543210");
        System.out.println(mapper.updatePassword(admin));

        testSelectList();
    }
//
    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(mapper.deleteById(new Integer[]{11}));

        testSelectList();
    }
}
