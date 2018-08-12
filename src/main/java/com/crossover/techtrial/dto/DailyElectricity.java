package com.crossover.techtrial.dto;


/**
 * DailyElectricity class will hold sum, average,minimum and maximum electricity for a given day.
 * @author Crossover
 *
 */

public interface DailyElectricity {
  
	Long getSum();
	Double getAverage();
	Long getMin();
	Long getMax();
	String getDate();

}
