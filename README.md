# 员工管理系统

## 技术选型
- 前端技术栈：vue + axios
- 后端技术栈：springboot + mybatis + mysql + tomcat + Redis

## 项目准备

### 需求分析
- 用户模块
	- 用户登录
	- 用户注册
	- 验证码实现
	- 欢迎页
	- 安全退出
	- 员工列表展示
- 员工模块
	- 员工添加
	- 员工删除
	- 员工修改
	- 员工列表加入Redis缓存实现

### 库表设计
> 分析系统需要表的数量、表与表之间的关系以及表中字段
#### 用户表
id username realname password sex stauts registerTime
``` mysql
create table t_user(
    id int primary key auto_increment,
    username varchar(60),
    realname varchar(60),
    password varchar(60),
    sex varchar(4),
    status varchar(4),
    regsterTime timestamp
);
```

#### 员工表
id name path(头像) salary age
``` mysql
create table t_emp(
    id int primary key auto_increment,
    name varchar(40),
    path varchar(100),
    salary double(10, 2),
    age int
);
```
#### 选用数据库
emp01

### 编码环节
#### 环境准备
> 搭建springboot + mybatis的环境
项目名：emps_vue
项目包结构：
- src/main/java
	- com.learn.xxx
	- com.learn.util
	- com.learn.dao
	- com.learn.service
	- ...
- src/main/resource
	- application.yml(properties)	【springboot配置文件】
	- application-dev.yml(properties)	【测试配置】
	- application-prod.yml(properties)	【生产配置】
	- com/learn/mapper	【用于存放mybatis的mapper文件】
	- com/learn/sql		【用于存放项目中的数据库文件】
	- static		【用于存放静态资源】

 > 项目编码：UTF-8

- 测试

- 部署，上线
