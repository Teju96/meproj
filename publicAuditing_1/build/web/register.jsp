<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd"
    >
<html lang="en">
     
<head>    
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>Register Form</title>
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
    
function save1() {
var fn  = document.form1.fn.value;
var ln  = document.form1.ln.value;
var un  = document.form1.un.value;
var pwd = document.form1.pwd.value;
var em  = document.form1.email.value;
var cn  = document.form1.cname.value;
var addr  = document.form1.addr.value;

var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

if(fn=="" || ln=="" || un=="" || pwd=="" || em=="" || cn=="" || addr=="") {
    alert("Please Fill the informations");    
    }

if(!filter.test(em)) {
	alert("Enter a valid email id");
}
else if(isNaN(cn))
{
	alert("Enter phone Number.");
}
else if(cn<=9)
{
	alert(" phone minimum 10 Number.");
}

else{
        
        document.form1.action = "./registration";
        alert('Thanks for your information');
    }

}


function clear(){
document.form1.action='register.jsp';  
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
    
    .styled-select select {
        padding: 8px 6px;        
        width:280px;
   }
</style>


<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" style="background:lightblue">
    <%@ include file="header1.jsp" %>
    <div id="wrapper">
        <form name="form1" onsubmit="save1()">
            <fieldset>
                <legend>Registration Form</legend>
                <div>
                    <input type="text" name="fn" placeholder="First Name" value=""/>
                </div>
                
                <div>
                    <input type="text" name="ln" placeholder="Last Name" value=""/>
                </div>
                
                <div>
                    <input type="text" name="un" placeholder="Login Id" value=""/>
                </div>
                
                <div>
                    <input type="password" name="pwd" placeholder="Password" value=""/>
                </div>
                
                <div>
                    <input type="text" name="email" placeholder="Email Address" value=""/>
                </div>
                
                <div>
                    <input type="text" name="cname" placeholder="Mobile Number" value=""/>
                </div>
                
                
                <div>
                    <input type="text" name="addr" placeholder="Residential Address" value=""/>
                </div>      
    
                <div><br></div>
                
                              
                
                <div>
                <input type="submit" value="Submit" onclick="save1()"/>
                </div>
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
</body>
</html>
