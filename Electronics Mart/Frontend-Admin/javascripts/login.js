function login() {
	var http = new XMLHttpRequest();
	var details = {
		"email" : document.querySelector("#email").value,
		"pwd" : document.querySelector("#pwd").value
	};
	var data = JSON.stringify(details);
	var url = "https://electronics-mart-api.herokuapp.com/admin_login";
	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) {
			console.log(http.responseText);
			var json = JSON.parse(this.responseText);
			localStorage.setItem("login",json.token);
			console.log(json.message);
			localStorage.setItem("name",json.name);
			location.href = "index.html";
		}
	}
	http.open('post',url,true);
	http.setRequestHeader('Content-Type','application/json');
	http.send(data);
}
function forgot_pass(){
	location.href = "forgot_pass.html";
}