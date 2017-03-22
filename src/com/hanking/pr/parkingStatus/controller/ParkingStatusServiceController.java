package com.hanking.pr.parkingStatus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanking.pr.parkingStatus.service.ParkingStatusService;

@Controller
@RequestMapping(value = "/parkingStatus")
public class ParkingStatusServiceController {

	@Autowired
	private  transient ParkingStatusService parkingStatusService;
	

	@RequestMapping(value = "/getBpaLines", method = { RequestMethod.POST })
	@ResponseBody
	public ModelMap getBpaLine(@RequestBody Map<String, Object> criteria) {
		
		return new ModelMap("status", "success").addAttribute("data", parkingStatusService.findAll());
	}
	
}
