package com.example.testMybatis;

import com.example.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class XMLConfigurationDemo {
    public static void main(String[] args) throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("mybatis/mybatis-config.xml");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        Reader reader = encodedResource.getReader();

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(reader, "development", new Properties());

        SqlSession sqlSession = factory.openSession();
        User user = sqlSession.selectOne("com.example.mapper.UserMapper.selectUserOne", 1);

        System.out.println(user);

        sqlSession.close();


    }
}
