package com;


import java.sql.*;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Fund {
	
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public String readFunds() 
	{ 
	String output = ""; 
	try
	{ 
	
	Connection con = connect();
	
	if (con == null) 
	{
		 return "Error while connecting to the database for reading."; 
	} 
	 
	 // Prepare the html fund table to be displayed
	 output = "<table border='1'>"
	 		+ "<tr>"
	 		+ "<th>Fund Code</th>"
	 		+ "<th>Fund Type</th>" 
	 		+ "<th>Amount</th>"
	 		+ "<th>Date</th>" 
	 		+ "<th>Status</th>"
	 		+ "<th>Update</th>"
	 		+ "<th>Remove</th>"
	 		+ "</tr>"; 
	 
	 //call sql query
	 String query = "select * from funds"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	
	 while (rs.next()) 
	 { 
	 String fundId = Integer.toString(rs.getInt("fundId")); 
	 String fundCode = rs.getString("fundCode"); 
	 String fundType = rs.getString("fundType"); 
	 String amount = Double.toString(rs.getDouble("amount")); 
	 String date = rs.getString("date"); 
	 String status = rs.getString("status"); 
	 
	 // Add into the html table
	 output += "<tr><td><input id='hidFundIDUpdate'  name='hidFundIDUpdate'  type='hidden' value='" + fundId+ "'>" + fundCode + "</td>"; 
	 output += "<td>" + fundType + "</td>"; 
	 output += "<td>" + amount + "</td>"; 
	 output += "<td>" + date + "</td>"; 
	 output += "<td>" + status + "</td>"; 
	 
	 
	 // buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" 
				+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemovebtn btn-danger' data-fundid='"
				+ fundId + "'>" + "</td></tr>"; 
	 } 
	 con.close(); 
	
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the fund details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }


	
	
	
	public String addFund(String code, String type, String amount, String date,String status) 
	{ 
		String output = ""; 
		try
		{ 		
				//database connection
			Connection con = connect();
				
				if (con == null) 
				{
					return "Error while connecting to the database for inserting."; 
					
				} 
	 
				// create a prepared statement
				String query = " insert into test.funds (fundCode,fundType,amount,date,status)" + " values (?, ?, ?, ?, ?,?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, code); 
				preparedStmt.setString(3, type); 
				preparedStmt.setDouble(4, Double.parseDouble(amount)); 
				preparedStmt.setString(5, date); 
				preparedStmt.setString(6, status);
				
				// execute the statement
	 			preparedStmt.execute(); 
				con.close(); 
				
				String newFunds = readFunds();
				output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
			
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\":  \"Error while inserting the fund.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	

	
		
	
	
	//update fund details
			public String updateFund(String id, String code, String type, String amount, String date, String status)
			{ 
				String output = ""; 
				try
				{ 	
					//database connection
					Connection con = connect(); 
					if (con == null) 
					{
						return "Error while connecting to the database for updating."; 
					} 
				
					// call the query
					String query = "UPDATE test.funds SET fundCode=?,fundType=?,amount=?,date=?,status=?  WHERE fundId=?"; 
				
					// create a prepared statement
					PreparedStatement preparedStmt = con.prepareStatement(query); 
				
					preparedStmt.setString(1, code); 
					preparedStmt.setString(2, type); 
					preparedStmt.setDouble(3, Double.parseDouble(amount)); 
					preparedStmt.setString(4, date);
					preparedStmt.setString(5, status);
					preparedStmt.setInt(6, Integer.parseInt(id)); 
				
					// execute the statement
					preparedStmt.executeUpdate(); 
					
					con.close();
	
					
					//output
						String newFunds = readFunds();
						output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
					
				} 
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\":  \"Error while update the fund.\"}"; 
					System.err.println(e.getMessage()); 
				} 
				
				return output; 
				
			}
			
			




//delete fund record
		public String deleteFund(String fundId) 
		{ 
			String output = ""; 
			try
			{ 
				//database connection
				Connection con = connect();  
				if (con == null) 
				{
					return "Error while connecting to the database for deleting."; 
				} 
				
				
			
				// call the query
				String query = "delete from test.funds where fundId=?"; 
				
			
				//create the prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query); 
			
				preparedStmt.setInt(1, Integer.parseInt(fundId)); 
				// execute the statement
				preparedStmt.executeUpdate(); 
				
				
				//output
				String newFunds = readFunds();
				output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
			
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\":  \"Error while deleting the fund.\"}"; 
			System.err.println(e.getMessage()); 
		} 
			return output; 
		} 
		
	
	
	
	
	
	
	

}
