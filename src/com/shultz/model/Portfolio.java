package com.shultz.model;

import com.shultz.*;

/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author GalShultz
 * @since 22/4/2015
 */
@SuppressWarnings("unused")
public class Portfolio {
	
	private static final int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private Stock[] stocks;
	private int portfolioSize;
	
	
	/**
	 * C'tor of Portfolio.
	 * Creates an instance of an array of Stocks {@link com.shultz.model.Stock}
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @see com.shultz.model.Stock
	 * @author GalShultz
	 */
	
	public Portfolio(String string) {
		this.title = string;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
	}
	
	/**
	 * copy C'tor for Portfolio type.
	 * @param oldPortfolio
	 * @author GalShultz
	 */
	public Portfolio (Portfolio oldPortfolio){
		
		this(oldPortfolio.getTitle());
		this.portfolioSize = oldPortfolio.getPortfolioSize();
		
		copyStocksArray(oldPortfolio.getStocks(), this.getStocks());	
	}


	/**
	 * copy an array of stock from one array (old) to new empty array (new).
	 * @param oldStocksArray
	 * @param newStocksArray
	 * @author GalShultz
	 */
	
	private void copyStocksArray(Stock[] oldStocksArray, Stock[] newStocksArray ){
			
		for(int i = 0; i<this.portfolioSize; i++){
			newStocksArray[i]= new Stock (oldStocksArray[i]);
		
		}
	}

	/**
	 * Add Stock to the portfolio's array of stocks.
	 * @param stock : a refferance of Stock type
	 * @author GalShultz
	 */
	public void addStock(Stock stock){

		if(portfolioSize<MAX_PORTFOLIO_SIZE && stock != null){
			stocks[this.portfolioSize] = stock;
			this.portfolioSize++;
		}

		else {
			System.out.println("Sorry, Portfolio is Full OR Stock is NULL");
		}
	}
	
	/**
	 * Removes all stocks from portfolio with the same symbol as received. 
	 * @param stockSymbol : the stock's symbol
	 */
	public void removeStock(String stockName){
		
		for(int i = 0; i< MAX_PORTFOLIO_SIZE; i++){
			if((this.stocks[i].getSymbol().equals(stockName) == true && stocks[i] != null)){
				if (portfolioSize != 1){
				stocks[i] = stocks[portfolioSize-1];
				}else  if (portfolioSize == 1){
					stocks[i]=null;
				}
				portfolioSize--;
				System.out.println("Stock was deleted as per request");
				return;
			}
		}
		System.out.println("Stock was not found in this Portfolio");
		return;
	}
	
	/**
	 * Method uses the portfolio's stock details.
	 * @return string with portfolio's details in HTML code.
	 */
	public String getHtmlString(){
		
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize; i++)
		{
			Stock tempStock = stocks[i];
			if (tempStock != null){
				htmlResString = htmlResString + tempStock.getHtmlDescription()+"<br>";
			}
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
	public int getPortfolioSize() {
		return portfolioSize;
	}

	
}
