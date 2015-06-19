package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] sdlfkms) {
		DaoUti koneksi = new DaoUti();

		String url = "jdbc:mysql://localhost:3306/";
		String namaDatabase = "type3db";
		String user = "root";
		String password = "aaa123";

		Connection con = koneksi.konek(namaDatabase, url, user, password);
		PreparedStatement preparedStatement = null;
		try {
			con = (Connection) DriverManager.getConnection(url, user, password);
			String selectSQL = "select * from type3db.kelurahan";
			preparedStatement = con.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String userid = rs.getString(1);
				String username = rs.getString(2);
				System.out.println(userid + "\t" + username);
			}
			preparedStatement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {

			}
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {

			}
		}

	}
}
