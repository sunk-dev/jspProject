<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

<style type="text/css">

.logo{
	position: absolute;
	top:-0.5%;
	left:3%;
	border: none;
}
.logo>img{
 width: 150px;
  height: 75px;
  object-fit: contain;

}
.menu2{
position: absolute;
	top:3%;
	left:20%;

}
</style>

</head>
<body>
	<a href="indexT.jsp">
	<div class="logo"><img  src="./img/coffeelogo.png"></div>
	</a>
<div class="menu2">
<%
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	String admin=(String)session.getAttribute("admin");
	if(id == null){
%>
	<a href="./indexT.jsp?page=login">로그인</a> 
	<a href="./indexT.jsp?page=join">회원가입</a>
<%
	}
	else{
		if(admin.equals("admin")){
%>	
	<%= name %>관리자님
	<%}else{
		%>
		<%= name %>님
	<% }%>
	<a href="./indexT.jsp?page=infoView">MyPage</a> 
	<a href="./logout.jsp">Logout</a> 
<%		
	}	
%>
</div>
</body>
</html>	