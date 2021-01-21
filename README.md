# 项目概述

> 针对该包内的项目的一些说明

## wechatdemo01

### 项目准备工作

#### 开发环境

- jdk 1.8
- mysql 8.0.22
- maven 3.6.3
- 开发工具：
  - 编译器：idea
  - 版本控制工具：git
  - 接口测试工具：postman



#### 关联框架

- mybatis 3.5.6
- springboot 2.1.4（当前最新稳定版本）
- junit 4（测试环境）



#### 导入环境依赖

- spring-boot-starter-web
- mybatis-spring-boot-starter
- spring-boot-starter-test
- junit
- mysql-connector-java
- c3p0



### 项目具体信息

#### 项目描述

基于 *Spring Boot* + *Mybatis* 框架整合的一个前后端分离项目，本项目为后端接口



#### 项目目录

- **main/java**
  - com.example.config【用于存放项目所需的配置类】
    - dao【存放有关dao层的配置类】
      - `DataSourceConfiguration.java`----------【实现 *c3p0* 的数据库连接池】
      - `SessionFactoryConfiguration.java`----------【数据库工厂类将，*dataSource*与*Mybatis*打通】
    - service【存放事务控制的配置类】
      - `TransactionManagementConfiguration.java`----------【实现事务管理的类】
  - com.example.controller【用于存放接口实现类】
- com.example.handler【存放事务处理类】
  - `GlobalExceptionHandler.java`----------【异常处理类】
  - com.example.dao【用于存放数据库持久层接口类】
  - com.example.entity【用于存放实体类】
  - `Wechatdemo01Application.java`----------【项目启动文件】

- **main/resources**
  - mapper【用于存放项目持久层的 *.xml* 配置文件】
  - static【用于存放项目所需的静态资源】
  - templates
  - `application.properties`----------【*springboot*主配置文件】
  - `mybatis-config.xml`----------【*mybatis*配置文件】



## seckill

> 秒杀业务

### 项目准备工作

#### 开发环境

- jdk 1.8
- mysql 8.0.22
- maven 3.6.3
- 开发工具：
  - 编译器：idea
  - 版本控制工具：git



#### 关联框架

- Spring 5.2.12
- Spring MVC 5.2.12
- MyBatis 3.4.6
- junit 4.11【测试环境】



### 项目具体信息

#### 项目描述

基于 *SSM （Spring + SpringMVC + MyBatis）* 框架整合的**秒杀系统**

- 秒杀业务的核心就是对库存的处理
- 秒杀事务的难点
  - MySQL：事务 + 行级锁
  - 高效处理竞争



#### 项目目录

- **main/java**
  - com.seckill.dao【存放持久层】
  - com.seckill.dto【存放数据传输层/关注的是*web*与*service*之间的数据传递】
    - `Exposer.java`----------【秒杀接口地址dto】
    - `SeckillExecution.java`----------【秒杀执行结果dto】
  - com.seckill.entity【存放实体类/关注的是业务封装】
  - com.seckill.enums【存放枚举类】
  - com.seckill.exception【存放异常类】
    - `RepeatKillException.java`----------【继承自*SeckillException*，重复秒杀异常】
    - `SeckillCloseException.java`----------【继承自*SeckillException*，秒杀关闭异常】
    - `SeckillException.java`----------【继承自*RuntimeException*，秒杀相关业务异常】
  - com.seckill.service【存放service层】
- **main/resources**
  - mapper【存放对应持久层的mybatis运行sql语句的配置文件】
  - spring【存放spring配置文件】
    - `spring-dao.xml`----------【spring持久层配置文件】
  - `jdbc.properties`----------【jdbc配置文件】
  - `mybatis-config.xml`----------【mybatis配置文件】
- **main/sql**【存放项目相关数据库语句】
  - `schema.sql`----------【秒杀相关的数据库建表语句】
- **main/webapp**



### 秒杀功能

- 秒杀接口暴露
- 执行秒杀
- 相关查询



## springboot-mybatis-redis

> 实现了 SpringBoot + MyBatis + Redis 整合的一个后端接口项目

### 项目准备工作

#### 开发环境

- jdk 1.8
- mysql 8.0.22
- maven 3.6.3
- 开发工具：
  - 编译器：idea
  - 版本控制工具：git
  - 接口测试工具：postman



#### 关联框架

- mybatis 3.5.6
- springboot 2.4.2（当前最新稳定版本）
- junit 4（测试环境）



#### 导入环境依赖

- spring-boot-starter-web
- mybatis-spring-boot-starter
- spring-boot-starter-test
- junit
- mysql-connector-java
- spring-boot-devtools



### 项目具体信息

#### SpringBoot热部署

SpringBoot 使用 devtools 进行热部署



#### 模板引擎

- springboot 整合 freemarker
- springboot 整合 thymeleaf

