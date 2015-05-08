package com.shultz.model;

import com.shultz.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;

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
		this.setBalance(oldPortfolio.getBalance());
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
	//ASK IF WE SHOULD CHANGE THIS FUNCTION TO BOOLEAN OR NOT?!
	public void addStock(Stock stock){

		if(portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only "+this.portfolioSize+" stocks”");
			return;
		}else if (stock == null){
			System.out.println("There is an error with stock received! (Check if it it istanciated)");
			return;
		}else {
			for(int i = 0; i< this.portfolioSize; i++){
				if(stock.getSymbol().equals(this.stocks[i].getSymbol())){
					System.out.println("Stock already exists in portfolio.");
					return;
				}
			}
		}
		stocks[this.portfolioSize] = stock;
		stocks[this.portfolioSize].setStockQuantity(0); // NOT ACTUALLY NEEDED CAUSE WHEN WE CREATE STOCK DEFAULD IS 0.
		this.portfolioSize++;
		return;
	}
	
	
	/**
	 * Removes all stocks from portfolio with the same symbol as received. 
	 * @param stockSymbol : the stock's symbol
	 */
	public boolean removeStock(String stockName){
		
		if (stockName == null){
			System.out.println("The stock received is invalid!");
			return false;
		}
	
		for(int i = 0; i< this.portfolioSize; i++){
			
			if((this.stocks[i].getSymbol().equals(stockName) == true)){
				if (portfolioSize > 1){
					this.sellStock(stocks[i].getSymbol(), -1);
					stocks[i] = stocks[this.portfolioSize-1];
					stocks[this.portfolioSize-1]=null;
					
				}else  if (this.portfolioSize == 1){
					this.sellStock(stocks[i].getSymbol(), -1);
					stocks[i]=null;
				}
				portfolioSize--;
				System.out.println("Stock "+stockName+" was deleted as per request");
				return true;
			}
		}
		System.out.println("Stock was not found in this Portfolio");
		return false;
	}

	/**
	 * Method return true if the stock recommendation was updated to SELL otherwise return false. an error will be shown 
	 * on screen in case of one.
	 * Method will update the stock quantity as per request. In case quantity will be "-1" the entire stock quantity 
	 * will be sold
	 * 
	 * @param symbol
	 * @param quantity
	 * @return TRUE in case of success, otherwise FALSE.
	 */
	
	public boolean sellStock(String symbol, int quantity){
	
		if(symbol == null || quantity < -1){
			System.out.println("There is an error! Please check your stock symbol or stock quntity.");
			return false;
		}
		
		for(int i = 0; i< this.portfolioSize; i++){

			if(this.stocks[i].getSymbol().equals(symbol) == true){

				if(this.stocks[i].getStockQuantity() - quantity < 0){
					System.out.println("Not enough stocks to sell");
					return false;

				}else if(quantity == -1){
					this.balance += this.stocks[i].getStockQuantity()*this.stocks[i].getBid();
					this.stocks[i].setRecommendation(ALGO_RECOMMENDATION.SELL);
					this.stocks[i].setStockQuantity(0);
					System.out.println("Entire stock ("+symbol+") holdings was sold succefully");
					return true;

				}else {
					this.balance += quantity*this.stocks[i].getBid();
					this.stocks[i].setRecommendation(ALGO_RECOMMENDATION.SELL);
					this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()-quantity);
					System.out.println("An amount of "+quantity+" of stock ("+symbol+") was sold succefully");
					return true;
				}
			}

		}
		System.out.println("Stock was not found in this Portfolio");
		return false; 
	}
	
	/**
	 * Method return true if the stock recommendation was updated to BUY otherwise return false. an error will be shown 
	 * on screen in case of one.
	 * Method will update the stock quantity as per request. In case quantity will be "-1" the entire balance quantity 
	 * will be used
	 * 
	 * @param symbol
	 * @param quantity
	 * @return TRUE in case of success, otherwise FALSE.
	 */
	// ASK REGARDING THE STATUS!!! WHY SHOULD IT BE BUY, WHAT IS THE LOGIC BEHIND THIS ENUMS??

	public boolean buyStock(Stock stock, int quantity){
		int i = 0;
		if(stock == null || quantity < -1){
			System.out.println("There is an error! Please check your stock symbol or stock quntity.");
			return false;
		}
		if(quantity*stock.getAsk() > this.balance){
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}
		for(i = 0; i< this.portfolioSize; i++){

			if(this.stocks[i].getSymbol().equals(stock.getSymbol()) == true){
				
				if(quantity == -1){
					int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
					this.balance -= howManyToBuy*this.stocks[i].getAsk();
					this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+howManyToBuy);
					this.stocks[i].setRecommendation(ALGO_RECOMMENDATION.BUY);
					System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
							+ "was bought succefully.");
					return true;

				}else {
					this.stocks[i].setRecommendation(ALGO_RECOMMENDATION.BUY);
					this.balance -= quantity*this.stocks[i].getAsk();
					this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
					System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
					return true;
				}
			}

		}
		if(i == MAX_PORTFOLIO_SIZE){
			System.out.println("Please note that the portfolio has reached it's maximum stock capacity.");
			return false;
		}else{
			this.addStock(stock);
			this.balance -= quantity*this.stocks[portfolioSize -1].getAsk();
			this.stocks[portfolioSize -1].setStockQuantity(quantity);
			System.out.println("Stock "+stock.getSymbol()+" was added successfuly to the portfolio. With quantity of "
					+ quantity+" stocks.");
			return true;
			
		}
	}	

	/**
	 * Method calculates the portfolio's total stocks value.
	 * @return float representing portfolio's total stocks value.
	 * @author GalShultz
	 */
	public float getStocksValue(){
		float totalValue =0;
		for(int i = 0; i<this.portfolioSize ;i++){
			totalValue += this.stocks[i].getStockQuantity()*this.stocks[i].getBid();
		}
		return totalValue;		
	}
	
	/**
	 * Method calculates the portfolio's total value.
	 * @return float representing portfolio's total value.
	 * @author GalShultz
	 */
	public float getTotalValue(){
		
		return this.getStocksValue()+this.balance;		
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
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$.<br>"+
		"Total Stocks Value :"+this.getStocksValue()+"$. <br>"+"Balance :"+this.balance+"$.";
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
