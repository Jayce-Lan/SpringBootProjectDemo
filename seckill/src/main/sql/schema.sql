create database seckill;
use seckill;
-- 创建秒杀库存表
create table seckill(
    seckill_id bigint not null auto_increment comment '商品库存id',
    name varchar(120) not null comment '商品名称',
    number int not null comment '库存数量',
    start_time timestamp not null comment '秒杀开启时间',
    end_time timestamp not null comment '秒杀结束时间',
    create_time timestamp not null default current_timestamp comment '创建时间',
    primary key (seckill_id),
    key idx_start_time(start_time),
    key idx_end_time(end_time),
    key idx_create_time(create_time)
)AUTO_INCREMENT = 1000 COMMENT = '秒杀库存表';

-- 初始化数据
insert into seckill(name, number, start_time, end_time)
values('1000元秒杀iPhoneX', 100, '2021-01-12 00:00:00', '2021-01-13 00:00:00'),
      ('500元优惠券', 200, '2021-01-12 00:00:00', '2021-01-13 00:00:00'),
      ('600元无门槛优惠券', 100, '2021-01-12 00:00:00', '2021-01-13 00:00:00'),
      ('7000元秒杀MacBook Pro', 50, '2021-01-12 00:00:00', '2021-01-13 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
create table success_killed(
    seckill_id bigint not null comment '秒杀商品id',
    user_phone bigint not null comment '用户手机号',
    state tinyint not null default -1 comment '状态标识：-1.无效 0.成功 1.已付款 2.已发货',
    create_time timestamp not null default current_timestamp comment '创建时间',
    primary key (seckill_id, user_phone),  /*联合主键*/
    key idx_create_time(create_time)
)comment '秒杀成功明细表';

