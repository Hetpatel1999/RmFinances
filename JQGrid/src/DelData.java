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

@WebServlet(urlPatterns = "/DelData")
public class DelData extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		
		int id=Integer.parseInt(req.getParameter("id"));
		
		try {
					
				 Class.forName("com.mysql.jdbc.Driver");

				 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/het","root","root");
			     
			     String query="DELETE FROM student WHERE id=?";
			     PreparedStatement ps = con.prepareStatement(query);
			     ps.setInt(1,id);
			     
			     ps.execute();
			
			     out.print("Data Deleted...");
			     con.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
