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
	
	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD 
	}
	
	private String title;
	private Stock[] stocks;
	private int portfolioSize;
	private float balance;
	
	
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
		this.balance = 0;
	}
	
	/**
	 * copy C'tor for Portfolio type.
	 * @param oldPortfolio
	 * @author GalShultz
	 */
	public Portfolio (Portfolio oldPortfolio){
		
		this(oldPortfolio.getTitle());
		this.setPortfolioSize(oldPortfolio.getPortfolioSize());
		
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

		if(portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only "+this.portfolioSize+" stocks”");
			return;
		}else if (stock == null){
			System.out.println("There is an error with stock received! (Check if it it istanciated)");
			return;
		}else {
			for(int i = 0; i< this.portfolioSize; i++){
				if(stock.getSymbol() == this.stocks[i].getSymbol()){
					System.out.println("Stock already exists in portfolio.");
					return;
				}
			}
		}
		stocks[this.portfolioSize] = stock;
		this.portfolioSize++;
		return;
	}
	
	
	/**
	 * Removes all stocks from portfolio with the same symbol as received. 
	 * @param stockSymbol : the stock's symbol
	 */
	public void removeStock(String stockName){
		
		if (stockName == null){
			System.out.println("The stock received is invalid!");
			return;
		}
	
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
	
	
	
	
	/**
	 * method receives amount and calculates the current balance.
	 * @param amount
	 * @author GalShultz
	 */
	
	public void updateBalance (float amount){
		float tempBalance = this.balance + amount;
		if(tempBalance < 0){
			System.out.println("Please note you may not change balance to negative amount!");
		}else {
			this.balance = tempBalance;
			System.out.println("Balance has been updated to "+ this.balance);
		}
		
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

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	
}
