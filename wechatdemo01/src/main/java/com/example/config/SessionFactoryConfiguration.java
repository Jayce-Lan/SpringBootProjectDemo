package com.example.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

//事务提交驱动，将dataSource与Mybatis打通，并且创建工厂类

@Configuration
public class SessionFactoryConfiguration {
    @Value("${mybatis_config_file}")
    private String mybatisConfigFilePath;
    @Value("${mapper_path}")
    private String mapperPath;
    @Value("${entity_package}")
    private String entityPackage;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    /**
     * 创建SqlSessionFactory工厂，并且自动装配到bean容器中
     * @return 返回一个SqlSessionFactory工厂
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));  //扫描mybatis配置文件路径
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;   //mapper配置文件存储路径
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));

        sqlSessionFactoryBean.setDataSource(dataSource);        //与连接池互通

        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);    //映射实体类扫描路径

        return sqlSessionFactoryBean;
    }
}
