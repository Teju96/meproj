/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;

import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
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
public class aduit1 extends HttpServlet {

        public Connection connection = null;
        public Statement statement=null;
        public PreparedStatement prep; 
        public ResultSet rs;  
public Pairing pairing;
	public byte[] array;
   public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
                    System.out.println("inside aduit1");
                    String name=request.getParameter("role1");
                     System.out.println("name"+name);
                     aduit(name);
                     RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("details.jsp");
                     requestDispatcher1.forward(request, response);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                
}
   public void aduit(String name)
   {
       try{
           String usname="";
                         String fname="";
           String status="";
           String uname="";
           String dkey="";
          SecureRandom rand = new SecureRandom();
         
		PairingParametersGenerator pg = new TypeACurveGenerator(50,50);
		PairingParameters curveParams = pg.generate();
		//PairingParameters curveParams = PairingFactory.getPairingParameters("E:\\new jpbc jar\\Elliptic-Curve-Cryptography-master\\a_181_603.properties");
		this.pairing = PairingFactory.getPairing(curveParams,rand);
             
		
		//Initialize the parameters for second-level encryption
          
                    System.out.println("name:-"+name);
                    Connection con=getConnection();
			Statement st =con.createStatement();
                        ResultSet rs11 = st.executeQuery("select * from cloudserver1 where fname='"+name+"'");
			while(rs11.next()){
                              uname=rs11.getString("uname");
                              System.out.println("uname:"+uname);
                        }
                         ResultSet rs1 = st.executeQuery("select * from dkey");
                        while(rs1.next()){
                           String username1=rs1.getString("username");
                             if(uname.equals(username1))
                             {
                                 dkey=rs1.getString("dkey");
                                 System.out.println("dkey"+dkey);
                           }
                        }
                        int count=1;
                              //BigInteger numBig = new BigInteger(dkey);
                               //System.out.println("key"+numBig);
                            long lStartTime = System.currentTimeMillis();
                          ResultSet rs = st.executeQuery("select * from cloudserver1 where fname='"+name+"'");
			    while(rs.next()){
                               
                             usname=rs.getString("uname");
                             fname=rs.getString("fname"); 
                            String path=rs.getString("path");
                             
                            String sign=rs.getString("sign1");
                           String tag=rs.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver1 signature is valid.");
                                               }
                                               else
                                               {
                                                status="corrupted";
                                                System.out.println("The signature is NOT valid. so cloudserver1 is corrupted ");
                                               
                                               }  
                                               
                                               long lEndTime = System.currentTimeMillis();
                                               
                                               long difference = lEndTime - lStartTime;

double cost1=difference/1000.0;
System.out.println("starting time"+ lStartTime);
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+cost1);
System.out.println("difference"+difference/1000.0);
                File inputFile=new File("F:\\"+fname);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);  
                  finsert(usname,fname,count,cost1);
                 finsert1(usname,fname,count,cost1);
                                   count++;        
                                    }
                            if(status.equals("corrupted"))
                            {
                                 long lStartTime1 = System.currentTimeMillis();
                                 String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
               Connection connection1 = DriverManager.getConnection(url+dbName,userName,dpassword);

                                   String isql = "INSERT INTO repair VALUES(?,?,?,1)";
                                    prep = connection1.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver1");
                                    prep.execute();
                                    prep.close();
                                    status="";
                                    long lEndTime = System.currentTimeMillis();
                                    long difference = lEndTime - lStartTime;
double cost1=difference/1000.0;        
System.out.println("starting time"+ lStartTime);     
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+cost1);
System.out.println("difference"+difference/1000.0);
 File inputFile=new File("F:\\"+fname);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);
                
//                String isql1 = "INSERT INTO rtime VALUES(?,?,?,?)";
//                                    prep = connection.prepareStatement(isql1); 
//                                    prep.setString(1,usname);
//                                    prep.setString(2,fname);
//                                    prep.setDouble(3,cost1);
//                                     prep.setDouble(4,kilobytes);
//                                    prep.execute();
//                                    prep.close();
                            }
                            
                            ResultSet rs2 = st.executeQuery("select * from cloudserver2 where fname='"+name+"'");
			    while(rs2.next()){
                            usname=rs2.getString("uname");
                           fname=rs2.getString("fname"); 
                            String path=rs2.getString("path");
                            String sign=rs2.getString("sign1");
                            String tag=rs2.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver2 signature is valid.");
                                               }
                                               else
                                               {
                                                   status="corrupted"; 
                                               System.out.println("The signature is NOT valid. so cloudserver2 is corrupted ");
                                               
                                               }             
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                                               long lEndTime = System.currentTimeMillis();
                                               
                                               long difference = lEndTime - lStartTime;
                                    double cost1=difference/1000.0;
System.out.println("starting time"+ lStartTime);
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+cost1);
System.out.println("difference"+difference/1000.0);
                File inputFile=new File("F:\\"+fname);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);  
                 
                 //finsert(usname,fname,count,cost1);
                   finsert1(usname,fname,count,cost1);
                                   count++;                   
                        }
                            if(status.equals("corrupted"))
                            {
                                String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,2)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver2");
                                      prep.execute();
                                      prep.close();
                                     status="";
                            }
                            
                             ResultSet rs3 = st.executeQuery("select * from cloudserver3 where fname='"+name+"'");
			    while(rs3.next()){
                            usname=rs3.getString("uname");
                            fname=rs3.getString("fname"); 
                            String path=rs3.getString("path");
                            String sign=rs3.getString("sign1");
                            String tag=rs3.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver3 signature is valid.");
                                               }
                                               else
                                               {
                                                   status="corrupted"; 
                                               System.out.println("The signature is NOT valid. so cloudserver3 is corrupted ");
                                               
                                               }             
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                                                long lEndTime = System.currentTimeMillis();
                                               
                                               long difference = lEndTime - lStartTime;
                                    double cost1=difference/1000.0;
System.out.println("starting time"+ lStartTime);
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+cost1);
System.out.println("difference"+difference/1000.0);
                File inputFile=new File("F:\\"+fname);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);  
                 
                 finsert1(usname,fname,count,cost1);
                                   count++;     
                        }
                           if(status.equals("corrupted"))
                            {
                                String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,3)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver3");
                                      prep.execute();
                                      prep.close();
                                     status="";
                            }
                            
                             ResultSet rs4 = st.executeQuery("select * from cloudserver4 where fname='"+name+"'");
			    while(rs4.next()){
                        usname=rs4.getString("uname");
                       fname=rs4.getString("fname"); 
                            String path=rs4.getString("path");
                             String sign=rs4.getString("sign1");
                            String tag=rs4.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver4 signature is valid.");
                                               }
                                               else
                                               {
                                                   status="corrupted";
                                               System.out.println("The signature is NOT valid. so cloudserver4 is corrupted ");
                                               
                                               }             
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                                                long lEndTime = System.currentTimeMillis();
                                               
                                               long difference = lEndTime - lStartTime;
                                    double cost1=difference/1000.0;
System.out.println("starting time"+ lStartTime);
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+cost1);
System.out.println("difference"+difference/1000.0);
                File inputFile=new File("F:\\"+fname);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);  
                 
                 finsert1(usname,fname,count,cost1);
                                   count++;     
                        }
                            if(status.equals("corrupted"))
                            {
                                   String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,4)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver4");
                                      prep.execute();
                    prep.close();
                                     status="";
                            }
                            
                             ResultSet rs5 = st.executeQuery("select * from cloudserver5 where fname='"+name+"'");
			    while(rs5.next()){
                             usname=rs5.getString("uname");
                            fname=rs5.getString("fname"); 
                            String path=rs5.getString("path");
                             String sign=rs5.getString("sign1");
                            String tag=rs5.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver5 signature is valid.");
                                               }
                                               else
                                               {
                                                 status="corrupted";   
                                               System.out.println("The signature is NOT valid. so cloudserver5 is corrupted ");
                                               
                                               }
                                                long lEndTime = System.currentTimeMillis();
                                               
                                               long difference = lEndTime - lStartTime;
                                    double cost1=difference/1000.0;
System.out.println("starting time"+ lStartTime);
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+cost1);
System.out.println("difference"+difference/1000.0);
                File inputFile=new File("F:\\"+fname);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);  
                 
                 finsert1(usname,fname,count,cost1);
                                   count++;     
                        }
       
                            
                            
                            if(status.equals("corrupted"))
                            {
                                 String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,5)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver5");
                                      prep.execute();
                                      prep.close();
                                     status="";
                            }
                            
                            
                            
                             ResultSet rs6 = st.executeQuery("select * from cloudserver6 where fname='"+name+"'");
			    while(rs6.next()){
                           usname=rs6.getString("uname");
                            fname=rs6.getString("fname"); 
                            String path=rs6.getString("path");
                             String sign=rs6.getString("sign1");
                            String tag=rs6.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver6 signature is valid.");
                                               }
                                               else
                                               {
                                                   status="corrupted"; 
                                                 System.out.println("The signature is NOT valid. so cloudserver6 is corrupted ");
                                               
                                               }             
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                        }
       
                          if(status.equals("corrupted"))
                            {
                                 String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,6)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver6");
                                      prep.execute();
                                      prep.close();
                                     status="";
                            }   
                             ResultSet rs7 = st.executeQuery("select * from cloudserver7 where fname='"+name+"'");
			    while(rs7.next()){
                            usname=rs7.getString("uname");
                             fname=rs7.getString("fname"); 
                            String path=rs7.getString("path");
                             String sign=rs7.getString("sign1");
                            String tag=rs7.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver7 signature is valid.");
                                               }
                                               else
                                               {
                                                  status="corrupted";  
                                               System.out.println("The signature is NOT valid. so cloudserver7 is corrupted ");
                                               
                                               }             
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                        }
                            if(status.equals("corrupted"))
                            {
                                 String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,7)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver7");
                                      prep.execute();
                                      prep.close();
                                     status="";
                            }
       
                            
                             ResultSet rs8 = st.executeQuery("select * from cloudserver8 where fname='"+name+"'");
			    while(rs8.next()){
                            usname=rs8.getString("uname");
                             fname=rs8.getString("fname"); 
                            String path=rs8.getString("path");
                             String sign=rs8.getString("sign1");
                            String tag=rs8.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver8 signature is valid.");
                                               }
                                               else
                                               {
                                                   status="corrupted"; 
                                               System.out.println("The signature is NOT valid. so cloudserver8 is corrupted ");
                                               
                                               }             
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                        }
                            if(status.equals("corrupted"))
                            {
                                 String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,8)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver8");
                                      prep.execute();
                    prep.close();
                                     status="";
                            }
       
                            ResultSet rs9 = st.executeQuery("select * from cloudserver9 where fname='"+name+"'");
			    while(rs9.next()){
                             usname=rs9.getString("uname");
                             fname=rs9.getString("fname"); 
                            String path=rs9.getString("path");
                             String sign=rs9.getString("sign1");
                            String tag=rs9.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver9 signature is valid.");
                                               }
                                               else
                                               {
                                                   status="corrupted"; 
                                               System.out.println("The signature is NOT valid. so cloudserver9 is corrupted ");
                                               
                                               }             
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                        }
       
                            if(status.equals("corrupted"))
                            {
                                 String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,9)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver9");
                                      prep.execute();
                    prep.close();
                                     status="";
                            }
                             ResultSet rs10 = st.executeQuery("select * from cloudserver10 where fname='"+name+"'");
			    while(rs10.next()){
                            usname=rs10.getString("uname");
                             fname=rs10.getString("fname"); 
                            String path=rs10.getString("path");
                             String sign=rs10.getString("sign1");
                            String tag=rs10.getString("hvalue");
                            String temp1=tag;
                            
                            System.out.println("path:"+path);
                        
                                                 String hvalue1=MD5HashFile(path);
                                                 System.out.println("hvalue"+hvalue1);
                                                 nBytes n=new nBytes();
                                                 byte[] bbyte=new byte[hvalue1.length()];
                                                 bbyte=hvalue1.getBytes();
                                               
			
                                                 System.out.println("-------------->"+bbyte);                                                 

                                                 Element h1 = pairing.getG1().newElement().setFromHash(bbyte,0,bbyte.length);
                                                
                                                 Element temp21 = pairing.pairing(h1,h1);
                                                 String temp2=hvalue1;
                                              // System.out.println(sign+"-----signature------"+temp2);
                                               if(temp1.equals(String.valueOf(temp2)))
                                               {
                                                System.out.println("The cloudserver10 signature is valid.");
                                               }
                                               else
                                               {
                                                   status="corrupted"; 
                                               System.out.println("The signature is NOT valid. so cloudserver10 is corrupted ");
                                               
                                               } 
                                               if(status.equals("corrupted"))
                            {
                                 String url = "jdbc:mysql://localhost/";
   
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                   String isql = "INSERT INTO repair VALUES(?,?,?,10)";
                                    prep = connection.prepareStatement(isql); 
                                    prep.setString(1,usname);
                                    prep.setString(2,fname);
                                    prep.setString(3,"clouserver10");
                                      prep.execute();
                                   prep.close();
                                     status="";
                            }
                                             //String path=rs11.getString("path");
                                            //Element g = pairing.getG2().newRandomElement();
                                          // Element pk = g.duplicate().pow(numBig);
                                       
                                       //   System.out.println("dkey"+temp1);
                        }
       
                          
       
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
    public static String MD5HashFile(String filename) throws Exception {
          byte[] buf = ChecksumFile(filename);
          String res = "";
          for (int i = 0; i < buf.length; i++) {
          res+= Integer.toString((buf[i] & 0xff) + 0x100, 16).substring(1);
         }
          return res;
     }
     public static byte[]  ChecksumFile(String filename) throws Exception {
      InputStream fis = new FileInputStream(filename);
      byte[] buf = new byte[1024];
      MessageDigest complete = MessageDigest.getInstance("MD5");
      int n;
      do {
       n= fis.read(buf);
       if (n > 0) {
        complete.update(buf, 0, n);
       }
      } while (n != -1);
      fis.close();
      return complete.digest();
     }
     public void finsert(String uname,String fname,int count,double time)
     {
         try{
                 Connection con=getConnection();
                 Statement st =con.createStatement();
                 st.executeUpdate("insert into audittime values('"+uname+"','"+fname+"','"+count+"','"+time+"')"); 
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
     }
      public void finsert1(String uname,String fname,int count,double time)
     {
         try{
                 Connection con=getConnection();
                 Statement st =con.createStatement();
                 st.executeUpdate("insert into audittime1 values('"+uname+"','"+fname+"','"+count+"','"+time+"')"); 
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
