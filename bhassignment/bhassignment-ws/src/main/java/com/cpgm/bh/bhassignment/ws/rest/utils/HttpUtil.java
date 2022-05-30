package com.cpgm.bh.bhassignment.ws.rest.utils;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	
	public static String getRealClientAddress(HttpServletRequest request){
		String xForwardedForHeader = request.getHeader("X-Forwarded-For");
	    if (xForwardedForHeader == null) {
	        return request.getRemoteAddr();
	    } else {
	    	StringTokenizer tokenizer = new StringTokenizer(xForwardedForHeader, ",");
	        return tokenizer.nextToken().trim();
	    }
	}

}
