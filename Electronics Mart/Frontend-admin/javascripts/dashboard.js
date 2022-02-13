function user_name() {
    n = localStorage.getItem("name");
    document.getElementById("user_name").innerText = n;
  }
  user_name();