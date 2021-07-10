package com.springjwt.services;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springjwt.helper.CSVHelper;
import com.springjwt.models.StockData;
import com.springjwt.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	public void save(InputStream is) throws ParseException {

		List<StockData> stockDatas = CSVHelper.csvToStockDatas(is);
		stockRepository.saveAll(stockDatas);

	}

}
