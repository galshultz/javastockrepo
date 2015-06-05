package com.shultz.exception;


public class StockNotExistException extends Exception{
	
	public StockNotExistException(){
		super("Stock Was not found in portfolio");
	}
	
	public StockNotExistException(String stock){
		super("Stock " + stock +  " Was not found in portfolio");
	}
	

}
