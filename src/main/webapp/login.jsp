<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Session Login</title>
<style>
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
    <section id="loginArea">
	<form action="loginPro.jsp" method="post">
		<table>
			<tr>
				<td><label for="id">아이디 : </label></td>
				<td><input type="text" name="id" id="id"></td>
			</tr>
			<tr>
				<td><label for="pass">비밀번호 :</label></td>
				<td><input type="password" name="password" id="pass"></td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn" type="submit" value="로그인"> <input
					class="btn" type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
	</section>
</body>
</html>
