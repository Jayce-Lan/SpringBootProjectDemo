package com.example.service.impl;

import com.example.dao.AreaDao;
import com.example.entity.Area;
import com.example.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.getAreaList();
    }

    @Override
    public Area getAreaById(int areaId) {
        return areaDao.getAreaById(areaId);
    }

    /**
     * @Transactional 对事物进行事务控制
     * 默认只处理RuntimeException的异常，如果需要处理其它异常，则需要在后面加入参数
     * 如@Transactional(rollbackFor = Exception.class)
     *
     * 对插入的信息进行一个非空判断
     *
     * @param area 传入的区域信息
     * @return 返回结果
     */
    @Transactional
    @Override
    public boolean insertArea(Area area) {
        if (area.getAreaName() != null && !area.getAreaName().equals("")) {
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.insertArea(area);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("插入失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("插入失败:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean updateArea(Area area) {
        if (area.getAreaId() != null && area.getAreaId() > 0) {
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.updateArea(area);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新失败:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean deleteArea(int areaId) {
        if (areaId > 0) {
            try {
                int effectedNum = areaDao.deleteArea(areaId);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除失败:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域id不能为空！");
        }
    }
}
