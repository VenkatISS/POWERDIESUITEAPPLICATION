var tbody = document.getElementById('user_data_table_body');
for(var f=user_data.length-1; f>=0; f--){
   var tblRow = tbody.insertRow(-1);
   tblRow.style.height="20px";
   tblRow.align="left";
   tblRow.innerHTML = '<td>' + user_data[f].userId +'</td>'+
   		'<td>'+ user_data[f].userName +'</td>'+
   		'<td>'+ user_data[f].passCode + '</td>'+
   		'<td>'+ user_data[f].userMobile + '</td>'+
   		'<td>'+ user_data[f].userEmail + '</td>'+
   		'<td>'+ user_data[f].userAddress + '</td>'+
   		'<td valign="top" height="4" align="center"><img src="images/delete.png" class="fa fa-trash-o" onclick="deleteItem('+user_data[f].id+')"></td>'	   
};
 
function addRow() {
	document.getElementById('myDIV').style.display = 'block';
	document.getElementById('savediv').style.display="inline";
    $("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');

    var trcount = document.getElementById('user_add_table_body').getElementsByTagName('tr').length;
    if(trcount>0){
    	var trv=document.getElementById('user_add_table_body').getElementsByTagName('tr')[trcount-1];
    	var saddv=trv.getElementsByClassName('sadd');
    	var eaddv=trv.getElementsByClassName('eadd');
    
    	var res=checkRowData(saddv,eaddv);
    	if(res == false){
    		document.getElementById("dialog-1").innerHTML = "Please enter all the values in current row and then add next row";
    		alertdialogue();
    		//alert("Please enter all the values in current row and then add next row");
    		return;
    	}		
    }

	var ele = document.getElementsByClassName("udc");
	if(ele.length < 4){
		var tbody = document.getElementById('user_add_table_body');
		var newRow = tbody.insertRow(-1);

		var a = newRow.insertCell(0);
		var b = newRow.insertCell(1);
		var c = newRow.insertCell(2);
		var d = newRow.insertCell(3);
		var e = newRow.insertCell(4);
		var f = newRow.insertCell(5);
		var g = newRow.insertCell(6);


		a.innerHTML = "<td><INPUT TYPE=text NAME='u_code' ID='u_code' class='form-control input_field udc eadd' size='8' maxlength='6' placeholder='User Id'></td>";
		b.innerHTML = "<td><INPUT TYPE=text NAME='u_name' ID='u_name' class='form-control input_field eadd' size='8' maxlength='30' placeholder='User Name'></td>";
		c.innerHTML = "<td><INPUT TYPE=password NAME='u_pwd' ID='u_pwd' class='form-control input_field eadd' size='8' maxlength='8' placeholder='User Password'></td>";
		d.innerHTML = "<td><INPUT TYPE=text NAME='u_mobile' ID='u_mobile' class='form-control input_field eadd' size='8' maxlength='10' placeholder='User Mobile'></td>";
		e.innerHTML = "<td><INPUT TYPE=text NAME='u_email' ID='u_email' class='form-control input_field eadd' size='8' maxlength='30' placeholder='User Email'></td>";
		f.innerHTML = "<td><INPUT TYPE=text NAME='u_address' ID='u_address' class='form-control input_field eadd' size='8' maxlength='30' placeholder='User Address'></td>";

		g.innerHTML = "<td><img src='images/delete.png' onclick='doRowDelete(this)'></td>";
	}else{
		document.getElementById("dialog-1").innerHTML = "Please Save the Records and ADD Again";
		alertdialogue();
		//alert("Please Save the Records and ADD Again");
	}
}

function saveData(obj) {
	var formobj = document.getElementById('user_data_form');
	var ems = "";

	if (document.getElementById("u_code") != null) {

		var elements = document.getElementsByClassName("udc");
		if (elements.length == 1) {
			var eucode = formobj.u_code.value.trim();
			var euname = formobj.u_name.value.trim();
			var eumobile = formobj.u_mobile.value.trim();
			var euemail = formobj.u_email.value.trim();
			var eupwd = formobj.u_pwd.value.trim();
			var euadds = formobj.u_address.value.trim();

			ems = validateSDEntries(eucode, euname, eumobile, euemail, eupwd,euadds);
		} else if (elements.length > 1) {
			for (var i = 0; i < elements.length; i++) {
				var eucode = formobj.u_code[i].value.trim();
				var euname = formobj.u_name[i].value.trim();
				var euemail = formobj.u_email[i].value.trim();
				var eumobile = formobj.u_mobile[i].value.trim();
				var eupwd = formobj.u_pwd[i].value.trim();
				var euadds = formobj.u_address[i].value.trim();

				ems = validateSDEntries(eucode,euname,eumobile, euemail,eupwd, euadds);
				if (ems.length > 0)
					break;
			}
		}
	} else {
		document.getElementById("dialog-1").innerHTML = "PLEASE ADD DATA";
		alertdialogue();
		//alert("PLEASE ADD DATA");
		return;
	}

	if (ems.length > 0) {
		document.getElementById("dialog-1").innerHTML = ems;
		alertdialogue();
		//alert(ems);
		return;
	}
	
	var objId = obj.id;
	document.getElementById(objId).disabled = true;
	formobj.submit();
}

function deleteItem(id){
	 $("#myDialogText").text("Are You Sure You Want To Delete?");
	 var formobj = document.getElementById('user_data_form');
	 confirmDialogue(formobj,3543,id);
}

function validateSDEntries(code,name,mobile,email,pwd,address) {
	var errmsg = "";

	for(var f=0; f<user_data.length; f++){
		   var ucode =user_data[f].adminId;
		   if(ucode==code)
				errmsg = "USER ID already exist.Please enter different USER ID<br>";
			   }
	
	if (!(code.length > 0))
		errmsg = "Please enter USER ID<br>";
	else if (validateDot(code))
		errmsg = errmsg + "Please Enter Valid USER ID.<br>";

	if (!(name.length > 0))
		errmsg = errmsg + "Please Enter NAME.<br>";
	else if (!(IsNameSpaceDot(name)))
		errmsg = errmsg + "Please Enter Valid NAME.<br>";
	
	if ((mobile != "")) {
		if (!(checkNumber(mobile)))
			errmsg = errmsg + "MOBILE NUMBER Must Contain Only Digits. <br>";
		else if (!(mobile.length == 10))
			errmsg = errmsg + "MOBILE NUMBER Must Contain 10 Digits. <br>";
	}
	
	if ((email == ""))
		errmsg = errmsg + "Please Enter EMAIL ID<br>";
	else if (!checkEmail(email))
		errmsg = errmsg + "Please Enter valid EMAIL ID<br>";
	
		
	if (!(pwd.length > 0))
		errmsg = errmsg + "Please Enter PASSWORD. <br>";

	if (!(address.length > 0))
		errmsg = errmsg + "Please Enter Address <br>";

	return errmsg;
}
