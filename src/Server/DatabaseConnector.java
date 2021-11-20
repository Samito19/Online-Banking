package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseConnector {

	private static final String url = "jdbc:mysql://u9w57chjvorabzwq:wxbuRMH32yZtr2BZDl97@bltnbbogkmtcxu46zela-mysql.services.clever-cloud.com:3306/bltnbbogkmtcxu46zela";

	private static Statement statement;

	Random rand = new Random();
        
	
	public DatabaseConnector() {
		
		try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url);
		statement = con.createStatement();
		} 
		catch (SQLException | ClassNotFoundException e ) {System.out.println("Connection to the database failed !");}
	}	
	
	public String fetchInDatabasePIN(String id) {
		
		try {

			ResultSet result = statement.executeQuery(String.format("select Customer_PIN from user_information where Customer_ID = '%s';", id));
			result.next();
			return result.getString(1);
			
		} catch (SQLException e) {return null;}	
	}
	
	public String fetchInDatabaseID(String id) {
		
		try {
			
			ResultSet result = statement.executeQuery(String.format("select Customer_ID from user_information where Customer_ID = '%s';", id));
		
			if(result.next()) {
				return result.getString(1);
			} else {return null;}
			
		} catch (SQLException e) {return null;}		
	}

	public static List returnCustomerInfo(String id){
		try {
			List<String> user_information = new ArrayList<String>();
			ResultSet result = statement.executeQuery(String.format("select Customer_Name, Account_Number, Checking_Balance, Savings_Balance from user_information where Customer_ID = '%s';", id));

			if(result.next()) {
				for (int i=1; i<5; i++){
					user_information.add(result.getString(i));
				}
				return user_information;
			} else {return null;}
		}
		catch(SQLException e) {return null;}
	}
	
	public void insertNewUserIntoDatabase(int id, int pin, String name){

		try{statement.executeUpdate(String.format("INSERT INTO user_information VALUES (%s, %s, %s, %s, %s, '%s');", id, pin, rand.nextInt(99999), 1000, 10000, name));} catch (SQLException e) {System.out.println(e);}
	}

	public static void transferMoney(int accountNumberCredit, int accountNumberDebit, int amount){
		try{
			statement.executeUpdate(String.format("UPDATE user_information SET checking_balance = checking_balance + %s WHERE account_number = %s", amount, accountNumberCredit));
			statement.executeUpdate(String.format("UPDATE user_information SET checking_balance = checking_balance - %s WHERE account_number = %s", amount, accountNumberDebit));
		} catch (SQLException e) {System.out.println(e);}

	}
}
