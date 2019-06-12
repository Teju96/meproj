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
import java.sql.ResultSet;
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
public class registration extends HttpServlet {
    public Pairing pairing;
	public static Field zr, g1, gt;
	public static Element pk_a, sk_a, isk_a, isk_b, pk_b, sk_b, ownersk_a, g, k, g_k, z_k, e, rka_b;
       Connection connection = null;
        Statement statement=null;
        PreparedStatement prep; 
        ResultSet rs;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String fn            =  request.getParameter("fn");
            String ln            =  request.getParameter("ln");
            String un            =  request.getParameter("un");
            String pwd           =  request.getParameter("pwd");
            String email         =  request.getParameter("email");
            String cname         =  request.getParameter("cname");  
            String addr          =  request.getParameter("addr");            
           
            
System.out.println("servlet side"+fn+" "+ln+" "+un+" "+pwd+" "+email+" "+cname+" "+addr+" ");
       String un_S = "NE"; 

            
try{   
    
    String url = "jdbc:mysql://localhost/";
    String dbName ="publicauditing";
    String driver ="com.mysql.jdbc.Driver";
    String userName ="root";
    String dpassword="root";

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url+dbName,userName,dpassword);
statement = (Statement) connection.createStatement();
}
catch(Exception e)
{
e.printStackTrace();
}

     
           if(connection!=null) {
               
            System.out.println("Connection established successfully");
           
            try {
            String query = "select * from user";    
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs1 = ps.executeQuery();
                while(rs1.next()) {            
                   if(rs1.getString("uname").equals(un)) {                   
                   un_S = "E"   ;                   
                   }            
                }
            }
            catch(Exception e) {
                System.out.println("sel Exception: "+e);
            }
            
            System.out.println(un_S);
            
            
            if(un_S.equals("E")) {
                request.setAttribute("status",un_S);
            }
            else {
                
                request.setAttribute("status",un_S);
                try {     
                  keygen(un,email);
                    String isql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";
                    prep = connection.prepareStatement(isql);
                    prep.setString(1, fn);
                    prep.setString(2, ln);
                    prep.setString(3, un);
                    prep.setString(4, pwd);
                    prep.setString(5, email);
                    prep.setString(6, cname);
                   
                    prep.setString(7,String.valueOf(pk_a));           
                    prep.setString(8,String.valueOf(sk_a));
                    prep.execute();
                    prep.close();

                }
                catch(Exception e) {
                System.out.println("Insertion Exception: "+e);
                }
                
            }            
            
           }//if_connection!=null
           
//        connection.close();            

         RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("index.jsp");
         requestDispatcher1.forward(request, response);
   


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
		//isk_a = sk_a.invert().getImmutable(); //invert the secret key to calculate the proxy re-encryption key
		System.out.println("------------------------");
		System.out.print("user Public Key:");
		System.out.println(pk_a);
		System.out.println("------------------------");
		System.out.print("user Private Key:");
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
                 return new PasswordAuthentication("janakiit512@gmail.com","janubala14061991");//change accordingly
                 }
                 });

                 //compose message
                 try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("janaki@aislyntech.com"));//change accordingly
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(too));
                    message.setSubject("hi");
                    message.setText("user Name: "+ uname +"\n  privae_key: "+key);
                    Transport.send(message);
                    System.out.println("mail send successfully");
                    
                 }
                 catch(Exception e)
                 {
                     e.printStackTrace();
                 }
}
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
