package com.sbs.java.blog.dao;

import java.sql.Connection;

public class MemberDao {
	private Connection dbConn;

	public MemberDao(Connection dbConn) {
		this.dbConn = dbConn;
	}
	
	

}
