package app.rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import app.dao.DaoUti;
import app.kota.Kecamatan;
import app.kota.Kelurahan;

@Path("/type3")
public class AppResources {
	static Logger log = Logger.getRootLogger();

	List<Kecamatan> kecamatan = new ArrayList<Kecamatan>();
	List<Kelurahan> kelurahan = new ArrayList<Kelurahan>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListKecamatan/{kota}")
	// localhost:8080/Type3/resources/type3/getListKecamatan/namaKota
	public List<Kecamatan> getListKecamatan(@PathParam("kota") String kota,
			@HeaderParam("authorization") String authString) throws Exception {
		if (!isUserAuthenticated(authString)) {
			log.info("User not authenticated");
			return kecamatan;
		} else {
			Long idKota = 0l;
			DaoUti koneksi = new DaoUti();
			String url = "jdbc:mysql://localhost:3306/";
			String namaDatabase = "type3db";
			String user = "root";
			String password = "aaa123";
			Connection con = koneksi.konek(namaDatabase, url, user, password);
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ResultSet rs1 = null;
			try {
				con = (Connection) DriverManager.getConnection(url, user,
						password);

				// ================================================================================
				String selectSQL = "select idkota from type3db.kota where  namaKota=?";
				preparedStatement = con.prepareStatement(selectSQL);
				preparedStatement.setString(1, kota);
				rs1 = preparedStatement.executeQuery();
				while (rs1.next()) {
					idKota = rs1.getLong(1);
				}
				rs = null;
				preparedStatement = null;

				// ================================================================================
				selectSQL = "select namaKecamatan from type3db.kecamatan where  idkota=?";
				preparedStatement = con.prepareStatement(selectSQL);
				preparedStatement.setLong(1, idKota);
				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Kecamatan k = new Kecamatan();
					k.setNamaKecamatan(rs.getString(1));
					kecamatan.add(k);
				}
				preparedStatement.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (Exception e) {

				}
				try {
					if (!rs1.isClosed()) {
						rs1.close();
					}
				} catch (Exception e) {

				}

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

			// ===================================================================
			return kecamatan;
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListKelurahan/{kecamatan}")
	// localhost:8080/Type3/resources/type3/getListKelurahan/namaKecamatan
	public List<Kelurahan> getListkelurahan(
			@PathParam("kecamatan") String kecamatan,
			@HeaderParam("authorization") String authString) throws Exception {
		if (!isUserAuthenticated(authString)) {
			log.info("User not authenticated");
			return kelurahan;

		} else {

			Long idKecamatan = 0l;

			DaoUti koneksi = new DaoUti();
			String url = "jdbc:mysql://localhost:3306/";
			String namaDatabase = "type3db";
			String user = "root";
			String password = "aaa123";
			Connection con = koneksi.konek(namaDatabase, url, user, password);
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ResultSet rs1 = null;
			try {
				con = (Connection) DriverManager.getConnection(url, user,
						password);

				// ================================================================================
				String selectSQL = "select idkecamatan from type3db.kecamatan where namaKecamatan=?";
				preparedStatement = con.prepareStatement(selectSQL);
				preparedStatement.setString(1, kecamatan);
				rs1 = preparedStatement.executeQuery();
				while (rs1.next()) {
					idKecamatan = rs1.getLong(1);
				}
				rs = null;
				preparedStatement = null;

				// ================================================================================
				selectSQL = "select namaKelurahan from type3db.kelurahan where idkecamatan=?";
				preparedStatement = con.prepareStatement(selectSQL);
				preparedStatement.setLong(1, idKecamatan);
				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Kelurahan k = new Kelurahan();
					k.setNamaKelurahan(rs.getString(1));
					kelurahan.add(k);
				}
				preparedStatement.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (Exception e) {

				}
				try {
					if (!rs1.isClosed()) {
						rs1.close();
					}
				} catch (Exception e) {

				}

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
			return kelurahan;
		}
	}

	private boolean isUserAuthenticated(String authString) {
		if (authString != null && !authString.equals("")) {
			String decodedAuth = "";
			String[] authParts = authString.split("\\s+");
			String authInfo = authParts[1];
			byte[] bytes = null;
			try {
				bytes = new BASE64Decoder().decodeBuffer(authInfo);
			} catch (IOException e) {
				log.info("otentikasi exceptionnnnnnnnnnnnnnnnnnnn");
				e.printStackTrace();
			}
			decodedAuth = new String(bytes);

			// =====================================================================
			String userAndPasswdBolehMasuk = "Budi:PasswordBudi";
			System.out.println(decodedAuth);
			if (!decodedAuth.equals(userAndPasswdBolehMasuk)) {
				return false;
			} else {
				return true;
			}
			// return true;
		} else {
			return false;
		}
	}

}
