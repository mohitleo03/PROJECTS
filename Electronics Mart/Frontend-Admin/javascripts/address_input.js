function register(){
	var user = {
		name :localStorage.getItem("name"),
		email :localStorage.getItem("email"),
		pwd :localStorage.getItem("pass"),

		address : {
			Houseno:document.querySelector("#houseno").value,
			Landmark:document.querySelector("#landmark").value,
			City:document.querySelector("#city").value,
			State:document.querySelector("#state").value,
			Pincode:document.querySelector("#pincode").value,
		}
	}
	localStorage.setItem("name",undefined);
	localStorage.setItem("email",undefined);
	localStorage.setItem("pass",undefined);
	var http = new XMLHttpRequest();
	var data = JSON.stringify(user);
	var url = "https://electronics-mart-api.herokuapp.com/admin_register";
	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) {
			console.log(http.responseText);
			var json = JSON.parse(this.responseText);
			console.log(json.message);
			location.href = "activate.html";
		}
	}
	http.open('post',url,true);
	http.setRequestHeader('Content-Type','application/json');
	http.send(data);
}
function login(){
	location.href="login.html";
}
