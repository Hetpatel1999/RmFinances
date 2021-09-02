import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/SubGrid")
public class SubGrid  extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String id=req.getParameter("rowId");
		System.out.println(id);
		try {
			
			String url="jdbc:mysql://localhost:3306/het";
			String user="root";
			String pass="root";
			String query="select id, name , age from student where id=?";
			
		
			 Class.forName("com.mysql.jdbc.Driver");

		     Connection con = DriverManager.getConnection(url,user,pass);
		     PreparedStatement ps = con.prepareStatement(query);
		    
		     ps.setString(1,id);
		     ResultSet rs = ps.executeQuery();
		     
		     List<SubgridModel> list=new ArrayList<>();
				while(rs.next())
				{
					SubgridModel mo=new SubgridModel();
					mo.setId(rs.getInt(1));
					mo.setName(rs.getString(2));
					mo.setAge(rs.getString(3));
					list.add(mo);
				}
		 
			JSONObject jsonobj1 = new JSONObject();
		    jsonobj1.put("page", 1);
		    jsonobj1.put("total", 1);
		    jsonobj1.put("records", 1);
		    jsonobj1.put("rows", list);
		    out.print(jsonobj1);
		System.out.println(jsonobj1);
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}