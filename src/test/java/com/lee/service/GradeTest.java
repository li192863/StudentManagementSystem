package com.lee.service;

import com.lee.bean.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring_conf/application-context.xml")
public class GradeTest {
    @Autowired
    private GradeService service;

    @Test
    public void testSelectList() {
        // 5.使用代理对象执行方法
        Grade admin = new Grade();
        admin.setName("大");
//        admin.setClazzName("三班");
        List<Grade> users = service.selectList(null);
        for (Grade user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectAll() {
        List<Grade> users = service.selectAll();
        for (Grade user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByName() {
        // 5.使用代理对象执行方法
        System.out.println(service.findByName("大一"));
    }

    @Test
    public void testInsert() {
        // 5.使用代理对象执行方法
        Grade admin = new Grade();
        admin.setName("大三");
        admin.setManager("老陈");
        admin.setEmail("1929614205@qq.com");
        admin.setTelephone("99945678910");
        admin.setIntroduction("老油条们");
        System.out.println(service.insert(admin));

        testSelectList();
    }

    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法
        Grade admin = new Grade();
        admin.setId(96);
        admin.setManager("小陈");
        admin.setIntroduction("卷王你好");
        System.out.println(service.update(admin));

        testSelectList();
    }

    @Test
    public void testDeleteByID() {
        // 5.使用代理对象执行方法
        System.out.println(service.deleteById(new Integer[]{96}));

        testSelectList();
    }
}
