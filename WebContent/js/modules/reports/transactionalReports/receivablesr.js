//Construct Vendor Type html
vendatahtml = "<OPTION VALUE='-1' selected='selected'>SELECT</OPTION>";
if(cvo_data.length>0) {
	for(var z=0; z<cvo_data.length; z++){
		if(cvo_data[z].cvo_cat==1) {
			vendatahtml=vendatahtml+"<OPTION VALUE='"+cvo_data[z].id+"'>"+cvo_data[z].cvo_name+"</OPTION>";
		}
	}
}
document.getElementById("pitemsv").innerHTML="<select name='cs' id='cs' class='form-control input_field select_dropdown'>"+vendatahtml+"</select></td>";

if(rrtype == 1) {
	document.getElementById("data_div").style.display="block";
	var tb = "0.00";
	var custName = getCustomerName(cvo_data,custid);
	var l30d = "0.00";
	var l60g30d = "0.00";
	var g60d = "0.00";
	
	/*for(var i=0; i<page_data.length; i++) {
		if(page_data[i].daysType==1) {
			l30d = (+l30d) + (+page_data[i].invAmount);
		} else if (page_data[i].daysType==2) {
			l60g30d = (+l60g30d) + (+page_data[i].invAmount);
		} else if (page_data[i].daysType==3) {
			g60d = (+g60d) + (+page_data[i].invAmount);
		}
		tb = (+tb) + (+page_data[i].invAmount);
	}
	*/
	
		for(var i=0; i<page_data.length; i++) {
		                for(var z=0; z<cvo_data.length; z++){
		                        if(cvo_data[z].id==custid) {
		                if(page_data[i].daysType==1) {
		                        l30d = (+l30d) + (+parseFloat(page_data[i].invAmount)+parseFloat(cvo_data[z].obal));
		                } else if (page_data[i].daysType==2) {
		                        l60g30d = (+l60g30d) + (+parseFloat(page_data[i].invAmount)+parseFloat(cvo_data[z].obal));
		                } else if (page_data[i].daysType==3) {
		                        g60d = (+g60d) + (+parseFloat(page_data[i].invAmount)+parseFloat(cvo_data[z].obal));
		                }
		                tb = (+tb) + (+parseFloat(page_data[i].invAmount)+parseFloat(cvo_data[z].obal));
		        }
		                }
		        }
	document.getElementById("cnSpan").innerHTML=custName;
	document.getElementById("l30Span").innerHTML=l30d;
	document.getElementById("l60g30Span").innerHTML=l60g30d;
	document.getElementById("g60Span").innerHTML=g60d;
	document.getElementById("bSpan").innerHTML=tb;

	var sdate = new Date(sad);
	obdetails = sdate.getDate()+"-"+months[sdate.getMonth()]+"-"+sdate.getFullYear();		
	document.getElementById("rbSpan").innerHTML = obdetails;
}

function fetchReceivablesReport(formobj){
	var cvoid = formobj.cs;
	var date = formobj.ad.value;

	var cvoidv = cvoid.options[cvoid.selectedIndex].value;
	 if(!(cvoidv > 0)){
		 document.getElementById("dialog-1").innerHTML = "PLEASE SELECT CUSTOMER";
		 alertdialogue();
		 //alert("PLEASE SELECT CUSTOMER");
		document.getElementById("data_div").style.display="none";

		return;
	}else{
	formobj.submit();

	}
}