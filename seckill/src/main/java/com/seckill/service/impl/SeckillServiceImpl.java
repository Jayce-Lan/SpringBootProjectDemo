package com.seckill.service.impl;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillStatEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());   //日志对象
    private final String slat = "siadasiSSUdioi8*(&*(&*(^&*quih1";  //md5盐值字符串，用于混淆md5
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 10);
    }

    @Override
    public Seckill getSeckillById(long id) {
        return seckillDao.queryById(id);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        //业务判断，如果查询不到地址，说明无此id的秒杀
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        //如果在非秒杀时间内，秒杀则未开启
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        //转换特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);

        return new Exposer(true, md5, seckillId);
    }

    /**
     * 使用注解控制事务方法
     * 优点：
     *  1.开发团队达成一致约定，明确标注事务方法的编程风格
     *  2.保证事务方法执行时间尽可能短，不穿插其它网络操作RPC/HTTP请求或者剥离到事务方法外部
     *  3.不是所有的方法都需要事务，如只有一条修改操作或者只读操作
     *
     * @param seckillId 商品id
     * @param userPhone 用户手机号
     * @param md5 接口加密
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        try {
            if (md5 == null || !md5.equals(getMD5(seckillId))) {
                throw new SeckillException("秒杀数据被重写");
            }

            //执行秒杀业务逻辑：减库存 + 记录购买行为
            int updateCount = seckillDao.reduceNumber(seckillId, new Date());
            if (updateCount <= 0) {
                throw new SeckillCloseException("秒杀结束");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("重复秒杀");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch(Exception e){
            logger.debug(e.getMessage(), e);
            //所有编译异常转为运行异常，随后会进行事务回滚
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    /**
     * 生成md5加密字符串
     * @param seckillId 传入秒杀的id
     * @return 返回加密字符串
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());   //spring工具类生成md5

        return md5;
    }
}
