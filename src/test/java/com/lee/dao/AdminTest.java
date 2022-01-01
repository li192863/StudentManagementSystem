package com.lee.dao;

import com.lee.bean.Admin;
import com.lee.bean.LoginForm;
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
public class AdminTest {
    @Autowired
    private SqlSessionFactory factory;
    private SqlSession session;
    private AdminMapper adminMapper;

    @Before
    public void init() throws IOException {
        // 3.使用SqlSessionFactory生产SqlSession (使用工厂模式创建SqlSession)
        session = factory.openSession();
        // 4.使用SqlSession创建dao接口的代理对象 (使用代理模式创建dao接口实现类)
        adminMapper = session.getMapper(AdminMapper.class);
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
        Admin admin = new Admin();
        admin.setName("admmm");
        List<Admin> users = adminMapper.selectList(null);
        for (Admin user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLogin() {
        // 5.使用代理对象执行方法
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("Admin");
        loginForm.setPassword("12345678");
        System.out.println(adminMapper.login(loginForm));
    }

    @Test
    public void testFindByName() {
        // 5.使用代理对象执行方法
        System.out.println(adminMapper.findByName("Admin"));
    }

    @Test
    public void testInsert() {
        // 5.使用代理对象执行方法
        Admin admin = new Admin();
        admin.setName("test");
        admin.setAddress("北京");
        admin.setEmail("1929614205@qq.com");
        admin.setGender('女');
        admin.setPassword("123456");
        admin.setTelephone("99945678910");
        System.out.println(adminMapper.insert(admin));

        testSelectList();
    }

    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Admin admin = new Admin();
        admin.setId(0);
        admin.setName("Admin");
        admin.setAddress("上海");
        admin.setPassword("123456");
        System.out.println(adminMapper.update(admin));
        testSelectList();
    }

    @Test
    public void testUpdatePassword() {
        // 5.使用代理对象执行方法
        Admin admin = new Admin();
        admin.setId(0);
        admin.setPassword("123456");
        admin.setTelephone("9876543210");
        System.out.println(adminMapper.updatePassword(admin));

        testSelectList();
    }

    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(adminMapper.deleteById(new Integer[]{161, 162, 163}));

        testSelectList();
    }
}
