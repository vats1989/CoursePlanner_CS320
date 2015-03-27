package controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddCourse")

public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddCourse() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String loggedUser = (String)request.getSession().getAttribute("login_user");
		if(loggedUser == null)	{
			request.getSession().setAttribute("login_error","Please Login before Add/Edit Courses !!!");
			response.sendRedirect("Login");
			return;
		}
		
        Connection c = null;
        String course_codes [];
        try
        {
            String url = "jdbc:mysql://localhost/courseplanner";
            String username = "root";
            String password = "";
            int count = 0;
            
            c = DriverManager.getConnection( url, username, password );
            Statement stmt1 = c.createStatement();
            Statement stmt2 = c.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select count(courseid) code from course");
            while(rs1.next())
            	count = rs1.getInt(1);
            
            course_codes = new String[count];
            ResultSet rs2 = stmt2.executeQuery("select code from course");
            count = 0;
            while(rs2.next())
            	course_codes[count++] = rs2.getString(1);
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }       
        finally  
        {
        	try  {
                if( c != null ) c.close();
            }
            catch( SQLException e )  {
                throw new ServletException( e );
            }
        }		
        request.setAttribute("course_codes", course_codes);
		request.getRequestDispatcher( "/WEB-INF/AddCourse.jsp" ).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("AddCode") != null && request.getParameter("AddTitle") != null)  
		{
			String courseCode = request.getParameter("AddCode");
			if(!(courseCode.equals("")))	
			{			
				String courseDesc = request.getParameter("AddTitle");
				String preReqCode[] = request.getParameterValues("AddPreReqCode");	
		        Connection c = null;
		        try
		        {
		            String url = "jdbc:mysql://localhost/courseplanner";
		            String username = "root";
		            String password = "";
		
		            c = DriverManager.getConnection( url, username, password );
		            Statement stmt1 = c.createStatement();
		            
		            stmt1.executeUpdate("INSERT INTO COURSE(CODE,TITLE) "
		            		+ "VALUES('"+courseCode+"','"+courseDesc+"')");
		            
		            if(preReqCode != null && preReqCode.length > 0)  
		            {
			            for(int i = 0; i < preReqCode.length; i++)  {
			            	Statement stmt4 = c.createStatement();
			            	stmt4.executeUpdate(
			            			"INSERT INTO PREREQUISITES (COURSEID,PRECOURSEID) "
			            			+ "VALUES ((SELECT COURSEID FROM COURSE WHERE CODE = '"+courseCode+"'),"
			            					+ "(SELECT COURSEID FROM COURSE WHERE CODE = '"+preReqCode[i]+"'))");
			            }
			        }
		        }
		        catch( SQLException e )
		        {
		            throw new ServletException( e );
		        }       
		        finally  
		        {
		        	try  {
		                if( c != null ) c.close();
		            }
		            catch( SQLException e )  {
		                throw new ServletException( e );
		            }
		        }
			}
		}
        response.sendRedirect( "DisplayCourses" );
	}
}