package com.crossover.techtrial.repository;

import java.util.List;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.Panel;

/**
 * DailyElectricityRepository get all DailyElectricity From Yesterday.
 * @author Hendry Rodriguez
 *
 */

@RestResource(exported = false)
@Repository
public interface DailyElectricityRepository extends CrudRepository<Panel, Long>{
	    
	    @Query(value = " SELECT  SUM(hourly_electricity.generated_electricity) as sum, AVG(hourly_electricity.generated_electricity) as average, MAX(hourly_electricity.generated_electricity) as max, MIN(hourly_electricity.generated_electricity) as min,  DATE_FORMAT(hourly_electricity.reading_at,\"%Y-%m-%d\") as date " + 
	    		  " FROM  panel  INNER JOIN hourly_electricity on panel.id = hourly_electricity.panel_id WHERE panel.serial = ?1  group by DATE_FORMAT(hourly_electricity.reading_at,\"%Y-%m-%d\") ", nativeQuery = true)
	    List<DailyElectricity> allDailyElectricityFromYesterday(String panelSerial);
}
