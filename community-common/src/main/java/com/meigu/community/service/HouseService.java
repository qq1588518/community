package com.meigu.community.service;

import org.springframework.stereotype.Service;

import com.meigu.community.db.pojo.House;

@Service
public interface HouseService {
	
	/**
	 * 添加房源
	 * @throws Exception
	 */
	public void saveHouse(House house) throws Exception;

}
