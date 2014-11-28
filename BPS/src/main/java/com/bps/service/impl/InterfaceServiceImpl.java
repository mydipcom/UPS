package com.bps.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.dao.InterfaceDao;
import com.bps.dto.TInterface;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.InterfaceService;

public class InterfaceServiceImpl implements InterfaceService{

	@Autowired
	private InterfaceDao interfaceDao;
	
	public PagingData loadInterfaceList(DataTableParamter rdtp) {
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="name"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getString(key)));
					}
				}
			}
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return interfaceDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		
		return interfaceDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	
	public void deleteInterface(String[] ids) {
		interfaceDao.deleteAll(ids);
		
	}


	public void createInterface(TInterface interfaces) {
		interfaceDao.create(interfaces);
		
	}


	public void updateInterface(TInterface interfaces) {
		interfaceDao.update(interfaces);
		
	}


	public String[] getInterfaceNameList() {
		// TODO Auto-generated method stub
		List<TInterface>interfaceList=interfaceDao.LoadAll();
		String interfaceName[]=new String[interfaceList.size()];
		int i=0;
		for(TInterface interfaces:interfaceList){
			interfaceName[i++]=interfaces.getName();
		}
		return interfaceName;
	}

}
