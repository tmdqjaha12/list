package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService(Connection dbConn) {
		articleDao = new ArticleDao(dbConn);
	}

	public List<Article> getForPrintListArticles(int cateItemId,int  page) {
		return articleDao.getForPrintListArticles(cateItemId, page);
	}

	public Article getForPrintDetailArticle(int id) {
		return articleDao.getForPrintDetailArticle(id);
	}
	
//	public List<Article> getForPrintDetailArticle(String title) {
//		return articleDao.getForPrintDetailArticle(title);
//	}

	public int getForPrintArticlesCount(int cateItemId) {
		return articleDao.getForPrintArticlesCount(cateItemId);
	}

	public int saveForWriteInsertArticle(String title, String body) {
		return articleDao.saveForWriteInsertArticle(title, body);
	}

}
