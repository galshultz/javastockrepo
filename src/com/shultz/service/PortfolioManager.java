package com.shultz.service;

import java.util.Calendar;
import java.util.Date;

import com.shultz.Stock;
import com.shultz.model.*;

public class PortfolioManager {
		
		
		public Portfolio getPortfolio(){
			
		Portfolio p1 = new Portfolio("portfolio");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014,10,15);
		
		Stock pih;
		Stock aal;
		Stock caas;
		
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime(); 
		
		
		aal = new Stock("AAl", 5.5F, 5.78F,date1);
		pih = new Stock ("PIH",12.4F,13.1F,date2);
		caas = new Stock("CAAS", 31.5F, 32.2F,date3);
		
		p1.addStock(aal);
		p1.addStock(pih);
		p1.addStock(caas);

		return p1;
		}
}
