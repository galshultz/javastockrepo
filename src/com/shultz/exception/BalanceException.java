package com.shultz.exception;

public class BalanceException extends Exception{
	

	public BalanceException(){
		super("Please note you may not change balance to negative amount!");
	}
}
