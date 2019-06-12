/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Message;
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

/**
 *
 * @author balaji
 */
public class pkeysending extends HttpServlet {
public Pairing pairing;
	public static Field zr, g1, gt;
	public static Element pk_a, sk_a, isk_a, isk_b, pk_b, sk_b, ownersk_a, g, k, g_k, z_k, e, rka_b;
  public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
                       System.out.println("inside");
			String username=request.getParameter("u");
                        String email=request.getParameter("e");
                        
                        System.out.println(email+"---"+username);
			keygen(username,email);
			
				RequestDispatcher dis = request.getRequestDispatcher("proxy_details.jsp");
				dis.forward(request,response);
			}
		
		catch(Exception e){
			e.printStackTrace();
		}		
	}
public void keygen(String uname,String email)
{
    try{
                SecureRandom rand = new SecureRandom();
		PairingParametersGenerator pg = new TypeACurveGenerator(50,50);
		PairingParameters curveParams = pg.generate();
		//PairingParameters curveParams = PairingFactory.getPairingParameters("E:\\new jpbc jar\\Elliptic-Curve-Cryptography-master\\a_181_603.properties");
		this.pairing = PairingFactory.getPairing(curveParams,rand);
		
		//Initialize the parameters for second-level encryption
		g1 = pairing.getG1();
	        gt = pairing.getGT();
	        zr = pairing.getZr();
	        g = g1.newRandomElement().getImmutable();
		ElementPowPreProcessing gPre = g.getElementPowPreProcessing();
	         k= zr.newRandomElement().getImmutable();
	         g_k = gPre.powZn(k).getImmutable();
		z_k = pairing.pairing(g, g_k).getImmutable();
					      
		//Generate data owner keys
		sk_a = pairing.getZr().newRandomElement().getImmutable(); //private key
		pk_a = gPre.powZn(sk_a).getImmutable();
		isk_a = sk_a.invert().getImmutable(); //invert the secret key to calculate the proxy re-encryption key
		System.out.println("------------------------");
		System.out.print("poxy's server Public Key:");
		System.out.println(pk_a);
		System.out.println("------------------------");
		System.out.print("poxy's server Private Key:");
		System.out.println(sk_a);
		System.out.println("------------------------");
                sendmail(uname,email,sk_a);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}

public void sendmail(String uname,String too,Element key) {
      
      

        System.out.println("send mail called: "+too);
        
                 Properties props = new Properties();
                 props.put("mail.smtp.ssl.trust", "*");                 
                 props.put("mail.smtp.host", "smtp.gmail.com");
                 props.put("mail.smtp.socketFactory.port", "465");
                 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                 props.put("mail.smtp.auth", "true");
                 props.put("mail.smtp.port", "465");
                props.put("mail.smtp.starttls.required", "true");
                 props.put("mail.smtp.starttls.enable","true");                 
                props.put("mail.smtp.socketFactory.fallback","false");
                props.put("mail.smtp.socketFactory.port","465");
                 
                 Session session = Session.getInstance(props,
                 new javax.mail.Authenticator() {
                 protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication("janaki@aislyntech.com","softwarerocks");//change accordingly
                 }
                 });

                 //compose message
                 try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("janaki@aislyntech.com"));//change accordingly
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(too));
                    message.setSubject("hi");
                    message.setText("user Name: "+ uname +"\n  proxyserver: "+key);
                    Transport.send(message);
                    System.out.println("mail send successfully");
                     Connection con=getConnection();
	            Statement st =con.createStatement();
                   String isql = "INSERT INTO proxyserver VALUES(?,?)";
                    
                    PreparedStatement prep = con.prepareStatement(isql);
                    prep.setString(1,String.valueOf(pk_a));
                    prep.setString(2,String.valueOf(sk_a));
                    
                   
                                
                   
                    prep.execute();
                    prep.close();
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
}
