package com.bps.service;

import com.bps.dto.TpointsLog;

public interface PointsLogService {
	
	TpointsLog getPointsHistoryById(String id);
	
	void createPointsHistory(TpointsLog pointsHistory);
	
	void updatePointsHistory(TpointsLog pointsHistory);
	
	void deletePointsHistory(TpointsLog pointsHistory);
	
}
