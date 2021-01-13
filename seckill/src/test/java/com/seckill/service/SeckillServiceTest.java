package com.seckill.service;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    @Autowired
    private SeckillService seckillService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        for (Seckill seckill : seckillList) {
            logger.info("list = {}", seckill);
        }
    }

    @Test
    public void getSeckillById() {
        long id = 1000L;
        Seckill seckill = seckillService.getSeckillById(id);
        logger.info("seckill = {}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        /*
        exposed=true,
        md5='6addedd61a1a200dc3923c3e8812d444',
        seckillId=1000,
        now=0,
        start=0,
        end=0
         */
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 13866668889L;
        String md5 = "6addedd61a1a200dc3923c3e8812d444";
        SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
        logger.info("seckillExecution={}", seckillExecution);
        /*
        seckillId=1000,
        state=1,
        stateInfo='秒杀成功',
        successKilled=SuccessKilled{
            seckillId=1000,
            userPhone=13866668889,
            state=0,
            createTime=Thu Jan 14 00:16:57 CST 2021
        }
         */
    }

    @Test
    public void testSeckillLogic() {
        long id = 1003;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 13866668886L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillException e) {
                logger.error(e.getMessage());
            }
        } else {
            //秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }
}