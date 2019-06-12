

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/login.css">
      <script type="text/javascript">
      window.history.forward();
       function noBack(){ window.history.forward(); }
</script>  

        
        
<script type="text/javascript">
        
// window.history.forward();
//function noBack(){ window.history.forward(); }
        
function b() {  
    var username = document.login.un.value;
    var password = document.login.pwd.value;
    var role=document.login.role.value;
    //alert(role);
               if((username === "") || (password === "")){
			alert("Enter the username and password.");
		}
               else if(role=="TPA")
                {
                  if((username === "TPA") && (password === "TPA")){
                      document.login.action="details.jsp";
		      document.login.submit();
		}
               else{
                   alert("invalid credentails");  
                }
                }
               else if(role=="cloudserver")
               {
                 if((username === "admin") && (password === "admin")){
                      document.login.action="cserver_details.jsp";
		      document.login.submit();
		}
              else{
                   alert("invalid credentails");  
                }  
                }		
	      else if(role=="user")
                {
		       alert("thank u log in");
			document.login.action="./login";
			document.login.submit();
		}
              else if(role=="proxy")
               {
                 if((username === "proxy") && (password === "proxy")){
                      document.login.action="proxy_details.jsp";
		      document.login.submit();
	       }
              else{
                   alert("invalid credentails"); 
                }  
                }
            }

</script>
    </head>
    <body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
             <%@ include file="header1.jsp" %>      
            <br><br><br><br><br><br><br><br><br>
                <br><br><br><br><br><br><br><br><br>
         <div class="wrapper">
         
	<div class="container1">
					
		<form class="form" name="login" method="post">
                    <input type="text" placeholder="Username" name="un">
			<input type="password" placeholder="Password" name="pwd">
                        <select name="role">                        
                        <option value=""><---select----></option>
                        <option value="user">user</option>
                        <option value="proxy">proxy</option>
                        <option value="TPA">TPA</option>
                        <option value="cloudserver">cloud server</option>
                        </select>
			<button type="submit" id="login-button" onclick="b()">Login</button>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index.js"></script>

<%
if(request.getAttribute("nuns")!=null) {   
if(request.getAttribute("nuns")=="NE") {
%>     
<script> alert('Wrong User Name or Password');</script>
<%
}  

%>     

<%	



}

if(request.getAttribute("status")!=null) {   

    if(request.getAttribute("status")=="NE") {
    %>     
    <script> alert('Your Registration success. Please Login ');</script>
    <%
    }    
}
%>
    </body>
</html>
