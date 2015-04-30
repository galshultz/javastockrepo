package com.shultz;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shultz.model.Stock;

import java.util.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		resp.setContentType("text/html");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15);
		
		Stock pih;
		Stock aal;
		Stock caas;
		
		//cal.getTime() - יוצרת אינסטנס חדש של קלאס דייט בזיכרון 
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime(); 
		
		String strPih, strAal, strCaas;
		
		aal = new Stock("AAl", 5.5F, 5.78F,date1);
		pih = new Stock ("PIH",12.4F,13.1F,date2);
		caas = new Stock("CAAS", 31.5F, 32.2F,date3);
		
		strAal = new String(aal.getHtmlDescription());
		strPih = new String(pih.getHtmlDescription());
		strCaas = new String(caas.getHtmlDescription());
		
		String finalResult = strAal+"<br><br>"+strPih+"<br><br>"+strCaas;
		resp.getWriter().println(finalResult);
	}
}
