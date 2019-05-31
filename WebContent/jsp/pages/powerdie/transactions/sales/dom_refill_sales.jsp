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
    	<link rel="stylesheet" type="text/css" href="css/main.css?<%=randomUUIDString%>">
    	
 		<script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>
	    <script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
		<link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

    	<title>DOMESTIC CYLINDER SALES </title>
    	<jsp:useBean id="agencyVO" scope="session" class="com.it.erpapp.framework.model.vos.AgencyVO"></jsp:useBean>
    	<jsp:useBean id="cl_data" scope="request" class="java.lang.String"></jsp:useBean>
   		<jsp:useBean id="cylinder_types_list" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="bank_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="cvo_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="area_codes_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="staff_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="equipment_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="refill_prices_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="drs_data" scope="request" class="java.lang.String"></jsp:useBean>

		<script type="text/javascript">
			var cat_cyl_types_data = <%= cylinder_types_list.length()>0? cylinder_types_list: "[]" %>;
			var page_data = <%= drs_data.length()>0? drs_data : "[]" %>;
			var bank_data = <%= bank_data.length()>0? bank_data: "[]"%>;
			var cvo_data = <%= cvo_data.length()>0? cvo_data : "[]" %>;
			var staff_data = <%= staff_data.length()>0? staff_data : "[]" %>;
			var area_codes_data = <%= area_codes_data.length()>0? area_codes_data : "[]" %>;
			var equipment_data = <%= equipment_data.length()>0? equipment_data : "[]" %>;
			var refill_prices_data = <%= refill_prices_data.length()>0? refill_prices_data : "[]" %>;
			var ctdatahtml = "";
			var vendordatahtml = "";
			var staffdatahtml = "";
			var areacodeshtml = "";
			var bankdatahtml = "";
			var cust_cl_data = <%= cl_data.length()>0? cl_data: "[]" %>;
			
			var months = ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];
			var payment_terms_values = ["NONE","CASH","CREDIT"];
			//Construct Cylinder list
			ctdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
			if(cat_cyl_types_data.length>0) {
				for(var z=0; z<cat_cyl_types_data.length; z++){
					if(cat_cyl_types_data[z].cat_name=='DOMESTIC') {
						//ctdatahtml=ctdatahtml+"<OPTION VALUE='"+cat_cyl_types_data[z].id+"'>"+cat_cyl_types_data[z].cat_code+"-"
						//+cat_cyl_types_data[z].cat_name+"-"+cat_cyl_types_data[z].cat_desc+"</OPTION>";
						for(var y=0; y<refill_prices_data.length; y++){
							if(refill_prices_data[y].prod_code == cat_cyl_types_data[z].id) {
								ctdatahtml = ctdatahtml+"<OPTION VALUE='"+cat_cyl_types_data[z].id+"'>"
											+cat_cyl_types_data[z].cat_name+"-"+cat_cyl_types_data[z].cat_desc+"</OPTION>";
								break;
							}
						}
					}
				}
			}
			//Construct Staff html
			staffdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
			if(staff_data.length>0) {
				for(var z=0; z<staff_data.length; z++){
					if(staff_data[z].deleted == 0){
						staffdatahtml=staffdatahtml+"<OPTION VALUE='"+staff_data[z].id+"'>"+staff_data[z].emp_name+"</OPTION>";
					}
				}
			}
			
			//Construct bank html
			bankdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
			if(bank_data.length>0) {
				for(var z=0; z<bank_data.length; z++) {
					var bc = bank_data[z].bank_code;
					if(!(bc=="TAR ACCOUNT" || bc=="STAR ACCOUNT" || bc=="LOAN") && bank_data[z].deleted == 0) {
						bankdatahtml=bankdatahtml+"<OPTION VALUE='"+bank_data[z].id+"'>"+bank_data[z].bank_code+" - "+bank_data[z].bank_acc_no+"</OPTION>";
					}
				}
			}
		
			//Area codes html
			areacodeshtml = "<OPTION VALUE='0'>SELECT</OPTION>";
			if(area_codes_data.length>0) {
				for(var z=0; z<area_codes_data.length; z++){
					if(area_codes_data[z].deleted == 0){
						areacodeshtml=areacodeshtml+"<OPTION VALUE='"+area_codes_data[z].id+"'>"+area_codes_data[z].area_code+" - "+area_codes_data[z].area_name+"</OPTION>";
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
			table tr.even {
    			background: #fff;
			}
			table tr.odd {
    			background: #eeeeee;
			}
		</style>
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
          			<div><h1>DOMESTIC CYLINDER SALES</h1></div>
					<ul id="nav" class="nav nav-pills clearfix right" role="tablist">
          				<li class="dropdown"><span class="dropdown-toggle  btn-info" data-toggle="dropdown" id="ahelp">Help</span>
							<ul id="products-menu" class="dropdown-menu clearfix" role="menu">
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">English</a></li>
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">Hindi</a></li>
							</ul>
						</li>
					</ul>
        		</div>
				<button name="b_sb" id="b_sb" class="btn btn-info color_btn bg_color4" onclick="showDOMRSalesFormDialog()">ADD</button>
				<button name="cancel_data" id="cancel_data"	class="btn btn-info color_btn bg_color3" onclick="doAction('MasterDataControlServlet','jsp/pages/erp/home.jsp','2001')">BACK</button>
				<!-- Modal -->
				<div class="modal_popup_add fade in" id="myModal" style="display: none;height: 100%">
					<div class="modal-dialog modal-lg">
			  			<!-- Modal content-->
			  			<div class="modal-content" id="modal-content">
							<div class="modal-header" id="modal-header">
					  			<span class="close" id="close" onclick="closeDOMRSalesFormDialog()">&times;</span>
					  			<h4 class="modal-title">DOMESTIC CYLINDER SALES </h4>
							</div>
							<div class="modal-body">
								<form id="data_form" name="" method="post" action="TransactionsDataControlServlet">
									<input type="hidden" id="agencyId" name="agencyId" value="<%= agencyVO.getAgency_code() %>">
									<input type="hidden" id="page" name="page" value="jsp/pages/erp/transactions/sales/dom_refill_sales.jsp">
									<input type="hidden" id="bankId" name="bankId" value="">
									<input type="hidden" id="actionId" name="actionId" value="5122">
									<div class="row">
										<div class="col-adj-res-sub">
											<label>SALES INVOICE DATE </label>
											<input type="date" class="form-control input_field sinvd freez"  id="si_date" name="si_date" placeholder="DD-MM-YY">
										</div>
										<div class="col-adj-res-sub">
											<label>CUSTOMER NAME  </label>
											<span id="cSpan"></span>
										</div>
										<div class="col-adj-res-sub">
											<label>PAYMENT TERMS </label>
											<select class="form-control input_field select_dropdown sadd freez" name='pt' id='pt' onchange="setPPCValueAndBankAcct()">
						  						<option value="0">SELECT</option>
						 						<option value="1">CASH</option>
						   						<option value="2">CREDIT</option>
											</select>
										</div>	
										<div class="col-adj-res-sub">
											<label>SALES INVOICE AMOUNT </label>
											<input type="text" class="form-control input_field" id="si_amt" name="si_amt" value="" readonly="readonly" style="background-color:#F3F3F3;" placeholder="INV AMOUNT">
						    				<input type="hidden" id="c_amt" name="c_amt" value="">
										</div>
										<div class="clearfix"></div>
									</div>
									<br/>
									<div class="row">
                    					<div class="clearfix"></div>
                    					<div class="col-md-12">
                    						<div class="main_table">
                    							<div class="table-responsive">
                									<table class="table">
                  										<thead>
                    										<tr class="title_head">
					   											<th width="10%" class="text-center sml_input">PRODUCT</th>
					   											<th width="10%" class="text-center sml_input">GST %</th>
					    										<th width="10%" class="text-center sml_input">UNIT PRICE</th>
																<th width="10%" class="text-center sml_input">DISCOUNT ON UNIT PRICE</th>
																<th width="10%" class="text-center sml_input">QUANTITY</th>
																<th width="10%" class="text-center sml_input">PREPAID CYLINDERS</th>
																<th width="10%" class="text-center sml_input">PSV QUANTITY</th>
																<th width="10%" class="text-center sml_input">DELIVERED BY</th>
																<th width="10%" class="text-center sml_input">BANK ACCOUNT</th>
																<th width="10%" class="text-center sml_input">AREA CODE</th>
																<th width="10%" class="text-center sml_input">TRANSACTION<br>AMOUNT</th>
																<th width="10%" class="text-center sml_input">TOTAL AMOUNT</th>
																<th width="10%" class="text-center sml_input">ONLINE RCVD AMOUNT</th>
						 										<th width="10%" class="text-center sml_input">Actions</th>
                    										</tr>
                  										</thead>
                  										<tbody id="data_table_body">
                    										<tr>
                      											<td>
                      												<select class="form-control input_field select_dropdown sadd pid freez" name='epid' id='epid'>
						   												<%String str = "<script>document.writeln(ctdatahtml)</script>";
						   														out.println("value: " + str);%>
																	</select>
																</td>
                      											<td>
                      												<input type="text" name='vatp' class='form-control input_field eadd' id='vatp' size='6' readonly='readonly' style='background-color:#FAFAC2;'  placeholder="Gst%">
                      											</td>
					    										<td>
					    											<input type="text" name='up'  class='form-control input_field eadd' id='up' size='6' maxlength='7' value='0' readonly='readonly' style='background-color:#FAFAC2;' placeholder="Unit Price">
					    										</td>
																<td>
																	<input type="text" name='upd' class='form-control input_field freez eadd' id='upd' maxlength='7' size='6' value='0' placeholder=" Discount On Unit Price">
																</td>
						 										<td>
						 											<input type="text" name='qty' class='form-control input_field qtyc freez eadd' id='qty' maxlength='4' size='6' placeholder="Quantity"></td>
						 										<td>
						 											<input type="text" name='prec' class='form-control input_field freez eadd' id='pre' maxlength='3' size='6' placeholder="Prepaid Cylinders">
						 										</td>
						 										<td>
						 											<input type="text" name='psvc' class='form-control input_field freez eadd' id='psvc' maxlength='3' size='6' placeholder="PSV Quantity">
						 										</td>
						 										<td>
						 											<select class="form-control input_field select_dropdown" name='sid' id='sid'>
						   													<%String str2 = "<script>document.writeln(staffdatahtml)</script>";
						   															out.println("value: " + str2);%>
																	</select>
																</td>
						  										<td>
						 											<select name="bid" id="bid" class="form-control input_field select_dropdown freez">
                  														<%String str4 = "<script>document.writeln(bankdatahtml)</script>";
				   															out.println("value: " + str4);%>
				   													</select>
																</td>
							 									<td>
							 									<select class="form-control input_field select_dropdown" name='acid' id='acid'>
						   													<%String str3 = "<script>document.writeln(areacodeshtml)</script>";
						   															out.println("value: " + str3);%>
																	</select>
																</td>
																<td>
																<input type=text name='txnamt' class='form-control input_field eadd' id='txnamt' size='6' readonly='readonly' style='background-color:#F3F3F3;' placeholder='Transaction Amount'></td>
						  										<td>
						  											<input type="text" name='siamt' class='form-control input_field eadd' id='siamt' size='6' readonly='readonly' style='background-color:#F3F3F3;' placeholder="Sale Amount">
					  												<input type=hidden name='igsta' id='igsta'>
					  												<input type=hidden name='cgsta' id='cgsta'>					  												
						  											<input type=hidden name='sgsta' id='sgsta'>
					  												<input type=hidden name='ppsiamt' id='ppsimat'>
						  										</td>
																
																<td>
																<input type=text name='onlinercvdamt' class='form-control input_field eadd' id='onlinercvdamt' size='6' readonly='readonly' style='background-color:#F3F3F3;' placeholder='Online rcvd Amount'></td>
						  										
                      											<td><img src='images/delete.png' onclick='doRowDeleteInTranxs(this,siamt,si_amt,data_table_body)'></td>
                    										</tr>
                  										</tbody>
                									</table>
              									</div>
            								</div>
            							</div>
            						</div>
             					</form>
									<div class="row">	
										<div class="clearfix"></div>
										<br/>
										<div class="col-md-2">
											<button name="add_data" id="add_data"  class="btn btn-info color_btn" onclick="addRow()">ADD</button>
										</div>
										<div class="col-md-2">
											<button name="fetch_data" class="btn btn-info color_btn fetch" id="fetch_data"  onclick="fetchPriceAndVAT()">FETCH PRICE</button>
										</div>
										<div class="col-md-2">
											<button name="calc_data" class="btn btn-info color_btn" id="calc_data" onclick="calculateValues()" disabled="disabled">CALCULATE</button>
										</div>
										<div class="col-md-2">
											<button name="save_data" class="btn btn-info color_btn bg_color2" id="save_data"  onclick="saveData(this)" disabled="disabled">SAVE</button>
										</div>
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
								<input type="hidden" id="page" name="page" value="jsp/pages/erp/transactions/sales/dom_refill_sales.jsp">
								<input type="hidden" id="actionId" name="actionId" value="">
								<input type="hidden" id="dataId" name="dataId" value="">
                				<table class="table table-striped" id="m_data_table">
                  					<thead>
                    					<tr class="title_head">
											<th width="10%" class="text-center sml_input"> INV NO</th>
                      						<th width="10%" class="text-center sml_input">INVOICE DATE</th>
                      						<th width="10%" class="text-center sml_input">CUSTOMER</th>
					  						<th width="10%" class="text-center sml_input">PAYMENT TERMS</th>                      						
                      						<th width="10%" class="text-center sml_input">TRANSACTION<br>AMOUNT</th>
					  						<th width="10%" class="text-center sml_input">INV AMOUNT</th>
					  						<th width="10%" class="text-center sml_input">PRODUCT</th>
					  						<th width="10%" class="text-center sml_input">UNIT PRICE</th>
					  						<th width="10%" class="text-center sml_input">DISCOUNT AMOUNT</th>
                      						<th width="10%" class="text-center sml_input">QUANTITY</th>
					  						<th width="10%" class="text-center sml_input">IGST AMOUNT</th>                      						
					  						<th width="10%" class="text-center sml_input">CGST AMOUNT</th>
					   						<th width="10%" class="text-center sml_input">SGST AMOUNT</th>
					   						<th width="10%" class="text-center sml_input">TOTAL AMOUNT</th>
					    					<th width="10%" class="text-center sml_input">PRE PAID QUANTITY</th>
					  						<th width="10%" class="text-center sml_input">PSV QUANTITY</th>
					  						<th width="10%" class="text-center sml_input">DELIVERED BY</th>
											<th width="10%" class="text-center sml_input">AREA CODE</th>
											<th width="10%" class="text-center sml_input">BANK ACCOUNT</th>
											<th width="10%" class="text-center sml_input">ONLINE RCVD AMOUNT</th>
											
											<th width="10%" class="text-center sml_input">Actions</th>
                   						</tr>
                  					</thead>
                  					<tbody id="m_data_table_body"></tbody>
                				</table>
                			</form>
              			</div>
        			</div>
      			</div>
    		</div>
    		<div id = "dialog-1" title="Alert(s)"></div>
 			<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div>
  		</body>
  		<script type="text/javascript" src="js/modal.js?<%=randomUUIDString%>"></script>
  		<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
  		<script type="text/javascript" src="js/modules/transactions/sales/dom_refill_sales.js?<%=randomUUIDString%>"></script>
  		<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
		<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
  		<script src="js/commons/main.js?<%=randomUUIDString%>"></script>
  		<script type="text/javascript">
 			document.getElementById("nameSpan").innerHTML = <%= agencyVO.getAgency_code() %>
 			var dedate = <%=agencyVO.getDayend_date()%>;
 	     	var effdate = <%=agencyVO.getEffective_date()%>;
 			var a_created_date = <%=agencyVO.getCreated_date()%>;
	    	var dealergstin =  "<%= agencyVO.getGstin_no() %>"; 			
 			$(document).ready(function() {
 	       	    $('#m_data_table').DataTable( {
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
 			
  	        /* $(document).ready(function() {
 	        	$('#m_data_table').DataTable( {
 	        		"bFilter": false,
 	        		"ordering": false,
 	        	    "scrollY": 230,
 	        	    "scrollX": true
 	        	} );
 	         } ); */
  	</script>
</html> --%>