package com.springjwt.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
@Entity
@Data
public class StockData {

	@Id
	private long serialNo;
	@Temporal(TemporalType.DATE)
    Date date;
	String open;
	String high;
	String low;
	String close;
	String volume;
	String dividends;
	String stockSplit;
	String stock;

	public StockData() {

	}

	public StockData(long serialNo, Date date, String open, String high, String low, String close, String volume,
			String dividends, String stockSplit, String stock) {
		super();
		this.serialNo = serialNo;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.dividends = dividends;
		this.stockSplit = stockSplit;
		this.stock = stock;
	}

	



}
