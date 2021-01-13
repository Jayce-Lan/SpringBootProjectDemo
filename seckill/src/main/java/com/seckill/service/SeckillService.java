package com.seckill.service;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

import java.util.List;

//业务接口
public interface SeckillService {
    /**
     * 查询所有秒杀记录
     * @return 返回秒杀的记录
     */
    List<Seckill> getSeckillList();

    /**
     * 根据id查询单个秒杀记录
     * @param id 传入被查询记录的id
     * @return 返回查询结果
     */
    Seckill getSeckillById(long id);

    /**
     * 秒杀开启时输出秒杀接口的地址
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId 传入被秒杀的id
     * @return 返回秒杀接口（开启/关闭）
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId 商品id
     * @param userPhone 用户手机号
     * @param md5 接口加密
     * @return 返回秒杀状态
     * @throws SeckillException 返回秒杀异常结果
     * @throws RepeatKillException 重复秒杀异常
     * @throws SeckillCloseException 秒杀关闭异常
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
