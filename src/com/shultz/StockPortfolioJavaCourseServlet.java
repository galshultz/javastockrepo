package com.shultz;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockPortfolioJavaCourseServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		int num1 =4, num2 = 3, num3 = 7;
		int result = 0 ;
				
		result = (num1+num2)*num3;
		String resultString = new String("<h1>Result of ("+num1+"+"+num2+")*"+num3+" = "+result+"</h1>");
		
		resp.getWriter().println(resultString);
	}
}
