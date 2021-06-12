<%@page import = "Project.ConnectionProvider" %>
<%@page import = "java.sql.*" %>
<%
String id = request.getParameter("id");
String name = request.getParameter("name");
String father = request.getParameter("father");
String mother = request.getParameter("mother");
String contactnumber = request.getParameter("contactnumber");

String email = request.getParameter("email");

String address = request.getParameter("address");
try{
	Connection conn = ConnectionProvider.getCon();
	PreparedStatement ps = conn.prepareStatement("update donor set name=?, father=?,mother=?,contactnumber=?,email=?,bloodgroup=?,address=? where id=?" );
	ps.setString(1,id);
    ps.setString(2,name);
    ps.setString(3,father);
    ps.setString(4,mother);
    ps.setString(5,contactnumber);
    
    ps.setString(6,email);
    
    ps.setString(7,address);
    ps.executeUpdate();
    response.sendRedirect("editDeleteList.jsp?msg = valid");
}
catch(Exception e){
	response.sendRedirect("addNewDonor.jsp?msg = invalid");
}

%>