package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseData;

@WebServlet(urlPatterns = "/DisplayCourses", loadOnStartup = 1)
public class DisplayCourses extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DisplayCourses() {
        super();
    }

    public void init( ServletConfig config ) throws ServletException {
        super.init( config );
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e ) {
            throw new ServletException( e );
        }
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException {
    	List<CourseData> courses = new ArrayList<CourseData>();
        Connection c = null;
        int rowCount = 0;
        try
        {
            String url = "jdbc:mysql://localhost/courseplanner";
            String username = "root";
            String password = "";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt1 = c.createStatement();
            Statement stmt2 = c.createStatement();
            Statement stmt3 = c.createStatement();
            ResultSet rs = stmt1.executeQuery( "select * from course" );
            ResultSet rs1 = null;
            ResultSet rs2 = null;

            while( rs.next() )
            {
				int id = rs.getInt( "COURSEID" );
				String courseCode = rs.getString( "CODE" );
				String courseDesc = rs.getString( "TITLE" );
				
				ArrayList<String> preReqCodes = new ArrayList<String>();				
				String query1 = "SELECT COUNT(*)  "
								+ "FROM COURSE C, COURSE C1, PREREQUISITES P "
								+ "WHERE C.COURSEID = P.COURSEID "
								+ "AND C1.COURSEID = P.PRECOURSEID "
								+ "AND C.CODE = '"+courseCode+"'";
				
				rs1 = stmt2.executeQuery(query1);
				String preCode = "";
				
				while(rs1.next())
					rowCount = rs1.getInt(1);
				
				if(rowCount == 0)
					preReqCodes.add("");
				else if(rowCount > 0)	
				{	
					String query2 = "SELECT C1.CODE  "
									+ "FROM COURSE C, COURSE C1, PREREQUISITES P "
									+ "WHERE C.COURSEID = P.COURSEID "
									+ "AND C1.COURSEID = P.PRECOURSEID "
									+ "AND C.CODE = '"+courseCode+"'";
					
					rs2 = stmt3.executeQuery(query2);
					
					while(rs2.next()) 
					{
						preCode = preCode+ rs2.getString(1)+" ";
						preReqCodes.add(rs2.getString(1));
					}
				}
				courses.add(new CourseData(id,courseCode,courseDesc,preCode,preReqCodes));
            }
        }
        catch( SQLException e )  {
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
        getServletContext().setAttribute( "courses", courses );
        request.getRequestDispatcher( "/WEB-INF/DisplayCourses.jsp" ).forward(
            request, response );
    }
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {  doGet( request, response ); }
}