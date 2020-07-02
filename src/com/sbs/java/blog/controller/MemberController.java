package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;

	public MemberController(Connection dbConn) {
		memberService = new MemberService(dbConn);
	}

	@Override
	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		//System.ou.printf("member 컨트롤러인 나는 %s 요청을 받았다\n", actionMethodName);
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		return "";
	}
}
