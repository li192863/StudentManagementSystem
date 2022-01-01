package com.lee.dao;

import com.lee.bean.Grade;
import com.lee.bean.LoginForm;
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
public class GradeTest {
    @Autowired
    private SqlSessionFactory factory;
    private SqlSession session;
    private GradeMapper mapper;

    @Before
    public void init() throws IOException {
        // 3.使用SqlSessionFactory生产SqlSession (使用工厂模式创建SqlSession)
        session = factory.openSession();
        // 4.使用SqlSession创建dao接口的代理对象 (使用代理模式创建dao接口实现类)
        mapper = session.getMapper(GradeMapper.class);
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
        Grade admin = new Grade();
        admin.setName("大");
//        admin.setClazzName("三班");
        List<Grade> users = mapper.selectList(null);
        for (Grade user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectAll() {
        List<Grade> users = mapper.selectAll();
        for (Grade user: users) {
            System.out.println(user);
        }
    }


    @Test
    public void testFindByName() {
        // 5.使用代理对象执行方法
        System.out.println(mapper.findByName("大一"));
    }
//
    @Test
    public void testInsert() {
        // 5.使用代理对象执行方法
        Grade admin = new Grade();
        admin.setName("大三");
        admin.setManager("老陈");
        admin.setEmail("1929614205@qq.com");
        admin.setTelephone("99945678910");
        admin.setIntroduction("老油条们");
        System.out.println(mapper.insert(admin));
        testSelectList();
    }
////
    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Grade admin = new Grade();
        admin.setId(1);
        admin.setManager("小陈");
        admin.setIntroduction("卷王你好");
        System.out.println(mapper.update(admin));

        testSelectList();
    }
////
    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(mapper.deleteById(new Integer[]{95}));

        testSelectList();
    }
}
