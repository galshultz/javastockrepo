package com.shultz.servlet;

import javax.servlet.ServletException;

import org.algo.service.ServiceManager;

import com.shultz.exception.BalanceException;
import com.shultz.exception.PortfolioFullException;
import com.shultz.exception.StockAlreadyExistsException;
import com.shultz.exception.StockNotExistException;
import com.shultz.service.PortfolioManager;

@SuppressWarnings("serial")
public class InitServlet extends  javax.servlet.http.HttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);	

	}
}
