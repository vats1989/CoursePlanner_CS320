package controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Quarter;
import model.QuarterPlan;
import model.CourseData;

@WebServlet("/CoursePlan")
public class CoursePlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CoursePlan() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	//List<CourseData> courses = new ArrayList<CourseData>();
    	Vector<CourseData> courses = new Vector<CourseData>();
    	
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
				String query1 = "select count(*)  from course c, course c1, prerequisites p where c.courseid = p.courseid and c1.courseid = p.precourseid and c.code = '"+courseCode+"'";
				
				rs1 = stmt2.executeQuery(query1);
				String preCode = "";
				
				while(rs1.next())
					rowCount = rs1.getInt(1);
				
				if(rowCount == 0)
					preReqCodes.add("");
				else if(rowCount > 0)	
				{
					String query2 = "select c1.code  from course c, course c1, prerequisites p where c.courseid = p.courseid and c1.courseid = p.precourseid and c.code = '"+courseCode+"'";
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
        getServletContext().setAttribute( "courseplan", courses );
        request.getRequestDispatcher( "/WEB-INF/CoursePlan.jsp" ).forward(
            request, response );
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Vector<CourseData> v = (Vector<CourseData>) getServletContext().getAttribute("courseplan");
//		Vector<CourseLoad> v1 = (Vector<CourseLoad>) getServletContext().getAttribute("remainCourse");
	//	if(v1 == null)
		Vector<CourseData>	v1 = (Vector<CourseData>) v.clone();
		
		CourseData courseLoad;
		QuarterPlan q = null;
		Enumeration<CourseData> vEnum = v1.elements();
		
		String nextQuarter = null;
		String s;
		s = (String) getServletContext().getAttribute("str");
		if (s == null)
			s = "";
		
		ArrayList<QuarterPlan> quarterPlan;
		quarterPlan = (ArrayList<QuarterPlan>) getServletContext().getAttribute("QuarterCourseList");
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		year += 1;
		
		if(quarterPlan != null)
			q = (QuarterPlan) quarterPlan.get(quarterPlan.size()- 1);

		if(quarterPlan == null)
			quarterPlan = new ArrayList<QuarterPlan>();
		
		if(q != null) {
			String s1 = q.getQuarter().getQuarter();
			if(s1.equals("Winter"))			nextQuarter = "Spring";
			else if(s1.equals("Spring"))	nextQuarter = "Summer";
			else if(s1.equals("Summer"))	nextQuarter = "Fall";
			else if(s1.equals("Fall"))		nextQuarter = "Winter";
		}
		else {		
			String currentQuarter  = new Quarter().findQuarter(); //Take the current Quarter
			if(currentQuarter.trim().equals("Fall"))		nextQuarter = "Winter";
			if(currentQuarter.trim().equals("Winter"))		nextQuarter = "Spring";
			if(currentQuarter.trim().equals("Spring"))		nextQuarter = "Summer";
			if(currentQuarter.trim().equals("Summer"))		nextQuarter = "Summer";
		}
		
		getServletContext().setAttribute("nextQuarter", nextQuarter);
		getServletContext().setAttribute("year", year);
		/*****************************************************************/
		
		int size=0;
		String taken_subjects[] = null;
		if(request.getParameter("plan_subjects") != null)	{
			taken_subjects = request.getParameterValues("plan_subjects");
		}
		if(taken_subjects != null) size = taken_subjects.length;
		if(size > 1)
			for(int j=0; j < size; j++) s = s+taken_subjects[j]+" ";
		else if(size == 1)
			s = s + taken_subjects[0]+ " ";
		/*****************************************************************/

		if (s != null) 
		{
			int flag, counter, token_count;
			while(vEnum.hasMoreElements())	
			{
				flag = 0;
				counter = 0;
				token_count = 0;
				courseLoad = (CourseData) vEnum.nextElement();
				if(!(s.contains(courseLoad.getCourseCode().trim())) && courseLoad.getPreReq().equals(""))
				{
					quarterPlan.add(new QuarterPlan(new Quarter(nextQuarter), courseLoad));
					flag = 1;
				}
				else if(!(s.contains(courseLoad.getCourseCode().trim())) && flag == 0)	
				{	
					StringTokenizer st = new StringTokenizer(courseLoad.getPreReq()," ");
					token_count = st.countTokens();
					while(st.hasMoreElements())	{						
						String prereqcode = st.nextToken();
						if(s.contains(prereqcode.trim()))
							counter++;
					}
					if(token_count == counter)
						quarterPlan.add(new QuarterPlan(new Quarter(nextQuarter), courseLoad));
				}
			}
		}
		getServletContext().setAttribute("QuarterCourseList", quarterPlan);
		//getServletContext().setAttribute("takenCourse", takenCourse);
		getServletContext().setAttribute("remainCourse", v1);
		getServletContext().setAttribute("str", s);
		response.sendRedirect("QuarterCourse");
	}
}