<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Properties"%>
<%String x="";
String y="";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd"
    >
<html lang="en">
     
<head>    
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>cloud server Form</title>
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
    function b(a,b)
    { 
        alert("hai");
        alert(a+b);
       document.form1.u.value = a;
       document.form1.e.value = b;
       document.form1.action = "./ckeysending";
       document.form1.submit();
       alert("thank u");
    }
    function product(x,y) {
    	document.form1.u.value = x;
    	document.form1.e.value = y;
    	
    	document.form1.action = "./callbuy";
    	alert(document.form1.price.value);
    	document.form1.submit();
    	alert("thank u");
    }
  
function clear(){
document.form1.action='register.jsp';  
} 
    
function call()
{
    document.getElementById("analysis").style.display = 'none';
    document.getElementById("analysis1").style.display = 'inline';
}
</script>
</head>
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
   .tb2 {
    
    background: white; 
    border: 1px double #DDD; 
    border-radius: 5px; 
    box-shadow: 0 0 5px #333; 
    color: #666; 
    outline: none; 
    height:25px; 
    width: 170px;
}

.tb3 { 
    background: white; 
    border: 1px double #DDD; 
    border-radius: 5px; 
    box-shadow: 0 0 5px #333; 
    color: #666; 
    outline: none; 
    height:25px; 
     
  }
  
  .btn {
	border: none;
	font-family: inherit;
	font-size: inherit;
	color: inherit;
	background: gray;
	cursor: pointer;
	padding: 5px;
	display: inline-block;
	margin: 15px 30px;
	text-transform: lowercase;
	letter-spacing: 1px;
	font-weight: 700;
	outline: none;
	position: absolute;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
        height: 10;
}

.btn:after {
	content: '';
	position: absolute;
	z-index: -1;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
}

/* Pseudo elements for icons */
.btn:before,
.icon-heart:after,
.icon-star:after,
.icon-plus:after,
.icon-file:before {
	font-family: 'icomoon';
	speak: none;
	font-style: normal;
	font-weight: normal;
	font-variant: normal;
	text-transform: none;
	line-height: 1;
	position: relative;
	-webkit-font-smoothing: antialiased;
}


/* Button 4 */
.btn-4 {
	border-radius: 40px;
	border: 3px solid #fff;
	color: #fff;
	overflow: hidden;
}

.btn-4:active {
	border-color: #17954c;
	color: #17954c;
       
}

.btn-4:hover {
	//background: #02B4FA;
        //background: #D502FA;
        background: #0483C7;
}

.btn-4:before {
	position: absolute;
	height: 100%;
	font-size: 125%;
	line-height: 3.5;
	color: #fff;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
}

.btn-4:active:before {
	color: #17954c;
}

/* Button 4c */
.btn-4c:before {
	left: 70%;
	opacity: 0;
	top: 0;
}

.btn-4c:hover:before {
	left: 80%;
	opacity: 1;
}

.CSSTableGenerator {
margin:0px;padding:0px;
width:95%;
border:1px solid #000000;
-moz-border-radius-bottomleft:14px;
-webkit-border-bottom-left-radius:14px;
border-bottom-left-radius:14px;
-moz-border-radius-bottomright:14px;
-webkit-border-bottom-right-radius:14px;
border-bottom-right-radius:14px;

-moz-border-radius-topright:14px;
-webkit-border-top-right-radius:14px;
border-top-right-radius:14px;

-moz-border-radius-topleft:14px;
-webkit-border-top-left-radius:14px;
border-top-left-radius:14px;
}.CSSTableGenerator table{
border-collapse: collapse;
border-spacing: 0;
width:100%;
height:100%;
margin:0px;padding:0px;
}.CSSTableGenerator tr:last-child td:last-child {
-moz-border-radius-bottomright:14px;
-webkit-border-bottom-right-radius:14px;
border-bottom-right-radius:14px;
}
.CSSTableGenerator table tr:first-child td:first-child {
-moz-border-radius-topleft:14px;
-webkit-border-top-left-radius:14px;
border-top-left-radius:14px;
}
.CSSTableGenerator table tr:first-child td:last-child {
-moz-border-radius-topright:14px;
-webkit-border-top-right-radius:14px;
border-top-right-radius:14px;
}.CSSTableGenerator tr:last-child td:first-child{
-moz-border-radius-bottomleft:14px;
-webkit-border-bottom-left-radius:14px;
border-bottom-left-radius:14px;
}.CSSTableGenerator th{                       
height: 25px;
color: white;
background-color:#7f0000;//#A6D15C;//#556B2F;//#ffaaaa;

}.CSSTableGenerator tr:hover td{
background-color:#556B2F;//#D3D3D3;//#A6D15C;//#ffaaaa;
color: white;

}.CSSTableGenerator td{ 
height: 15px;
vertical-align:middle;
background-color:#ffffff;
border:1px solid #000000;
border-width:0px 1px 1px 0px;
text-align:middle;
padding:7px;
font-size:10px;
font-family:Arial;
font-weight:normal;
color:#000000;
}.CSSTableGenerator tr:last-child td{
border-width:0px 1px 0px 0px;
}.CSSTableGenerator tr td:last-child{
border-width:0px 0px 1px 0px;
}.CSSTableGenerator tr:last-child td:last-child{
border-width:0px 0px 0px 0px;
}
.CSSTableGenerator tr:first-child td{
background:-o-linear-gradient(bottom, #ff5656 5%, #7f0000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ff5656), color-stop(1, #7f0000) );
background:-moz-linear-gradient( center top, #ff5656 5%, #7f0000 100% );
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#ff5656", endColorstr="#7f0000");	background: -o-linear-gradient(top,#ff5656,7f0000);

background-color:#ff5656;
border:0px solid #000000;
text-align:center;
border-width:0px 0px 1px 1px;
font-size:14px;
font-family:Arial;
font-weight:bold;
color:#ffffff;
}
.CSSTableGenerator tr:first-child:hover td{
background:-o-linear-gradient(bottom, #ff5656 5%, #7f0000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ff5656), color-stop(1, #7f0000) );
background:-moz-linear-gradient( center top, #ff5656 5%, #7f0000 100% );
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#ff5656", endColorstr="#7f0000");	background: -o-linear-gradient(top,#ff5656,7f0000);

background-color:#ff5656;
}
.CSSTableGenerator tr:first-child td:first-child{
border-width:0px 0px 1px 0px;
}
.CSSTableGenerator tr:first-child td:last-child{
border-width:0px 0px 1px 1px;
}

</style>


<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
   <div class="codrops-top clearfix">				
                                <a href="index.jsp"><strong>userdetails </strong></a>
                                <a href="#" onclick="call()"><strong>upload detail</strong></a> 
                                   <a href="index.jsp"><strong>logout</strong></a>                   
			</div>
			<header class="clearfix">      
                            <img src="images/k.png" width="120" height="76" align="left"/>
                            <br>                            
                            <h1 style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Privacy-Preserving Public Auditing for
Regenerating-Code-Based</h1>
			</header>
    <div id="wrapper">
   
        <form name="form1" method="post">
           <fieldset id="analysis" style="display:inline;">
                <legend>user details</legend>
                <table class="CSSTableGenerator" align="left">
                                       <th>Fname</th>
                                       <th>Lname</th>
                                       <th>Uname</th>
                                        <th>password</th>
                                        <th>Email</th> 
                                        <th>Ph no</th>
                                        <th>addr</th>
                                       
                                         <tr><br></br>
                                        <br></br>
                                    </tr>	
                                   
                                  <%
                                       try{  
                                    	   Connection connection = null;
                                    	    Statement statement=null;
                                    	   
                                    	    ResultSet rs;
                                    	   String url = "jdbc:mysql://localhost/";
                                    	   String dbName = "publicauditing";
                                    	   String driver = "com.mysql.jdbc.Driver";
                                    	   String userName = "root";
                                    	   String dpassword= "root";

                                    	   Class.forName(driver).newInstance();
                                    	    connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                    	    statement = (Statement) connection.createStatement();
                                           rs=statement.executeQuery("select * from user");
                                           while(rs.next())
                                           {
                                               System.out.println("-------myrecomends----->");
                                               
                                                

                                       
                                  %>
                                  
                                    <tr>
                                        
                                         <td><%=rs.getString("fname")%></td>
                                         <td><%=rs.getString("lname")%></td>
                                         
                                         <td><%=rs.getString("uname")%></td>
                                         <td><%=rs.getString("pass")%></td> 
                                         <td><%=rs.getString("email")%></td>
                                         <td><%=rs.getString("ph")%></td>
                                         <td><%=rs.getString("addr")%></td>
                                         <% x=rs.getString("uname");
                                            y=rs.getString("email");
                                         %>
                                         
                                    </tr>
                                    
                                   <% }}
                                catch(Exception e)
                                {
                                    System.out.println("wrr");
                                }%>
                                 <input type="hidden" name="u"/>
                                 <input type="hidden" name="e"/> 
                                   
                                    </table>   
                             
            </fieldset>    
            
             <fieldset id="analysis1" style="display:none;">
                <legend>booking details</legend>
                <table class="CSSTableGenerator" align="left">
                                       <th>name</th>
                                       <th>filename</th>
                                       <th>servername</th>
                                        
                                      
                                       
                                        
                                         <tr><br></br>
                                        <br></br>
                                    </tr>	
                                    <tr>
                                         <h4 class="title">user details</h4>
                                    </tr>
                
                                  <%
                                       try{  
                                    	   Connection connection = null;
                                    	    Statement statement=null;
                                    	   
                                    	    ResultSet rs;
                                    	   String url = "jdbc:mysql://localhost/";
                                    	   String dbName = "publicauditing";
                                    	   String driver = "com.mysql.jdbc.Driver";
                                    	   String userName = "root";
                                    	   String dpassword= "root";

                                    	   Class.forName(driver).newInstance();
                                    	    connection = DriverManager.getConnection(url+dbName,userName,dpassword);
                                    	    statement = (Statement) connection.createStatement();
                                           rs=statement.executeQuery("select * from lblock");
                                           while(rs.next())
                                           {
                                               System.out.println("-------myrecomends----->");
                                               
                                                

                                       
                                  %>
                                  
                                    <tr>
                                        
                                         <td><%=rs.getString("uname")%></td>
                                         <td><%=rs.getString("fname")%></td>
                                         <td><%=rs.getString("path")%></td>
                                        
                                         
                                         
                                    
                                    </tr>
                                    
                                   <% }}
                                catch(Exception e)
                                {
                                    System.out.println("wrr");
                                }%>
                                    </table>   
                             
            </fieldset>    
            
            
            
        </form>
        
        
        

    </div>
</body>
</html>
