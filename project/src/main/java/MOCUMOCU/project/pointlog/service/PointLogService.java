package MOCUMOCU.project.pointlog.service;

import MOCUMOCU.project.pointlog.entity.PointLog;

import java.util.List;

public interface PointLogService {

    void saveLog(PointLog pointLog);

    List<PointLog> findAllPointLog(Long customerId);
}
