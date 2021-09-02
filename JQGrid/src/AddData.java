import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/AddData")
public class AddData extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String location=req.getParameter("location");
		String sql=req.getParameter("searchField");
		
		System.out.println("At Add:"+id+" "+name+" "+age+" "+location);
		
		try {
			
		
			 Class.forName("com.mysql.jdbc.Driver");

			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/het","root","root");
		     
		     String query="insert into student(name,age,location) value(?,?,?)";
		     PreparedStatement ps = con.prepareStatement(query);
		    
		     ps.setString(1, name);
		     ps.setString(2, age);
		     ps.setString(3, location);
		     
		     ps.execute();
		     
			out.print("Data added.....");
			con.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}