package com.example.demo.dao;

import com.example.demo.domain.Admin;
import com.example.demo.service.IAdminService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class TestSSM {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAdminDao adminDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        adminDao = session.getMapper(IAdminDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

    public static void main(String[] args) throws Exception {
        //1读取配置文
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4使用SqlSession创建Dao接口的代理对象
        IAdminDao adminDao = session.getMapper(IAdminDao.class);
        //5使用代理对象执行方法
        List<Admin> admins = adminDao.findAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
        //6释放资源
        session.close();
        in.close();
    }

    @Test
    public void test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminDao adm = (IAdminDao) app.getBean("AdminDao");
        //adm.saveAdmin(adm);
    }

    @Test
    public void testInsert() {
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID().toString());
        admin.setAdmin_name("123");
        admin.setAdmin_password("123");
        admin.setRealname("1234");
        admin.setAdmin_qq("12345");
        admin.setAdmin_mail("12345");
        adminDao.saveAdmin(admin);
    }

    @Test
    public void testSSM() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminService admin = (IAdminService) context.getBean("admin");
        for (Admin admin1 : admin.findAll()) {
            System.out.println(admin1);
        }
    }
}
