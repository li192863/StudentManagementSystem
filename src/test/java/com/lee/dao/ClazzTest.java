package com.lee.dao;

import com.lee.bean.Clazz;
import com.lee.bean.Grade;
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
public class ClazzTest {
    @Autowired
    private SqlSessionFactory factory;
    private SqlSession session;
    private ClazzMapper mapper;

    @Before
    public void init() throws IOException {
        // 3.使用SqlSessionFactory生产SqlSession (使用工厂模式创建SqlSession)
        session = factory.openSession();
        // 4.使用SqlSession创建dao接口的代理对象 (使用代理模式创建dao接口实现类)
        mapper = session.getMapper(ClazzMapper.class);
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
        Clazz admin = new Clazz();
        admin.setName("班");
        admin.setGradeName("大一");
        List<Clazz> users = mapper.selectList(null);
        for (Clazz user: users) {
            System.out.println(user);
        }
    }
//
    @Test
    public void testSelectAll() {
        List<Clazz> users = mapper.selectAll();
        for (Clazz user: users) {
            System.out.println(user);
        }
    }


    @Test
    public void testFindByName() {
        // 5.使用代理对象执行方法
        System.out.println(mapper.findByName("1班"));
    }
//
    @Test
    public void testInsert() {
        // 5.使用代理对象执行方法
        Clazz admin = new Clazz();
        admin.setName("2班");
        admin.setNumber("2");
        admin.setIntroduction("2班2班");
        admin.setCoordinator("小陈");
        admin.setEmail("1929614205@163.com");
        admin.setTelephone("99945678910");
        admin.setGradeName("大二");
        System.out.println(mapper.insert(admin));

        testSelectList();
    }
////
    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Clazz admin = new Clazz();
        admin.setId(70);
        admin.setCoordinator("小张");
        admin.setIntroduction("卷王你好");
        System.out.println(mapper.update(admin));

        testSelectList();
    }
////
    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(mapper.deleteById(new Integer[]{70}));
        testSelectList();
    }
}
