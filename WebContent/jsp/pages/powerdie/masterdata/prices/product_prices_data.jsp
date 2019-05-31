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
    <title>PRODUCT PRICE MASTER</title>
 
    <jsp:useBean id="adminDO" scope="session" class="com.it.diesuiteapp.framework.model.AdminDO"></jsp:useBean>
	<jsp:useBean id="product_types_list" scope="request" class="java.lang.String"></jsp:useBean> 
	<jsp:useBean id="product_data" scope="request" class="java.lang.String"></jsp:useBean>
	<jsp:useBean id="product_prices_data" scope="request" class="java.lang.String"></jsp:useBean>
    <script src="js/commons/jquery-2.1.4.min.js?<%=randomUUIDString%>"></script>
<%-- 	<script src="js/commons/checkBrowser.js?<%=randomUUIDString%>"></script>     --%>

	<!-- Sidenav -->
		<jsp:include page="/jsp/pages/commons/sidenav.jsp"/>    
	<!---END Sidenav--->
	
	<script type="text/javascript"> 
		window.onload = setWidthHightNav("100%");
	</script>
	<script type="text/javascript">
		var months = ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];
		var years = ["2018","2019","2020"];
		var cat_types_data = <%= product_types_list %>;
		var product_data = <%= product_data.length()>0? product_data : "[]" %>;
		var ctdatahtml, mvdatahtml, yvdatahtml = "";
		var page_data =  <%= product_prices_data.length()>0? product_prices_data : "[]" %>;
	</script>
	<script type="text/javascript" src="js/commons/general_validations.js?<%=randomUUIDString%>"></script>
  </head>
  <body class="sidebar-mini fixed">
    <div class="wrapper">
     <!-- Header-->
		<jsp:include page="/jsp/pages/commons/header.jsp"/>
	<!--header End--->
  
    <div class="content-wrapper">
<!--     	<div id = "dialog-1" title="Alert(s)"></div>
		<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div> -->
        
        <div class="page-title">
			<div>
				<h1>PRODUCT PRICE MASTER </h1>
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
				<form id="page_data_form" name="" method="post" action="MasterDataControlServlet">
					<input type="hidden" id="adminId" name="adminId" value="<%= adminDO.getAdminId() %>">
					<input type="hidden" id="page" name="page" value="jsp/pages/powerdie/masterdata/prices/product_prices_data.jsp">
					<input type="hidden" id="actionId" name="actionId" value="3202">
					<input type="hidden" id="itemId" name="itemId" value="">

                	<table id="page_add_table" class="table">
                  		<thead>
                    		<tr class="title_head">
                      			<th width="10%" class="text-center sml_input"> PRODUCT</th>
                      			<th width="10%" class="text-center sml_input">RSP</th>
                			    <th width="10%" class="text-center sml_input">BASIC PRICE</th>
                      			<th width="10%" class="text-center sml_input">SGST AMOUNT</th>
                      			<th width="10%" class="text-center sml_input">CGST AMOUNT</th>
					  			<th width="10%" class="text-center sml_input">MONTH</th>
					    		<th width="10%" class="text-center sml_input">YEAR</th>
						 		<th width="10%" class="text-center sml_input">ACTIONS</th>
                    		</tr>
                  		</thead>
                  		<tbody id="page_add_table_body">
                  			
                  		
                  		</tbody>
                	</table>
                </form>	
              </div>
            </div>

          </div>
        </div>
		<button class="btn btn-info color_btn btnadd" onclick="addRow()">ADD</button>
		
		<span id="savediv" style="display:none;">
			<button class="btn btn-info color_btn bg_color2" id="calculate_data" onclick="calculateValues()" >CALCULATE</button>
			<button class="btn btn-info color_btn bg_color2" id="save_data" onclick="saveData(this)" disabled="disabled">SAVE</button>
		</span>
		
		<button class="btn btn-info color_btn bg_color2" onclick="doAction('MasterDataControlServlet','jsp/pages/powerdie/home.jsp','2001')">BACK</button>	
		<br>
		<br>	
		<div class="row">
          <div class="clearfix"></div>
          <div class="col-md-12">
            <div class="main_table">
              <div class="table-responsive">
                <table class="table  table-striped" id="page_data_table">
                  <thead>
                    <tr class="title_head">
                       <th width="20%" class="text-center sml_input">PRODUCT</th>
                       <th width="20%" class="text-center sml_input">RSP</th>
                       <th width="20%" class="text-center sml_input">BASIC PRICE</th>
                       <th width="20%" class="text-center sml_input">SGST AMOUNT</th>
                       <th width="20%" class="text-center sml_input">CGST AMOUNT</th>
                       <th width="20%" class="text-center sml_input">MONTH</th>
					   <th width="20%" class="text-center sml_input">YEAR</th>
				       <th width="20%" class="text-center sml_input">ACTIONS</th>
                    </tr>
                  </thead>
                  <tbody id="page_data_table_body"></tbody>
                </table>
              </div>
            </div>			
          </div>
        </div>
      </div>
      <%--  <!-- popup div -->
      <div class="modal_popup_add fade in" id="myModalrefilPin" style="display:block;" padding-left: 14px; height: 100%">
      	<div class="modal-dialog modal-lg">
        	<!-- Modal content-->
            <div class="modal-content prof_modalContent" style="height:200px;width:60%; margin:auto;">
				<div id="contentDiv">
					<div class="modal-header" style="text-align:center;">
                    	<h4 class="modal-title">ENTER SECURITY PIN DETAILS</h4>
                    </div>
                    <div class="modal-body">
						<input type="hidden" id="pinNO" name="pinNO" value="<%= agencyVO.getPinNumber() %>">
                        <table id="t_data_table">
							<tr class="spaceUnder">
								<td>Pin Number:&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td><input class="form-control" type="password" name="enteredPin" id="enteredPin" placeholder="Enter pin here"></td>
								<td colspan="6">
									<div class="submit_div" style="text-align: center; width: 100px; margin-left: 40px; margin-top: -3px;">
										<input type="button" name="prof_submit_btn" id="prof_submit_btn" value="SUBMIT" class="btn btn-success m-r-15" onclick="submitPinNumber()">
									</div>
								</td>
							</tr>
						</table>
						<div class="submit_div" style="text-align: center; width: 100px; margin-left: 2px; margin-top: 10px;">
                  			<button name="prof_submit_btn" id="prof_submit_btn" class="btn btn-success m-r-15" onclick="doAction('MasterDataControlServlet','jsp/pages/erp/home.jsp','2001')">BACK</button>
                  		</div>
                	</div>
                </div>
                <div id="displayDiv">
        			<div class="modal-header" style="text-align:center;">
    	            	<h4 class="modal-title">SECURITY PIN DETAILS</h4>
    	            </div>
    	            <div class="modal-body"><br>
        				YOU  HAVEN'T  SET  ANY  PROFILE  PIN.
        				<a href="#prof" onclick="doAction('MasterDataControlServlet','jsp/pages/erp/profile.jsp','9001')">CLICK HERE</a>
        				 TO  SET  PIN  OR  SET  PIN  IN  YOUR  PROFILE  DETAILS  AND  THEN  PROCEED  <br><br>
        				<div class="submit_div" style="text-align: center; width: 100px; margin-left: 200px;">
                  			<button name="prof_submit_btn" id="prof_submit_btn" class="btn btn-success m-r-15" onclick="doAction('MasterDataControlServlet','jsp/pages/erp/home.jsp','2001')">BACK</button>
		                </div>
        			</div>	
        		</div>
			</div>
        </div>
 	</div> --%>
</div>
    	<div id = "dialog-1" title="Alert(s)"></div>
		<div id="dialog-confirm"><div id="myDialogText" style="color:black;"></div></div>
					
    <!-- Javascripts-->
	 <script type="text/javascript">
		var checkDisplay = <%=request.getAttribute("checkDisplay") %>;
		<%--	if(!checkDisplay)
        	checkDisplay="0";
  		var pinNum = <%= agencyVO.getPinNumber() %>;   --%>
    
	</script> 
	<script src="js/commons/bootstrap.min.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/plugins/pace.min.js?<%=randomUUIDString%>"></script>
	<script src="js/commons/main.js?<%=randomUUIDString%>"></script>
	<script type="text/javascript" src="js/modules/masterdata/prices/product_prices_data.js?<%=randomUUIDString%>"></script> 
	<script>
		<%-- document.getElementById("nameSpan").innerHTML = <%= adminDO.getAdminId() %> --%>

		document.getElementById('page_data_form').agencyId = <%=adminDO.getAdminId() %>;
		
		<%-- var dedate = new Date(<%=adminDO.getDayend_date()%>);
		var effdate = new Date(<%=adminDO.getEffective_date()%>); --%>
		
		$(document).ready(function() {
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
