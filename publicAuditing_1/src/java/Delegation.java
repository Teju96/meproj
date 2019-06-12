/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author balaji
 */
public class Delegation extends HttpServlet {
     Connection connection = null;
        Statement statement=null;
        PreparedStatement prep; 
        ResultSet rs;
public Pairing pairing;
	public static Field zr, g1, gt;
	public static Element pk_a, sk_a, isk_a, isk_b, pk_b, sk_b, ownersk_a, g, k, g_k, z_k, e, rka_b;
  public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
                       System.out.println("inside");
			String username=request.getParameter("uname");
                        String email=request.getParameter("skey");
                        
                        System.out.println(email+"-Delegation--"+username);
			keygen(username,email);
			
				RequestDispatcher dis = request.getRequestDispatcher("fileuploading.jsp");
				dis.forward(request,response);
			}
		
		catch(Exception e){
			e.printStackTrace();
		}		
	}
public void keygen(String uname,String key)
{
    try{
        
        String url = "jdbc:mysql://localhost/";
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
statement = (Statement) connection.createStatement();

 System.out.println(uname+"--insert method--"+ key);
                   String isql = "INSERT INTO dkey VALUES(?,?)";
                    prep = connection.prepareStatement(isql);
                    prep.setString(1,uname);
                    prep.setString(2,key);
                   
                    prep.execute();
                    prep.close();
                    System.out.println(uname+"--insert method successfully--"+ key);
                    
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
}
