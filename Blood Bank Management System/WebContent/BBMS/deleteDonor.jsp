<%@page import = "Project.ConnectionProvider" %>
<%@page import = "java.sql.*" %>
<%
String id = request.getParameter("id");
try {
	Connection conn = ConnectionProvider.getCon();
	Statement st = conn.createStatement();
	 st.executeQuery("delete from donor where id ='"+id+"'");
	 response.sendRedirect("editDeleteList.jsp?msg=deleted");
}
catch(Exception e){
	response.sendRedirect("editDeleteList.jsp?msg=invalid");
}


%>