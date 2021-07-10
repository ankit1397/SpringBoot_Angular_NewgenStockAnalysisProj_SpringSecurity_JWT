package com.springjwt.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.models.StockData;
import com.springjwt.payload.request.DateRangeRequest;
import com.springjwt.repository.StockRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TestController {

	
	@Autowired
	private StockRepository stockRepository;
	
	@PostMapping("/getStockData")
	@PreAuthorize("hasRole('USER')")
	public List<StockData> getStockAPI(@RequestBody DateRangeRequest dateRange) throws ParseException {
		Date startDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateRange.getStart());
		Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateRange.getEnd());
		//return stockRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(startDate,endDate);
		return stockRepository.findAllByDateBetween(startDate,endDate);
	}

}
