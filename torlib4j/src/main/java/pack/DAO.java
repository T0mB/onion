package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAO {
	
	private static final String DB_URL = "jdbc:mysql://localhost:1234/test";
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_USERNAME = "user";
	private static final String DB_PASSWORD = "pass";

	
	public DAO() {
		try { 
			Class.forName(DB_DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getInts(){
		List<Integer> l = new ArrayList<Integer>();
		String sql = "select iddata from data";
		PreparedStatement ps = null;
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			ps = (PreparedStatement) con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("iddata");
				
				l.add(id);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return l;
	}
}
