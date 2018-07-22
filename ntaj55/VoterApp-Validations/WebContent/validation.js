  function validate(frm){
		 var name;
		 var  age;
		 alert("Client side form validations");
		 //empty the error messages
            document.getElementById("nameErr").innerHTML="";
            document.getElementById("ageErr").innerHTML="";
			document.getElementById("nameErr").style="color:red";
			document.getElementById("ageErr").style="color:red";
			//change vflag value(hidden box) to yes indication client java script is enabled
            frm.vflag.value="yes";
		 //read form data
		 name=frm.pname.value;
		 age=frm.page.value;
		 //write client side form validations
		 if(name==""){
		     document.getElementById("nameErr").innerHTML="Person name is mandatory";
			 frm.pname.focus();
			 return false;
		 }
		 if(age==""){
			  document.getElementById("ageErr").innerHTML="Person age is mandatory";
			 frm.page.focus();
			 return false;
		 }
		 else{
            if(isNaN(age)){
               document.getElementById("ageErr").innerHTML="Person age must numeric value";
			  frm.page.focus();
			  frm.page.value="";
			  return false;
			}//else
		 }//if
		 return true;
	 }//function