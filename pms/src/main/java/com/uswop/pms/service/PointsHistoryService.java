package com.uswop.pms.service;

import java.util.List;

import com.uswop.pms.dto.TpointsHistory;

public interface PointsHistoryService {
	
	TpointsHistory getPointsHistoryById(String id);
	
	void createPointsHistory(TpointsHistory pointsHistory);
	
	void updatePointsHistory(TpointsHistory pointsHistory);
	
	void deletePointsHistory(TpointsHistory pointsHistory);
	
	List<TpointsHistory> findPointsHistoryByStatus(String userId,Boolean status);
}
