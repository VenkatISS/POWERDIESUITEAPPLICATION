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
    <title>LPG DEALER ERP WEB APPLICATION - BANK MASTER</title>
    <jsp:useBean id="adminDO" scope="session" class="com.it.diesuiteapp.framework.model.AdminDO"></jsp:useBean>
	<jsp:useBean id="bank_data" scope="request" class="java.lang.String"></jsp:useBean>
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
	  	<!--header End--->

		<div class="content-wrapper">
        	<div class="page-title">
        		<div>
            		<h1>BANK MASTER</h1>
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
				<div class="col-md-12">
          			<div class="col-md-15" id="myDIV" style="display:none;">
            			<div class="main_table">
              				<div class="table-responsive">
								<form id="bank_data_form" name="" method="post" action="MasterDataControlServlet">
									<input type="hidden" id="agencyId" name="agencyId" value="<%= adminDO.getAdminId() %>">
									<input type="hidden" id="page" name="page" value="jsp/pages/powerdie/masterdata/bank_data.jsp">
									<input type="hidden" id="actionId" name="actionId" value="3512">
									<input type="hidden" id="dataId" name="dataId" value="">
                				
	                				<table class="table" id="bank_add_table">
    	              					<thead>
        	            					<tr class="title_head">
						  						<th width="15%" class="text-center sml_input"> BANK NAME</th>
                	      						<th width="15%" class="text-center sml_input">ACCOUNT TYPE</th>
                    	  						<th width="15%" class="text-center sml_input">ACCOUNT NO</th>
                      							<th width="15%" class="text-center sml_input">BRANCH</th>
                      							<th width="15%" class="text-center sml_input">IFSC CODE</th>
                      							<th width="15%" class="text-center sml_input">ADDRESS</th>
					   							<th width="35%" class="text-center sml_input">OPENING BALANCE</th>
						 						<th width="5%" style="padding: 18px 20px !important;">ACTIONS</th>
	                    					</tr>
	                  					</thead>
    	              					<tbody  id="bank_add_table_body"></tbody>
			                		</table>
        		        		</form>
            		  		</div>
            			</div>
						<div class="clearfix"></div>
    	      		</div>
    	    	</div>
    	   	</div>
<!--         	<span id="odinstrn" style="display:none;"><font color="red"> * </font>Please &nbspenter &nbsppositive &nbspvalue &nbspif &nbspyou &nbsphave &nbspcredit  &nbspbalance, &nbspenter &nbspnegative &nbspvalue &nbspif &nbspyou &nbsphave &nbspdebit &nbspbalance &nbspin &nbspOpening &nbspbalance &nbspof &nbspOverdraft &nbspaccount</span> -->
        	<span id="odinstrn" style="display:none;color:red;"> * Please &nbspenter &nbsppositive &nbspvalue &nbspif &nbspyou &nbsphave &nbspcredit  &nbspbalance, &nbspenter &nbspnegative &nbspvalue &nbspif &nbspyou &nbsphave &nbspdebit &nbspbalance &nbspin &nbspOpening &nbspbalance &nbspof &nbspOverdraft &nbspaccount</span>
			<button class="btn btn-info color_btn btn" onclick="addRow()">ADD</button>
			<span id="savediv" style="display:none;"><button class="btn btn-info color_btn bg_color2" name="save_data" id="save_data" onclick="saveData(this)">SAVE</button></span>
			<button name="cancel_data" id="cancel_data" class="btn btn-info color_btn bg_color2" onclick="doAction('MasterDataControlServlet','jsp/pages/powerdie/home.jsp','2001')">BACK</button>

			<div class="row">
          		<div class="clearfix"></div>
          		<div class="col-md-12">
            		<div class="main_table">
              			<div class="table-responsive">
                			<table class="table table-striped">
                  				<thead>
                    				<tr class="title_head">
                      					<th width="10%" class="text-center sml_input"> BANK NAME</th>
                      					<th width="10%" class="text-center sml_input">ACCOUNT TYPE</th>
                      					<th width="10%" class="text-center sml_input">ACCOUNT NO</th>
                      					<th width="10%" class="text-center sml_input">BRANCH</th>
                      					<th width="10%" class="text-center sml_input">IFSC CODE</th>
                      					<th width="10%" class="text-center sml_input">ADDRESS</th>
					   					<th width="10%" class="text-center sml_input">OPENING BALANCE</th>
					   					<th width="10%" class="text-center sml_input">CLOSING BALANCE</th>
									    <th width="10%" class="text-center sml_input">ACTIONS</th>
                    				</tr>
                  				</thead>
                  				<tbody id="bank_data_table_body">
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

	<div id="dialog-form" style="display:none;">
    	<form>
			<label for="odv" style="margin-top:8px;">Please enter Debit Balance of OverDraft account upto which we can allow your transactions: </label><br>
			<input type="text" name="odv" id="odval" class="text ui-widget-content ui-corner-all" style="margin-top:8px;" />
		</form>
	</div>
		
    <!-- Javascripts-->
	<script type="text/javascript" src="js/local.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript">
	var bank_data =  <%= bank_data.length()>0? bank_data : "[]" %>;
	</script>
	<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript" src="js/modules/masterdata/bank_data.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
    <script src="js/commons/main.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript">
		document.getElementById("nameSpan").innerHTML = <%= adminDO.getAdminId() %>
		document.getElementById('bank_data_form').agencyId = <%= adminDO.getAdminId() %>;
		
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
