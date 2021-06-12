<%@page import = "Project.ConnectionProvider" %>
<%@page import = "java.sql.*" %>
<%
String contactnumber = request.getParameter("contactnumber");
try{
	Connection conn = ConnectionProvider.getCon();
	PreparedStatement ps = conn.prepareStatement("update bloodrequest status='completed' where contactnumber=? ");
	ps.setString(1,contactnumber);
   
    ps.executeUpdate();
    response.sendRedirect("requestForBlood.jsp");
}
catch(Exception e){
	response.sendRedirect("requestForBlood.jsp");
}

%>