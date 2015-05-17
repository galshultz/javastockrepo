package com.shultz.service;

import java.util.Calendar;
import java.util.Date;

import org.algo.dto.PortfolioTotalStatus;
import org.algo.exception.PortfolioException;
import org.algo.model.PortfolioInterface;
import org.algo.service.PortfolioManagerInterface;

import com.shultz.model.*;
/**
 * This class represents a Portfolio Manager this class will execute different methods of Portfolio.
 * 
 * @author GalShultz
 * @since 22/4/2015
 */
public class PortfolioManager implements PortfolioManagerInterface{
			
	/**
	 * Return a new Portfolio instance.
	 * @return
	 */
	public Portfolio getPortfolio(){
			
		Portfolio myPortfolio = new Portfolio("Exercise 7 portfolio ");
		myPortfolio.updateBalance(10000);
		
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
		
		myPortfolio.buyStock(stock1, 20);
		myPortfolio.buyStock(stock2, 30);
		myPortfolio.buyStock(stock3, 40);
		
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
	
	
}
		
		
		
