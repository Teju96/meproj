
<%@page import="ChartDirector.BarLayer"%>
<%@page import="ChartDirector.Chart"%>
<%@page import="ChartDirector.XYChart"%>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd"
    >
<%String userdetails1=new String();%>
<%  Vector s1=new Vector();
    Vector s2=new Vector();
    Vector s3=new Vector();
    Vector s4=new Vector();
    Vector s5=new Vector();
    
    Vector sc1=new Vector();
    Vector sc2=new Vector();
    Vector sc3=new Vector();
    Vector sc4=new Vector();
    Vector sc5=new Vector();
    String name=new String();%>
     
<head>    
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>uploading Form</title>
		<meta name="description" content="Simple Multi-Item Slider: Category slider with CSS animations" />
		<meta name="keywords" content="jquery plugin, item slider, categories, apple slider, css animation" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico"> 
		<link rel="stylesheet" type="text/css" href="css/register.css" />
  
    <script type="text/javascript">
	window.history.forward();
	function noBack(){ window.history.forward(); }
</script>
<% String pdt = null; String ver = null;%>    
<script type="text/javascript">  
   
function en() {
    alert("inside");
    var uname=document.form1.uname.value;
    
    var fname = document.form1.fname1.value;
    alert('Thanks for your information');
        document.form1.action = "./Encryption";
        document.form1.submit();
        alert('Thanks for your information');
    }


function fupload()
{
    var name=document.form1.uname.value;
    var fname=document.form1.fname.value;
    var role=document.form1.role.value;
    var dkey=prompt("enter the dkey","");
     document.form1.dkey11.value=dkey;
        document.form1.action = "./fsupload";
        document.form1.submit();
        alert('Thanks for your information');
 }

function call1()
{
    document.getElementById("upload").style.display = 'none';
    document.getElementById("analysis").style.display = 'inline';
    document.getElementById("analysis1").style.display = 'none';
     document.getElementById("analysis2").style.display = 'none';
}
function call2()
{
    document.getElementById("upload").style.display = 'none';
    document.getElementById("analysis").style.display = 'none';
    document.getElementById("analysis1").style.display = 'inline';
     document.getElementById("analysis2").style.display = 'none';
}
function call3()
{
    document.getElementById("upload").style.display = 'none';
    document.getElementById("analysis").style.display = 'none';
     document.getElementById("analysis1").style.display = 'none';
    document.getElementById("analysis2").style.display = 'inline';
}
function call4()
{
    document.getElementById("upload").style.display = 'none';
    document.getElementById("analysis").style.display = 'none';
     document.getElementById("analysis1").style.display = 'none';
    document.getElementById("analysis2").style.display = 'none';
    document.getElementById("analysis3").style.display = 'inline';
}
function call5()
{
    document.getElementById("upload").style.display = 'none';
    document.getElementById("analysis").style.display = 'none';
     document.getElementById("analysis1").style.display = 'none';
    document.getElementById("analysis2").style.display = 'none';
    document.getElementById("analysis3").style.display = 'none';
      document.getElementById("analysis4").style.display = 'inline';
}
function clear(){
document.form1.action='register.jsp';  
} 
  function download(a,b) {
    alert(a+b);
   var dkey=prompt("enter the dkey","");
     document.form1.dkey.value=dkey;
    document.form1.usname.value=a;
    document.form1.fsname.value=b;
    document.form1.action="./decryption";
    document.form1.submit();
     alert(a+b);
}  
 function del(a,b)
{
    alert(a+b);
    document.form1.uname11.value=a;
    document.form1.fname11.value=b;
    var dkey=prompt("enter the dkey","");
    document.form1.dkey11.value=dkey;
    document.form1.action = "./deletefile";
    document.form1.submit();
}
</script>
</head>
 <% 

if(session.getAttribute("user")!=null) 
userdetails1=(String)session.getAttribute("user"); 
%>
<style type="text/css">
    #wrapper {
        width:450px;
        margin:0 auto;
        font-family:Verdana, sans-serif;
    }
    legend {
        color:#0481b1;
        font-size:16px;
        padding:0 10px;
        background:#fff;
        -moz-border-radius:4px;
        box-shadow: 0 1px 5px rgba(4, 129, 177, 0.5);
        padding:5px 10px;
        text-transform:uppercase;
        font-family:Helvetica, sans-serif;
        font-weight:bold;
    }
    fieldset {
        border-radius:4px;
        background: #fff; 
        background: -moz-linear-gradient(#fff, #f9fdff);
        background: -o-linear-gradient(#fff, #f9fdff);
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff), to(#f9fdff)); 
        background: -webkit-linear-gradient(#fff, #f9fdff);
        padding:20px;
        border-color:rgba(4, 129, 177, 0.4);
    }
    input,
    textarea {
        color: #373737;
        background: #fff;
        border: 1px solid #CCCCCC;
        color: #aaa;
        font-size: 14px;
        line-height: 1.2em;
        margin-bottom:15px;

        -moz-border-radius:4px;
        -webkit-border-radius:4px;
        border-radius:4px;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1) inset, 0 1px 0 rgba(255, 255, 255, 0.2);
    }
    input[type="text"],    
    input[type="password"]{
        padding: 8px 6px;
        height: 22px;
        width:280px;
    }
    input[type="text"]:focus,
    input[type="password"]:focus {
        background:#f5fcfe;
        text-indent: 0;
        z-index: 1;
        color: #373737;
        -webkit-transition-duration: 400ms;
        -webkit-transition-property: width, background;
        -webkit-transition-timing-function: ease;
        -moz-transition-duration: 400ms;
        -moz-transition-property: width, background;
        -moz-transition-timing-function: ease;
        -o-transition-duration: 400ms;
        -o-transition-property: width, background;
        -o-transition-timing-function: ease;
        //expand attribute
        width: 350px;
        
        border-color:#ccc;
        box-shadow:0 0 5px rgba(4, 129, 177, 0.5);
        opacity:0.6;
    }
    input[type="submit"]{
        background: #222;
        border: none;
        text-shadow: 0 -1px 0 rgba(0,0,0,0.3);
        text-transform:uppercase;
        color: #eee;
        cursor: pointer;
        font-size: 15px;
        margin: 5px 0;
        padding: 5px 22px;
        -moz-border-radius: 4px;
        border-radius: 4px;
        -webkit-border-radius:4px;
        -webkit-box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
        -moz-box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
        box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
    }
    textarea {
        padding:3px;
        width:96%;
        height:100px;
    }
    textarea:focus {
        background:#ebf8fd;
        text-indent: 0;
        z-index: 1;
        color: #373737;
        opacity:0.6;
        box-shadow:0 0 5px rgba(4, 129, 177, 0.5);
        border-color:#ccc;
    }
    .small {
        line-height:14px;
        font-size:12px;
        color:#999898;
        margin-bottom:3px;
    }
    
    .styled-select select {
        padding: 8px 6px;        
        width:280px;
   }
   table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
</style>

<% 

if(session.getAttribute("user")!=null) 
userdetails1=(String)session.getAttribute("user"); 
%>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
       <div class="container">
			
			<div class="codrops-top clearfix">				
                                
                                
                                <a href="./fileuploading.jsp" ><strong>logout</strong></a>                           
			</div>
                        
                        
                      
			<header class="clearfix">      
                             <img src="images/trffic.png" width="120" height="76" align="left"/>
                            <br>                            
                            <h1 style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Privacy-Preserving Public Auditing for Regenerating-Code-Based
</h1>
			</header>
                </div>
    <div id="wrapper">
        <form name="form1" method="post">
            <fieldset id="upload" style="display:inline;">
                <legend>GRAPH</legend>
              
                                <%
                                 

String chart1URL = "";
String chart2URL = "";
String imageMap1 = ""; 
String imageMap2 = ""; 

String xxx = "none";
String yyy = "none";
String zzz = "none";
String gs = "none";
Connection conn = null;
                String url = "jdbc:mysql://localhost/";
                String dbName = "publicauditing";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root";
                String dpassword= "root";
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url+dbName,userName,dpassword);
			Statement st =conn.createStatement();
                        //System.out.println("------inside try2"+user);
                        //ResultSet rs = st.executeQuery("select * from recommend where uname='"+user+"'");
                        
                      
                                    s1.removeAllElements();
                                    sc1.removeAllElements();
                                    ResultSet rs = st.executeQuery("select * from audittime LIMIT 6");
                                    while(rs.next())
                                    {
                                       if(rs.getString("uname").equals(userdetails1))
                                       {
                                           String size=rs.getString("count");
                                           String cost=rs.getString("ttime");
                                           s1.add(size);
                                           
                                           sc1.add(cost);
                                           
                                       }
                                    }
                                            

                        double[] cr1 = new double[8];
                       double[] cr2 = new double[8];
                        String[] labels = new String[8];
                          

try {
   
    for(int i=0;i<6;i++) {
         String a=sc1.get(i).toString();
          double aa = Double.parseDouble(a)*200;
          String bb  =s1.get(i).toString();
          cr1[i]=aa+100;
        
         System.out.println("cr1"+ cr1[i]); 
           labels[i]=bb;
       
       
                  
    }
     cr1[6]=250;
        cr1[7]=300;
       labels[6]="7";
     labels[7]="8";
     cr2[0]=100;
          cr2[1]=200;
          cr2[2]=300;
          cr2[3]=420;
          cr2[4]=570;
          cr2[5]=700;
          cr2[6]=800;
          cr2[7]=950;
   
    String param = "";
    if(session.getAttribute("param")!=null) {
        String data = (String)session.getAttribute("param");
        if(data.trim().equals("Station")) { param = "Station";}
        if(data.trim().equals("Driver type")) { param = "Driver type";}
        if(data.trim().equals("Accident severity")) { param = "Accident severity";}
        if(data.trim().equals("Age of victim")) { param = "Age of victim";}
        if(data.trim().equals("Vehicle Type")) { param = "Vehicle Type";}
        if(data.trim().equals("Road type")) { param = "Road type";}
        if(data.trim().equals("Light conditions")) { param = "Light conditions";}
        if(data.trim().equals("Weather conditions")) { param = "Weather conditions";}
        if(data.trim().equals("Road surface condition")) { param = "Road surface condition";}
        if(data.trim().equals("Vehicle manoeuvre")) { param = "Vehicle manoeuvre";}
        if(data.trim().equals("hour")) { param = "hour";}
        
    }
    System.out.println("labels"+ labels.length+" "+labels);
    System.out.println("values"+ cr1.length);
                        XYChart c = new XYChart(600,350); //750, 600
                       c.addTitle(" comparison graph", "", 10);

                     c.setPlotArea(60, 60,500,200);
                       
                      // c.setPlotArea(50, 25,600,250, 0xffffc0, 0xffffe0);

                       c.addLegend(55, 18, false, "",10).setBackground(Chart.Transparent);
                         BarLayer layer=c.addBarLayer(Chart.Side,3);
                          layer.addDataSet(cr2,0x80ff80,"individualy audit");
                           layer.addDataSet(cr1,0xff8080,"batch audit");
                        c.xAxis().setLabels(labels);
                        c.xAxis().setLabelStep(0);
                        c.addTitle("audit time cost", "Times New Roman Bold Italic", 15).setBackground(0xccccff, 0x000000, Chart.glassEffect());
                        c.yAxis().setTitle("audit time cost", "Arial Bold Italic", 12);
                        c.xAxis().setTitle("Number of blocks per server", "Arial Bold Italic", 12);

                        chart1URL = c.makeSession(request, "chart1");
                        //imageMap1 = c.getHTMLImageMap("", "","title='At {xLabel} Seconds, Rated as {value}'"); 
                        imageMap1 = c.getHTMLImageMap("", "", "title='Hour {xLabel}: Traffic {value} GBytes'");              
                      
}

catch(Exception e)
{
    e.printStackTrace();
}


                                   
                                   %>
                                   <div> <a href ="<%=response.encodeURL("getchart.jsp?" + chart1URL)%>" download>
                    <img src='<%=response.encodeURL("getchart.jsp?" + chart1URL)%>' usemap="#map1" border="0"> </img>   
                   <map name="map1"><%=imageMap1%></map>
                  
    </a></div>
                                    </fieldset>
                                    </fieldset>
    
    
    
    
        </form>
        
        
        
<%
if(request.getAttribute("status")!=null) {   
    
    if(request.getAttribute("status")=="E") {
    %>     
    <script> alert('User Name Exist Already ');</script>
    <%
    }
    if(request.getAttribute("status")=="NE") {
    %>     
    <script> alert('User Registration success');</script>
    <%
    }    
}
%>
    </div>
     <input type="hidden" name="key"/>  
</body>

