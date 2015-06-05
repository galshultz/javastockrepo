package com.shultz.exception;

public class StockAlreadyExistsException extends Exception{
	
	public StockAlreadyExistsException(){
		super("Stock already exists in portfolio.");
	}
	public StockAlreadyExistsException(String symbol){
		super("Stock "+symbol+" already exists in portfolio.");
	}
}
