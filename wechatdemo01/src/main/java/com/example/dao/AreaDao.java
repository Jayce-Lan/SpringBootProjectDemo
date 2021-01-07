package com.example.dao;

import com.example.entity.Area;

import java.util.List;

public interface AreaDao {
    List<Area> getAreaList();
    Area getAreaById(int areaId);
    int insertArea(Area area);
    int updateArea(Area area);
    int deleteArea(int areaId);
}
