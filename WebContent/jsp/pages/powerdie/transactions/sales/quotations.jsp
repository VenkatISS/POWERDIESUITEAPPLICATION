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
    	<script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>
	    <script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
		<link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    	<title>POWER DIESUITE WEB APPLICATION - QUOTATIONS</title>
        <jsp:useBean id="adminDO" scope="session" class="com.it.diesuiteapp.framework.model.AdminDO"></jsp:useBean>
		<jsp:useBean id="arb_types_list" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="qtn_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="cvo_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="users_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="product_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="product_prices_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="cl_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="arb_prices_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="product_types_list" scope="request" class="java.lang.String"></jsp:useBean>
		
		<script type="text/javascript">
			var cat_prod_types_data = <%= product_types_list.length()>0? product_types_list:"[]" %>;
			var page_data = <%= qtn_data.length()>0? qtn_data : "[]" %>;
			var cvo_data = <%= cvo_data.length()>0? cvo_data : "[]" %>;
			var users_data = <%= users_data.length()>0? users_data : "[]" %>;
			var product_data = <%= product_data.length()>0? product_data : "[]" %>;
			var product_prices_data = <%= product_prices_data.length()>0? product_prices_data : "[]" %>;
			var arb_prices_data = <%= arb_prices_data.length()>0? arb_prices_data : "[]" %>;
			var cust_cl_data = <%= cl_data.length()>0? cl_data: "[]" %>;
			var ctdatahtml = "";
			var vendordatahtml = "";
			var staffdatahtml = "";
			var months = ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];
			//Construct Category Type html
			ctdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
			ctdatahtml = ctdatahtml + "<OPTION VALUE='-1' disabled>---PRODUCT LIST---</OPTION>";
			if(cat_prod_types_data.length>0) {
				for(var z=0; z<cat_prod_types_data.length; z++){
					if(cat_prod_types_data[z].cat_type==1) {
						ctdatahtml=ctdatahtml+"<OPTION VALUE='"+cat_prod_types_data[z].id+"'>"
							+cat_prod_types_data[z].cat_name+"-"+cat_prod_types_data[z].cat_desc+"</OPTION>";
					}
				}
			}
		</script>
<%-- 		<script src="js/commons/checkBrowser.js?<%=randomUUIDString%>"></script> --%>

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
          		<div><h1>QUOTATIONS</h1></div>
					<ul id="nav" class="nav nav-pills clearfix right" role="tablist">
          				<li class="dropdown"><span class="dropdown-toggle  btn-info" data-toggle="dropdown" id="ahelp">Help</span>
							<ul id="products-menu" class="dropdown-menu clearfix" role="menu">
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">English</a></li>
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">Hindi</a></li>
							</ul>
						</li>
					</ul>
        	</div>
        	<button name="b_sb" id="b_sb" class="btn btn-info color_btn bg_color4" onclick="showQuoFormDialog()">ADD QUOTATION</button>
			<button name="cancel_data" id="cancel_data"	class="btn btn-info color_btn bg_color3" onclick="doAction('MasterDataControlServlet','jsp/pages/erp/home.jsp','2001')">BACK</button>
			<!-- Modal -->
			<div class="modal_popup_add fade in" id="myModal" style="display: none;height: 100%">					
		 		<div class="modal-dialog modal-lg">
			  		<!-- Modal content-->
			  			<div class="modal-content" id="modal-content">
							<div class="modal-header" id="modal-header">
					  			<span class="close" id="close" onclick="closeQuoFormDialog()">&times;</span>
					  			<h4 class="modal-title">QUOTATIONS</h4>
							</div>
							<div class="modal-body">
								<form id="data_form" name="" method="post" action="TransactionsDataControlServlet">
									<input type="hidden" id="adminId" name="adminId" value="<%= adminDO.getAdminId() %>">
									<input type="hidden" id="page" name="page" value="jsp/pages/powerdie/transactions/sales/quotations.jsp">
									<input type="hidden" id="actionId" name="actionId" value="5142">
									<div class="row">
										<div class="col-adj-res-sub">
											<label>QUOTATION DATE </label>
											<input type="date" id="qtn_date" name="qtn_date" class="form-control input_field tp" placeholder="DD-MM-YY">
										</div>
										<div class="col-adj-res-sub">
											<label>CUSTOMER NAME</label>
											<span id="cSpan"></span>
										</div>
										<!-- <div class="col-adj-res-sub">
											<label>STAFF NAME  </label>
											<span id="sSpan"></span>
										</div> -->
										<div class="col-adj-res-sub">
											<label>QUOTATION VALUE </label>
											<input type="text" class="form-control input_field" id="qtn_c_amt" name="qtn_c_amt" value="" readonly="readonly" placeholder="INV AMOUNT" style="background-color:#F3F3F3;">
										</div>				
										<div class="clearfix"></div>
									</div>
									<br>
									<div class="row">
										<div class="col-md-12">
											<div class="animated-radio-button">
													<label>SALE TYPE</label>&nbsp;&nbsp;
													<label>
						  								<input type="radio"  id="stype" name="stype"  value="ls"><span  id="ptype" class="label-text">LOCAL SALE</span>
													</label>&nbsp;&nbsp;
													<label>
						  								<input type="radio" id="stype" name="stype"  value="iss"><span  id="ptype" class="label-text">INTER-STATE SALE</span>
													</label>
											</div>
										</div>
									</div>
									<br>
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
					   											<th width="10%" class="text-center sml_input">DISCOUNT ON <br> UNIT PRICE</th>
					   											<th width="10%" class="text-center sml_input">QUANTITY</th>
					   											<th width="10%" class="text-center sml_input">TAXABLE AMOUNT</th>
					   											<th width="10%" class="text-center sml_input">IGST AMOUNT</th>					   											
					   											<th width="10%" class="text-center sml_input">CGST AMOUNT</th>
					   											<th width="10%" class="text-center sml_input">SGST AMOUNT</th>
					   											<th width="10%" class="text-center sml_input">TOTAL AMOUNT</th>
					   											<th width="10%" class="text-center sml_input">FOOT NOTES</th>
					   											<th width="10%" class="text-center sml_input">Actions</th>
                       										</tr>
                       									</thead>
                       									<tbody id="data_table_body">
                       										<tr>
                       											<td>
                       												<select class="form-control input_field select_dropdown sadd tp epid freez" name='epid' id='epid'>
						 												<%String str = "<script>document.writeln(ctdatahtml)</script>";
						   														out.println("value: " + str);%>
						   											</select>
					   											</td>
                       											<td>
                       												<input type="text" class="form-control input_field eadd" name='vp' id='vp' size='8' readonly='readonly' placeholder="Gst%" style='background-color:#FAFAC2;'>
                       											</td>
					   											<td>
					   												<input type="text" class="form-control input_field eadd" name='up' id='up' maxlength='7' size='8' readonly='readonly' placeholder="Unit Price" style='background-color:#FAFAC2;'>
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field freez eadd" name='upd' id='upd' size='8' maxlength='7' size='8' placeholder=" Discount On Unit Price">
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field qtyc freez eadd" name='qty' id='qty' size='8' maxlength='4' placeholder="Quantity">
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field eadd" name='bp' id='bp' size='8' maxlength='8' size='8' value='0.00' readonly='readonly' placeholder="TAXABLE AMOUNT" style='background-color:#F3F3F3;'>
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field eadd" name='igsta' id='igsta' size='8' readonly='readonly' placeholder="IGST" style='background-color:#F3F3F3;'>
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field eadd" name='cgsta' id='cgsta' size='8' readonly='readonly' placeholder="CGST" style='background-color:#F3F3F3;'>
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field eadd" name='sgsta' id='sgsta' size='8' readonly='readonly' placeholder="SGST" style='background-color:#F3F3F3;'>
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field eadd" name='amt' id='amt' size='8' readonly='readonly' placeholder="Total Amount" style='background-color:#F3F3F3;'>
					   											</td>
					   											<td>
					   												<input type="text" class="form-control input_field eadd" name='fn' id='fn' maxlength='200' size='8' placeholder="Foot Notes">
					   											</td>
                       											<td><img src='images/delete.png' onclick='doRowDeleteInTranxs(this,amt,qtn_c_amt,data_table_body)'></td>
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
											<button name="fetch_data" class="btn btn-info color_btn fetch" id="fetch_data"  onclick="fetchPriceAndVAT()">FETCH VALUES</button>
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
								<input type="hidden" id="adminId" name="adminId" value="<%= adminDO.getAdminId() %>">
								<input type="hidden" id="page" name="page" value="jsp/pages/powerdie/transactions/sales/quotations.jsp">
								<input type="hidden" id="actionId" name="actionId" value="">
								<input type="hidden" id="dataId" name="dataId" value="">
                				<table class="table table-striped" id="m_data_table">
                  					<thead>
                    					<tr class="title_head">
					 						<th width="10%" class="text-center sml_input">QTN NUMBER</th>
                      						<th width="10%" class="text-center sml_input">QTN DATE</th>
                      						<th width="10%" class="text-center sml_input">CUSTOMER</th>
					  						<th width="10%" class="text-center sml_input">STAFF NAME</th>
					  						<th width="10%" class="text-center sml_input"> QTN AMT</th>
					   						<th width="10%" class="text-center sml_input">PRODUCT</th>
					   						<th width="10%" class="text-center sml_input">GST %</th>
					    					<th width="10%" class="text-center sml_input">UNIT PRICE</th>
											<th width="10%" class="text-center sml_input">DISCOUNT AMOUNT</th>
											<th width="10%" class="text-center sml_input">TAXABLE AMOUNT</th>
											<th width="10%" class="text-center sml_input">QUANTITY</th>
											<th width="10%" class="text-center sml_input">IGST</th>
											<th width="10%" class="text-center sml_input">CGST</th>											
											<th width="10%" class="text-center sml_input">SGST</th>
											<th width="10%" class="text-center sml_input">TOTAL AMOUNT</th>
											<th width="10%" class="text-center sml_input">FOOT NOTES</th>
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
		<script type="text/javascript" src="js/modules/transactions/sales/quotations.js?<%=randomUUIDString%>"></script>
		<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
		<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
		<script src="js/commons/main.js?<%=randomUUIDString%>"></script>
		<script type="text/javascript">
 			document.getElementById("nameSpan").innerHTML = <%= adminDO.getAdminId() %>; 
 			<%-- var dedate = <%=agencyVO.getDayend_date()%>;
 	     	var effdate = <%=agencyVO.getEffective_date()%>; --%>
 			var a_created_date = <%=adminDO.getCreatedDate()%>;
	   <%--  	var dealergstin =  "<%= adminDO.getGstin_no() %>"; 			 --%>
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
</html>