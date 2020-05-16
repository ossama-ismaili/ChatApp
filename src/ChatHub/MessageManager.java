package ChatHub;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

public class MessageManager {
	Connection myConnection; 
	String user="root";
	String pass="";
	String url="jdbc:mysql://localhost:3308/appdb";
	
	
	public MessageManager() {
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

	public boolean testReciver(String username) throws SQLException {
		String sql="SELECT * FROM users";
		Statement st;
		st = myConnection.createStatement();
		ResultSet rs=st.executeQuery(sql);
		boolean isUser=false;
		
		while(rs.next()) {
			if(rs.getString(3).equals(username)) {
					isUser=true;
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
	
	public void insertMessage(String sender,String receiver,String message) throws SQLException {
		String sql="INSERT INTO messages VALUES(?,?,?)";
		PreparedStatement st=myConnection.prepareStatement(sql);
		st.setString(1,sender);
		st.setString(2, receiver);
		st.setString(3, message);
		
		int nb=st.executeUpdate();
		System.out.println(nb+" inserted");
	}
	
	public ArrayList<String[]> getMessages(String receiver) throws SQLException {
		ArrayList<String[]> messagesList=new ArrayList<String[]>();
		String[] messageInfo=new String[2];
		String sql="SELECT * FROM messages";
		Statement st = myConnection.createStatement();
		ResultSet rs=st.executeQuery(sql);
		
		while(rs.next()) {
			if(rs.getString(2).equals(receiver)) {
				messageInfo[0]=rs.getString(1);
				messageInfo[1]=rs.getString(3);
				messagesList.add(messageInfo);
			}
		}
		
		return messagesList;
	}
	
	public void deleteMessage(String rec) throws SQLException {
		String sql="DELETE FROM messages WHERE receiver=?";
		PreparedStatement st=myConnection.prepareStatement(sql);
		st.setString(2, rec);
		int nb=st.executeUpdate();
		System.out.println(nb+" Deleted");
	}
}

