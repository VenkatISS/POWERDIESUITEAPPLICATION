<%@ page import="java.util.UUID" %>
<!DOCTYPE html>
<html>
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
    <title>POWER DIESUITE WEB APPLICATION - USER CREATION PAGE</title>
    <jsp:useBean id="adminDO" scope="session" class="com.it.diesuiteapp.framework.model.AdminDO"></jsp:useBean>
	<jsp:useBean id="users_data" scope="request" class="java.lang.String"></jsp:useBean>
	    <script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>
	
	<script type="text/javascript">
		var user_data =  <%= users_data.length()>0? users_data : "[]" %>;
	</script>

	<!-- Sidenav -->
	<jsp:include page="/jsp/pages/commons/sidenav.jsp"/>    
	<!---END Sidenav--->
	 	
	<script type="text/javascript"> 
		window.onload = setWidthHightNav("100%");
	</script>
    
  </head>
  <body class="sidebar-mini fixed">
    	<div class="wrapper">
		<!-- Header-->
		<jsp:include page="/jsp/pages/commons/header.jsp"/>      
	  	<!--header End--->
		
 		<div class="content-wrapper">
 		<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div> -->
        		<div class="page-title">
          			<div>
            			<h1>USER CREATION </h1>
          			</div>
					<ul id="nav" class="nav nav-pills clearfix right" role="tablist">
          				<li class="dropdown"><span class="dropdown-toggle  btn-info" data-toggle="dropdown" id="ahelp">Help</span>
							<ul id="products-menu" class="dropdown-menu clearfix" role="menu">
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">English</a></li>
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">Hindi</a></li>
							</ul>
						</li>
					</ul>
        		</div>
        		<div class="row">
          			<div class="clearfix"></div>
          			<div class="col-md-12" id="myDIV" style="display:none;">
            			<div class="main_table">
              				<div class="table-responsive">
              					<form id="user_data_form" name="" method="post" action="MasterDataControlServlet">
									<input type="hidden" id="adminId" name="adminId" value="<%= adminDO.getAdminId() %>">
									<input type="hidden" id="page" name="page" value="jsp/pages/powerdie/masterdata/user_data.jsp">
									<input type="hidden" id="actionId" name="actionId" value="3002">
									<input type="hidden" id="userDataId" name="userDataId" value="">
               						<table class="table" id="user_add_table">
                  						<thead>
                    						<tr class="title_head">
                      							<th width="10%" class="text-center sml_input">USER ID</th>
                      						<th width="10%" class="text-center sml_input">USER NAME</th>
                      						<th width="10%" class="text-center sml_input">PASSWORD</th>
                      						<th width="10%" class="text-center sml_input">MOBILE NO</th>
					  						<th width="10%" class="text-center sml_input">EMAIL</th>
					  						<th width="10%" class="text-center sml_input">ADDRESS</th>
					   						<th width="10%" class="text-center sml_input">ACTIONS</th>
                    						</tr>
                  						</thead>
                  						<tbody id="user_add_table_body">
                  						</tbody>
                					</table>
                				</form>
              				</div>
            			</div>
						<div class="clearfix"></div>
				 	</div>
        		</div>
				<button name="add_data" id="add_data"  class="btn btn-info color_btn" onclick="addRow()">ADD</button>
				<span id="savediv" style="display:none;"><button class="btn btn-info color_btn bg_color2" name="save_data" id="save_data" onclick="saveData(this)">SAVE</button></span>
				<button name="cancel_data" id="cancel_data"	class="btn btn-info color_btn bg_color2" onclick="doAction('MasterDataControlServlet','jsp/pages/powerdie/home.jsp','2001')">BACK</button>
				<div class="row">
          			<div class="clearfix"></div>
          			<div class="col-md-12">
            			<div class="main_table">
              				<div class="table-responsive">
                				<table class="table  table-striped">
                  					<thead>
                    					<tr class="title_head">
                      						<th width="10%" class="text-center sml_input">USER ID</th>
                      						<th width="10%" class="text-center sml_input">USER NAME</th>
                      						<th width="10%" class="text-center sml_input">PASSWORD</th>
                      						<th width="10%" class="text-center sml_input">MOBILE NO</th>
					  						<th width="10%" class="text-center sml_input">EMAIL</th>
					  						<th width="10%" class="text-center sml_input">ADDRESS</th>
					   						<th width="10%" class="text-center sml_input">ACTIONS</th>
                    					</tr>
                  					</thead>
                  					<tbody id="user_data_table_body">  
                  					</tbody>
                				</table>
              				</div>
            			</div>
		         	</div>
        		</div>
      		</div>
    	</div>

 		<div id = "dialog-1" title="Alert(s)"></div>
 		<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div>
 		
	<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript" src="js/modules/masterdata/user_data.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
    <script src="js/commons/main.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript">
	document.getElementById("nameSpan").innerHTML = <%= adminDO.getAdminId() %>
	document.getElementById('user_data_form').adminId = <%= adminDO.getAdminId() %>;
	
	$(document).ready(function() {
    	/* tooltip for select */
		$('select').each(function(){
             var tooltip = $(this).tooltip({
                 selector: 'data-toggle="tooltip"',
                 trigger: 'manual'
             });
             var selection = $(this).find('option:selected').text();
             tooltip.attr('title', selection)

             $('select').change(function() {
                 var selection = $(this).find('option:selected').text();
                 tooltip.attr('title', selection)
             });
         });
       	    
	} );
	</script>
  </body>
</html>
