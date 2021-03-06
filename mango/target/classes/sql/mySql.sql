use wechatdemo01;

#用户表包含用户信息，通过dept_id与机构表关联
create table sys_user(
    id bigint(20) not null auto_increment comment '编号',
    name varchar(50) not null comment '用户名',
    nick_name varchar(150) default null comment '昵称',
    avatar varchar(150) default null comment '头像',
    password varchar(100) default null comment '密码',
    salt varchar(40) default null comment '加密盐',
    email varchar(100) default null comment '邮箱',
    mobile varchar(100) default null comment '手机号',
    status tinyint(4) default null comment '状态 0：禁用 1：正常',
    dept_id bigint(20) default null comment '机构id',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    del_flag tinyint(4) default '0' comment '是否删除 -1：已删除 0：正常',
    primary key (id),
    unique key name (name)
)engine=InnoDB AUTO_INCREMENT=34 default charset=utf8 comment = '用户管理';

show create table sys_user;

#角色表代表用户角色，用户拥有角色，角色拥有菜单，菜单拥有权限标识，不同角色拥有不同权限
create table sys_role(
    id bigint(20) not null auto_increment comment '编号',
    name varchar(100) default null comment '角色名称',
    remark varchar(100) default null comment '备注',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    del_flag tinyint(4) default '0' comment '是否删除 -1：已删除 0：正常',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=9 default charset=utf8 comment = '角色管理';

show create table sys_role;

#机构表代表组织机构，可有子机构，用户归属于机构
create table sys_dept(
    id bigint(20) not null auto_increment comment '编号',
    name varchar(50) default null comment '机构名称',
    parent_id bigint(20) default null comment '上级机构id， 一级机构为0',
    order_num int(11) default null comment '排序',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    del_flag tinyint(4) default '0' comment '是否删除 -1：已删除 0：正常',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=36 default charset=utf8 comment = '机构管理';

show create table sys_dept;

#菜单表，分为菜单目录、菜单和操作按钮3中类型，可以进行权限控制
create table sys_menu(
    id bigint(20) not null auto_increment comment '编号',
    name varchar(50) default null comment '菜单名称',
    parent_id bigint(20) default null comment '父级菜单id，一级菜单为0',
    url varchar(200) default null comment '菜单url，类型，1：普通页面（如用户管理/sys/user）2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe：前缀+目标url',
    perms varchar(500) default null comment '授权（多个用逗号分隔，如sys:user:add,sys:user:edit）',
    type int(11) default null comment '类型 0.目录 1.菜单 2.按钮',
    icon varchar(50) default null comment '菜单图标',
    order_num int(11) default null comment '排序',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    del_flag tinyint(4) default '0' comment '是否删除 -1：已删除 0：正常',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=45 default charset=utf8 comment = '菜单管理';

show create table sys_menu;

#用户角色表，用户表与角色表的中间表
create table sys_user_role(
    id bigint(20) not null auto_increment comment '编号',
    user_id bigint(20) default null comment '用户id',
    role_id bigint(20) default null comment '角色id',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=76 default charset=utf8 comment = '用户角色';

show create table sys_user_role;

#角色菜单表，角色表和菜单表的中间表
create table sys_role_menu(
    id bigint(20) not null auto_increment comment '编号',
    role_id bigint(20) default null comment '角色id',
    menu_id bigint(20) default null comment '菜单id',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=469 default charset=utf8 comment = '角色菜单';

show create table sys_role_menu;

#角色机构表，角色表和机构表的中间表
create table sys_role_dept(
    id bigint(20) not null auto_increment comment '编号',
    role_id bigint(20) default null comment '角色id',
    dept_id bigint(20) default null comment '机构id',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=4 default charset=utf8 comment = '角色机构';

show create table sys_role_dept;

#字典表，存储系统常用枚举类型数据
create table sys_dict(
    id bigint(20) not null auto_increment comment '编号',
    value varchar(100) not null comment '数据值',
    label varchar(100) not null comment '标签名',
    type varchar(100) not null comment '类型',
    description varchar(100) not null comment '描述',
    sort decimal(10, 0) not null comment '排序（升序）',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    remarks varchar(255) default null comment '备注信息',
    del_flag tinyint(4) default '0' comment '是否删除 -1：已删除 0：正常',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=5 default charset=utf8 comment = '角色机构';

show create table sys_dict;

#配置表，主要存储系统配置信息
create table sys_config(
    id bigint(20) not null auto_increment comment '编号',
    value varchar(100) not null comment '数据值',
    label varchar(100) not null comment '标签名',
    type varchar(100) not null comment '类型',
    description varchar(100) not null comment '描述',
    sort decimal(10, 0) not null comment '排序（升序）',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    remarks varchar(255) default null comment '备注信息',
    del_flag tinyint(4) default '0' comment '是否删除 -1：已删除 0：正常',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=5 default charset=utf8 comment = '系统配置表';

show create table sys_config;

#操作日志表，记录系统用户日常操作信息
create table sys_log(
    id bigint(20) not null auto_increment comment '编号',
    user_name varchar(50) default null comment '用户名',
    operation varchar(50) default null comment '用户操作',
    method varchar(200) default null comment '请求方法',
    params varchar(5000) default null comment '请求参数',
    time bigint(20) not null comment '执行时常（毫秒）',
    ip varchar(64) default null comment 'IP地址',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=2798 default charset=utf8 comment = '系统操作日志表';

show create table sys_log;

#登录日志表，记录用户登录和退出的状态
create table sys_login_log(
    id bigint(20) not null auto_increment comment '编号',
    user_name varchar(50) default null comment '用户名',
    status varchar(50) default null comment '登录状态（online：在线，登录初始状态，方便统计在线人数；login：退出登录后将online置为login；logout：退出登录）',
    ip varchar(64) default null comment 'IP地址',
    create_by varchar(50) default null comment '创建人',
    create_time datetime default null comment '创建时间',
    last_update_by varchar(50) default null comment '更新人',
    last_update_time datetime default null comment '更新时间',
    primary key (id)
)engine=InnoDB AUTO_INCREMENT=2798 default charset=utf8 comment = '系统登录日志';

show create table sys_login_log;