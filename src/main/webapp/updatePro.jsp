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
	<% 	request.setCharacterEncoding("UTF-8");%>
		<jsp:useBean id="m" class="beans.Member"/>	
		<jsp:setProperty property="*" name="m"/>이름은 <jsp:getProperty property="name" name="m"/>
	<%
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/myWeb";
		String driver = "com.mysql.jdbc.Driver";
		String sql=null;
		PreparedStatement ps =  null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root", "1111");
			sql = "update member set password=?, name=?, age=?, gender=?, email=? where id = ?";			
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getPassword());
			ps.setString(2, m.getName());
			ps.setInt(3, m.getAge());
			ps.setString(4, m.getGender());
			ps.setString(5, m.getEmail());
			ps.setString(6, m.getId());
			int a = ps.executeUpdate();
			if(a == 1){
				session.setAttribute("name", m.getName());
				response.sendRedirect("index.jsp?page=infoView");
			}
			else{ %>
				<script>
					history.go(-1);
				</script>		
				
	<%		}
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
	
	%>
</body>
</html>