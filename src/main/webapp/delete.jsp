<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	String id =(String)session.getAttribute("id");

	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/myweb";
	String driver = "com.mysql.jdbc.Driver";
	String sql=null;
	PreparedStatement ps =  null;	
	
	try{
		Class.forName(driver);
		con = DriverManager.getConnection(url, "root", "1234");
		sql = "delete from member where id = ?";		
		ps = con.prepareStatement(sql);		
		ps.setString(1, id);
		int a = ps.executeUpdate();
		if(a==1){
			out.println("<script>");
			out.println("alert('탈퇴되었습니다. Bye!')");
			session.invalidate();
			out.println("location.href='indexT.jsp'");
			out.println("</script>");
		}		
	}	
	catch(SQLException e){
		e.printStackTrace();
	}

%>
		


</body>
</html>