package com.seckill.dao;

import com.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /**
     * 减库存
     *
     * 注意：
     *  当传入参数为多个时，Java不会自动识别形参，而是以arg0,arg1...的形式传给mybatis
     *  因此需要使用 @Param() 定义参数
     *
     * @param seckillId 被减库存的id
     * @param killTime 减库存的时间
     * @return 更新语句的行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询库存
     * @param seckillId 库存id
     * @return 返回结果集
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询所有被秒杀库存
     * @param offset 偏移量
     * @param limit 取记录的条数
     * @return 返回查询列表
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
