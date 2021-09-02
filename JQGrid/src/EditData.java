import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditData")
public class EditData extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String location=req.getParameter("location");
		
		System.out.println("At Edit:"+id+" "+name+" "+age+" "+location);
		
		try {
			
		
			 Class.forName("com.mysql.jdbc.Driver");

			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/het","root","root");
		     
		     String query="UPDATE student SET name=?, age=?, location=? WHERE id=?";
		     PreparedStatement ps = con.prepareStatement(query);
		     
		     ps.setInt(4, id);
		     ps.setString(1, name);
		     ps.setString(2, age);
		     ps.setString(3, location);
		     
		     ps.execute();
		     
		     out.print("Data Edited....");
		     con.close();
		     
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
