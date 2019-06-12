/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import it.unisa.dia.gas.jpbc.Pairing;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sagar
 */
public class verify1 extends HttpServlet {
public Pairing pairing;
public Vector v1=new Vector();
   public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
                 String f=request.getParameter("f");
                 String s=request.getParameter("s");
                 System.out.println(f+"role"+s);
                 verify();
                 RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("proxy_details.jsp");
                     requestDispatcher1.forward(request, response);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

}
   public void verify()
   {
       try{
           long lStartTime = System.currentTimeMillis();
           int total=3;
           int current=0;
           int count=0;
         String filename="";
         String servername="";
         String uname=""; 
           Connection con=getConnection();
			Statement st =con.createStatement();
            ResultSet rs1 = st.executeQuery("select * from repair");
                        while(rs1.next()){
                           uname=rs1.getString("uname");
                           filename=rs1.getString("fname");
                           servername=rs1.getString("sname");  
                           long lEndTime = System.currentTimeMillis();
                                               
long difference = lEndTime - lStartTime;
double cost1=difference/1000.0;
System.out.println("starting time"+ lStartTime);
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+cost1);
System.out.println("difference"+difference/1000.0);
                File inputFile=new File("F:\\"+filename);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);
                insert(uname,filename,cost1,kilobytes);
                        }
                        while(current<total)
                        {
                            int tempflag=0;
                            while(tempflag==0)
                            {
                                SecureRandom rand = new SecureRandom();
                                int k=rand.nextInt(10);
                                String c="cloudserver"+String.valueOf(k).toString();
                                System.out.println("c:-"+c);
                                if(!v1.contains(c))
                                {
                                    if(!c.equals("cloudserver0"))
                                    {
                                    if(!c.equals(servername))
                                    {
                                        v1.add(c);
                                        tempflag++;
                                      
                                        current++;
                                          String[] split = filename.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println(count+"length"+ext);
                                                System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                 System.out.println("F:\\public auditing\\"+c+"\\"+split[0]+count+"."+ext);
                                             if(count<=0)
                                             {
                                                 int f1=0;
                                                 int f2=1;
                                        File source = new File("F://public auditing//"+c+"//"+split[0]+f1+"."+ext);
                                        File source1 = new File("F://public auditing//"+c+"//"+split[0]+f2+"."+ext);
                                         File dest = new File("F://public auditing//cloudserver12//"+split[0]+f1+"."+ext);
                                        File dest1 = new File("F://public auditing//cloudserver12//"+split[0]+f2+"."+ext);
                                        
                                    System.out.println(source+"--------count0--------->"+dest);
                                    System.out.println(source1+"-------count0---------->"+dest1);
                                 Files.copy(source.toPath(), dest.toPath());    
                                 Files.copy(source1.toPath(), dest1.toPath());
                                  count++;

                                             }
                                             if(count==1)
                                             {
                                                 int f1=2;
                                                 int f2=3;
                                        File source = new File("F://public auditing//"+c+"//"+split[0]+f1+"."+ext);
                                        File source1 = new File("F://public auditing//"+c+"//"+split[0]+f2+"."+ext);
                                         File dest = new File("F://public auditing//cloudserver12//"+split[0]+f1+"."+ext);
                                        File dest1 = new File("F://public auditing//cloudserver12//"+split[0]+f2+"."+ext);
                                        
        System.out.println(source+"--------count1--------->"+dest);
        System.out.println(source1+"-------count1---------->"+dest1);
                                 Files.copy(source.toPath(), dest.toPath());
                                  Files.copy(source1.toPath(), dest1.toPath());
                                  count++;

                                             }
                                            if(count==2)
                                             {
                                                 int f1=4;
                                                 int f2=5;
                                         File source = new File("F://public auditing//"+c+"//"+split[0]+f1+"."+ext);
                                        File source1 = new File("F://public auditing//"+c+"//"+split[0]+f2+"."+ext);
                                         File dest = new File("F://public auditing//cloudserver12//"+split[0]+f1+"."+ext);
                                        File dest1 = new File("F://public auditing//cloudserver12//"+split[0]+f2+"."+ext);
                                        
                                    System.out.println(source+"--------count2--------->"+dest);
                                    System.out.println(source1+"-------count2---------->"+dest1);
                                  Files.copy(source.toPath(), dest.toPath());
                                  Files.copy(source1.toPath(), dest1.toPath());
                                  count++;

                                             }
                                          
                                        
                                        
                                         //total++;
                                        System.out.println("c"+c);
                                    }
                                }
                                }
                            }
                        }

 }
                        
       
       catch(Exception e)
       {
           e.printStackTrace();
       }

}
      public void insert(String uname,String fname,double count,double time)
     {
         try{
                 Connection con=getConnection();
                 Statement st =con.createStatement();
                 st.executeUpdate("insert into satime values('"+uname+"','"+fname+"','"+count+"','"+time+"')"); 
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
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
