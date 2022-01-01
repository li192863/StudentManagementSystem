package com.lee.service;

import com.lee.bean.Clazz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring_conf/application-context.xml")
public class ClazzTest {
    @Autowired
    private ClazzService service;

    @Test
    public void testSelectList() {
        // 5.使用代理对象执行方法
        Clazz admin = new Clazz();
        admin.setName("班");
        admin.setGradeName("大一");
        List<Clazz> users = service.selectList(null);
        for (Clazz user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectAll() {
        List<Clazz> users = service.selectAll();
        for (Clazz user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByName() {
        // 5.使用代理对象执行方法
        System.out.println(service.findByName("1班"));
    }

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
        System.out.println(service.insert(admin));

        testSelectList();
    }

    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Clazz admin = new Clazz();
        admin.setId(71);
        admin.setCoordinator("小张");
        admin.setIntroduction("卷王你好");
        System.out.println(service.update(admin));

        testSelectList();
    }

    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(service.deleteById(new Integer[]{71}));

        testSelectList();
    }
}
