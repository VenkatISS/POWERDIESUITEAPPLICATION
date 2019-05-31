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
    <link rel="stylesheet" type="text/css" href="css/main.css?<%=randomUUIDString%>">
    <title>POWER DIESUITE WEB APPLICATION - PRODUCT MASTER PAGE</title>
    <jsp:useBean id="adminDO" scope="session" class="com.it.diesuiteapp.framework.model.AdminDO"></jsp:useBean>
 	<jsp:useBean id="product_types_list" scope="request" class="java.lang.String"></jsp:useBean> 
	<jsp:useBean id="product_data" scope="request" class="java.lang.String"></jsp:useBean>
	<jsp:useBean id="dproduct_data" scope="request" class="java.lang.String"></jsp:useBean>
	<script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>
<%-- 	<script src="js/commons/checkBrowser.js?<%=randomUUIDString%>"></script> --%>

	<!-- Sidenav -->
	<jsp:include page="/jsp/pages/commons/sidenav.jsp"/>    
	<!---END Sidenav--->
		
	<script type="text/javascript">
		window.onload = setWidthHightNav("100%");
	</script>
	<script type="text/javascript">
		var months = ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];
		var cat_types_data = <%= product_types_list.length()>0? product_types_list : "[]" %>; 
		var ctdatahtml = "";
		var page_data =  <%= product_data.length()>0? product_data : "[]" %>;
		var del_equipment_data = <%= dproduct_data.length()>0? dproduct_data : "[]" %>;
		var rawmid = ["CU","SILVER","STEEL","NICKLE","IRON","ZINK","CARBON","ALUMINIUM","RUBBER"];
		var unitstype = ["MM","CM","SQ CM","INCHES","FEETS","SQ FEETS"];
		
		
		
		
		/* //Construct Category Type html
		ctdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
		if(cat_types_data.length>0) {
			for(var z=0; z<cat_types_data.length; z++){
				ctdatahtml=ctdatahtml+"<OPTION DATA-VALUE='"+cat_types_data[z].id+"'>"+cat_types_data[z].cat_name+"-"+cat_types_data[z].cat_desc+"</OPTION>";
			}
		}
 */

 //Construct product Type html
	productdatahtml = "";
	if(cat_types_data.length>0) {
		for(var z=0; z<cat_types_data.length; z++){
			if(cat_types_data[z].deleted == 0)
				productdatahtml=productdatahtml+"<OPTION DATA-VALUE='"+cat_types_data[z].id+"'>"+cat_types_data[z].cat_desc+"</OPTION>";
		}
	}
 
 
 </script>
  </head>
  <body class="sidebar-mini fixed">
    	<div class="wrapper">
		<!-- Header-->
		<jsp:include page="/jsp/pages/commons/header.jsp"/>      
	  	<!--header End--->
      		<div class="content-wrapper">
<!--       		<div id = "dialog-1" title="Alert(s)"></div>
 			<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div> -->
        		<div class="page-title">
          			<div>
            			<h1>PRODUCT MASTER</h1>
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
          			<div class="col-dmp-12" id="page_add_table_div" style="display:none;">
            			<div class="main_table">
              				<div class="table-responsive">
              					<form id="page_add_form" name="page_add_form" method="post" action="MasterDataControlServlet">
									<input type="hidden" id="adminId" name="adminId" value="<%= adminDO.getAdminId() %>">
									<input type="hidden" id="page" name="page" value="jsp/pages/powerdie/masterdata/products/product_data.jsp">
									<input type="hidden" id="actionId" name="actionId" value="3102">
									<input type="hidden" id="itemId" name="itemId" value="">
									<div>
									<label style="margin-left:50px;margin-top:20px;font-weight:500px;">PRODUCT :</label>
                                    <%-- <input list="rfrom" id="epid"  placeholder="Received From">
									<datalist id="rfrom">
									   <%String str1 = "<script>document.writeln(productdatahtml)</script>";
											out.println("value: " + str1);%>
									</datalist> 
																	    <input type="hidden" name="rfrom" id="answerInputHidden">									
									
									--%>
									<input list="pro_id" id="answerInput" onchange="changeSaleTypeBasedOnProduct()"  placeholder="SELECT">
								   <datalist id="pro_id">
                                   <%String str1 = "<script>document.writeln(productdatahtml)</script>";
											out.println("value: " + str1);%>

											</datalist>
											<input type="hidden" name="pro_id" id="answerInputHidden">
											<input type="hidden" name="pro_name" id="pro_name">
											</div>
                					<table class="table" id="page_add_table" style="margin-top:25px;">
                  						<thead>
                    						<tr class="title_head">
                      							<th width="10%" class="text-center"> RAW MATERIALS</th>
                      							<th width="10%" class="text-center">UNIT TYPE</th>
                      							<th width="10%" class="text-center">GST %</th>
                      							<th width="10%" class="text-center">UNITS</th>
                      					   <!-- <th width="10%" class="text-center">SECURITY DEPOSIT</th> -->
                      							<th width="10%" class="text-center">OPENING STOCK</th>
					  							<th width="10%" class="text-center">PURCHASE PRICE</th>
					  					 <!-- 	<th width="10%" class="text-center">SELLING RATE</th> -->
					  							<th width="10%" class="text-center">EFFECTIVE DATE</th>
					  							<th width="10%" class="text-center">ACTIONS</th>
                    						</tr>
                  						</thead>
                  						<tbody id="page_add_table_body">
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
          			<div class="col-dmp-12">
            			<div class="main_table">
              				<div class="table-responsive">
                				<table class="table  table-striped">
                  					<thead>
                    					<tr class="title_head">
                    					    <th width="30%" class="text-center sml_input">PRODUCT</th>
                    						<th width="30%" class="text-center sml_input">RAW MATERIALS</th>
                      						<th width="20%" class="text-center sml_input">UNIT TYPE</th>
                      						<th width="30%" class="text-center sml_input">GST %</th>
                      						<th width="30%" class="text-center sml_input">UNITS</th>
                      				<!-- 	<th width="30%" class="text-center sml_input">SECURITY DEPOSIT</th> -->
					  						<th width="30%" class="text-center sml_input">OPENING STOCK</th>
					  						<th width="30%" class="text-center sml_input">PURCHASE PRICE</th>
					  				<!-- 	<th width="30%" class="text-center sml_input">SELLING RATE</th> -->
					  						<th width="30%" class="text-center sml_input">EFFECTIVE DATE</th>
					  						<th width="30%" class="text-center sml_input">Actions</th>
                    					</tr>
                  					</thead>
                  					<tbody id="page_data_table_body">                    
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
    	
	</body>
	<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript" src="js/modules/masterdata/products/product_data.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/main.js?<%=randomUUIDString%>"></script>

	<script type="text/javascript">
		document.getElementById("nameSpan").innerHTML = <%= adminDO.getAdminId() %>
		document.getElementById('page_add_form').agencyId = <%= adminDO.getAdminId() %>;
	<%-- 	var dedate = <%=adminDO.getDayend_date()%>; --%>
		
	
	// for datalist
	document.querySelector('input[list]').addEventListener('input', function(e) {
	    var input = e.target,
		    list = input.getAttribute('list'),
	        options = document.querySelectorAll('#' + list + ' option'),
	        hiddenInput = document.getElementById(input.id + 'Hidden'),
	        inputValue = input.value.trim();

	    hiddenInput.value = inputValue;

	    for(var i = 0; i < options.length; i++) {
	        var option = options[i];

	        if(option.innerText.trim() === inputValue) {
	            hiddenInput.value = option.getAttribute('data-value');
	            break;
	        }
	    }
	});	
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
</html>