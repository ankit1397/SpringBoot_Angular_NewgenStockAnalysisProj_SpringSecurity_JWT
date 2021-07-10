package com.springjwt.services;

import java.io.InputStream;
import java.text.ParseException;

public interface StockService {
   public void save(InputStream is) throws ParseException;
}
