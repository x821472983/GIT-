package test;

import Mybatis.UserDao2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import user.SysUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class hello {
 /*   @Test
    public void helloWorld1() {
        XmlBeanFactory factory = new XmlBeanFactory
                (new ClassPathResource("springMVC.xml"));
        HelloWord obj=(HelloWord) factory.getBean("HelloWord");
        obj.getMessage();
    }

    @Test
    public void helloWorld2() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("springMVC.xml");
        HelloWord obj = (HelloWord) context.getBean("HelloWord");
        obj.getMessage();
    }



    @Test
    public void addUser() {
// 1. 加载Spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("springMVC.xml");
        UserController userController = context.getBean(UserController.class);
        *//*userController.addUser();*//*
    }*/


    SqlSession sqlSession = null;
    public SqlSession getSqlSession() throws IOException {
        String resource = "Mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
// 获取到 SqlSession
        sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    /*查询数据库所有信息*/
    @Test
    public void selectAllUser() throws IOException {
        UserDao2 dao = getSqlSession().getMapper(UserDao2.class);
        try {
            List<SysUser> listUser = dao.selectAllUser();
            for (SysUser p : listUser) {
                System.out.println(p);
            }
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*靠ID查询数据库*/
    @Test
    public void selectUserById() throws Exception {
        UserDao2 dao = getSqlSession().getMapper(UserDao2.class);
        SysUser user = dao.selectUserById(1);
        System.out.println(user);
    }

    /*数据库添加*/
    @Test
    public void addUser()throws  Exception{
        UserDao2 dao = getSqlSession().getMapper(UserDao2.class);
        SysUser u =new SysUser();
        u.setUser_id(5);
        u.setName("张三");
        u.setPassword("0760");
        u.setAccount("zhangsan");
        int count = dao.insertUser(u);
        System.out.println(u);
        System.out.println(count);
        sqlSession.commit();
    }

    @Test
    public void updateUser() throws Exception {
        UserDao2 dao = getSqlSession().getMapper(UserDao2.class);
        SysUser u = new SysUser();
        u.setUser_id(1);
        u.setAccount("admin");
        u.setPassword("admin");
        u.setName("管理员");
        int count = dao.updateUser(u);
        System.out.println(u);
        System.out.println(count);
        sqlSession.commit();
    }

    @Test
    public void deleteUser() throws Exception {
        UserDao2 dao = getSqlSession().getMapper(UserDao2.class);
        int count = dao.deleteUser(3);
        sqlSession.commit();
        System.out.println(count);
    }
}
