function save(){
	name=document.querySelector("#name").value;
	email=document.querySelector("#email").value;
	pass=document.querySelector("#pwd").value;
	localStorage.setItem("name",name);
	localStorage.setItem("email",email);
	localStorage.setItem("pass",pass);
	confirm_pass = document.querySelector("#confirm_pwd").value;
	console.log(pass,confirm_pass)
	if(pass!=confirm_pass){
		window.alert("password didnt matched");
	}
	else{
		location.href = "address_input.html"
	}
}
function login(){
	location.href="login.html"
}