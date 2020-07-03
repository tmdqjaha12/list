package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {
	private ArticleService articleService;
	
	public ArticleController(Connection dbConn) {
		articleService = new ArticleService(dbConn);
	}

	@Override
	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
//		System.out.printf("article 컨트롤러인 나는 %s 요청을 받았다\n", actionMethodName);
		switch ( actionMethodName ) {
		case "list":
			return doActionList(req, resp);
		case "detail" :
			return doActionDetail(req, resp);
		case "doWrite" :
			return doActionDoWrite(req, resp);
//		case "search" :
//			return doActionSearch(req, resp);
		}
		
		return "";
	}

//	private String doActionSearch(HttpServletRequest req, HttpServletResponse resp) {
//		String title =req.getParameter("text");
//		
//		List<Article> articles = articleService.getForPrintDetailArticle(title);
//		
//		req.setAttribute("articles", articles);
//		
//		for(Article article : articles) {
//			System.out.println(article.getBody());
//		}
//		
//		return "article/search.jsp";
//	}

	private String doActionDoWrite(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		articleService.saveForWriteInsertArticle(title, body); //int return
		
		return "article/doWrite.jsp";
	}

//	private String doActionDetail(HttpServletRequest req, HttpServletResponse resp) {
//		int id = Integer.parseInt(req.getParameter("id"));
//		Article article = articleService.getForPrintDetailArticle(id);
//		
//		req.setAttribute("article", article);
//		
//		return "article/detail.jsp";
//	}
	
	private String doActionDetail(HttpServletRequest req, HttpServletResponse resp) {
		if (Util.empty(req, "id")) {
			return "plain:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "plain:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		Article article = articleService.getForPrintDetailArticle(id);

//		ex) article.getExtra().get("writer");

		req.setAttribute("article", article);

		return "article/detail.jsp";
	}

	private String doActionList(HttpServletRequest req, HttpServletResponse resp) {
		int cateItemId = 0;
		int page = 1;
		int count = 0;
		if(req.getParameter("cateItemId") != null) {
			cateItemId = Integer.parseInt(req.getParameter("cateItemId"));
			count = getCount(cateItemId);
		}
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		List<Article> articles = articleService.getForPrintListArticles(cateItemId, page);
		
		req.setAttribute("articles", articles);
		req.setAttribute("count", count);
		
		return "article/list.jsp";
	}
	
	private int getCount(int cateItemId) {
		return articleService.getForPrintArticlesCount(cateItemId);
	}
}
