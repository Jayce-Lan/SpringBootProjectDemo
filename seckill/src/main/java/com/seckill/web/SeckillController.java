package com.seckill.web;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.dto.SeckillResult;
import com.seckill.entity.Seckill;
import com.seckill.enums.SeckillStatEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")     //url:模块/资源/{id}/细分     /seckill/list
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     *
     * @param model list.jsp + model = ModelAndView
     * @return
     */
    @RequestMapping(name = "/seckillList", method = RequestMethod.GET)
    public String seckillList(Model model) {
        //获取列表页
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("seckillList", seckillList);
        return "seckillList";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:seckill/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckillId);
        if (seckill == null) {
            return "forward:seckill/list";  //重定向
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * 返回一个ajax/json类型的接口
     *
     * @param seckillId 秒杀的id
     * @return 返回json接口
     */
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = {"applicarion/json; charset=UTF-8"})
    @ResponseBody   //声明返回的是json类型的数据
    public SeckillResult<Exposer> exposer(Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }

        return result;
    }

    /**
     * 秒杀接口
     *
     * @param seckillId
     * @param md5
     * @param phone 注解@CookieValue value：传入参数 required：是否必须
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"applicarion/json; charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long phone) {
//        SeckillResult<SeckillExecution> result;

        if (phone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }

        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false, execution);
        } catch (SeckillException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(false, execution);
        } catch (Exception e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false, execution);
        }
    }

    /**
     * 返回系统时间
     * @return 系统时间
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }
}
