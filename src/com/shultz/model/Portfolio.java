package com.shultz.model;

import java.text.DecimalFormat;

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
		this.portfolioSize = oldPortfolio.getPortfolioSize();
		this.updateBalance(oldPortfolio.getBalance());
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

		if(this.portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only "+this.portfolioSize+" stocks”");
			return;
		}else if (stock == null){
			System.out.println("There is an error with stock received! (Check if it it istanciated)");
			return;
		}else {
				int i = this.findStock (stock.getSymbol());
				if(i != -1){
					System.out.println("Stock already exists in portfolio.");
					return;
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
	
		int i = this.findStock (stockName);
			
		if(i>-1){
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
		
		int i = this.findStock (symbol);
		
		if(i>-1){	
			if(this.stocks[i].getStockQuantity() - quantity < 0){
				System.out.println("Not enough stocks to sell");
				return false;

			}else if(quantity == -1){
				this.updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
				this.stocks[i].setStockQuantity(0);
				System.out.println("Entire stock ("+symbol+") holdings was sold succefully");
				return true;

			}else {
				this.updateBalance(quantity*this.stocks[i].getBid());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()-quantity);
				System.out.println("An amount of "+quantity+" of stock ("+symbol+") was sold succefully");
				return true;
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

	public boolean buyStock(Stock stock, int quantity){
		if(stock == null || quantity < -1){
			System.out.println("There is an error! Please check your stock symbol or stock quntity.");
			return false;
		}
		if(quantity*stock.getAsk() > this.balance){
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}
			// THIS BLOCK REFEST TO THE CASE THAT THE STOCK ALREADY EXIST IN OUR STOCK ARRAY.
		int i = this.findStock (stock.getSymbol());
			
		if(i>-1){
			if(quantity == -1){
				int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
				this.updateBalance(-howManyToBuy*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+howManyToBuy);
				System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
						+ "was bought succefully.");
				return true;

			}else {
				this.updateBalance(-quantity*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
				System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
				return true;
			}
		}

		
		
		// THIS BLOCK REFEST TO THE CASE THAT THE STOCK DOES NOT EXIST IN OUR STOCK ARRAY.
		if(i == MAX_PORTFOLIO_SIZE-1){
			System.out.println("Please note that the portfolio has reached it's maximum stock capacity.");
			return false;
		}

		if (quantity == -1){
			this.addStock(stock); //add the stock to portfolioSize-1 in the stocks array.
			int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
			this.updateBalance(-(howManyToBuy*this.stocks[this.portfolioSize-1].getAsk()));
			this.stocks[i].setStockQuantity(this.stocks[this.portfolioSize-1].getStockQuantity()+howManyToBuy);
			System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
					+ "was bought succefully.");
			return true;
		} else {
			this.addStock(stock); //add the stock to portfolioSize-1 in the stocks array.
			this.updateBalance(-quantity*this.stocks[portfolioSize -1].getAsk());
			this.stocks[this.portfolioSize -1].setStockQuantity(quantity);
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
		DecimalFormat decimalFormat=new DecimalFormat("#.#"); // SHOULD WE USE IT OR NOT?
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize; i++)
		{
			Stock tempStock = stocks[i];
			if (tempStock != null){
				htmlResString = htmlResString + tempStock.getHtmlDescription()+"<br>";
			}
		}
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$, "+
		"Total Stocks Value :"+this.getStocksValue()+"$, "+"Balance :"+this.balance+"$.";
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
	
	/**
	 * Find the place of a stock in stocks array.
	 * @param stockToFind
	 * @return place of a stock in the stocks array.
	 * 		 -1 if the stock was not found in the array.
	 */
	private int findStock (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
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
	public float getBalance() {
		return balance;
	}

}
