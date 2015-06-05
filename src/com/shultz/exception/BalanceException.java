package com.shultz.exception;

public class BalanceException extends Exception{
	

	public BalanceException(){
		super("There is not enough BALANCE in Portfolio");
	}
	
	public BalanceException(String errorString){
		super(errorString);
	}
}
