package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUti {
	public Connection konek(String db, String url, String user, String passwd) {
		Connection koneksi = null;
		url = url + db;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			koneksi = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return koneksi;
	}

	public ResultSet queri(Connection koneksi, String kalimat) {
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = (Statement) koneksi.createStatement();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			rs = stmt.executeQuery(kalimat);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rs;
	}
}
