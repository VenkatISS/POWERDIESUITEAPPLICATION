var tbody = document.getElementById('page_data_table_body');
for(var f=page_data.length-1; f>=0; f--){
	if(page_data[f].deleted==0) {
		var tblRow = tbody.insertRow(-1);
		tblRow.style.height="20px";
   		tblRow.align="left";
   		var spd = fetchProductName(cat_types_data, page_data[f].prod_code);
   	//	var spd =  page_data[f].prod_code;
   	//	document.getElementById('proid').value=spd;
	   	var rd = new Date(page_data[f].effective_date);
   		tblRow.innerHTML = "<td>" + spd +  "</td>" + "<td>" + rawmid[page_data[f].units] + "</td>"+ "<td>" + unitstype[page_data[f].units] + "</td>" + 
   			"<td>" + page_data[f].gstp +  "</td>" + "<td>" + page_data[f].units +  "</td>" + /*"<td>" + page_data[f].security_deposit +  "</td>" +*/ 
   			"<td>" + page_data[f].opening_stock +  "</td>" + "<td>" + page_data[f].purchase_price +  "</td>" + 
   			/*"<td>" + page_data[f].selling_rate +  "</td>"+*/
   			"<td>" + rd.getDate()+"-"+months[rd.getMonth()]+"-"+rd.getFullYear() + "</td>" + 
   			"<td align='center'><img src='images/delete.png' onclick='deleteItem("+page_data[f].id+")'></td>";
	}
};

function addRow() {
	document.getElementById('page_add_table_div').style.display="block";
	document.getElementById('savediv').style.display="inline";
    $("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');

    var trcount = document.getElementById('page_add_table_body').getElementsByTagName('tr').length;
    if(trcount>0) {
    	var trv=document.getElementById('page_add_table_body').getElementsByTagName('tr')[trcount-1];
    	var saddv=trv.getElementsByClassName('sadd');
    	var eaddv=trv.getElementsByClassName('eadd');
    
    	var res=checkRowData(saddv,eaddv);
    	if(res == false){
    		//alert("Please enter all the values in current row and then add next row");
    		document.getElementById("dialog-1").innerHTML = "Please enter all the values in current row and then add next row";
    		alertdialogue();
    		return;
    	}		
    }
    
	var ele = document.getElementsByClassName("ic");
	if(ele.length < 4){
		var tbody = document.getElementById('page_add_table_body');
		var newRow = tbody.insertRow(-1);

		var a = newRow.insertCell(0); 
		var b = newRow.insertCell(1); 
		var c = newRow.insertCell(2);
		var d = newRow.insertCell(3);
		var e = newRow.insertCell(4);
		var f = newRow.insertCell(5);
		var g = newRow.insertCell(6);
		var h = newRow.insertCell(7);
		/*var i = newRow.insertCell(8);
		var j = newRow.insertCell(9);*/

	
	//	a.innerHTML = "<td valign='top' height='4' align='center'><select name='rawmid' id='rawmid' onchange='checkForDeletedProducts(this)' class='form-control input_field select_dropdown ic sadd'>"+ctdatahtml+"</select></td>";
	//	a.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='rawmid' id='rawmid' class='form-control input_field eadd' maxlength='8' placeholder='RAW MATERIALS'>" +
		a.innerHTML = "<td valign='top' height='4' align='center'>" +
		"<SELECT NAME='rawmid' ID='rawmid' class='form-control input_field select_dropdown sadd'>"
			+ "<OPTION VALUE='0'>SELECT</OPTION>"
			+ "<OPTION VALUE='11'>CU</OPTION>"
			+ "<OPTION VALUE='12'>SILVER</OPTION>"
			+ "<OPTION VALUE='13'>STEEL</OPTION>"
			+ "<OPTION VALUE='14'>NICKLE</OPTION>"
			+ "<OPTION VALUE='15'>IRON</OPTION>"
			+ "<OPTION VALUE='16'>ZINK</OPTION>"
			+ "<OPTION VALUE='17'>CARBON</OPTION>"
			+ "<OPTION VALUE='18'>ALUMINIUM</OPTION>"
			+ "<OPTION VALUE='19'>RUBBER</OPTION>"
			+ "</SELECT></td></td>";
		b.innerHTML = "<td valign='top' height='4' align='center'><SELECT NAME='unitstype' ID='unitstype' class='form-control input_field select_dropdown sadd'>"
			+ "<OPTION VALUE='0'>SELECT</OPTION>"
			+ "<OPTION VALUE='1'>MM</OPTION>"
			+ "<OPTION VALUE='2'>CM</OPTION>"
			+ "<OPTION VALUE='3'>SQ CM</OPTION>"
			+ "<OPTION VALUE='4'>INCHES</OPTION>"
			+ "<OPTION VALUE='5'>FEETS</OPTION>"
			+ "<OPTION VALUE='6'>SQ FEETS</OPTION>"
			+ "</SELECT></td>";
		c.innerHTML = "<td valign='top' height='4' align='center'><SELECT NAME='rgst' ID='rgst' class='form-control input_field select_dropdown sadd'>"
			+ "<OPTION VALUE='-1'>SELECT</OPTION>"
			+ "<OPTION VALUE='0'>0</OPTION>"
			+ "<OPTION VALUE='5'>5</OPTION>"
			+ "<OPTION VALUE='12'>12</OPTION>"
			+ "<OPTION VALUE='18'>18</OPTION>"
			+ "<OPTION VALUE='28'>28</OPTION>"
			+ "<OPTION VALUE='NA'>NA</OPTION>"
			+ "</SELECT></td>";
		d.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='units' id='units' class='form-control input_field eadd' value='1' readonly></td>";
	//	e.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='rsd' id='rsd' class='form-control input_field eadd' value='0.00' maxlength='8'></td>";
		e.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='ops' id='ops' class='form-control input_field eadd' maxlength='4' placeholder='OPENING STOCK'></td>";
		f.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='lrate' id='lrate' class='form-control input_field eadd' maxlength='4' placeholder='LANDING RATE'></td>";
	//	h.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='srate' id='srate' class='form-control input_field eadd' maxlength='4' placeholder='SELLING RATE'></td>";
		g.innerHTML = "<td valign='top' height='4' align='center'><input type=date name='rd' id='rd' class='form-control input_field eadd' placeholder='DD-MM-YYYY'></td>";
		h.innerHTML = "<td valign='top' height='4' align='center'><img src='images/delete.png' onclick='doRowDelete(this)'></td>";
	}else {
		//alert("Please Save the Records and ADD Again");
		document.getElementById("dialog-1").innerHTML = "Please Save the Records and ADD Again";
		alertdialogue();
	}
}

function checkForDeletedProducts(eqpobj) {
	var formobj = document.getElementById('page_add_form');
	var clsname = eqpobj.className;
	var ele = document.getElementsByClassName(clsname);
	if(ele.length==1){
		var prod = formobj.rawmid.value.trim();
		var sprod = document.getElementById("rawmid").options;
		var sunits = document.getElementById("unitstype").options;
		var sgstp = document.getElementById("rgst").options;
		for(var d=0;d<del_equipment_data.length;d++) {
			if(del_equipment_data[d].prod_code == parseInt(prod)) {

				// FOR PRODUCT
				var prodval = del_equipment_data[d].prod_code;
				enableSelect(sprod,sprod.length);		
				formobj.rawmid.selectedIndex=prodval;
				disableSelect(sprod,sprod.length);
				
				// FOR UNITS
				var units = del_equipment_data[d].units;
				enableSelect(sunits,sunits.length);					
				if(units==1){
					formobj.unitstype.selectedIndex=1;
					disableSelect(sunits,sunits.length);
				}else if(units==2){
					formobj.unitstype.selectedIndex=2;
					disableSelect(sunits,sunits.length);
				}
					
				//FOR GST PERCENT
				var gstp = del_equipment_data[d].gstp;
				enableSelect(sgstp,sgstp.length);
				switch(gstp){
					case "0" :
						formobj.rgst.selectedIndex=1;
						disableSelect(sgstp,sgstp.length);
						break;
					case "5" :
						formobj.rgst.selectedIndex=2;
						disableSelect(sgstp,sgstp.length);
						break;
					case "12" :
						formobj.rgst.selectedIndex=3;
						disableSelect(sgstp,sgstp.length);
						break;
					case "18" :
						formobj.rgst.selectedIndex=4;
						disableSelect(sgstp,sgstp.length);
						break;
					case "28" :
						formobj.rgst.selectedIndex=5;
						disableSelect(sgstp,sgstp.length);
						break;
					case "NA" :
						formobj.rgst.selectedIndex=6;
						disableSelect(sgstp,sgstp.length);
						break;
				}
					
				
				// For FULLS
				var fulls = del_equipment_data[d].cs_fulls;
				formobj.ops.value = fulls;
				document.getElementById("ops").setAttribute("readOnly","true");
					
				// For EMPTIES
				var empties = del_equipment_data[d].cs_emptys;
				formobj.lrate.value = empties;
				document.getElementById("lrate").setAttribute("readOnly","true");
				
				break;
			}else {
				// FOR UNITS
//				formobj.unitstype.selectedIndex=0;
				enableSelect(sunits,sunits.length);
					
				//FOR GST PERCENT
//				formobj.rgst.selectedIndex=0;
				//if(!(prod==10 || prod==11 || prod==12))
				enableSelect(sgstp,sgstp.length);
				
			
					
				// For FULLS
//				formobj.ops.value = "";
				document.getElementById("ops").readOnly = false;
				
				// For EMPTIES
//				formobj.lrate.value = "";
				document.getElementById("lrate").readOnly = false;
				
				// For SECURITY DEPOSIT
//				formobj.esd.value = "";
				
				// For EFFECTIVE DATE
//				formobj.rd.value = "";
					
			}
		}
	}else if(ele.length>1){
		for(var e=0;e<ele.length;e++){
			var prod = formobj.rawmid[e].value.trim();
			var sprod = formobj.rawmid[e].options;
			var sunits = formobj.unitstype[e].options;
			var sgstp = formobj.rgst[e].options;
			for(var d=0;d<del_equipment_data.length;d++){
				if(del_equipment_data[d].prod_code == parseInt(prod)) { 
					// FOR PRODUCT
					var prdval = del_equipment_data[d].prod_code;
					enableSelect(sprod,sprod.length);		
					formobj.rawmid[e].selectedIndex=prdval;
					disableSelect(sprod,sprod.length);
					
					// FOR UNITS
					var units = del_equipment_data[d].units;
					enableSelect(sunits,sunits.length);					
					if(units==1){
						formobj.unitstype[e].selectedIndex=1;
						disableSelect(sunits,sunits.length);
					}else if(units==2){
						formobj.unitstype[e].selectedIndex=2;
						disableSelect(sunits,sunits.length);
					}
					
					//FOR GST PERCENT
					var gstp = del_equipment_data[d].gstp;
					enableSelect(sgstp,sgstp.length);
					switch(gstp){
						case "0" :
							formobj.rgst[e].selectedIndex=1;
							disableSelect(sgstp,sgstp.length);
							break;
						case "5" :
							formobj.rgst[e].selectedIndex=2;
							disableSelect(sgstp,sgstp.length);
							break;
						case "12" :
							formobj.rgst[e].selectedIndex=3;
							disableSelect(sgstp,sgstp.length);
							break;
						case "18" :
							formobj.rgst[e].selectedIndex=4;
							disableSelect(sgstp,sgstp.length);
							break;
						case "28" :
							formobj.rgst[e].selectedIndex=5;
							disableSelect(sgstp,sgstp.length);
							break;
						case "NA" :
							formobj.rgst[e].selectedIndex=6;
							disableSelect(sgstp,sgstp.length);
							break;
					}
					
				
					// For FULLS
					var fulls = del_equipment_data[d].cs_fulls;
					formobj.ops[e].value = fulls;
					formobj.ops[e].setAttribute("readOnly","true");
					
					// For EMPTIES
					var empties = del_equipment_data[d].cs_emptys;
					formobj.lrate[e].value = empties;
					formobj.lrate[e].setAttribute("readOnly","true");
					
					break;
				}else {
					// FOR UNITS
//					formobj.unitstype[e].selectedIndex=0;
					enableSelect(sunits,sunits.length);
					//FOR GST PERCENT
//					formobj.rgst[e].selectedIndex=0;
				//	if(!(prod==10 || prod==11 || prod==12))
					enableSelect(sgstp,sgstp.length);
					
					// For FULLS
//					formobj.ops[e].value = "";
					formobj.ops[e].readOnly = false;
					// For EMPTIES
//					formobj.lrate[e].value = "";
					formobj.lrate[e].readOnly = false;
				}
			}
		}		
	}
}

function saveData(obj){
	var formobj = document.getElementById('page_add_form');
	var ems = "";	
	 var proid = document.getElementById('answerInputHidden').value.trim(); // here we will get CUST ID
     
 	if(proid != ""){

 	var proname = document.getElementById('answerInput').value.trim(); // here we will get CUST NAME

	if(document.getElementById("rawmid") != null){
		
		var elements = document.getElementsByClassName("ic");
       
		if(elements.length==1) {
			var rawmid = formobj.rawmid.selectedIndex;
			var unitstype = formobj.unitstype.selectedIndex;
			var rgst = formobj.rgst.selectedIndex;
			var units = formobj.units.value.trim();
		//	var rsd= formobj.rsd.value;
			var ops = formobj.ops.value.trim();
			var lrate = formobj.lrate.value.trim();
		//	var srate = formobj.srate.value.trim();

			var rd = formobj.rd.value.trim();

		//	formobj.rsd.value = formobj.rsd.value.trim();
			formobj.ops.value = formobj.ops.value.trim();
			formobj.lrate.value = formobj.lrate.value.trim();
		//	formobj.srate.value = formobj.srate.value.trim();

			formobj.rd.value = formobj.rd.value.trim();

			if (checkDateFormate(rd)) {
				var ved = dateConvert(rd);
				formobj.rd.value = ved;
				rd = ved;
			}
			
		//	ems = validateEntries(rawmid,unitstype,rgst,rsd,ops,lrate,srate,rd);
			ems = validateEntries(rawmid,unitstype,rgst,ops,lrate,rd);

		}else if (elements.length>1){
			for(var i=0; i<elements.length; i++){
				var rawmid = formobj.rawmid[i].selectedIndex;
				var unitstype = formobj.unitstype[i].selectedIndex;
				var rgst = formobj.rgst[i].selectedIndex;
			//	var rsd= formobj.rsd[i].value.trim();
				var ops = formobj.ops[i].value.trim();
				var lrate = formobj.lrate[i].value.trim();
			//	var srate = formobj.srate[i].value.trim();

				var rd = formobj.rd[i].value.trim();

				if (checkDateFormate(rd)) {
					var ved = dateConvert(rd);
					formobj.rd[i].value = ved;
					rd = ved;
				}
				
			//	formobj.rsd[i].value = formobj.rsd[i].value.trim();
				formobj.ops[i].value = formobj.ops[i].value.trim();
				formobj.lrate[i].value = formobj.lrate[i].value.trim();
			//	formobj.srate[i].value = formobj.srate[i].value.trim();

				formobj.rd[i].value = formobj.rd[i].value.trim();
				
			//	ems = validateEntries(rawmid,unitstype,rgst,rsd,ops,lrate,srate,rd);
				ems = validateEntries(rawmid,unitstype,rgst,ops,lrate,rd);

				if(ems.length>0)
					break;
			}			
		}
	}else {
		//alert("Please Add Data");
		document.getElementById("dialog-1").innerHTML = "Please Add Data";
		alertdialogue();
		return;
	}
 	}else{
		document.getElementById("dialog-1").innerHTML = "Please SELECT/ENTER A PRODUCT";
		alertdialogue();
		return;
 	}
	
	if(ems.length>0) {
		//alert(ems);
		document.getElementById("dialog-1").innerHTML = ems;
		alertdialogue();
		return;
	}

	document.getElementById('pro_name').value = proname;
  /*  if((parseInt(proid)!=0) && (custarr.indexOf(parseInt(proid))) == -1)
            document.getElementById('answerInputHidden').value = 0;*/
	var objId = obj.id;
	document.getElementById(objId).disabled = true;
	formobj.submit();
}

/*function deleteItem(id) {
	if (confirm("ARE YOU SURE YOU WANT TO DELETE?") == true) {
		var formobj = document.getElementById('page_add_form');
		formobj.actionId.value = "3103";
		formobj.itemId.value = id;
		formobj.submit();
	}
}
*/
function changeSaleTypeBasedOnProduct() {
    $(':radio:not(:checked)').attr('disabled', false);
}

function deleteItem(id){
	 $("#myDialogText").text("Are You Sure You Want To Delete?");
	 var formobj = document.getElementById('page_add_form');
	 confirmDialogue(formobj,3103,id);
}

function validateProduct2(text) {
	if (page_data.length != 0) {
		for (var i = 0; i < page_data.length; i++) {
			var spd = fetchProductName(cat_types_data,page_data[i].prod_code);
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
		var e = document.getElementById("rawmid");
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


//function validateEntries(rawmid,unitstype,rgst,rsd,ops,lrate,srate,rd){
	function validateEntries(rawmid,unitstype,rgst,ops,lrate,rd){

	rawmid,item,units,gstp,cstehno,sd,fs,es,rd
	var formobj = document.getElementById('page_add_form');
	var errmsg = "";

	if (!validateProduct())
		errmsg = errmsg + "The Product has been already selected Please select another Product<br>";
	else {
		if (!(rawmid > 0))
			errmsg = errmsg + "Please Select RAW MATERIALS<br>";
		
		if (!(unitstype > 0))
			errmsg = errmsg + "Please Select UNITS<br>";

		if (!(rgst > 0))
			errmsg = errmsg + "Please Select GST%<br>";
		
		/*if (!(rsd.length > 0))
			errmsg = errmsg + "Please Enter SECURITY DEPOSIT<br>";
		else if (validateDot(rsd))
			errmsg = errmsg + "SECURITY DEPOSIT Must Contain Atleast One Number. <br>";
		else if (!isDecimalNumber(rsd))
			errmsg = errmsg + "SECURITY DEPOSIT Must Contain Only Numerics. <br>";
		else if ((!validateDecimalNumberMinMax(rsd, -1, 1000000)))
			errmsg = errmsg
				+ "SECURITY DEPOSIT It Must Be Numeric And <999999.99 And >=0.00<br>";
		else
			formobj.rsd.value = round(parseFloat(rsd), 2);*/

		if (!(ops.length > 0))
			errmsg = errmsg + "Please Enter FULLS if not Enter 0.<br>";
		else if ((!validateNumberMinMax(ops, -1, 10000)))
			errmsg = errmsg + "FULLS Must Be Numeric And <=9999 And >=0<br>";


		
		if (!(lrate.length > 0))
			errmsg = errmsg + "Please Enter LANDING RATE<br>";
		else if (validateDot(lrate))
			errmsg = errmsg + "LANDING RATE Must Contain Atleast One Number. <br>";
		else if (!isDecimalNumber(lrate))
			errmsg = errmsg + "LANDING RATE Must Contain Only Numerics. <br>";
		else if ((!validateDecimalNumberMinMax(lrate, -1, 1000000)))
			errmsg = errmsg
				+ "LANDING RATE It Must Be Numeric And <999999.99 And >=0.00<br>";
		else
			formobj.lrate.value = round(parseFloat(lrate), 2);
		
	/*	if (!(srate.length > 0))
			errmsg = errmsg + "Please Enter LANDING RATE<br>";
		else if (validateDot(srate))
			errmsg = errmsg + "LANDING RATE Must Contain Atleast One Number. <br>";
		else if (!isDecimalNumber(srate))
			errmsg = errmsg + "LANDING RATE Must Contain Only Numerics. <br>";
		else if ((!validateDecimalNumberMinMax(srate, -1, 1000000)))
			errmsg = errmsg
				+ "LANDING RATE It Must Be Numeric And <999999.99 And >=0.00<br>";
		else
			formobj.lrate.value = round(parseFloat(srate), 2);*/
		
		var vd = isValidDate(rd);
		var vfd = ValidateFutureDate(rd);
		if (!(rd.length > 0))
			errmsg = errmsg + "Please Enter EFFECTIVE DATE<br>";
		else if (vd != "false")
			errmsg = errmsg +"EFFECTIVE"+vd+"<br>";
		else if (vfd != "false") 
			errmsg = errmsg +"EFFECTIVE"+vfd+"<br>";
		else if(validateDayEndAdd(rd,dedate)){
	        errmsg = "EFFECTIVE DATE should be after DayEndDate<br>";
	        return errmsg;
		}
	 }
	 return errmsg;
}
