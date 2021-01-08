package com.example.dao;

import com.example.entity.Area;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    void getAreaList() {
        List<Area> areaList = areaDao.getAreaList();
        for (Area area : areaList) {
            System.out.println(area);
        }
    }

    @Test
    void getAreaById() {
        Area area = areaDao.getAreaById(1);
        System.out.println(area);
    }

    @Test
    void insertArea() {
        Area area = new Area();
        area.setAreaName("兴宁区");
        area.setPriority(1);
        area.setCreateTime(new Date());
        int count = areaDao.insertArea(area);
        System.out.println(count == 1  ? "运行成功" : "运行失败");
    }

    @Test
    void updateArea() {
        Area area = new Area();
        area.setAreaName("五象新区");
        area.setLastEditTime(new Date());
        area.setAreaId(3);
        int count = areaDao.updateArea(area);
        System.out.println(count == 1  ? "运行成功" : "运行失败");
    }

    @Test
    void deleteArea() {
        int count = areaDao.deleteArea(4);
        System.out.println(count == 1  ? "运行成功" : "运行失败");
    }
}