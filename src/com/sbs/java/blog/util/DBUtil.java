package com.sbs.java.blog.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
//	public int delete(String sql) {
//		int affectedRows = 0;
//
//		Statement stmt;
//		try {
//			stmt = connection.createStatement();
//			affectedRows = stmt.executeUpdate(sql);
//		} catch (SQLException e) {
//			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
//		}
//
//		return affectedRows;
//	}
//
//	public int update(String sql) {
//		int affectedRows = 0;
//
//		Statement stmt;
//		try {
//			stmt = connection.createStatement();
//			affectedRows = stmt.executeUpdate(sql);
//		} catch (SQLException e) {
//			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
//		}
//
//		return affectedRows;
//	}
//
//	public int insert(String sql) {
//		int id = -1;
//
//		try {
//			Statement stmt = connection.createStatement();
//			stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
//			ResultSet rs = stmt.getGeneratedKeys();
//
//			if (rs.next()) {
//				id = rs.getInt(1);
//			}
//
//		} catch (SQLException e) {
//			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
//		}
//
//		return id;
//	}

	public static int insert(Connection connection, String sql) {
		int id = -1;
		
		try {
			Statement stmt = connection.createStatement();
			stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
		}

		return id;
	}

	public static int selectCount(Connection connection, String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		int rowcount = 0;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				rowcount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.err.println("[SQLException 예외]");
			System.err.println("msg : " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}
		}

		return rowcount;
	}

	public static Map<String, Object> selectRow(Connection connection, String sql) {
		List<Map<String, Object>> rows = selectRows(connection, sql);

		// System.out.println("rows : " + rows); []비어있다

		if (rows.size() == 0) {
			return new HashMap<>();
		}

		return rows.get(0);
	}

	public static List<Map<String, Object>> selectRows(Connection connection, String sql) {
		List<Map<String, Object>> rows = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnSize = metaData.getColumnCount();
//			System.out.println(columnSize);

			while (rs.next()) {
//				System.out.println();
				Map<String, Object> row = new HashMap<>();

				for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
					String columnName = metaData.getColumnName(columnIndex + 1);
					Object value = rs.getObject(columnName);

//					System.out.println("columnName : " + columnName);

					if (value instanceof Long) {
						int numValue = (int) (long) value;
						row.put(columnName, numValue);
					} else if (value instanceof Timestamp) {
						String dateValue = value.toString();
						dateValue = dateValue.substring(0, dateValue.length() - 2);
						row.put(columnName, dateValue);
					} else {
						row.put(columnName, value);
					}
				}

				rows.add(row);
			}
		} catch (SQLException e) {
			System.err.println("[SQLException 예외]");
			System.err.println("msg : " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}
		}

		return rows;
	}
}
