<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	int board_num=(Integer)request.getAttribute("board_num");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>MVC 게시판</title>
<style>
body{

	background-image: url("././img/bg.jpg");
	font-family: 'Noto Sans KR', sans-serif;
	color: #685449;
}

	#passForm{
		width:400px;
		margin:auto;
		border : 1px solid orange;
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
<section id = "passForm">
<form name="deleteForm" action="AdminBoardDeletePro.bo?board_num=<%=board_num %>" 
	method="post">
<input type = "hidden" name = "page" value = "<%=nowPage %>"/>
<table>
<tr>
	<td>
		<label>글 비밀번호 : </label>
	</td>
	<td>
		<input name="BOARD_PASS" type="password">
	</td>
</tr>
<tr>
	<td>
		<input  class="btn" type="submit" value = "삭제"/>
		&nbsp;&nbsp;
		<input class="btn" type = "button" value = "돌아가기" onClick ="javascript:history.go(-1)"/>
	</td>
</tr>
</table>
</form>
</section>
</body>
</html>