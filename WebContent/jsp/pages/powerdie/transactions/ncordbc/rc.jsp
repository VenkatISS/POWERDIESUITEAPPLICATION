<%-- <%@ page import="java.util.UUID" %>
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
    	<script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>
	    <script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
		<link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    	<title>RECONNECTIONS</title>
     	<jsp:useBean id="agencyVO" scope="session" class="com.it.erpapp.framework.model.vos.AgencyVO"></jsp:useBean>
		<jsp:useBean id="cylinder_types_list" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="arb_types_list" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="rcs_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="staff_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="equipment_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="services_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="refill_prices_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="prod_types_list" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="bank_data" scope="request" class="java.lang.String"></jsp:useBean>

  		<script type="text/javascript">
	  		var cat_cyl_types_data = <%= cylinder_types_list %>;
  			var page_data = <%= rcs_data.length()>0? rcs_data : "[]" %>;
  			var staff_data = <%= staff_data.length()>0? staff_data : "[]" %>;
  			var equipment_data = <%= equipment_data.length()>0? equipment_data : "[]" %>;
  			var services_data = <%= services_data.length()>0? services_data : "[]" %>;
  			var refill_prices_data = <%= refill_prices_data.length()>0? refill_prices_data : "[]" %>;
  			var prod_types = <%= prod_types_list %>;
  			var bank_data = <%= bank_data %>;
  			var ctdatahtml = "";
  			var rtdatahtml = "";
  			var vendordatahtml = "";
  			var staffdatahtml = "";
  			var rcservicesdatahtml = "";
  			var months = ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];

			var rcServicesId = [19,29];
			rcservicesdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
   			if(prod_types.length>0) {
   				for(var z=0; z<prod_types.length; z++){
   					if((prod_types[z].cat_type==5) && (services_data.length>0)) {
						for(var s=0;s<services_data.length;s++){
   							if((services_data[s].prod_code == prod_types[z].id) && (rcServicesId.includes(services_data[s].prod_code))) {
   								rcservicesdatahtml = rcservicesdatahtml + "<OPTION VALUE='"+services_data[s].prod_code+"'>"
									+prod_types[z].cat_desc+" ( Rs. " + services_data[s].prod_charges+"/-)" + "</OPTION>";
								break;
							}
						}
					}
   				}
   			}
  		</script>
  		
		<script src="js/commons/checkBrowser.js?<%=randomUUIDString%>"></script>

		<!-- Sidenav -->
			<jsp:include page="/jsp/pages/commons/sidenav.jsp"/>    
		<!---END Sidenav--->
	  
		<script type="text/javascript"> 
			window.onload = setWidthHightNav("100%");
		</script>  		
		<style>
			.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
    			padding: 12px;
    			line-height: 1.42857143;
    			vertical-align: top;
    			border-top: 0px solid #ddd;
		</style>
  	</head>
  	<body class="sidebar-mini fixed">
    	<div class="wrapper">
<!-- Header-->
		<jsp:include page="/jsp/pages/commons/header.jsp"/>      
	  	<!--header End--->

      		<div class="content-wrapper">
        		<div class="page-title">
          			<div><h1>ITV / RC </h1></div>
					<ul id="nav" class="nav nav-pills clearfix right" role="tablist">
          				<li class="dropdown"><span class="dropdown-toggle  btn-info" data-toggle="dropdown" id="ahelp">Help</span>
							<ul id="products-menu" class="dropdown-menu clearfix" role="menu">
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">English</a></li>
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">Hindi</a></li>
							</ul>
						</li>
					</ul>
        		</div>
				<button name="b_sb" id="b_sb" class="btn btn-info color_btn bg_color4" onclick="showRCFormDialog()">ADD</button>
				<button name="cancel_data" id="cancel_data"	class="btn btn-info color_btn bg_color2" onclick="doAction('MasterDataControlServlet','jsp/pages/erp/home.jsp','2001')">BACK</button>
				<!-- Modal -->
				<div class="modal_popup_add fade in" id="myModal" style="display: none;height: 100%">					
					<div class="modal-dialog modal-lg">
			  			<!-- Modal content-->
			  			<div class="modal-content" id="modal-content">
							<div class="modal-header" id="modal-header">
					  			<span class="close" id="close" onclick="closeRCFormDialog()">&times;</span>
					  			<h4 class="modal-title">ITV / RC  </h4>
							</div>
							<div class="modal-body">
								<form id="data_form" name="" method="post" action="TransactionsDataControlServlet">
									<input type="hidden" id="agencyId" name="agencyId" value="<%= agencyVO.getAgency_code() %>">
									<input type="hidden" id="page" name="page" value="jsp/pages/erp/transactions/ncordbc/rc.jsp">
									<input type="hidden" id="actionId" name="actionId" value="5712">
									<input type="hidden" id="cgsta" name="cgsta" value="">
									<input type="hidden" id="sgsta" name="sgsta" value="">
									<input type="hidden" id="scgsta" name="scgsta" value="">
									<input type="hidden" id="ssgsta" name="ssgsta" value="">
									<input type="hidden" id="bankId" name="bankId" value="">
									<input type="hidden" id="depamt" name="depamt" value="">
									<div class="row">
										<div class="col-adj-res-sub">										
											<label>INVOICE DATE </label>
											<input type="date" id="rc_date" name="rc_date" class="form-control input_field" placeholder="DD-MM-YY">
										</div>
										<div class="col-adj-res-sub">
											<label>STAFF NAME <span id="sSpan"></span> </label>
										</div>
										<div class="col-adj-res-sub">
											<label>CUST NO./NAME</label>
											<input type="text" class="form-control input_field" id="custn" name="custn" maxlength="15" placeholder="CUST NO./NAME">
										</div>	
										<div class="col-adj-res-sub">
											<label>INVOICE AMOUNT </label>
											<input type="text"  name="invamt" id="invamt"  value=""  class="form-control input_field" readonly="readonly" style="background-color:#F3F3F3;"placeholder="INV AMOUNT">
										</div>							
										<div class="clearfix"></div>					
									</div>
									<br/>
									<div class="row">
          								<div class="clearfix"></div>
          									<div class="col-md-12">
            									<div class="main_table">
            	  									<div class="table-responsive">
                										<table class="table" id="b_data_table">
                 	 										<thead>
                    											<tr class="title_head">
						                        					<th width="10%" class="text-center sml_input">PRODUCT</th>
					  												<th width="10%" class="text-center sml_input">NUMBER OF CYLINDERS	</th>
					  												<th width="10%" class="text-center sml_input">NUMBER OF REGULATORS</th>
                      												<th width="10%" class="text-center sml_input">CYLINDER DEPOSIT</th>
					  												<th width="10%" class="text-center sml_input">REGULATOR DEPOSIT</th>
					  												<th width="10%" class="text-center sml_input">ADMIN CHARGES</th>
					  												<th width="10%" class="text-center sml_input">DGCC AMOUNT</th>
					  												<th width="10%" class="text-center sml_input">GST AMOUNT</th>
					  												<th width="10%" class="text-center sml_input">PAYMENT TERMS</th>  
					  												<th width="10%" class="text-center sml_input">Actions</th>				
                    											</tr>
                  											</thead>
                  											<tbody  id="data_table_body">
                    											<tr height="30px" valign="top">
																	<td valign='top' height='4' align='center'>
																		<span id="epidSpan"></span>
																		<span id="rpidSpan"></span>
																	</td>
																	<td height='4' align='center'><input type=text name='nocyl' id='nocyl' maxlength="2" class="form-control input_field freez"  size='2' placeholder="No Of Cylinders"></td>
																	<td height='4' align='center'><input type=text name='noreg' id='noreg' maxlength="1" class="form-control input_field freez"  size='2' placeholder="No Of Regulators"></td>
																	<td valign='top' height='4' align='center'>
																		<input type=text name='cyld' id='cyld' maxlength="9" class="form-control input_field freez" maxlength='8' size='4' placeholder="Cylinder Deposit">
																		<input type="hidden" id="sprp" name="spup" value="">
																		<input type="hidden" id="sprp" name="sptp" value="">
																		<input type="hidden" id="sprp" name="spa" value="">
																		<input type="hidden" id="sprp" name="spgsta" value="">
																	</td>
																	<td valign='top' height='4' align='center'><input type=text name='regd' id='regd' maxlength="6" class="form-control input_field freez" maxlength='6' size='4' placeholder="Regulator Deposit"></td>
																	<td valign='top' height='4' align='center'>
																		<select name="acs" id="acs" class="form-control input_field acs sadd freez" onchange="fetchValueForAdminCharges()" >
    	        															<%String services = "<script>document.writeln(rcservicesdatahtml)</script>";
					   														out.println("value: " + services);%>
											   							</select>
																		<input type="hidden" name='ac' id='ac'>
																	</td>
																	<td valign='top' height='4' align='center'><input type=text name='dgcc' id='dgcc' class="form-control input_field qtyc" size='4' readonly='readonly' style='background-color:#F3F3F3;' placeholder="DGCC Amount"></td>
																	<td valign='top' height='4' align='center'>
																		<input type=hidden name='gstsa' id='gstsa' value="0">
																		<input type=text name='gsta' id='gsta' class="form-control input_field" size='4' readonly='readonly' style='background-color:#F3F3F3;' placeholder="Gst Amount">
																	</td>
																	<td valign='top' height='4' align='center'><input type=text name='pt' id='pt' value="cash" maxlength="6" class="form-control input_field"  size='18' placeholder="Payment Terms" readonly></td>
																	<td valign='top' height='4' align='center'><img src='images/delete.png' onclick='doRowDelete(this)'></td>
							    								</tr>
            												</tbody>
                										</table>
              										</div>
            									</div>
            								</div>
           								</div>
            						</form>	
            					</div>
            					<div class="row">	
									<div class="clearfix"></div>
									<br/>
									<div class="col-md-3" style="margin-right:10%">
										<button name="fetch_data" id="fetch_data" class="btn btn-info color_btn"  onclick="fetchReconnectionCharges()" style="margin-left: 15px;">FETCH DEPOSITS AND CHARGES</button>
									</div>
									<div class="col-md-2" >
										<button  name="calc_data" id="calc_data" class="btn btn-info color_btn" onclick="calculateValues()" disabled="disabled" style=" margin-left:-25px;">CALCULATE</button>
									</div>
									<div class="col-md-2">
										<button  name="save_data" id="save_data" class="btn btn-info color_btn bg_color2" onclick="saveData(this)" disabled="disabled" style=" margin-left: -35px;">SAVE</button>
									</div>
								</div>
        					</div>
						</div>
					</div>
					<div class="row">
          				<div class="clearfix"></div>
          				<div class="col-md-12">
            				<form id="m_data_form" name="" method="post" action="MasterDataControlServlet">
								<input type="hidden" id="agencyId" name="agencyId" value="<%= agencyVO.getAgency_code() %>">
								<input type="hidden" id="page" name="page" value="jsp/pages/erp/transactions/ncordbc/rc.jsp">
								<input type="hidden" id="actionId" name="actionId" value="">
								<input type="hidden" id="dataId" name="dataId" value="">
								<input type="hidden" id="bankId" name="bankId" value="">
                				<table class="table table-striped" id="m_data_table">
                  					<thead>
                    					<tr class="title_head">
					  						<th width="10%" class="text-center sml_input"> INV NO</th>
                      						<th width="10%" class="text-center sml_input">DATE</th>
					  						<th width="10%" class="text-center sml_input">STAFF NAME</th>
                      						<th width="10%" class="text-center sml_input">CUST NO./NAME</th>
                      						<th width="10%" class="text-center sml_input">PRODUCT</th>
					  						<th width="10%" class="text-center sml_input">NUMBER OF CYLINDERS	</th>
					  						<th width="10%" class="text-center sml_input">NUMBER OF REGULATORS</th>
                      						<th width="10%" class="text-center sml_input">CYLINDER DEPOSIT</th>
											<th width="10%" class="text-center sml_input">REGULATOR DEPOSIT</th>
					 	 					<th width="10%" class="text-center sml_input">DGCC AMOUNT</th>
					  						<th width="10%" class="text-center sml_input">ADMIN CHARGES</th>
					  						<th width="10%" class="text-center sml_input">CGST AMOUNT</th>
					 	 					<th width="10%" class="text-center sml_input">SGST AMOUNT</th>
					  						<th width="10%" class="text-center sml_input">PAYMENT TERMS</th>
					  						<th width="10%" class="text-center sml_input">INV AMOUNT</th>
					  						<th width="10%" class="text-center sml_input">Actions</th>
										</tr>
                  					</thead>
                  					<tbody id= "m_data_table_body"></tbody>
                				</table>
                			</form>
              			</div>
        			</div>
      			</div>
      		</div>
		</body>
		<div id = "dialog-1" title="Alert(s)"></div>
 		<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div>
    	<script type="text/javascript" src="js/modal.js?<%=randomUUIDString%>"></script>
		<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
		<script type="text/javascript" src="js/modules/transactions/ncordbc/rcs.js?<%=randomUUIDString%>"></script>
		<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
		<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
    	<script src="js/commons/main.js?<%=randomUUIDString%>"></script>
		<script type="text/javascript">
 			document.getElementById("nameSpan").innerHTML = <%= agencyVO.getAgency_code() %>;
 			var dedate = <%=agencyVO.getDayend_date()%>;
 	     	var effdate = <%=agencyVO.getEffective_date()%>;
 			var a_created_date = <%=agencyVO.getCreated_date()%>;
 			
	    	$(document).ready(function() {
		   	    $('#m_data_table').DataTable( { 
	//	   	    	"bPaginate" : $('#m_data_table tbody tr').length>5,
	//		   	    "iDisplayLength": 5,
					"lengthMenu": [[5,10, 25, 50, -1], [5,10, 25, 50, "All"]],			
					"bFilter": false,
	   	    		"ordering": false,
	   	    		"scrollY":'50vh',
					"scrollCollapse": true,
	   	    	    "scrollX": true,
	            	language: {
	                	paginate: {
	                		next: '&#x003E;&#x003E;',
	                    	previous: '&#x003C;&#x003C;'
	                	}
	              	}
	   	    	} );
		   	    
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
</html> --%>