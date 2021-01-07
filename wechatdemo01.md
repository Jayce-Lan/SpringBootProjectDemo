# wechatdemo01

## 项目准备工作

### 开发环境

- jdk 1.8
- mysql 8.0.22
- maven 3.6.3
- 开发工具：idea



### 关联框架

- mybatis 3.5.6
- springboot 2.1.4（当前最新稳定版本）
- junit 4（测试环境）



### 导入环境依赖

- spring-boot-starter-web
- mybatis-spring-boot-starter
- spring-boot-starter-test
- junit
- mysql-connector-java
- c3p0



## 项目具体信息

### 项目描述

基于 *Spring Boot* + *Mybatis* 框架整合的一个前后端分离项目，本项目为后端接口



### 项目目录

- **main/java**
  - com.example.config【用于存放项目所需的配置类】
    - `DataSourceConfiguration.java`----------【实现 *c3p0* 的数据库连接池】
    - `SessionFactoryConfiguration.java`----------【数据库工厂类将，*dataSource*与*Mybatis*打通】
  - com.example.controller【用于存放接口实现类】
  - com.example.dao【用于存放数据库持久层接口类】
  - com.example.entity【用于存放实体类】
  - `Wechatdemo01Application.java`----------【项目启动文件】

- **main/resources**
  - mapper【用于存放项目持久层的 *.xml* 配置文件】
  - static【用于存放项目所需的静态资源】
  - templates
  - `application.properties`----------【springboot主配置文件】
  - `mybatis-config.xml`----------【mybatis配置文件】