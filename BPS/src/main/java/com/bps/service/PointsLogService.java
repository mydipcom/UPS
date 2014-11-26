package com.bps.service;



import java.util.List;

import com.bps.dto.TpointsLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface PointsLogService {
	
	TpointsLog getPointsHistoryById(Integer id);
	
	void createPointsHistory(TpointsLog pointsHistory);
	
	void updatePointsHistory(TpointsLog pointsHistory);
	
	void deletePointsHistory(TpointsLog pointsHistory);
	
	void deletePointsHistoryByIds(Integer[] ids);
	
	public PagingData loadPointLogList(DataTableParamter rdtp);
	
	public   List<TpointsLog>  getPointsHistoryByRuleId(Integer id);
	
	public PagingData loadPointLogByUserId(DataTableParamter rdtp,String id);
}
