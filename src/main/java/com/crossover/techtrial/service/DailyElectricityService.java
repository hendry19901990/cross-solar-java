package com.crossover.techtrial.service;

import java.util.List;

import com.crossover.techtrial.dto.DailyElectricity;

/**
 * DailyElectricityService interface for DailyElectricity.
 * @author Hendry Rodriguez
 *
 */
public interface DailyElectricityService {
	
	/**
	   * Get all DailyElectricity From Yesterday.
	   * @param panelSerial.
	   */
	List<DailyElectricity> allDailyElectricityFromYesterday(String panelSerial);

}
