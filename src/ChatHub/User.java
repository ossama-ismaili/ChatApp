package ChatHub;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import security.HashPassword;

public class User {
	Connection myConnection; 
	String user="root";
	String pass="";
	String url="jdbc:mysql://localhost:3308/appdb";
	
	
	public User() {
		//etape 1 : tester l'accessibilite de driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Changement du driver est OK");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Changement du driver est ... Non");
			System.exit(0);
		}
		
		//etape 2 : connecter a la Base de donnees
		try {
			myConnection=DriverManager.getConnection(url,user,pass);
			System.out.println("Base de donnees accessible Ok");
		}catch(SQLException e) {
			System.out.println("Base de donnees accessible Non");
		}
	}

	public boolean testUser(String username,String password) throws SQLException {
		String sql="SELECT * FROM users";
		Statement st;
		st = myConnection.createStatement();
		ResultSet rs=st.executeQuery(sql);
		boolean isUser=false;
		
		while(rs.next()) {
			try {
				if(rs.getString(3).equals(username) && rs.getString(4).equals(HashPassword.hashPassword(password))) {
					isUser=true;
				}
			} catch (NoSuchAlgorithmException e) {
				System.err.println("Error in Security");
				isUser=false;
			}
		}
		
		return isUser;
	}
	
	public String getFullName(String username) throws SQLException {
		String sql="SELECT * FROM users";
		Statement st = myConnection.createStatement();
		ResultSet rs=st.executeQuery(sql);
		
		while(rs.next()) {
			if(rs.getString(3).equals(username)) {
				return rs.getString(1);
			}
		}
		
		return "none";
	}
	
	public void insert(String fullname,String email,String username,String password) throws SQLException {
		String sql="INSERT INTO users (fullname,email,username,password) VALUES(?,?,?,?)";
		PreparedStatement st=myConnection.prepareStatement(sql);
		st.setString(1,fullname);
		st.setString(2, email);
		st.setString(3, username);
		st.setString(4, password);
		
		int nb=st.executeUpdate();
		System.out.println(nb+" inserted");
	}
	
	public void delete(String username) throws SQLException {
		String sql="DELETE FROM users WHERE username=?";
		PreparedStatement st=myConnection.prepareStatement(sql);
		st.setString(3, username);
		int nb=st.executeUpdate();
		System.out.println(nb+" Deleted");
	}
}

