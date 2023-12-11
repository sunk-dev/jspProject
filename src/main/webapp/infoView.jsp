<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보관리 페이지</title>
<style type="text/css">

	#loginArea{
		position:relative;
		top:30%;
		width : 400px;
		margin : auto;
		border : 1px solid black;
	}
	table {
		margin : auto;
	}
	td{
		text-align : center;
	}
		.btn{

background-color:#dcd7d3;
outline: none;
	border-width: 0;
	background-color:#dcd7d3;

}

</style>
</head>
<body>

<% 	
	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/myweb";
	String driver = "com.mysql.jdbc.Driver";
	String sql=null;
	PreparedStatement ps =  null;
	ResultSet rs = null;
	String id = (String)session.getAttribute("id");
	
	try{
		Class.forName(driver);
		con = DriverManager.getConnection(url, "root", "1234");
		sql = "select * from member where id = ?";		
		ps = con.prepareStatement(sql);		
		ps.setString(1, id);
		rs = ps.executeQuery();
		rs.next();		
	}	
	catch(SQLException e){
		e.printStackTrace();
	}
%>
<section id="loginArea">
	<form action="updatePro.jsp" method="post">
		<table>
			<tr>
				<td><label>아이디 : </label></td>
				<td><input type="text" name="id" value = <%=rs.getString("id") %> readonly ></td>
			</tr>
			<tr>
				<td><label>비밀번호 :</label></td>
				<td><input type="password" name="password" value = <%=rs.getString("password")%> ></td>
			</tr>
			<tr>
				<td><label>이름 : </label></td>
				<td><input type="text" name="name" value = <%=rs.getString("name") %>></td>
			</tr>
			<tr>
				<td><label>나이 : </label></td>
				<td><input type="number" name="age"  value = <%=rs.getInt("age") %>></td>
			</tr>
			<tr>
				<td><label>성별 : </label></td>
				<% if(rs.getString("gender").equals("남자")){ %>
				<td><input type="radio" name="gender" value="남자" checked>남자
					<input type="radio" name="gender" value="여자">여자</td>					
				<%}else {%>						
				<td><input type="radio" name="gender" value="남자" >남자
				<input type="radio" name="gender" value="여자" checked>여자</td>		
				<% } %>		
			</tr>
			<tr>
				<td><label>이메일 : </label></td>
				<td><input type="email" name="email" value = <%=rs.getString("email") %>></td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn" type="submit" value="회원정보 수정"> 
								<input class="btn" type="button" value="회원탈퇴" onClick="location.href='delete.jsp'"></td>
			</tr>
		</table>
	</form>
</section>


</body>
</html>