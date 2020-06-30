package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

@WebServlet("/s/article/list")
public class ArticleListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		// DB 커넥터 로딩 시작
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.printf("[ClassNotFoundException 예외, %s]\n", e.getMessage());
			response.getWriter().append("DB 드라이버 클래스 로딩 실패");
			return;
		}
		// DB 커넥터 로딩 성공

		// DB 접속 시작
		String url = "jdbc:mysql://site36.iu.gy:3306/site36?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site36";
		String password = "sbs123414";

		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			// DB 접속 성공

			List<Article> articles = getArticles(connection);
			// System.out.println(article);
			
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

		} catch (SQLException e) {
			System.err.printf("[SQLException 예외, %s]\n", e.getMessage());
			response.getWriter().append("DB연결 실패");
			return;
		}
	}

	private List<Article> getArticles(Connection conn) {
//		sb.append(String.format("WHERE 1 "));
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM `article` ");
		sql += String.format("ORDER BY id DESC ");

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> rows = DBUtil.selectRows(conn, sql);
		
//		System.out.println(rows);
		
		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}
		
		return articles;
	}
}
