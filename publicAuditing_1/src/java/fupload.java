/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import static it.unisa.dia.gas.plaf.jpbc.wrapper.jna.PBCElementType.FieldType.G1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 *
 * @author balaji
 */
public class fupload extends HttpServlet {
public Pairing pairing;
	public static Field zr, g1, gt;
	public static Element pk_a, sk_a, isk_a, isk_b, pk_b, sk_b, ownersk_a,  k, g_k, z_k, e, rka_b;
	public static Element ciphertext;
	public static Element c1, c2, reencrypt;
	public static Element decrypt;
	public static Element decrypt_user1;
	public byte[] array;
	public static ArrayList<Element> ciphertext1, ciphertext2, reencrypttext;
	public static byte[] result;

	public static ArrayList<String> decoded;
        public static Element x,pk;
         public static Element g;
	BufferedWriter bw;
        // BigInteger numBig1;
         String m1="";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
                    long lStartTime = System.currentTimeMillis();
                    System.out.println("uploading file");
			String uname=request.getParameter("uname"); 
                        String fname=request.getParameter("fname");
                      
                         System.out.println(uname+"uploading file"+fname);
                          fupload(fname, uname);
                          upload(fname,uname);
                          long lEndTime = System.currentTimeMillis();
                          long difference = lEndTime - lStartTime;

double cost1=difference/1000.0;
System.out.println("starting time"+ lStartTime);
System.out.println("lEndTime time"+ lEndTime);
System.out.println("difference"+difference);
System.out.println("difference"+difference/1000.0);
                         // Thread.sleep(10000);
                           upload1(fname,uname);
                         //Thread.sleep(10000);    
                          upload2(fname,uname);
                         // Thread.sleep(10000);    
                          upload3(fname,uname);
                         // Thread.sleep(10000);    
                          upload4(fname,uname);
                        //  Thread.sleep(10000);    
                          upload5(fname,uname);
                        //  Thread.sleep(10000);    
                          upload6(fname,uname);
                        //  Thread.sleep(10000);    
                          upload7(fname,uname);
                       //   Thread.sleep(10000);    
                          upload8(fname,uname);
                       //   Thread.sleep(10000);    
                          upload9(fname,uname);
                        //  Thread.sleep(10000);    
                           

                File inputFile=new File("F:\\"+fname);
                double bytes = inputFile.length();
                double kilobytes = (bytes / 1024);  
                 Connection con=getConnection();
                 Statement st =con.createStatement();
                 st.executeUpdate("insert into time values('"+uname+"','"+fname+"','"+cost1+"','"+kilobytes+"')"); 
                                RequestDispatcher dis = request.getRequestDispatcher("fileuploading.jsp");
				dis.forward(request,response);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
        }
    
    
    public void fupload(String fname,String uname)
    {
        try{
                SecureRandom rand = new SecureRandom();
		PairingParametersGenerator pg = new TypeACurveGenerator(50,50);
		PairingParameters curveParams = pg.generate();		
		this.pairing = PairingFactory.getPairing(curveParams,rand);            
                 Element x = pairing.getZr().newRandomElement();
                 Element g = pairing.getG2().newRandomElement();
                 Element pk = g.duplicate().powZn(x); 
                 // send(uname,String.valueOf(pk),String.valueOf(x)); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
   
    }
    public void upload(String fname,String uname)
    {
        String filename="";
         
        try{
                                                                                      
                                            Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver1"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver1\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver1\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver1 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                                // String filename1 ="F:\\public auditing\\cloudserver1\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file1.php";        
                                                uploadFile(filename,upload_url); 
                                                Thread.sleep(5000); 
                                               ++count;   
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 public void upload1(String fname,String uname)
    {
        String filename="";
        try{
                                            Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver2"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver2\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver2\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver2 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                            //String filename1 ="F:\\public auditing\\cloudserver2\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file2.php";        
                                                uploadFile(filename,upload_url);         
                                                Thread.sleep(5000); 
                                                ++count;     
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 public void upload2(String fname,String uname)
    {
           String filename="";
           String filename1="";
        try{
                                          Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver3"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver3\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver3\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver3 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                               //String filename11 ="F:\\public auditing\\cloudserver3\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file3.php";        
                                                uploadFile(filename,upload_url);      
                                                Thread.sleep(5000); 
                                                ++count;   
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 public void upload3(String fname,String uname)
    {
        String filename="";
        try{
                                            Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                            Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver4"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver4\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver4\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver4 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                           //  String filename1 ="F:\\public auditing\\cloudserver4\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file4.php";        
                                                uploadFile(filename,upload_url);        
                                          Thread.sleep(5000); 
                                                ++count;   
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 public void upload4(String fname,String uname)
    {
        String filename="";
        try{
                                           Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver5"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver5\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver5\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver5 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                                //  String filename1 ="F:\\public auditing\\cloudserver5\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file5.php";        
                                                uploadFile(filename,upload_url);   
                                           Thread.sleep(5000); 
                                                ++count;   
                                                   
                                                  
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 public void upload5(String fname,String uname)
    {
        String filename="";
        try{
           Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver6"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver6\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver6\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                  String hvalue1=MD5HashFile(filename);
                                                //  int k=Integer.parseInt(hvalue1);
                                                   byte[] hash1=hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver6 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                            //  String filename1 ="F:\\public auditing\\cloudserver6\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file6.php";        
                                                uploadFile(filename,upload_url);       
                                         Thread.sleep(5000); 
                                                ++count;   
                                                  
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 public void upload6(String fname,String uname)
    {
        String filename="";
        try{
             Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver7"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver7\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver7\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver7 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                            //String filename1 ="F:\\public auditing\\cloudserver7\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file7.php";        
                                                uploadFile(filename,upload_url);         
                                         Thread.sleep(5000); 
                                                ++count;   
                                                  
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 public void upload7(String fname,String uname)
    {
        String filename="";
        try{
              Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                             byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver8"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver8\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver8\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver8 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                              // String filename1 ="F:\\public auditing\\cloudserver8\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file8.php";        
                                                uploadFile(filename,upload_url);      
                                           Thread.sleep(5000); 
                                                ++count;   
                                                 
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 
 
 public void upload8(String fname,String uname)
    {
        String filename="";
        try{
              Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                             byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                            Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver8"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver9\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver9\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver9 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                              // String filename1 ="F:\\public auditing\\cloudserver8\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file9.php";        
                                                uploadFile(filename,upload_url);      
                                        Thread.sleep(5000); 
                                                ++count;   
                                                 
                                                }
                                                  
			                        
        }    
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
// public void upload8(String fname,String uname)
//    {
//        String filename="";
//        try{
//                                            Connection con=getConnection();
//                                            Statement st =con.createStatement();
//                                            nBytes bytes = new nBytes();
//                                            File fis = new File("F:\\"+fname);
//                                            String hvalue=MD5HashFile("F:\\"+fname);
//                                             System.out.println("hvalue:"+hvalue);
//                                             byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
//                                             System.out.println("hash:"+hash);
//                                            Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
//            
//                                            Element sig = h; 
//                                             //   Element sig = pk;  
//                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
//                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
//                                            Element temp1 = pairing.pairing(sig,h);
//   
//                                             System.out.println(temp1+"total linear block sign."+temp1);
//                                              st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver9"+"','"+hvalue+"','"+temp1+"')"); 
//                                                int size=(int) fis.length();
//                                                int size1=size/5;
//                                                 System.out.println("total file size:"+size);
//                                                 System.out.println("dividing file size:"+size1);
//                                                 FileInputStream fis1;
//                                                 String s=fname;
//                                                 System.out.println("s:"+s);
//                                                 String[] split = s.split("\\.");
//                                                 String ext = split[split.length - 1];
//                                                 //String[] k1=s.split(".");
//                                                 System.out.println("length"+ext);
//                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
//                                                fis1 = new FileInputStream("F:\\"+fname);
//                                                
//                                                 byte buffer[] = new byte[size1];
//
//                                                int count = 0;
//                                                while (true) {
//                                                 int i = fis1.read(buffer, 0, size1);
//                                                 if (i == -1)
//                                                 break;
//
//                                                 filename ="F:\\public auditing\\cloudserver9\\"+split[0]+count+"."+ext;
//                                                  //String k1="cloud"+count+"\\";
//                                                    String f1 ="F:\\\\public auditing\\\\cloudserver9\\\\"+split[0]+count+"."+ext;
//                                                  FileOutputStream fos = new FileOutputStream(filename);
//                                                  fos.write(buffer, 0, i);
//                                                  fos.flush();
//                                                  fos.close();
//                                                 String hvalue1=MD5HashFile(filename);
//                                   
//                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
//                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);
//
//                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
//       
//                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
//        
//                                           Element temp21 = pairing.pairing(sig1,h1);
//
//                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
//                                                   
//                                           st.executeUpdate("insert into cloudserver9 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
//                                              // String filename1 ="F:\\public auditing\\cloudserver9\\"+fname;
//                                                String upload_url = "http://aislyn.in/public_aduting/upload_file9.php";        
//                                                uploadFile(filename,upload_url);      
//                                                ++count;   
//                                                  
//                                                }
//                                                  
//			                        
//        }    
//        
//        
//        
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
 public void upload9(String fname,String uname)
    {
        String filename="";
        try{
                                           Connection con=getConnection();
                                            Statement st =con.createStatement();
                                            nBytes bytes = new nBytes();
                                            File fis = new File("F:\\"+fname);
                                            String hvalue=MD5HashFile("F:\\"+fname);
                                            byte[] hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                              Element h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
            
                                              Element sig = h; 
                                             //   Element sig = pk;  
                                            hash = hvalue.getBytes(); // Generate an hash from m (48-bit hash)
                                            h = pairing.getG1().newElement().setFromHash(hash, 0, hash.length);
                                            Element temp1 = pairing.pairing(sig,h);
   
                                          System.out.println(temp1+"total linear block sign."+temp1);
                                          st.executeUpdate("insert into lblock values('"+uname+"','"+fname+"','"+"cloudserver10"+"','"+hvalue+"','"+temp1+"')"); 
                                                int size=(int) fis.length();
                                                int size1=size/5;
                                                 System.out.println("total file size:"+size);
                                                 System.out.println("dividing file size:"+size1);
                                                 FileInputStream fis1;
                                                 String s=fname;
                                                 System.out.println("s:"+s);
                                                 String[] split = s.split("\\.");
                                                 String ext = split[split.length - 1];
                                                 //String[] k1=s.split(".");
                                                 System.out.println("length"+ext);
                                                 System.out.println("k[0] :"+split[0]+"  "+"k1[1]"+split[1]);
                                                fis1 = new FileInputStream("F:\\"+fname);
                                                
                                                 byte buffer[] = new byte[size1];

                                                int count = 0;
                                                while (true) {
                                                 int i = fis1.read(buffer, 0, size1);
                                                 if (i == -1)
                                                 break;

                                                 filename ="F:\\public auditing\\cloudserver10\\"+split[0]+count+"."+ext;
                                                  //String k1="cloud"+count+"\\";
                                                    String f1 ="F:\\\\public auditing\\\\cloudserver10\\\\"+split[0]+count+"."+ext;
                                                  FileOutputStream fos = new FileOutputStream(filename);
                                                  fos.write(buffer, 0, i);
                                                  fos.flush();
                                                  fos.close();
                                                 String hvalue1=MD5HashFile(filename);
                                   
                                                   byte[] hash1= hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
                                                  Element h1 = pairing.getG1().newElement().setFromHash(hash1, 0, hash1.length);

                                                    Element sig1 =h1; // We can discard the value h, so we don't need to duplicate it.
       
                                           hash1 = hvalue1.getBytes(); // Generate an hash from m (48-bit hash)
        
                                           Element temp21 = pairing.pairing(sig1,h1);

                                           System.out.println("tag:"+hvalue1+"  "+"filename:"+filename+"co-effient:"+temp21);
                                                   
                                           st.executeUpdate("insert into cloudserver10 values('"+uname+"','"+fname+"','"+f1+"','"+hvalue1+"','"+temp21+"')");          
                                              //  String filename1 ="F:\\public auditing\\cloudserver10\\"+fname;
                                                String upload_url = "http://aislyn.in/public_aduting/upload_file10.php";        
                                                uploadFile(filename,upload_url);     
                                           Thread.sleep(5000); 
                                                ++count;   
                                                  
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
    
     public Connection getConnection(){
	 Connection conn = null;	
	try{
                String url = "jdbc:mysql://localhost/";
                String dbName ="publicauditing";
                String driver ="com.mysql.jdbc.Driver";
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

     
     public void send(String uname,String pkey,String skey) throws SQLException
	{
            try {
                System.out.println(pkey+"publick key"+skey);
                    String email="";
		    Connection con=getConnection();
			Statement st =con.createStatement();
			ResultSet rs = st.executeQuery("select * from user where uname='"+uname+"'");
			while(rs.next()){
		        email=rs.getString("email");
			}
		System.out.println("-------------em:-----------"+email);
                 
                 String to=email;
             	System.out.println("-------------em:-----------"+to);
                
              // String from = "emailcheckproject@gmail.com";
     

                    sendmail(uname,pkey,skey,to);
      
                } 
                catch (Exception e) {
                     e.printStackTrace();
              	}
        }
     		
public static void uploadFile(String filePath, String serverURL) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(serverURL);
        FileBody uploadFile = new FileBody(new File(filePath));
        MultipartEntity reqEntity = new MultipartEntity();
//        reqEntity.addPart("the args. the server takes", uploadFile);
        reqEntity.addPart("file", uploadFile);
        httpPost.setEntity(reqEntity);

        //debugging
      //  System.out.println("request: " + httpPost.getRequestLine());

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();

        //debugging
        //System.out.println("status line: " + response.getStatusLine());
        if (httpEntity != null) {
          //  System.out.println("server Response: " + EntityUtils.toString(httpEntity));
            httpEntity.consumeContent();
        }
        httpClient.getConnectionManager().shutdown();
    }
  public String sendmail(String uname1,String pkey,String skey,String too) throws SQLException, FileNotFoundException, IOException {
      
      String to = too;      
     

        System.out.println("send mail called: "+too);
        
                 Properties props = new Properties();
                 props.put("mail.smtp.ssl.trust", "*");                 
                 props.put("mail.smtp.host", "smtp.gmail.com");
                 props.put("mail.smtp.socketFactory.port", "465");
                 props.put("mail.smtp.socketFactory.class",
     	    "javax.net.ssl.SSLSocketFactory");
                 props.put("mail.smtp.auth", "true");
                 props.put("mail.smtp.port", "465");
                props.put("mail.smtp.starttls.required", "true");
                 props.put("mail.smtp.starttls.enable","true");                 
                props.put("mail.smtp.socketFactory.fallback","false");
                props.put("mail.smtp.socketFactory.port","465");
                 
                 Session session = Session.getInstance(props,
                 new javax.mail.Authenticator() {
                 protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication("usharanirathod49@gmail.com","viviandsena");//change accordingly
                 }
                 });

                 //compose message
                 try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("usharanirathod49@gmail.com"));//change accordingly
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(too.trim()));
                    message.setSubject("hi");
                    message.setText("user Name: "+ uname1 +"\n pkey:" + pkey+"\n skey: "+skey);
                    Transport.send(message);
                   
                    System.out.println("message sent successfully");
                     
                                    }
	           // replysendnodes(k,k11);
	        
                
                catch (MessagingException e) {
                       	 throw new RuntimeException(e);
              	}
                

  return null;
  }
}