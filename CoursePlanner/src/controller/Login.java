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

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("usernamelogin");
		String password = (String) request.getParameter("txtpasslogin");
		
		if(username.trim().equals("") && password.trim().equals(""))	{
			request.getSession().setAttribute("errorMessage", "Please Enter Username and Password !!!");
			doGet(request, response);
			return;
		}
		if(username.trim().equals(""))	{
			request.getSession().setAttribute("errorMessage","Please Enter Username !!!");
			doGet(request, response);
			return;
		}
		if(password.trim().equals(""))	{
			request.getSession().setAttribute("errorMessage","Please Enter Password !!!");
			doGet(request, response);
			return;
		}
		
        Connection c = null;
        int userExist = 0;
        try
        {
            String url = "jdbc:mysql://localhost/courseplanner";
            String user_name = "root";
            String pass_word = "";
            c = DriverManager.getConnection( url, user_name, pass_word );
            Statement stmt1 = c.createStatement();
            ResultSet rs = stmt1.executeQuery( 
            		"SELECT COUNT(*) FROM USER "
            		+ "WHERE USERNAME = '"+username.trim()+"'"
            		+ "AND PASSWORD = '"+password.trim()+"'" );
            
            while(rs.next())
            	userExist = rs.getInt(1);
            if(userExist > 0)
            {
				request.getSession().setAttribute("login_user",username);
				response.sendRedirect( "DisplayCourses" );
				return;
            }	
    		request.getSession().setAttribute("errorMessage","Invalid Username/Password !!!");
			doGet(request, response);
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