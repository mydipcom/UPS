package com.bps.service;

import java.util.List;

import com.bps.dto.TpointsLog;

public interface PointsLogService {
	
	TpointsLog getPointsHistoryById(String id);
	
	void createPointsHistory(TpointsLog pointsHistory);
	
	void updatePointsHistory(TpointsLog pointsHistory);
	
	void deletePointsHistory(TpointsLog pointsHistory);
	
	List<TpointsLog> findPointsHistoryByStatus(String userId,Boolean status);
}
