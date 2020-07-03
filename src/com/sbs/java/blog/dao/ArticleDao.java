package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

public class ArticleDao {
	private Connection dbConn;

	public ArticleDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public List<Article> getForPrintListArticles(int cateItemId, int page) {
		int page_ = 5;

//		sb.append(String.format("WHERE 1 "));
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM `article` ");
		sql += String.format("WHERE displayStatus = 1 AND cateItemId = %d ", cateItemId);
		sql += String.format("ORDER BY id ");
		sql += String.format("DESC LIMIT %d, 5 ", (page_ * page) - 5);

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);

//		System.out.println(rows);

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public Article getForPrintDetailArticle(int id) {
		String sql = "";

//		sql += String.format("SELECT * ");
//		sql += String.format("FROM article ");
//		sql += String.format("WHERE id = %d ", id);
		sql += String.format("SELECT *, '하승범' AS extra__writer ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE 1 ");
		sql += String.format("AND id = %d ", id);
		sql += String.format("AND displayStatus = 1 ");

		Map<String, Object> row = DBUtil.selectRow(dbConn, sql);

		return new Article(row);
	}

	public int getForPrintArticlesCount(int cateItemId) {
		String sql = "";

		sql += String.format("SELECT COUNT(*) ");
		sql += String.format("FROM `article` ");
		sql += String.format("WHERE cateItemId = %d ", cateItemId);
		
		return DBUtil.selectCount(dbConn, sql);
	}

	public int saveForWriteInsertArticle(String title, String body) {
		String sql = "";
		sql += String.format("INSERT INTO article ");
		sql += String.format("SET regDate = NOW() ");
		sql += String.format(", updateDate = NOW() ");
		sql += String.format(", title = '%s' ", title);
		sql += String.format(", body = '%s' ", body);
		
		return DBUtil.insert(dbConn, sql);
	}

//	public List<Article> getForPrintDetailArticle(String title) {
//		String sql = "";
//		
//		sql += String.format("SELECT * ");
//		sql += String.format("FROM article ");
//		sql += String.format("WHERE `title` ");
//		sql += String.format("AND `title` ", title);
//		sql += String.format("LIKE '%%%s%%' ", title);
//
//		List<Article> articles = new ArrayList<>();
//		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
//
//		for (Map<String, Object> row : rows) {
//			articles.add(new Article(row));
//		}
//
//		return articles;
//	}

}
