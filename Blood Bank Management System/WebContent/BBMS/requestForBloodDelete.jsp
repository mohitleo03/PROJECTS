<%@page import = "Project.ConnectionProvider" %>
<%@page import = "java.sql.*" %>
<%
String contactnumber = request.getParameter("contactnumber");
try{
	Connection conn = ConnectionProvider.getCon();
	Statement st = conn.createStatement();
	st.executeUpdate("delete from bloodrequest where contactnumber = '"+contactnumber+"'");
    response.sendRedirect("requestForBlood.jsp");
}
catch(Exception e){
	response.sendRedirect("requestForBlood.jsp");
}

%>