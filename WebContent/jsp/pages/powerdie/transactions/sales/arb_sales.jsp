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
    	<title>ARB SALES </title>
    	<jsp:useBean id="agencyVO" scope="session" class="com.it.erpapp.framework.model.vos.AgencyVO"></jsp:useBean>
		<jsp:useBean id="arb_types_list" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="cvo_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="staff_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="arb_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="arb_prices_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="arbs_data" scope="request" class="java.lang.String"></jsp:useBean>
		<jsp:useBean id="cl_data" scope="request" class="java.lang.String"></jsp:useBean>
		
	    <script type="text/javascript">
			var cat_arb_types_data = <%= arb_types_list %>;
			var cat_arb_types_data = <%=arb_data %>;
			var page_data = <%= arbs_data.length()>0? arbs_data : "[]" %>;
			var cvo_data = <%= cvo_data.length()>0? cvo_data : "[]" %>;
			var staff_data = <%= staff_data.length()>0? staff_data : "[]" %>;
			var arb_data = <%= arb_data.length()>0? arb_data : "[]" %>;
			var arb_prices_data = <%= arb_prices_data.length()>0? arb_prices_data : "[]" %>;
			var ctdatahtml = "";
			var vendordatahtml = "";
			var staffdatahtml = "";
			var areacodeshtml = "";
			var months = ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];
			var payment_terms_values = ["NONE","CASH","CREDIT"];
			var cust_cl_data = <%= cl_data.length()>0? cl_data: "[]" %>;			

			//Construct ARB list
			ctdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
			if(cat_arb_types_data.length>0) {
				for(var z=0; z<cat_arb_types_data.length; z++){
					//ctdatahtml=ctdatahtml+"<OPTION VALUE='"+cat_arb_types_data[z].id+"'>"+cat_arb_types_data[z].cat_code+"-"
					//+cat_arb_types_data[z].cat_name+"-"+cat_arb_types_data[z].cat_desc+"</OPTION>";
					for(var i = 0 ;i < arb_prices_data.length;i++){
						if(cat_arb_types_data[z].id == arb_prices_data[i].arb_code){
							ctdatahtml=ctdatahtml+"<OPTION VALUE='"+cat_arb_types_data[z].id+"'>"
							+getARBType(cat_arb_types_data[z].prod_code)+" - "+cat_arb_types_data[z].prod_brand+" - "+cat_arb_types_data[z].prod_name
					        }
						}	
				}
			}

			function getARBType(prod_code){
				if(prod_code==13)
					return "STOVE";
				if(prod_code==14)
					return "SURAKSHA";
				if(prod_code==15)
					return "LIGHTER";
				if(prod_code==16)
					return "KITCHENWARE";
				if(prod_code==17)
					return "OTHERS";
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
          			<div><h1>BLPG/ARB/NFR SALES </h1></div>
					<ul id="nav" class="nav nav-pills clearfix right" role="tablist">
          				<li class="dropdown"><span class="dropdown-toggle  btn-info" data-toggle="dropdown" id="ahelp">Help</span>
							<ul id="products-menu" class="dropdown-menu clearfix" role="menu">
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">English</a></li>
								<li><a style="font-size: 14px;" href="https://youtu.be/bgQnKRrz2X8" target="_blank">Hindi</a></li>
							</ul>
						</li>
					</ul>
        		</div>
				<button name="b_sb" id="b_sb" class="btn btn-info color_btn bg_color4" onclick="showARBSalesFormDialog()">ADD ARB SALES</button>
				<button name="cancel_data" id="cancel_data" class="btn btn-info color_btn bg_color2" onclick="doAction('MasterDataControlServlet','jsp/pages/erp/home.jsp','2001')">BACK</button>
				<!-- Modal -->
				<div class="modal_popup_add fade in" id="myModal" style="display: none;height: 100%">					
					<div class="modal-dialog modal-lg">
			  			<!-- Modal content-->
			  			<div class="modal-content" id="modal-content">
							<div class="modal-header" id="modal-header">
					  			<span class="close" id="close" onclick="closeARBSalesFormDialog()">&times;</span>
					  			<h4 class="modal-title">BLPG/ARB/NFR SALES </h4>
							</div>
							<div class="modal-body">
								<form id="data_form" name="" method="post" action="TransactionsDataControlServlet">
									<input type="hidden" id="agencyId" name="agencyId" value="<%= agencyVO.getAgency_code() %>">
									<input type="hidden" id="page" name="page" value="jsp/pages/erp/transactions/sales/arb_sales.jsp">
									<input type="hidden" id="actionId" name="actionId" value="5132">
								<div class="row">
									<div class="col-adj-res">
										<label>SALES INV DATE </label>
										<input type="date" class="form-control input_field blkcalc" id="si_date" name="si_date" placeholder="DD-MM-YY">
									</div>
									<div class="col-adj-res">
										<label>CUSTOMER </label>
										<span id="cSpan"></span>
									</div>
									<div class="col-adj-res">
										<label>STAFF NAME  </label>
										<span id="sSpan"></span>
									</div>
									<div class="col-adj-res">
										<label>PAYMENT TERMS  </label>
										<select class="form-control input_field select_dropdown" id='pt' name='pt'>
											<option value='0'>SELECT</option>
											<option value='1'>CASH</option>
											<option value='2'>CREDIT</option>
										</select>
									</div>
									<div class="col-adj-res">
										<label>SALES INVOICE AMOUNT </label>
										<input type="text" class="form-control input_field" id="si_amt" name="si_amt" value="" readonly="readonly" style="background-color:#F3F3F3;" placeholder="INV AMOUNT">
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
															<th width="10%" class="text-center sml_input">TOTAL AMOUNT</th>
						 									<th width="10%" class="text-center sml_input">Actions</th>
                    									</tr>
                  									</thead>
                  									<tbody id="data_table_body">
                    									<tr>
                      										<td>
                      											<select class="form-control input_field select_dropdown sadd pid blkcalc" name='epid' id='epid'>
						  											<%String str3 = "<script>document.writeln(ctdatahtml)</script>";
						   												out.println("value: " + str3);%>
																</select>
															</td>
                      										<td>
                      											<input type="text" class="form-control input_field eadd" name='vatp' id='vatp' size='6' readonly='readonly' style='background-color:#FAFAC2;' placeholder="Gst%">
                      										</td>
					    									<td>
					    										<input type="text" class="form-control input_field eadd" name='up' id='up' size='6' maxlength='7' readonly='readonly' style='background-color:#FAFAC2;' placeholder="Unit Price">
					    									</td>
															<td>
																<input type="text" class="form-control input_field freez eadd" name='upd' id='upd' size='6' maxlength='7' placeholder=" Discount On Unit Price" value="0.00">
															</td>
						 									<td>
						 										<input type="text" class="form-control input_field qtyc freez eadd" name='qty' id='qty' maxlength='4' size='6' placeholder="Quantity">
						 									</td>
						  									<td>
						  										<input type="text" class="form-control input_field eadd" name='siamt' id='siamt' size='6' readonly='readonly' style='background-color:#F3F3F3;' placeholder="Sale Amount">
																<input type=hidden name='igsta' id='igsta'>
																<input type=hidden name='cgsta' id='cgsta'>
						  										<input type=hidden name='sgsta' id='sgsta'>
						  									</td>
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
								<br>
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
						<input type="hidden" id="page" name="page" value="jsp/pages/erp/transactions/sales/arb_sales.jsp">
						<input type="hidden" id="actionId" name="actionId" value="">
						<input type="hidden" id="dataId" name="dataId" value="">
                		<table class="table table-striped" id="m_data_table">
                  			<thead>
                    			<tr class="title_head">
									<th width="10%" class="text-center sml_input">INV NO</th>
                      				<th width="10%" class="text-center sml_input">INVOICE DATE</th>
                      				<th width="10%" class="text-center sml_input">CUSTOMER</th>
					   				<th width="10%" class="text-center sml_input">STAFF NAME</th>
					   				<th width="10%" class="text-center sml_input">PAYMENT TERMS</th>
					  				<th width="10%" class="text-center sml_input">INVOICE AMOUNT</th>
					  				<th width="10%" class="text-center sml_input">PRODUCT</th>
					  				<th width="10%" class="text-center sml_input">UNIT PRICE</th>
					  				<th width="10%" class="text-center sml_input">DISCOUNT AMOUNT</th>
                      				<th width="10%" class="text-center sml_input">QUANTITY</th>
					  				<th width="10%" class="text-center sml_input">IGST AMOUNT</th>
					  				<th width="10%" class="text-center sml_input">CGST AMOUNT</th>
					   				<th width="10%" class="text-center sml_input">SGST AMOUNT</th>
					 				<th width="10%" class="text-center sml_input">TOTAL AMOUNT</th>
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
	<script type="text/javascript" src="js/modal.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript" src="js/modules/transactions/sales/arb_sales.js?<%=randomUUIDString%>"></script>
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
	       	});	       	
	</script>
	<div id = "dialog-1" title="Alert(s)"></div>
   <div id="dialog-confirm"><div id="myDialogText" style="color:black;min-height: 80px;"></div></div>
  </body>  
</html>
 --%>