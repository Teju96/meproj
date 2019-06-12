

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>
    <body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
             <%@ include file="header1.jsp" %>      
            <br><br><br><br><br><br><br><br><br>
               
         <div class="wrapper">
         
				<div class="container1">
					
					<form class="form" name="login" method="post">
						<h2>Your Session Is Expired. Click <u><a href="index.jsp">Here</a></u> to Login Again</h2> 
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
if(request.getAttribute("nuns")=="rejected") {
%>     
<script> alert('Sorry You Are Rejected By Our HR. Better Luck Next Time');</script>

<%	
}
if(request.getAttribute("nuns")=="test_over") {
%>     
<script> alert('Recruitment Process Is Going On. If Your Are Selected You Will Be Allowed To Login ');</script>

<%	
}

}
%>
        
    </body>
</html>
