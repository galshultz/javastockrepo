package com.shultz.model;

import com.shultz.Stock;

/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author GalShultz
 * @since 22/4/2015
 */
public class Portfolio {
	
	static final int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private Stock[] stocks;
	private int portfolioSize;
	
	
	/**
	 * C'tor of Portfolio.
	 * Receives the title of the portfolio.
	 * Creates an instance of an array of Stocks {@link com.shultz.Stock}
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @see com.shultz.Stock
	 */
	public Portfolio(String title) {
		this.title = title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		
	}
	
	public void addStock(Stock stock){
		
		if(portfolioSize<5)
		{
			stocks[this.portfolioSize] = stock;
			portfolioSize++;
		}
	}
	
	/**
	 * Method uses the portfolio's stock details.
	 * @return string with portfolio's detains in HTML code.
	 */
	public String getHtmlString(){
		
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.title+"</h1> <br>";
		
		for(int i=0; i<portfolioSize;i++)
		{
			htmlResString = htmlResString + stocks[i].getHtmlDescription()+"<br>";
		}
		
		return htmlResString;	
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Stock[] getStocks() {
		return stocks;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	
	
	
	
}
