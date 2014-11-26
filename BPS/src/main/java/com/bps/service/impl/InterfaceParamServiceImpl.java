package com.bps.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;



import com.bps.dao.InterfaceParamDao;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.InterfaceParamService;

public class InterfaceParamServiceImpl implements InterfaceParamService{

	
	@Autowired
	private InterfaceParamDao interfaceParamDao;
	
	
	public PagingData loadInterfaceinparam(String id, DataTableParamter rdtp) {
		List<Criterion> criterionsList=new ArrayList<Criterion>();
		criterionsList.add(Restrictions.eq("t.name", id));
		criterionsList.add(Restrictions.eqOrIsNull("flag", 1));
		Criterion[] criterions=new Criterion[criterionsList.size()];
		int i=0;
		for (Criterion criterion : criterionsList) {
			criterions[i]=criterion;	
			i++;
		}
		return interfaceParamDao.findPage(criterions, rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	
	public PagingData loadInterfaceoutparam(String id, DataTableParamter rdtp) {
		List<Criterion> criterionsList=new ArrayList<Criterion>();
		criterionsList.add(Restrictions.eq("t.name", id));
		criterionsList.add(Restrictions.eqOrIsNull("flag", 2));
		Criterion[] criterions=new Criterion[criterionsList.size()];
		int i=0;
		for (Criterion criterion : criterionsList) {
			criterions[i]=criterion;	
			i++;
		}
		 return interfaceParamDao.findPage(criterions, rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

}
