package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	
	public ArticleController(Connection dbConn) {
		articleService = new ArticleService(dbConn);
	}

	@Override
	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		//System.out.printf("article 컨트롤러인 나는 %s 요청을 받았다\n", actionMethodName);
		switch ( actionMethodName) {
		case "list":
			return doActionList(req, resp);
		}
		
		return "";
	}

	private String doActionList(HttpServletRequest req, HttpServletResponse resp) {
		List<Article> articles = articleService.getForPrintListArticles();
		
		req.setAttribute("articles", articles);
		return "article/list";
	}
}
