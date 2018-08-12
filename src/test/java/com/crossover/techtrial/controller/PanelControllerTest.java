package com.crossover.techtrial.controller;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * PanelControllerTest class will test all APIs in PanelController.java.
 * @author Crossover
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PanelControllerTest {
  
  MockMvc mockMvc;
  
  @Mock
  private PanelController panelController;
  
  @Autowired
  private TestRestTemplate template;

  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(panelController).build();
  }

  @Test
  public void testPanelShouldBeRegistered() throws Exception {
    HttpEntity<Object> panel = getHttpEntity(
        "{\"serial\": \"232323\", \"longitude\": \"54.123232\"," 
            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
    ResponseEntity<Panel> response = template.postForEntity(
        "/api/register", panel, Panel.class);
    Assert.assertEquals(202,response.getStatusCode().value());
  }
  
  @Test
  public void testHourlyElectricityShouldBeRegistered() throws Exception {
	  
	for (int i = 0; i < 5; i++) {
		int generatedElectricity = (int )(Math.random() * 99999 + 5000);
		
		Date date = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		
		HttpEntity<Object> panel = getHttpEntity(
		        "{\"panel\":{\"id\": 1}, \"generatedElectricity\": \"" + generatedElectricity + "\"," 
		            + " \"reading_at\": \"" + dt.format(date) + "\" }");
		    ResponseEntity<HourlyElectricity> response = template.postForEntity(
		        "/api/panels/232323/hourly", panel, HourlyElectricity.class);
		    Assert.assertEquals(200,response.getStatusCode().value());
	}
  }
  
  @Test
  public void testHourlyElectricityGET() throws Exception {
	  
		    ResponseEntity<HourlyElectricity> response = template.getForEntity(
		        "/api/panels/232323/hourly", HourlyElectricity.class);
		    Assert.assertEquals(200,response.getStatusCode().value());
	
  }
  
  @Test
  public void testAllDailyElectricityFromYesterday() throws Exception {
	  
		    ResponseEntity<List<DailyElectricity>> response = template.exchange(
		        "/api/panels/232323/daily", HttpMethod.GET, null, new ParameterizedTypeReference<List<DailyElectricity>>() {});
		    Assert.assertEquals(200,response.getStatusCode().value());
	
  }

  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }
}
