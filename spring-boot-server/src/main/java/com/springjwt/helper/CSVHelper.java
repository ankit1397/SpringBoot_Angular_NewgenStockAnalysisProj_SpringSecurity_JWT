package com.springjwt.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.springjwt.models.StockData;
import com.springjwt.services.StockService;

@Service
public class CSVHelper {

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private StockService stockService;

	public CSVHelper() {
	}

	@PostConstruct
	public void init() throws ParseException {
		Resource resource = resourceLoader.getResource("classpath:" + "1622471942_20MICRONS.csv");

		try {
			stockService.save(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<StockData> csvToStockDatas(InputStream is) throws ParseException {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<StockData> stockDatas = new ArrayList<StockData>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			long index = 1;
			for (CSVRecord csvRecord : csvRecords) {

				StockData stockData = new StockData(index, new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get("Date")), csvRecord.get("Open"),
						csvRecord.get("High"), csvRecord.get("Low"), csvRecord.get("Close"), csvRecord.get("Volume"),
						csvRecord.get("Dividends"), csvRecord.get("Stock Splits"), csvRecord.get("Stock"));
				stockDatas.add(stockData);
				index++;
			}

			return stockDatas;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	
}
