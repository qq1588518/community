package com.meigu.community.impl;

import org.springframework.stereotype.Repository;

import com.meigu.community.db.pojo.House;
import com.meigu.community.service.HouseService;
import com.meigu.community.util.persistence.dao.BaseDao;

@Repository
public class HouseServiceImpl extends BaseDao<House> implements HouseService {

	@Override
	public void saveHouse(House house) throws Exception {
		super.save(house);
	}

}
