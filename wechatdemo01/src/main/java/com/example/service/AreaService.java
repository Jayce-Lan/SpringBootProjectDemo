package com.example.service;

import com.example.entity.Area;

import java.util.List;

public interface AreaService {
    List<Area> getAreaList();
    Area getAreaById(int areaId);
    boolean insertArea(Area area);
    boolean updateArea(Area area);
    boolean deleteArea(int areaId);
}
