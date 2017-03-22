package com.hanking.pr.parkingStatus.service.impl;

import org.jboss.ejb3.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hanking.pr.parkingStatus.mapper.ParkingStatusMapper;
import com.hanking.pr.parkingStatus.pojo.ParkingStatus;
import com.hanking.pr.parkingStatus.service.ParkingStatusService;

@Service
@Transactional(readOnly = true)
public class ParkingStatusServiceImpl implements ParkingStatusService{

	@Autowired
	private transient ParkingStatusMapper parkingStatusMapper;
	
	@Override
	public ParkingStatus findAll() {
		return parkingStatusMapper.findAll();
	}

}
