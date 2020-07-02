package com.sbs.java.blog.util;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//나는 아직 미사용, 강사님이 강의로 작성하신 코드
public class Util {
	//입력되었나 안되었나 확인하는 함수
	public static boolean empty(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);
		
		return empty(paramValue);
	}
	
	public static boolean empty(Object obj) {
		if ( obj == null ) {
			return true;
		}
		
		if ( obj instanceof String ) {
			return ((String)obj).trim().length() == 0;
		}
		
		return true;
	}
	
	//숫자 맞는지 확인하는 함수
	public static boolean isNum(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);

		return isNum(paramValue);
	}
	
	public static boolean isNum(Object obj) {
		if ( obj == null ) {
			return false;
		}
		
		if ( obj instanceof Long ) {
			return true;
		} else if ( obj instanceof Integer ) {
			return true;
		} else if ( obj instanceof String ) {
			try {
				Integer.parseInt((String)obj);
				return true;
			} catch ( NumberFormatException e) {
				return false;
			}
		}
		return false;
		
	}
	
	public static int getInt(HttpServletRequest req, String paramName) {
		return Integer.parseInt(req.getParameter(paramName));
	}
	
	public static void printEx(String errName, HttpServletResponse resp, Exception e) {
		try {
			resp.getWriter()
					.append("<h1 style='color:red; font-weight:bold; text-align:left;'>[에러 : " + errName + "]</h1>");

			resp.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem;'>");
			e.printStackTrace(resp.getWriter());
			resp.getWriter().append("</pre>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
