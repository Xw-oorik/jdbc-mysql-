package com.card;
import java.sql.*;
public class QuerySql {
	public QuerySql(){}

	public boolean Query(String username,String password,int i)
	{
		boolean pass=false;
		try {
			String url="jdbc:mysql://localhost:3306/school";//sql2019cox
			String sql="select * from useTable where Account='"+username+"'and PassWord='"+password+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,"root","033018");
		
			Statement state=con.createStatement();
			ResultSet ret=state.executeQuery(sql);
			if(ret.next())
				{
				pass=true;
				}
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