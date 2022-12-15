package com.card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class QueryCardAndNum {

	public  QueryCardAndNum(){}

	public boolean Query(String cardNum,String studentNum)
	{
		boolean pass=false;
		try {
			String url="jdbc:mysql://localhost:3306/school";
			String sql="select * from cardTable where cardNumber= '"+cardNum+"'and studentNumber='"+studentNum+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,"root","033018");
		
			Statement state=con.createStatement();
			ResultSet set=state.executeQuery(sql);
			if(set.next())
				{
					pass=true;
					
					System.out.println(set.getString(3));
				}else{pass=false;}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass;
	}	
	
	
	
	
}