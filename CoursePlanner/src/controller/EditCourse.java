package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;


@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditCourse() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String loggedUser = (String)request.getSession().getAttribute("login_user");
		if(loggedUser == null)	{
			request.getSession().setAttribute("login_error","Please Login before Add/Edit Courses !!!");
			response.sendRedirect( "Login" );
			return;
		}
		
		String courseCode = (String)request.getParameter("courseID");
		String codes[];
		String title = "";
		int id = 0;
        Connection c = null;
        try
        {
            String url = "jdbc:mysql://localhost/courseplanner";
            String username = "root";
            String password = "";
            int row_count = 0;
            
            c = DriverManager.getConnection( url, username, password );
            Statement stmt1 = c.createStatement();
            ResultSet rs = stmt1.executeQuery( "select courseid,title from course where code = '"+courseCode+"'" );
            while (rs.next())  {
            	id = rs.getInt(1);
            	title = rs.getString(2);
            }	
            
            Statement stmt2 = c.createStatement();
            String query1 = "select count(*) from course where courseid in "
            		+ "( select c2.precourseid "
            		+ "from course c1,prerequisites c2 "
            		+ "where c1.courseid = c2.courseid and "
            		+ "c1.code = '"+courseCode+"')";
            ResultSet rs1 = stmt2.executeQuery(query1);
            
			while(rs1.next())
            	row_count = rs1.getInt(1);
            
			codes = new String[row_count];
			
            Statement stmt3 = c.createStatement();
            String query2 =	"select code from course where courseid in "
            			  + "(select c2.precourseid "
            			  + "from course c1,prerequisites c2 "
            			  + "where c1.courseid = c2.courseid "
            			  + "and c1.code = '"+courseCode+"')";
            ResultSet rs2 = stmt3.executeQuery(query2);
            
            int preReqCodeCounter = 0;
			while(rs2.next())
            	codes[preReqCodeCounter++] = rs2.getString(1);
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        
        finally  {
        	try  {
                if( c != null ) c.close();
            }
            catch( SQLException e )  {
                throw new ServletException( e );
            }
        }
        request.setAttribute("courseCode", courseCode);
        request.setAttribute("id",id );
        getServletContext().setAttribute("editCode", courseCode);
        request.setAttribute("title", title);
        request.setAttribute("codes", codes);
        request.getRequestDispatcher( "/WEB-INF/EditCourse.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newCourseCode    = (String) request.getParameter("EditCode");
		String newCourseDesc    = (String) request.getParameter("EditTitle");
		String newPreReqCodes[] = request.getParameterValues("EditPreReqCode");
		//String oldCourseCode    = (String) getServletContext().getAttribute("editCode");
		int courseId = Integer.parseInt(request.getParameter("id"));

		String query1 = null;
        Connection c = null;
        Statement stmt = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        
        try
        {
            String url = "jdbc:mysql://localhost/courseplanner";
            String username = "root";
            String password = "";

            c = DriverManager.getConnection( url, username, password );
                    
            stmt1 = c.createStatement();
            stmt1.executeUpdate("delete from prerequisites where courseid = '"+courseId+"'");
            
            if(newPreReqCodes != null && newPreReqCodes.length > 0)  
            {
	            for(int i = 0; i < newPreReqCodes.length; i++)  {
	            	stmt2 = c.createStatement();
	            	stmt2.executeUpdate(
	            			"insert into prerequisites (courseid,precourseid) "
	            			+ "values ('"+courseId+"',"
	            					+ "(select courseid from course where code = '"+newPreReqCodes[i]+"'))");
	            }
	        }
            
            stmt = c.createStatement();
            query1 = "UPDATE COURSE SET "
            		+ "CODE = '"+newCourseCode.trim()+"',"
            		+ "TITLE = '"+newCourseDesc.trim()+"' "
            		+ "WHERE CODE = '"+newCourseCode.trim()+"'";
            stmt.executeUpdate(query1);
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally  {
        	try  {
                if( c != null ) c.close();
            }
            catch( SQLException e )  {
                throw new ServletException( e );
            }
        }
        response.sendRedirect( "DisplayCourses" );
	}
}