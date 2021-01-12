package com.seckill.dao;

import com.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复（该表中，商品id与用户手机号为联合主键）
     * @param seckillId 被秒杀商品id
     * @param userPhone 用户手机号
     * @return 插入的结果集数量
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询秒杀成功对象，并携带秒杀成功商品
     * @param seckillId 秒杀成功的id
     * @return 返回秒杀成功对象
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
