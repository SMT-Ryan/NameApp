package com.riker.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * This filter will be used to count the number of hits
 * Servlet Filter implementation class TrafficCounter
 */
@WebFilter("/TrafficCounter")
public class TrafficCounter implements Filter {

	static int count = 0;

	/**
	 * starting an instance of the log4j logger
	 */
	public static final Logger log = Logger.getLogger(TrafficCounter.class);

	/**
	 * Default constructor. 
	 */
	public TrafficCounter() {
		//does nothing
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// does nothing
	}

	/**
	 * this method counts the incoming traffic stores it as an attribute of the 
	 * request.
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		count++;
		log.info("the count is: " + count);

		req.setAttribute("TrafficCount", count);

		if(req.getContentLength() > 0 ){
			log.info("request content greater than 0: " + req.getContentLength());
		}

		if (res.getBufferSize() >0) {
			System.out.println("so did the responce actions take place?");
		}

		//sets the contentLength of the response object to a lucky number.
		res.setContentLength(11381138);

		// pass the request along the filter chain
		chain.doFilter(req, res);
		log.info("Traffic filter chained, request object updated");
	}

	/**
	 * this method is called as initiation of the filter
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// no config needed
	}

}
