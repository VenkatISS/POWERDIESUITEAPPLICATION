/*if(!pinNum) {
	document.getElementById("contentDiv").style.display="none";
	document.getElementById("displayDiv").style.display="block";
}else {
	document.getElementById("displayDiv").style.display="none";
	document.getElementById("contentDiv").style.display="block";
}
*/
//Construct Category Type html
/*ctdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
if(cat_types_data.length>0) {
	for(var z=0; z<cat_types_data.length; z++){
		for(var y=0; y<cat_types_data.length; y++){
			if(cat_types_data[y].prod_code == cat_types_data[z].id) {
				if(cat_types_data[z].id<10) {
					ctdatahtml=ctdatahtml+"<OPTION VALUE='"+cat_types_data[z].id+"'>"
										+cat_types_data[z].cat_name+"-"+cat_types_data[z].cat_desc+"</OPTION>";
					break;
				}
			}
		}
	}
}*/

//Construct Category Type html
ctdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
if(cat_types_data.length>0) {
	for(var z=0; z<cat_types_data.length; z++){
		if(cat_types_data[z].deleted == 0){
		ctdatahtml=ctdatahtml+"<OPTION VALUE='"+cat_types_data[z].id+"'>"
		//+getProductType(cat_types_data[z].prod_code)+" - "+getRawMaterialType(cat_types_data[z].rawmat_code)
		+cat_types_data[z].cat_name+"-"+cat_types_data[z].cat_desc+"</OPTION>";		
		}
	}
}

//Construct Month html
mvdatahtml = "<OPTION VALUE='-1'>SELECT</OPTION>";
for(var z=0; z<months.length; z++){
	mvdatahtml=mvdatahtml+"<OPTION VALUE='"+z+"'>"+months[z]+"</OPTION>";
}
yvdatahtml = "<OPTION VALUE='-1'>SELECT</OPTION>";
for(var z=0; z<years.length; z++){
	yvdatahtml=yvdatahtml+"<OPTION VALUE='"+z+"'>"+years[z]+"</OPTION>";
}

var checkNum=checkDisplay;
if(checkNum=="1"){
//	document.getElementById("myModalrefilPin").style="none";
	var tbody = document.getElementById('page_data_table_body');
	for(var f=page_data.length-1; f>=0; f--){
		var tblRow = tbody.insertRow(-1);
		tblRow.style.height="20px";
		tblRow.align="left";
		tblRow.id="or";
		var spd = fetchProductDetails(cat_types_data, page_data[f].prod_code);
		tblRow.innerHTML = "<tr>"+
			"<td>"+ spd +"</td>"+
			"<td>"+ page_data[f].rsp +"</td>"+
			"<td>"+ page_data[f].base_price +"</td>"+
			"<td>"+ page_data[f].sgst_price +"</td>"+
			"<td>"+ page_data[f].cgst_price +"</td>"+
			"<td>"+ months[page_data[f].month] +"</td>"+
			"<td>"+ years[page_data[f].year] +"</td>"+
			"<td valign='top' height='4' align='center'><img src='images/delete.png' onclick='deleteItem("+page_data[f].id+")'></td>"+
			"</tr>";
	} 
}else {
	var tbody = document.getElementById('page_data_table_body');
	for(var f=page_data.length-1; f>=0; f--){
		var tblRow = tbody.insertRow(-1);
		tblRow.style.height="20px";
		tblRow.align="left";
		tblRow.id="or";
		var spd = fetchProductDetails(cat_types_data, page_data[f].prod_code);
		tblRow.innerHTML = "<tr>"+
			"<td>"+ spd +"</td>"+
			"<td>"+ page_data[f].rsp +"</td>"+
			"<td>"+ page_data[f].base_price +"</td>"+
			"<td>"+ page_data[f].sgst_price +"</td>"+
			"<td>"+ page_data[f].cgst_price +"</td>"+
			"<td>"+ months[page_data[f].month] +"</td>"+
			"<td>"+ years[page_data[f].year] +"</td>"+
			"<td valign='top' height='4' align='center'><img src='images/delete.png' onclick='deleteItem("+page_data[f].id+")'></td>"+
			"</tr>";
	}
}


function addRow() {
	var eqdataLen=cat_types_data.length;
    $("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');

	if (eqdataLen<1) {
		document.getElementById("dialog-1").innerHTML ="Please enter PRODUCT in EQUIPMENT MASTER";
		alertdialogueWithCollapse();
		// alert("Please enter PRODUCT in EQUIPMENT MASTER");
	}
	else {
		document.getElementById("save_data").disabled = true;
		document.getElementById('savediv').style.display="inline";
	
		var x = document.getElementById('myDIV');
		if (x.style.display === 'none')
			x.style.display = 'block';
    
		var trcount = document.getElementById('page_add_table_body').getElementsByTagName('tr').length;
		if(trcount>0){
			var trv=document.getElementById('page_add_table_body').getElementsByTagName('tr')[trcount-1];
			var saddv=trv.getElementsByClassName('sadd');
			var eaddv=trv.getElementsByClassName('eadd');
    
			var res=checkRowData(saddv,eaddv);
			if(res == false){
				//alert("Please enter all the values in current row,calculate and then add next row");
				document.getElementById("dialog-1").innerHTML ="Please enter all the values in current row,calculate and then add next row";
				alertdialogue();
				return;
			}		
		}
    
		var ele = document.getElementsByClassName("rspc");
		if(ele.length < 4){		
			var tbody = document.getElementById('page_add_table_body');
			var newRow = tbody.insertRow(-1);
			newRow.id = "nr";

			var a = newRow.insertCell(0);
			var b = newRow.insertCell(1);
			var c = newRow.insertCell(2);
			var d = newRow.insertCell(3);
			var e = newRow.insertCell(4);
			var f = newRow.insertCell(5);
			var g = newRow.insertCell(6);
			var h = newRow.insertCell(7);
			
			a.innerHTML = "<td valign='top' height='4' align='center'><select name='pid' class='form-control input_field select_dropdown ic sadd freez' id='pid'>"
				+ ctdatahtml + "</select></td>";
			b.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='rsp' id='rsp' placeholder='RSP' class='form-control input_field rspc freez eadd' maxlength='8'></td>";
			c.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='bp' id='bp' placeholder='BASIC PRICE' class='form-control input_field eadd' readonly='readonly' style='background-color:#F3F3F3;'></td>";
			d.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='sgst' id='sgst' placeholder='SGST' class='form-control input_field eadd' readonly='readonly' style='background-color:#F3F3F3;'></td>";
			e.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='cgst' id='cgst' placeholder='CGST' class='form-control input_field eadd' readonly='readonly' style='background-color:#F3F3F3;'></td>";
			f.innerHTML = "<td valign='top' height='4' align='center'><select name='mon' id='mon' class='form-control input_field select_dropdown sadd'>"
				+ mvdatahtml + "</SELECT></td>";
			g.innerHTML = "<td valign='top' height='4' align='center'><select name='yr' id='yr' class='form-control input_field select_dropdown sadd'>"
				+ yvdatahtml + "</SELECT></td>";
			h.innerHTML = "<td valign='top' height='4' align='center'><img src='images/delete.png' onclick='doRowDelete(this)'></td>";
		}else{
			//alert("Please Save the Records and ADD Again");
			document.getElementById("dialog-1").innerHTML ="Please Save the Records and ADD Again";
			alertdialogue();
			document.getElementById("save_data").disabled = false;
		}	
	}
}
function saveData(obj) {
	var formobj = document.getElementById('page_data_form');
	var ems = "";

	if (document.getElementById("pid") != null) {

		var elements = document.getElementsByClassName("rspc");
		if (elements.length == 1) {
			var epid = formobj.pid.selectedIndex;
			var ersp = formobj.rsp.value.trim();
			var esgst = formobj.sgst.value;
			var ecgst = formobj.cgst.value;
			var ebp = formobj.bp.value;
			var emon = formobj.mon.selectedIndex;
			var eyr = formobj.yr.selectedIndex;
			var eyrv = formobj.yr.options[eyr].value;
			var pidv = formobj.pid.value;
			var gstpercent=findGSTPercent(ersp, pidv);
			
			ems = validateRefillEntries(epid, ersp, esgst, ecgst,gstpercent, ebp, emon,eyr,eyrv);
			
		} else if (elements.length > 1) {
			for (var i = 0; i < elements.length; i++) {
				var epid = formobj.pid[i].selectedIndex;
				var ersp = formobj.rsp[i].value.trim();
				var esgst = formobj.sgst[i].value;
				var ecgst = formobj.cgst[i].value;
				var ebp = formobj.bp[i].value;
				var emon = formobj.mon[i].selectedIndex;
				var eyr = formobj.yr[i].selectedIndex;
				var eyrv = formobj.yr[i].options[eyr].value;
				var pidv = formobj.pid[i].value;
				var gstpercent=findGSTPercent(ersp, pidv);
				ems = validateRefillEntries(epid,ersp,esgst,ecgst,gstpercent,ebp,emon,eyr,eyrv);
				if (ems.length > 0)
					break;
			}
		}
	} else {
		document.getElementById("dialog-1").innerHTML ="PLEASE ADD DATA";
		alertdialogue();
		//alert("PLEASE ADD DATA");
		return;
	}

	if (ems.length > 0) {
		document.getElementById("dialog-1").innerHTML =ems;
		alertdialogue();
		//alert(ems);
		return;
	}
	
	var objId = obj.id;
	document.getElementById(objId).disabled = true;
	formobj.submit();
}

/*function deleteItem(id) {

	if (confirm("Are you sure you want to delete?") == true) {
		var formobj = document.getElementById('page_data_form');
		formobj.actionId.value = "3203";
		formobj.itemId.value = id;
		formobj.submit();
	}
}*/
function deleteItem(id){
	 $("#myDialogText").text("Are You Sure You Want To Delete?");
	 var formobj = document.getElementById('page_data_form');
	 confirmDialogue(formobj,3203,id);
}

function calculateValues() {
	var formobj = document.getElementById('page_data_form');
	if (document.getElementById("rsp") != null) {
		var elements = document.getElementsByClassName("rspc");
		if (elements.length == 1) {
			var rspv = formobj.rsp.value.trim();
			var epid = formobj.pid.selectedIndex;
			
			var ems = validateRefillEntries2(epid,rspv);
			if (ems.length > 0) {
				//alert(ems);
				document.getElementById("dialog-1").innerHTML = ems;
				alertdialogue();
				return;
			}
			var pid = formobj.pid.value;
			var gstp = calculateGSTPrice(rspv, pid);
			var gstav = (Math.round(gstp * 100)) / 100;
			formobj.sgst.value = gstav;
			formobj.cgst.value = gstav;
			formobj.rsp.value = round(parseFloat(rspv), 2);
			formobj.bp.value = (+rspv - (+(2 * gstav))).toFixed(2);
	
		} else if (elements.length > 1) {
			for (var i = 0; i < elements.length; i++) {
				var rspv = formobj.rsp[i].value.trim();
				var epid = formobj.pid[i].selectedIndex;
				
				var ems = validateRefillEntries2(epid,rspv);
				if (ems.length > 0) {
					document.getElementById("dialog-1").innerHTML = ems;
					alertdialogue();
					//alert(ems);
					return;
				}

				var pid = formobj.pid[i].value;					
				var e = document.getElementById("pid");
				var text = e.options[e.selectedIndex].text;
				
				var gstp = calculateGSTPrice(rspv, pid);
				var gstav = (Math.round(gstp * 100)) / 100;
				formobj.sgst[i].value = gstav;
				formobj.cgst[i].value = gstav;
				formobj.rsp[i].value = round(parseFloat(rspv), 2);
				formobj.bp[i].value = (+rspv - (+(2 * gstav))).toFixed(2);
			}
		}

	} else {
		document.getElementById("dialog-1").innerHTML = "PLEASE ADD REFILL PRICE DATA AND CLICK CALCULATE";
		alertdialogue();
		//alert("PLEASE ADD REFILL PRICE DATA AND CLICK CALCULATE");
		return;
	}
	document.getElementById("save_data").disabled = false;	
	restrictChangingAllValues(".freez");
}


function validateRefillEntries(prod, rsp, sgst, cgst,gstpercent, bp, mon, yr,yrv) {
	var errmsg = "";
	var formobj = document.getElementById('page_data_form');

	if (!(prod > 0))
		errmsg = "Please Select The PRODUCT <br>";
	else if (!validateProduct())
		errmsg = errmsg +"The PRICE has already been fixed for the product you have selected." ;	

	if (!(rsp.length > 0))
		errmsg = errmsg + "Please enter RSP.<br>";
	else if (validateDot(rsp))
		errmsg = errmsg + "RSP must contain atleast one number. <br>";
	else if (!(isDecimalNumber(rsp)))
		errmsg = errmsg + "RSP must contain only numerics.<br>";
	else if (!((parseFloat(rsp) > 0) && (parseFloat(rsp) <= 99999.99))) {
		errmsg = errmsg+ "RSP must be less than 1 lakh and greater than 0.<br>";
		errmsg = errmsg+ "please enter RSP correctly and then CALCULATE again.<br>";
	} else
		formobj.rsp.value = round(parseFloat(rsp), 2);

	if (!(sgst.length > 0))
		errmsg = errmsg + "Please calculate SGST.<br>";
	else if (!(isDecimalNumber(sgst)))
		errmsg = errmsg + "SGST must not contain alphabets.<br>";
	else if(parseInt(gstpercent)!=0 && parseFloat(sgst)==0) 
		errmsg = errmsg + "Invalid SGST Amount For a non 0% GST.<br>";
	else if (!(validateDecimalNumberMinMax(cgst, -1, 100000))) 
		errmsg = errmsg + "SGST must be less than 1,00,000 and greater than 0.<br>";

	if (!(isDecimalNumber(cgst)))
		errmsg = errmsg + "CGST must not contain alphabets.<br>";
	else if(parseInt(gstpercent)!=0 && parseFloat(cgst)==0) 
		errmsg = errmsg + "Invalid CGST Amount For a non 0% GST.<br>";
	else if (!(validateDecimalNumberMinMax(cgst, -1, 100000))) 
		errmsg = errmsg + "CGST must be less than 1,00,000 and greater than 0.<br>";

	if (!(isDecimalNumber(bp)))
		errmsg = errmsg + "BASIC PRICE must not contain alphabets.<br>";
	else if (!(validateDecimalNumberMinMax(bp, 0, 100000))) 
		errmsg = errmsg + "BASIC PRICE must be less than 1,00,000 and greater than 0.<br>";
	
	var date= new Date;
	var curmonth=date.getMonth();
	var curyear=date.getFullYear();

	if (!(parseInt(mon) > 0))
		errmsg = errmsg + "Please select MONTH.<br>";
	else if((parseInt(mon)>curmonth+1) && (parseInt(years[yrv])>=curyear))
		errmsg = errmsg + "MONTH can't be Future month<br>";
	else if((parseInt(years[yrv])>curyear))
		errmsg = errmsg + "YEAR can't be Future year<br>";

	if (!(parseInt(yr) > 0))
		errmsg = errmsg + "Please select YEAR.<br>";
/*	else if (parseInt(yr) == 1 && parseInt(mon) < 7)
		errmsg = errmsg+ "Select MONTH and YEAR from JULY,2017 onwards .<br>";
*/
	else {
		var yrval = years[yr-1];
		var cdate = new Date(parseInt(yrval),parseInt(mon-1),01,0,0,0);
/*		var ddate = new Date(dedate.getFullYear(),dedate.getMonth(),01,0,0,0);
		if(cdate < ddate) {
			errmsg = errmsg+ "MONTH and YEAR should be after DayEnd Date .<br>";
		}
*/
		
/*		var edate = new Date(effdate.getFullYear(),effdate.getMonth(),01,0,0,0);
		if(cdate < edate) {
			errmsg = errmsg+ "MONTH and YEAR is acceptable from the EFFECTIVE Date provided.<br>";
		}
*/
		
		var pcode = document.getElementById("pid").value.trim();
		var plongdate = 0;
		for(var e=0;e<cat_types_data.length;e++) {
			if(cat_types_data[e].prod_code == pcode) {
				plongdate = parseFloat(cat_types_data[e].effective_date);
			}
		}
		if(plongdate !=0 ) {
			var pdate = new Date((new Date(plongdate)).getFullYear(),(new Date(plongdate)).getMonth(),01,0,0,0);
			if(cdate < pdate) {
				errmsg = errmsg+ "MONTH and YEAR is acceptable from the Effective Date provided for this product in its Master.<br>";
			}
		}/*else {
			var edate = new Date(effdate.getFullYear(),effdate.getMonth(),01,0,0,0);
			if(cdate < edate) {
				errmsg = errmsg+ "MONTH and YEAR is acceptable from the EFFECTIVE Date provided.<br>";
			}
		}	*/	
	}
	return errmsg;
}


function validateRefillEntries2(pid,rsp) {
	var errmsg = "";
	var formobj = document.getElementById('page_data_form');

	if (!(pid > 0))
		errmsg = "Please Select The PRODUCT <br>";
	else if (!validateProduct()){
		errmsg = "The PRICE has already been fixed for the product you have selected.";
		return errmsg;
	}
		
	else if (!(rsp.length > 0))
		errmsg = errmsg + "Please enter RSP.<br>";
	else if (validateDot(rsp))
		errmsg = errmsg + "RSP must contain atleast one number. <br>";
	else if (!(isDecimalNumber(rsp)))
		errmsg = errmsg + "Please Enter Valid RSP.<br>";
	else if (!((parseFloat(rsp) > 0) && (parseFloat(rsp) <= 99999.99))) {
		errmsg = errmsg + "RSP must be less than 1 lakh and greater than 0.<br>";
		errmsg = errmsg + "please enter RSP correctly and then CALCULATE again.<br>";
	} else
		formobj.rsp.value = round(parseFloat(rsp), 2);

	return errmsg;
}
	
function calculateVATPrice(ebp, spc) {
	var cv = 0;
	for (var z = 0; z < cat_types_data.length; z++) {
		if (cat_types_data[z].id == spc) {
			var vatv = cat_types_data[z].vatp;
			cv = ((ebp * vatv) / 100);
		}
	}
	return cv;
}

function calculateGSTPrice(ebp, spc) {
	var cv = 0;
	for (var z = 0; z < cat_types_data.length; z++) {
		if (cat_types_data[z].id == spc) {
			var vatv = cat_types_data[z].gstp;
			cv = ((ebp * vatv) / (100 + +vatv));
			cv = cv / 2;
		}
	}
	return cv;
}

function findGSTPercent(ebp, spc) {
	var cv = 0;
	for (var z = 0; z < cat_types_data.length; z++) {
		if (cat_types_data[z].id == spc) {
			var cv = cat_types_data[z].gstp;
		}
	}
	return cv;
}
function validateProduct2(text) {
	if (page_data.length != 0) {
		for (var i = 0; i < page_data.length; i++) {
			var spd = fetchProductDetails(cat_types_data,page_data[i].prod_code);
			if (spd.localeCompare(text) == 0) {
				return false;
			}
		}
	}
	return true;
}

function validateProduct() {

	var e2 = document.getElementsByClassName("form-control input_field select_dropdown ic");
	if (e2.length == 1) {
		var e = document.getElementById("pid");
		var text = e.options[e.selectedIndex].text;
		var flag = validateProduct2(text);
		return flag;
	} else if (e2.length > 1) {
		var flag = false;
		var h = 1;
		for (i = 0; i < e2.length - 1; i++) {
			var e3 = e2[i].options[e2[i].selectedIndex].text;
			var flag = validateProduct2(e3);
			if (flag) {
				for (j = 0; j < e2.length - 1; j++) {
					var e4 = e2[j + 1].options[e2[j + 1].selectedIndex].text;
					flag = validateProduct2(e4);
					var k = 0;
					if (flag) {
						for (k; k < h && k < e2.length - 1; k++) {
							var e5 = e2[k].options[e2[k].selectedIndex].text;
							if (e5.localeCompare(e4) == 0) {
								flag = false;
								return flag;
							}
						}
						h = h + 1;
					}
					if (!flag)
						return flag;
				}
			}
			return flag;
		}

	}
}

/*function submitPinNumber(formobj) {
    var pinNO = formobj.pinNO.value.trim();
    var enteredPin = formobj.enteredPin.value.trim();
    var ems="";
    if(!(enteredPin.length>0))
            ems= ems+"Please Enter PIN NUMBER<br>";
    else if(!validatePinNumber(enteredPin,4,4))
            ems = ems+"PIN NUMBER Must Be Valid  4 DIGIT Number <br>";
    else if(enteredPin!==pinNO)
            ems= ems+"Please Enter Valid PIN NUMBER<br>";
    else if(enteredPin != formobj.enteredPin.value)
    		ems= ems+ "No spaces are allowed in pin Number\n";
    else if(enteredPin==pinNO){
            document.getElementById('myModalrefilPin').style="none";
            document.getElementById('pageDiv').style="block";
    }
    if (ems.length > 0) {
    	document.getElementById("dialog-1").innerHTML = ems;
		alertdialogue();    
		return;
    }
}*/

/*function submitPinNumber() {
	var pinNO = document.getElementById("pinNO").value.trim();
	var enteredPin = document.getElementById("enteredPin").value.trim();
	var ems="";
	if(!(enteredPin.length>0))
		ems= ems+"Please Enter PIN NUMBER<br>";
	else if(!validatePinNumber(enteredPin,4,4))
		ems = ems+"PIN NUMBER Must Be Valid  4 DIGIT Number <br>";
	else if(enteredPin !== pinNO)
		ems= ems+"Please Enter Valid PIN NUMBER<br>";
	else if(enteredPin != document.getElementById("enteredPin").value)
		ems= ems+ "No spaces are allowed in pin Number\n";
	else if(enteredPin == pinNO){
		document.getElementById('myModalrefilPin').style.display="none";
		document.getElementById('pageDiv').style.display="block";
	}
	if (ems.length > 0) {
		document.getElementById("dialog-1").innerHTML = ems;
		alertdialogue();    
		return;
	}
}*/

