package com.crossover.techtrial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.repository.DailyElectricityRepository;

/**
 * DailyElectricityServiceImpl for panel related handling.
 * @author Hendry Rodriguez
 *
 */
@Service
public class DailyElectricityServiceImpl implements DailyElectricityService {
	
	@Autowired
	DailyElectricityRepository dailyElectricityRepository;
	
	/* (non-Javadoc)
	   * @see com.crossover.techtrial.service.DailyElectricityService#allDailyElectricityFromYesterday(com.crossover.techtrial.dto.DailyElectricity)
	   */

	@Override
	public List<DailyElectricity> allDailyElectricityFromYesterday(String panelSerial) {
		return dailyElectricityRepository.allDailyElectricityFromYesterday(panelSerial);
	}

}
