package com.shultz.model;
import java.text.*;
import java.util.*;
/**
 * This class represents a Stock of Stocks.
 * @param symbol
 * @param bid : buy value
 * @param ask : sell value
 * @param date : creation date
 * @param recommendation : price recommendation
 * @param stockQuantity : the amount of stocks of that kind
 * @author GalShultz
 * @since 22/4/2015
 */
@SuppressWarnings("unused")
public class Stock {
	
	private static final int BUY = 0;
	private static final int SELL = 1;
	private static final int REMOVE = 2;
	private static final int HOLD = 3;

	private String symbol;
	private float bid, ask;
	private Date date;
	private int recommendation;
	private int stockQuantity;
	private SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
	
	/* TEMPORARY SITUATION - recommendation and stockQuantity are set to default of 0 
	   until next exercise or further change by lecturer (orr). */

	/**
	 * C'tor of Stock.
	 * @param newSymbol
	 * 		  : Stock Symbol.
	 * @param newBid
	 * 		  : Stock bid value.
	 * @param newAsk
	 * 		  : Stock ask value.
	 * @param date
	 * 		  : Creation date of the stock.
	 * @author GalShultz
	 */
	public Stock (String newSymbol, float newBid, float newAsk, Date date){
		this.symbol = newSymbol;
		this.bid = newBid;
		this.ask = newAsk;
		this.date = date;
		this.recommendation = 0;
		this.stockQuantity = 0;				
	}
	
	public Stock (Stock oldStock)
	{
		this(oldStock.getSymbol(),oldStock.getBid(),oldStock.getAsk(),oldStock.getDate());
	}
	

	/**
	 * Method uses the stock's details.
	 * @return string with stock's details in HTML code.
	 */
	public String getHtmlDescription(){
		return "<b>Stock symbol: </b>"+this.getSymbol()+" <b>ask: </b>"+this.getAsk()+"<b> bid: </b>"+this.getBid()+
				"<b> date: </b>"+this.formDate.format(this.getDate());
	}

	public int getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getSymbol() {
		return this.symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getBid() {
		return this.bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public float getAsk() {
		return this.ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public SimpleDateFormat getFormDate() {
		return formDate;
	}
	public void setFormDate(SimpleDateFormat formDate) {
		this.formDate = formDate;
	}
	
}
