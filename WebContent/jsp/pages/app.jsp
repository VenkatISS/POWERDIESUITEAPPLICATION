<%@ page import="java.util.UUID, com.it.diesuiteapp.utils.RandomAlphaNumericStringGenerator,
	com.it.diesuiteapp.response.MessageObject"%> 
<!DOCTYPE html>
<html style="overflow :hidden;">
	<%
		UUID uuid = UUID.randomUUID();
	    String randomUUIDString = uuid.toString();
    %>
  	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<!-- CSS-->
    	<link rel="stylesheet" type="text/css" href="css/main.css?<%=randomUUIDString%>">
		<!-- For Password Masking-START --> 
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-show-password/1.0.3/bootstrap-show-password.min.js"></script>
 -->		<!--Password Masking-END -->
		<title>POWER DIESUITE WEB APPLICATION - USER LOGIN</title>
		<jsp:useBean id="showDIV" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="showDIV1" scope="request" class="java.lang.String"></jsp:useBean>
		
		<script src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
		<script src="js/app.js?<%=randomUUIDString%>"></script>
		<script type="text/javascript" src="js/global.js?<%=randomUUIDString%>"></script>
		<script src="js/commons/detectmobilebrowser.js?<%=randomUUIDString%>"></script>
	  	<script src="js/commons/checkBrowser.js?<%=randomUUIDString%>"></script>
	  	<script type="text/javascript">
    	</script>
 		<script type="text/javascript">
			window.onload = checkBrowser();
			window.onbeforeunload = winClose();
		</script>
	  	<script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>
		
  	</head>
   	<body class="bg_white apppage_body">
		<section class="login-content">
		
<!-- 		<div class="containr" style="width:100%;height:auto;">
// This image is kept in css for bg_white class 
 				<img class="img-responsive" src="images/image3.jpg" style="width:100%;opacity:0.8;">
// 				<img class="img-responsive" src="images/background.png" style="width:100%;opacity:0.4;">
      		</div> -->
      		
      		<div class="contbox" id="contbox" >
	      		<div class="logo" style="margin-top:-10%;">
    	    		<h1><img src="images/logo.jpg" alt="" title="" width="100"/><br/>
						POWER DIESUITE ENTERPRISE BUSINESS SOLUTIONS PORTAL
					</h1>
      			</div>
 				<div style="text-align:center;" class="msgcls">
 				<%
					MessageObject msgObj = (MessageObject) request.getAttribute("msg_obj");
					if ((null != msgObj)) {
						if ((msgObj.getMessageId() == 1001) || (msgObj.getMessageId() == 1000) || (msgObj.getMessageId() == 1002)) {
				%>
					<font color="red" id="reg_errmsg"><%=msgObj.getMessageText()%></font>
				<%
						}
					}
				%>
				<%
					if ((showDIV != null) && showDIV.equalsIgnoreCase("successDiv")) {
				%>
					<div id="successDiv" align="center">
						<font color="red" face="tahoma" size="2"> ADMIN REGISTRATION SUCCUSSFUL. <BR>PLEASE CHECK YOUR REGISTERED EMAIL FOR ACTIVATION LINK</font>
					</div>
				<%
					}
					else if ((showDIV1 != null) && showDIV1.equalsIgnoreCase("successDiv1")) {
				%>
					<div id="successDiv" align="center">
						<font color="red" face="tahoma" size="2">AN EMAIL HAS BEEN SENT TO THE GIVEN EMAIL ID <BR> WITH A LINK TO RESET YOUR PASSWORD</font>
					</div>
				<%
					} 
                    else {
				%>
			</div>
			
			<%  if(msgObj != null) {
					if(msgObj.getMessageText() == null) { %>
						<div class="col-md-4 col-sm-4 col-xs-4 login-box" id="loginDiv" style="margin-top:1%;">
			<% 		}else if((msgObj.getMessageText() != null) && (msgObj.getMessageText().contains("AGENCY ID / EMAIL ID ALREADY EXISTS"))) { %>
						<div class="col-md-4 col-sm-4 col-xs-4 login-box" id="loginDiv" style="margin-top:2%;margin-left:48px;">
			<% 		}else if((msgObj.getMessageText() != null) && (!(msgObj.getMessageText().contains("AGENCY ID / EMAIL ID ALREADY EXISTS")))) { %>
						<div class="col-md-4 col-sm-4 col-xs-4 login-box" id="loginDiv" style="margin-top:1%;">
			<%		}}else {%>
						<div class="col-md-4 col-sm-4 col-xs-4 login-box" id="loginDiv" style="margin-top:1%;">
			<% 	}%>
<%-- 		  		<form name="dealer_login_form" id="dealer_login_form" class="login-form" action="<%=request.getContextPath() %>/login" method="post"> --%>
		  		<form name="dealer_login_form" id="dealer_login_form" class="login-form" action="<%=request.getContextPath() %>/login" method="post" onsubmit="return validateLoginDetails(this)">
	      			<input type="hidden" name="actionId" value="1001">
					<input type="hidden" name="page" value="powerdie/home">
	        		<h3 class="login-head"><img src="images/login_icon.png" alt="" title=""/> DIESUITE LOGIN</h3>
	        		<div class="form-group">
          			CHOOSE:<select class="sform-control" id="adminoruser" name="adminoruser" onchange="regED()"> 
          			<option value="0">---select---</option>
          			<option value="1">user</option>
          			<option value="2">admin</option>
          			</select>
          			</div>
        			<div class="form-group">
            			USER ID<input class="uform-control" type="text" id="ai" name="ai" title="USER ID" placeholder="USER ID" autofocus>
          			</div>
          			<div class="form-group">
            			PASSWORD<input class="pform-control" type="password" id="pwd" name="pwd" title="PASSOWRD" placeholder="PASSWORD" data-toggle="password">
          			</div>
          			
            		<p class="forgot_pass"><a data-toggle="flip" class="forgotpwd cleardata"><b>Forgot Password ?</b></a></p>
          			<div class="form-group btn-container">
<!--             			<input type="button" value="L O G I N" class="btn btn-success m-r-15" onclick="validateLoginDetails(this.form)"> -->
            			<input type="submit" value="L O G I N" class="btn btn-success m-r-15">
						<input type="button" value="NEW REGISTRATION" class="btn btn-info cleardata" id="regform" onclick="showRegistrationForm()" disabled>
          			</div>
        		</form>


        		<form class="forget-form" name="forget-form" id="forget-form" action="<%=request.getContextPath() %>/login" method="post" onsubmit="return validateEmailForForgotPwd(this)">
	        		<input type="hidden" name="actionId" value="1005">
					<input type="hidden" name="page" value="app">
          			<h3 class="login-head"><i class="fa fa-lg fa-fw fa-lock"></i>Forgot Password ?</h3>
          			<p><center>Enter Your Registered Email Address And We Will Send <br>You The Link To Reset Your Password</center></p>
          			<div class="form-group">
            			<label class="control-label">EMAIL</label>
            			<input class="form-control" type="text" name="femailId" id="femailId" placeholder="Email">
          			</div>
          			<div class="form-group btn-container">
          				<input type="submit" value="RESET PASSWORD" class="btn btn-info">
		          	</div>
          			<div class="form-group mt-20">
            			<p class="semibold-text mb-0"><a data-toggle="flip" class="clearform"><i class="fa fa-angle-left fa-fw"></i> Back to Login</a></p>
          			</div>
        		</form>
      		</div>


      		<div class="col-md-4 col-sm-4 col-xs-4 login-box register-box" id="registrationDiv" style="display: none;margin-top: 1%;">
      			<form name="dealer_reg_form" id="dealer_reg_form" class="login-form" action="<%=request.getContextPath()%>/RegistrationControlServlet" method="post" onsubmit="return submitRegistrationForm(this)">
					<input type="hidden" name="actionId" value="1002">
					<input type="hidden" name="page" value="app">
<!-- 					<input type="hidden" name="oc" value="2"> -->
          			<h3 class="login-head"><img src="images/registration_icon.png" alt="" title=""/> ADMIN REGISTRATION</h3>
		  			<div class="row">
          				<div class="form-group col-sm-6">
            				<input class="form-control" type="text" name="adminid" id="adminid" title="ADMIN ID" placeholder="ADMIN ID" autofocus>
          				</div>
          				<div class="form-group col-sm-6">
            				<input class="form-control" type="text" name="aname" id="aname" maxlength="35" title="ADMIN NAME" placeholder="ADMIN NAME">
          				</div>
          				<div class="form-group col-sm-6">
            				<input class="form-control" type="text" name="amobile" id="amobile" title="ADMIN MOBILE" placeholder="ADMIN MOBILE">
          				</div>
          				<div class="form-group col-sm-6">
            				<input class="form-control" type="text" name="emailId" id="emailId" title="EMAIL ID" placeholder="EMAIL ID">
          				</div>
          				<div class="form-group col-sm-6">
            				<input class="form-control" type="text" name="gstin" id="gstin" title="GSTIN" placeholder="GSTIN">
          				</div>
          				<div class="form-group col-sm-6">
            				<select class="form-control select_dropdown" id="storut" name="storut">
								<option value="0">STATE OR UT</option>
								<option value="01">01 - JAMMU & KASHMIR</option>
								<option value="02">02 - HIMACHAL PRADESH</option>
								<option value="03">03 - PUNJAB</option>
								<option value="04">04 - CHANDIGARH</option>
								<option value="05">05 - UTTARAKHAND</option>
								<option value="06">06 - HARYANA</option>
								<option value="07">07 - DELHI</option>
								<option value="08">08 - RAJASTHAN</option>
								<option value="09">09 - UTTAR PRADESH</option>
								<option value="10">10 - BIHAR</option>
								<option value="11">11 - SIKKIM</option>
								<option value="12">12 - ARUNACHAL PRADESH</option>
								<option value="13">13 - NAGALAND</option>
								<option value="14">14 - MANIPUR</option>
								<option value="15">15 - MIZORAM</option>
								<option value="16">16 - TRIPURA</option>
								<option value="17">17 - MEGHALAYA</option>
								<option value="18">18 - ASSAM</option>
								<option value="19">19 - WEST BENGAL</option>
								<option value="20">20 - JHARKHAND</option>
								<option value="21">21 - ODISHA</option>
								<option value="22">22 - CHHATTISGARH</option>
								<option value="23">23 - MADHYA PRADESH</option>
								<option value="24">24 - GUJARAT</option>
								<option value="25">25 - DAMAN & DIU</option>
								<option value="26">26 - DADRA & NAGAR HAVELI</option>
								<option value="27">27 - MAHARASHTRA</option>
								<option value="29">29 - KARNATAKA</option>
								<option value="30">30 - GOA</option>
								<option value="31">31 - LAKSHDWEEP</option>
								<option value="32">32 - KERALA</option>
								<option value="33">33 - TAMIL NADU</option>
								<option value="34">34 - PONDICHERRY</option>
								<option value="35">35 - ANDAMAN & NICOBAR ISLANDS</option>
								<option value="36">36 - TELANGANA</option>
								<option value="37">37 - ANDHRA PRADESH</option>
								<option value="97">97 - OTHER TERRITORY</option>
							</select>
          				</div>
		  				<div class="clearfix"></div>
          					<div class="form-group col-sm-6">
            					<input class="form-control" type="password" name="pwd" id="pwd" title="PASSWORD" placeholder="PASSWORD">
								<p class="small m-t-5">[8-12 Characters]</p>
          					</div>
          					<div class="form-group col-sm-6">
            					<input class="form-control" type="password" name="cpwd" id="cpwd" title="CONFIRM PASSWORD" placeholder="CONFIRM PASSWORD">
								<p class="small m-t-5">[Same as Password]</p>
          					</div>
          					<div class="clearfix"></div>
							<div class="clearfix"></div>
		  					<div class="form-group col-sm-6" style="margin-top:-2%;"><br>
								<label>TYPE THE CHARACTERS</label>
								<input type="text" class="form-control" name="uecaptchastr" title="CAPTCHA" value="">
									<%
										String cs = RandomAlphaNumericStringGenerator.getAlphaString(); 
												request.getSession().setAttribute("captchastr",cs);
									%>	
								<input type="hidden" id="captchaValue" value="<%=cs%>">
		  					</div>
		  					<div class="form-group col-sm-6 captchacls">
								<label class="hidden-xs">&nbsp;</label>
								<img alt="No Captcha" class="captcha" src="jsp/pages/scaptcha.jsp">
		  					</div>
		 					<div class="clearfix"></div>
 		  					<div>
                				<input id="checkbox1" type="checkbox" style="margin-left: 3.5%;">
		  						<a href="javascript:termsOfServices()" id="tos" style="margin-left: 1%;">Terms Of Services</a>
	          				</div>
<!--           					<center><a class="btn btn-success m-t-15 reg_btn" href="javascript:submitRegistrationForm(dealer_reg_form)">REGISTER</a></center>-->
          					<input type="submit" value="REGISTER" class="btn btn-success m-t-15 reg_btn">
		  					<p class="semibold-text reg_a"><a href="javascript:showLoginForm()" class="reg_a clearregdata" style="width: 150px;"><i class="fa fa-angle-left fa-fw"></i> Back to Login</a></p>
		  				</div>
		  				<div class="clearfix"></div>
        			</form>
      			</div>
			</div>
			
				
			<div class="tos_modal fade in" id="myModal" style="display: none; margin-left: -25%; height:100%;width: 156%;">
				<div class="modal-dialog modal-lg">
					<!-- Modal content-->
						<div class="tos_modal_div">
							<div class="modal-header">
								<span class="close" id="close" onclick="closeFormDialog()">&times;</span>
								<h4>TERMS OF SERVICE</h4>
							</div>
							<div class="modal-body" style="padding:20px;">
Updated on: 01st March 2019.<br>
Effective Date: 01st March 2019.
<br><br>



THIS IS AN AGREEMENT BETWEEN YOU OR THE ENTITY THAT YOU REPRESENT (hereinafter <q>You</q> or <q>Your</q>) AND POWER DIESUITE (INDIA) PVT LTD (hereinafter <q>POWER DIESUITE</q>) GOVERNING YOUR USE OF diesuite SOFTWARE PRODUCTS.M<br>
<h4>Parts of this Agreement</h4>
<h4>END OF TERMS OF SERVICE</h4>
If you have any questions or concerns regarding this Agreement, please contact us at <a href="mailto:support@diesuite.in">support@diesuite.in</a> .
<br>
<!-- <div style="float:right;">
<input id="checkbox1" type="checkbox">
<label for="checkbox1" >I Agree</label>
</div> -->
</div>
        					</div>
        				</div>
        					
<!--         			<div style="display:none;overflow:scroll;width:500px;height:500px;position:absolute;border:2px solid white;background-color:white; " id="tos">
 -->
					</div>
				
				
				
        		<%} %>
<!--     		</div> -->
</section>

  	</body>
    <script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
    <script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
    <script src="js/commons/main.js?<%=randomUUIDString%>"></script>
    <script type="text/javascript">
		$('.msgcls').click(function(e) { //button click class name is msgcls
			e.stopPropagation();
		});
		if(!($("#reg_errmsg").is(":visible"))){
			$(".login-box").css("margin-left","0px");
		}
		$(function(){
			$(document).click(function(){  
				$('.msgcls').hide();
				
				if(!($("#reg_errmsg").is(":visible"))){
					$(".login-box").css("margin-left","0px");
				}

			});
			$('.forgotpwd').click(function(){  
				$('.msgcls').hide();
			});
			$('.cleardata').click(function(){
				$('#dealer_login_form')[0].reset();
			});
 			$('.clearregdata').click(function(){
				$('#dealer_reg_form')[0].reset();
			});
			$('.clearform').click(function(){  
				$('#forget-form')[0].reset();
			});

		});
	</script>
    <script type="text/javascript">
    	function termsOfServices() {
//    		document.getElementById("contbox").style.display="none";
    		document.getElementById("myModal").style.display="block";
    	}
    	function closeFormDialog() {
    		document.getElementById("myModal").style.display="none";
//    		document.getElementById("contbox").style.display="block";
    	}
    	
    </script>
	<script type="text/javascript">
		$("#pwd").password('toggle');
	</script>
	
	<script type="text/javascript">
	function regED(){
		var formobj=document.getElementById("dealer_login_form");
		var selectedDropdown=formobj.adminoruser;
		var selectedDropdownv=selectedDropdown.options[selectedDropdown.selectedIndex].value;
		if(selectedDropdownv==2)
			document.getElementById("regform").disabled=false;
		else if(selectedDropdownv==0 || selectedDropdownv==1)
			document.getElementById("regform").disabled=true;
	}
	</script>
	
	<script>
	//	Example For Optional Comment:
	//	<script>
	//	<!-- 
	//	document.write("Helloo"); //-->
	//	</script>
	</script>
	
</html>