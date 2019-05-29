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
    <title>POWER ERP WEB APPLICATION - CUSTOMER/VENDOR MASTER</title>
    <jsp:useBean id="adminDO" scope="session" class="com.it.diesuiteapp.framework.model.AdminDO"></jsp:useBean>
	<jsp:useBean id="cvo_data" scope="session" class="java.lang.String"></jsp:useBean>
	<jsp:useBean id="dcvo_data" scope="request" class="java.lang.String"></jsp:useBean>

    <script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>

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
	   <!-- End Header--->

      	<div class="content-wrapper">
<!--       	<div id = "dialog-1" title="Alert(s)"></div>
 		<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div> -->
        <div class="page-title">
          	<div>
            	<h1>CUSTOMER / VENDOR MASTER</h1>
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
          	<div class="col-md-15" id="myDIV" style="display:none;">
            	<div class="main_table">
              		<div class="table-responsive">
             	 		<form id="cvo_data_form" name="" method="post" action="MasterDataControlServlet">
			  				<input type="hidden" id="agencyId" name="agencyId" value="<%= adminDO.getAdminId() %>">
							<input type="hidden" id="page" name="page" value="jsp/pages/powerdie/masterdata/cv_data.jsp">
							<input type="hidden" id="actionId" name="actionId" value="3502">
							<input type="hidden" id="dataId" name="dataId" value="">
<%-- 			  				<input type="hidden" id="effDateInFTL" name="effDateInFTL" value="<%= agencyVO.getEffective_date() %>">
 --%>              				
              				<table class="table" id="cvo_add_table">
                  				<thead>
                    				<tr class="title_head">
                      					<th width="10%" class="text-center sml_input">PARTY TYPE</th>
                      					<th width="10%" class="text-center sml_input">PARTY/PLANT NAME</th>
                      					<th width="10%" class="text-center sml_input">GST REG</th>
                      					<th width="10%" class="text-center sml_input">GSTIN</th>
					  					<th width="10%" class="text-center sml_input">PAN</th>
                      					<th width="10%" class="text-center sml_input">ADDRESS</th>
                      					<th width="10%" class="text-center sml_input">CONTACT NO</th>
                      					<th width="10%" class="text-center sml_input">EMAIL</th>
					  					<th width="10%" class="text-center sml_input">OPENING BALANCE</th>
						 				<th width="10%" class="text-center sml_input">ACTIONS</th>
						 				
                    				</tr>
                  				</thead>
                  				<tbody id='cvo_add_table_body'>
                  				</tbody>
                			</table>
                		</form>
              		</div>
            	</div>
				<div class="clearfix"></div>
          	</div>
        </div>
		<button class="btn btn-info color_btn btn" onclick="addRow()" href="#">ADD</button>
		<span id="savediv" style="display:none;"><button class="btn btn-info color_btn bg_color2" name="save_data" id="save_data" onclick="saveData(this)">SAVE</button></span>
		<button name="cancel_data" id="cancel_data"	class="btn btn-info color_btn bg_color2" onclick="doAction('MasterDataControlServlet','jsp/pages/powerdie/home.jsp','2001')">BACK</button>
		<div class="row">
        	<div class="clearfix"></div>
          	<div class="col-md-15">
	            <div class="main_table">
              		<div class="table-responsive">
                		<table class="table  table-striped">
                  			<thead>
                    			<tr class="title_head">
                      				<th width="10%" class="text-center sml_input">PARTY/PLANT NAME</th>
                      				<th width="10%" class="text-center sml_input">PARTY TYPE</th>
                      				<th width="10%" class="text-center sml_input">GST REG</th>
                      				<th width="10%" class="text-center sml_input">GSTIN</th>
					   				<th width="10%" class="text-center sml_input">PAN</th>
                      				<th width="10%" class="text-center sml_input">ADDRESS</th>
                      				<th width="10%" class="text-center sml_input">CONTACT NO</th>
                      				<th width="10%" class="text-center sml_input">EMAIL</th>
				  					<th width="10%" class="text-center sml_input">OPENING BALANCE</th>
				  					<th width="10%" class="text-center sml_input">CLOSING BALANCE</th>
						 			<th width="10%" class="text-center sml_input">ACTIONS</th>
                    			</tr>
                  			</thead>
                  			<tbody id='cvo_data_table_body' style="text-transform: uppercase;">                    
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
    
    <script type="text/javascript">
     	var cvo_data =  <%= cvo_data.length()>0? cvo_data : "[]" %>;
     	var dcvo_data =  <%= dcvo_data.length()>0? dcvo_data : "[]" %>;
     	var omc_name;
		
     /* 	if(agency_oc==1)
			omc_name = "IOCL";
		else if(agency_oc==2)
			omc_name = "HPCL";
		else if(agency_oc==3)
			omc_name = "BPCL"; */
		
    	var categories = ["VENDOR","CUSTOMER","OMC-"+omc_name,"OTHERS"];
     	var gstyn = ["SELECT","YES","NO"];
	</script>
	<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript" src="js/modules/masterdata/cvo_data.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
    <script src="js/commons/main.js?<%=randomUUIDString%>"></script>
    <script>
    document.getElementById("nameSpan").innerHTML = <%= adminDO.getAdminId() %>
    document.getElementById('cvo_data_form').agencyId = <%= adminDO.getAdminId() %>;
    
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
