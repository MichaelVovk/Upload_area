package datapool;

import java.io.IOException;
import java.sql.*;

import engine.BaseTest;

public class ObjectMap extends BaseTest {

	static {
		try {

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static String columnValue;
	public static String[] columnValues;
	public static String[] splitString;
	public static String sQuery, sQuery1, sQuery2, sQuery3, sQuery4;
	public static int i = 1;

	public static String fetchData(String sQuery) throws ClassNotFoundException, SQLException, IOException {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:NEW");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sQuery);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					if (i > 1)
						System.out.print(", ");
					columnValue = rs.getString(i);
				}
			}
			st.close();
			con.close();
			return columnValue;
		} catch (Exception e) {
			log.fail("Could not find the locator " + sQuery + " ; <br/><strong>Exception: </strong>"+ e.getMessage());
			return "Error";
		}
	}

	public static String fetchActiveRecord(String sColumn, String sSheet, String sWhere) throws ClassNotFoundException, SQLException, IOException {
		sQuery1 = "SELECT name FROM " + sSheet + " WHERE " + sWhere + "";
		sQuery2 = "SELECT id FROM " + sSheet + " WHERE " + sWhere + "";
		sQuery3 = "SELECT class FROM " + sSheet + " WHERE " + sWhere + "";
		sQuery4 = "SELECT xpath FROM " + sSheet + " WHERE " + sWhere + "";

		try {
			columnValue = fetchData(sQuery1);
			if (columnValue == null) {

				columnValue = fetchData(sQuery2);
			} else if (columnValue == null) {
				columnValue = fetchData(sQuery3);
			} else if (columnValue == null) {
				columnValue = fetchData(sQuery4);
			} else if (columnValue == null) {
				log.fail("Could not find the locator " + sColumn + " where " + sWhere);
				columnValue = "Error";
			}
		} catch (Exception e2) {
			try {
				columnValue = fetchData(sQuery2);
				if (columnValue == null) {
					columnValue = fetchData(sQuery3);
				} else if (columnValue == null) {
					columnValue = fetchData(sQuery4);
				} else if (columnValue == null) {
					log.fail("Could not find the locator " + sColumn + " where " + sWhere);
					columnValue = "Error";
				}
			} catch (Exception e3) {
				try {
					columnValue = fetchData(sQuery3);
					if (columnValue == null) {
						columnValue = fetchData(sQuery4);
					} else if (columnValue == null) {
						log.fail("Could not find the locator " + sColumn + " where " + sWhere);
						columnValue = "Error";
					}
				} catch (Exception e4) {
					try {
						columnValue = fetchData(sQuery4);
						if (columnValue == null) {
							log.fail("Could not find the locator " + sColumn + " where " + sWhere);
							columnValue = "Error";
						}
					} catch (Exception e5) {
						log.fail("Could not find the locator " + sColumn + " where " + sWhere);
						columnValue = "Error";
					}
				}
			}
		}

		return columnValue;
	}
}