/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author balaji
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
			String username=request.getParameter("un");
			String password=request.getParameter("pwd");
                       
                         System.out.println("-------------->"+username+password);
                      
                            System.out.println("-------------->");
		            HttpSession session = request.getSession(true);
		            session.setAttribute("user",username);
			    boolean status=Logincheck(username,password);
			if(status){
				RequestDispatcher dis = request.getRequestDispatcher("fileuploading.jsp");
				dis.forward(request,response);
			}
			else{
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request,response);
			}
		}
                
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public boolean Logincheck(String uname,String passw){
		boolean status=false;
		try{
			Connection con=getConnection();
			Statement st =con.createStatement();
			ResultSet rs = st.executeQuery("select * from user where uname='"+uname+"' and pass='"+passw+"'");
			if(rs.next()){
				status=true;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	public Connection getConnection(){
	 Connection conn = null;	
	try{
                String url = "jdbc:mysql://localhost/";
                String dbName = "publicauditing";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root";
                String dpassword= "root";

  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,dpassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	} 			
}
