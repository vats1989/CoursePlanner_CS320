package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Register() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = (String) request.getParameter("username");
		String pass_word = (String) request.getParameter("password");
		String repass    = (String) request.getParameter("repassword");
		String fname     = (String) request.getParameter("firstname");
		String lname     = (String) request.getParameter("lastname");
		String email     = (String) request.getParameter("email");
		
		if(user_name.trim().equals("") && pass_word.trim().equals(""))	{
			request.getSession().setAttribute("errorMessage", "Please Enter Username and Password !!!");
			doGet(request, response);
			return;
		}		
		if(user_name.trim().equals(""))	{
			request.getSession().setAttribute("errorMessage","Please Enter Username !!!");
			doGet(request, response);
			return;			
		}
		if(pass_word.trim().equals(""))	{
			request.getSession().setAttribute("errorMessage","Please Enter Password !!!");
			doGet(request, response);
			return;			
		}
		if(repass.trim().equals(""))	{
			request.getSession().setAttribute("errorMessage","Please Enter Re-type Password !!!");
			doGet(request, response);
			return;
		}
		if(user_name.trim().length() < 4)	{
			request.getSession().setAttribute("errorMessage","Username must be at least 4 characters !!!");
			doGet(request, response);
			return;
		}
		if(pass_word.trim().length() < 4)	{
			request.getSession().setAttribute("errorMessage","Password must be at least 4 characters !!!");
			doGet(request, response);
			return;
		}
		if(!(pass_word.trim().equals(repass.trim())))	{
			request.getSession().setAttribute("errorMessage","Password mismatches !!!");
			doGet(request, response);
			return;
		}
		
        Connection c = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        
        int userExist = 0;
        try
        {
            String url = "jdbc:mysql://localhost/courseplanner";
            String username = "root";
            String password = "";
            c = DriverManager.getConnection( url, username, password );
            stmt1 = c.createStatement();
            ResultSet rs = stmt1.executeQuery( 
            		"SELECT COUNT(*) FROM USER "
            		+ "WHERE USERNAME = '"+user_name.trim()+"'");
            
            while(rs.next())
            	userExist = rs.getInt(1);
            if(userExist > 0)
            {
				request.getSession().setAttribute("errorMessage","Username Already Exists !!!");
				doGet(request,response);
				return;
            }	
            else
            {
            	stmt2 = c.createStatement();
            	stmt2.executeUpdate("insert into user (username,password,fname,lname,email)"
            			+ "values('"+user_name+"','"+pass_word+"','"+fname+"','"+lname+"','"+email+"')");
            	response.sendRedirect("Login");
            }
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
	}	
}	