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




@WebServlet(urlPatterns = "/Gridservlet")
public class Gridservlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//for search operation
		String searchfield=request.getParameter("searchField");
		String searchop=request.getParameter("searchOper");
		String searchval=request.getParameter("searchString");
		String id="id";
		int val;
		
		
		
		System.out.println(searchfield+" "+" "+searchop+" "+searchval );
		
		if(searchval!=null)
		{
			
			try
			{
				 
			     List<GridModel> list1=new ArrayList<>();
			     GridModel mo=new GridModel();
			     JSONObject jsonobj = new JSONObject();
				
				if(searchop.equals("eq"))// equal
				{
					
					Class.forName("com.mysql.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/het","root","root");
					String sql="select * from student where " +searchfield + " =  ? ";
					PreparedStatement ps=con.prepareStatement(sql);
					
					ps.setString(1, searchval);
					
					
					ResultSet rs=ps.executeQuery();
					System.out.println(sql);
					while(rs.next())
					{
						
						mo.setId(rs.getInt(1));
						mo.setName(rs.getString(2));
						mo.setAge(rs.getString(3));
						mo.setLocation(rs.getString(4));
						
						list1.add(mo);
					}
					System.out.println(list1);
					
				    jsonobj.put("page", 1);
				    jsonobj.put("total", 2);
				    jsonobj.put("records", 2);
				    jsonobj.put("rows", list1);
				    out.print(jsonobj);
				    System.out.println(jsonobj);
				}
				
				//2 option
				else if(searchop.equals("ne"))// not equal
				{
					// sql="select * from student where ?!=?"; || in some version<>
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/het","root","");
					String sql="select * from student where " +searchfield + " != ? ";
					PreparedStatement ps=con.prepareStatement(sql);
					
					if(searchfield.equals(id))
					{
						val=Integer.parseInt(searchval);
						ps.setInt(1, val);
						System.out.println("val: "+val);
					}
					else
					{
						ps.setString(1, searchval);
						System.out.println("else:"+searchval);
					}
					
					System.out.println(sql);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						
						mo.setId(rs.getInt(1));
						mo.setName(rs.getString(2));
						mo.setAge(rs.getString(3));
						mo.setLocation(rs.getString(4));
						
						list1.add(mo);
					}
					System.out.println(list1);
					
				    jsonobj.put("page", 1);
				    jsonobj.put("total", 2);
				    jsonobj.put("records", 2);
				    jsonobj.put("rows", list1);
				    out.print(jsonobj);
				    System.out.println(jsonobj);
				}
				else if(searchop.equals("bw"))// (begins with (val%))
				{
					// sql="SELECT * FROM student WHERE ? LIKE '?%'";
				}
				else if(searchop.equals("bn"))// (does not begins with (val%))
				{
					// sql="SELECT * FROM student WHERE ? NOT LIKE '?%'";
				}
				else if(searchop.equals("rw"))//(ends with)
				{
					// sql="SELECT * FROM student WHERE ? LIKE '%?'";
				}
				else if(searchop.equals("en"))//does not end with)
				{
					// sql="SELECT * FROM student WHERE ? NOT LIKE '%?'";
				}
				else if(searchop.equals("cn"))//contain with(%val%)
				{
					// sql="SELECT * FROM student WHERE ? LIKE '%?%'";
				}
				else if(searchop.equals("nc"))//does not contain with(%val%)
				{
					// sql="SELECT * FROM student WHERE ? NOT LIKE '%?%'";
				}
				else if(searchop.equals("nu"))//is null
				{
					// sql="SELECT * FROM student WHERE ? IS NULL";
				}
				else if(searchop.equals("nn"))// is not null
				{
					// sql="SELECT * FROM student WHERE ? IS NOT NULL";
				}
				else if(searchop.equals("in"))//is in
				{
					// sql="SELECT * FROM student WHERE ? IN (?)";
				}
				else if(searchop.equals("ni"))//not is in
				{
					// sql="SELECT * FROM student WHERE ? NOT IN (?)";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		else {
		//deafalut data
			
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
		 
			JSONObject jsonobj = new JSONObject();
		    jsonobj.put("page", 1);
		    jsonobj.put("total", 2);
		    jsonobj.put("records", 2);
		    jsonobj.put("rows", list);
		    out.print(jsonobj);
		
		 prepared.close();
		 con.close();
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}

	}
}
