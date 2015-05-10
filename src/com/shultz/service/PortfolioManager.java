package com.shultz.service;

import java.util.Calendar;
import java.util.Date;

import com.shultz.model.*;
/**
 * This class represents a Portfolio Manager this class will execute different methods of Portfolio.
 * 
 * @author GalShultz
 * @since 22/4/2015
 */
public class PortfolioManager {
			
	/**
	 * Return a new Portfolio instance.
	 * @return
	 */
	public Portfolio getPortfolio(){
			
		Portfolio portfolio = new Portfolio("Exercise 7 portfolio ");
		portfolio.updateBalance(10000);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014,11,15);
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime(); 
		Stock stock1;
		Stock stock2;
		Stock stock3;
		
		stock1 = new Stock ("PIH",8.5F,10F,date2);
		stock2 = new Stock("AAL", 25.5F, 30F,date1);
		stock3 = new Stock("CAAS", 15.5F, 20F,date3);
		
		portfolio.buyStock(stock1, 20);
		portfolio.buyStock(stock2, 30);
		portfolio.buyStock(stock3, 40);
		
		portfolio.sellStock("AAL", -1);
		portfolio.removeStock("CAAS");
		
		return portfolio;
	}
}
		
		
		
