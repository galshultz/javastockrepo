package com.shultz;


import java.text.*;
import java.util.*;

public class Stock {
	private String symbol;
	private float bid, ask;
	private java.util.Date date;
	
	private SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public Stock (String newSymbol, float newBid, float newAsk, Date date){
		this.symbol = newSymbol;
		this.bid = newBid;
		this.ask = newAsk;
		this.date = date;
		
	}
	
	
	public String getHtmlDescription(){
		return "<b>Stock symbol: </b>"+this.getSymbol()+" <b>ask: </b>"+this.getAsk()+"<b> bid: </b>"+this.getBid()+
				"<b> date: </b>"+this.formDate.format(this.getDate());
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

	public java.util.Date getDate() {
		return this.date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public SimpleDateFormat getFormDate() {
		return formDate;
	}

	public void setFormDate(SimpleDateFormat formDate) {
		this.formDate = formDate;
	}
	
	
}
