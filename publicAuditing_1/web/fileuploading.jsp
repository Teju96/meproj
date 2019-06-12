
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd"
    >
<%String userdetails1=new String();%>
<html lang="en">
     
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
    
function save1() {
    alert("inside");
    var fname=document.form1.uname.value;
    var skey  = document.form1.skey.value;
   


        
        document.form1.action = "./Delegation";
        document.form1.submit();
        alert('Thanks for your information');
    }


function fupload()
{
    var name=document.form1.uname.value;
    var fname=document.form1.fname.value;
 
        document.form1.action = "./fupload";
        document.form1.submit();
        alert('Thanks for your information');
 }

function call1()
{
    document.getElementById("upload").style.display = 'none';
    document.getElementById("analysis").style.display = 'inline';
}

function clear(){
document.form1.action='register.jsp';  
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
</style>


<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
     <div class="container">
			
			<div class="codrops-top clearfix">				
                                
                 
                                <a href="#" onclick="call1()"><strong>Delegation</strong></a> 
                                <a href="./graph.jsp"><strong>settimecostgraph</strong></a> 
                                <a href="./graph1.jsp"><strong>audittimecostgraph</strong></a>
                                <a href="./graph2.jsp"><strong>sampledaudittimecostgraph</strong></a>
                                 <a href="./graph3.jsp"><strong>repairgraph</strong></a>
                                
                                 <a href="./graph4.jsp"><strong>frepairgraph</strong></a>
                                 <a href="./graph5.jsp"><strong>croamparison</strong></a>
                          
                                <a href="./index.jsp" ><strong>logout</strong></a>                           
			</div>
                        
                        
                      
			<header class="clearfix">      
                            <img src="images/k.png" width="120" height="76" align="left"/>
                            <br>                            
                            <h1 style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Privacy-Preserving Public Auditing for Regenerating-Code-Based</h1>
			</header>
                </div>
    <div id="wrapper">
        <form name="form1" method="post">
            <fieldset id="upload" style="display:inline;">
                <legend>File uploaded</legend>
               
                <div>
                     <input type="text" name="uname" placeholder="username" value="<%=userdetails1%>"/>
                </div>
                <div>
                    <input type="file" name="fname" placeholder="filename" value=""/>
                </div>
 
                <div><br></div>
  
                <div>
                <input type="submit" value="Submit" onclick="fupload()"/>
                </div>
            </fieldset>  
       
            <fieldset id="analysis" style="display:none;">
                <legend>Delegation</legend>
               
                 <div>
                     <input type="text" name="uname" placeholder="filename" value="<%=userdetails1%>"/>
                </div>
                <div>
                     <input type="text" name="skey" placeholder="enter ur secretkey" value=""/>
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
     <input type="hidden" name="key"/>  
</body>
</html>
