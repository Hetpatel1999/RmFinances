package com.GridDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.been.GridModel;

public class GridDao {

	public List<GridModel> getdata(int rows, int page) {
		try
		{
			 Class.forName("com.mysql.jdbc.Driver");

		     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/het","root","root");
		     PreparedStatement prepared = con.prepareStatement("Select * from student");
		     ResultSet rs = prepared.executeQuery();
		     
		     List<GridModel> list=new ArrayList<>();
				while(rs.next())
				{
					GridModel mo=new GridModel();
					mo.setId(rs.getInt("id"));
					mo.setName(rs.getString("name"));
					mo.setAge(rs.getString("age"));
					mo.setLocation(rs.getString("location"));
					
					list.add(mo);
				}
		 
		
		 prepared.close();
		 con.close();
		 return list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
