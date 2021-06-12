<%@page import = "Project.ConnectionProvider" %>
<%@page import = "java.sql.*" %>


<%@include file="header.html"%>
<html>
<head>
<style>
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 95%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>
<br>
<center>
<table id = "customers">
<tr>
<th>Name</th>
<th>Contact Number</th>
<th>Email</th>
<th>Blood Group</th>
<th>Done</th>
<th>Delete</th>
</tr>
<tr>
<%
try {
	Connection conn = ConnectionProvider.getCon();
	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery("select *from bloodrequest where status='pending'");
	while(rs.next())
	{

%>
<td><%=rs.getString(1) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getString(3) %></td>
<td><%=rs.getString(4) %></td>
<td><a href = "requestForBloodDone.jsp?contactnumber=<%=rs.getString(2) %>">Done</a></td>
<td><a href = "requestForBloodDelete.jsp?contactnumber=<%=rs.getString(2) %>">Delete</a></td>
</tr>
<%
	}
}
catch(Exception e){
	System.out.println(e);
}
%>



</center>
<br>
<br>
<br>
<br>
<h3><center>All Rights Reserved @ Eclipse Hospital</center></h3>
</body>
</html>