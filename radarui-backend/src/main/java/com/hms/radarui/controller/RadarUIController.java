package com.hms.radarui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.MappingIterator;



import com.fasterxml.jackson.dataformat.csv.*;

/**
 * 
 * @author EVRY IND
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/radarui")
public class RadarUIController {

	protected final static Logger logger = LoggerFactory.getLogger(RadarUIController.class);
	
	

	
	
	@Autowired
	private RestTemplate restTemplate;


	
	
	@CrossOrigin
	@RequestMapping(value = "/getRadarData", method = RequestMethod.GET)
	public String getData() {
		
		 File input = new File("src/main/resources/data.csv");
	      try {
	         CsvSchema csv = CsvSchema.emptySchema().withHeader();
	         CsvMapper csvMapper = new CsvMapper();
	         MappingIterator<Map<?, ?>> mappingIterator =  csvMapper.reader().forType(Map.class).with(csv).readValues(new InputStreamReader(new FileInputStream(input), "ISO-8859-1"));
	         List<Map<?, ?>> list = mappingIterator.readAll();
	        return JSONArray.toJSONString(list);
	      // System.out.println(list.toString());
	      } catch(Exception e) {
	    	 
	         e.printStackTrace();
	         return null;
	      }
	}

	
	
	//Testing
	public static void main(String[] args) {
		 File input = new File("src/main/resources/data.csv");
	      try {
	         CsvSchema csv = CsvSchema.emptySchema().withHeader();
	         CsvMapper csvMapper = new CsvMapper();
	         MappingIterator<Map<?, ?>> mappingIterator =  csvMapper.reader().forType(Map.class).with(csv).readValues(new InputStreamReader(new FileInputStream(input), "ISO-8859-1"));
	         List<Map<?, ?>> list = mappingIterator.readAll();
	         String jsonStr = JSONArray.toJSONString(list);
	       System.out.println(jsonStr);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}
}
