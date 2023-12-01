<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Session Login</title>
<style>
	#loginArea{
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
</style>
</head>
<body>
    <section id="loginArea">
	<form action="joinPro.jsp" method="post">
		<table>
			<tr>
				<td><label>아이디 : </label></td>
				<td><input type="text" name="id" ></td>
			</tr>
			<tr>
				<td><label>비밀번호 :</label></td>
				<td><input type="password" name="password" ></td>
			</tr>
			<tr>
				<td><label>이름 : </label></td>
				<td><input type="text" name="name" ></td>
			</tr>
			<tr>
				<td><label>나이 : </label></td>
				<td><input type="number" name="age" ></td>
			</tr>
			<tr>
				<td><label>성별 : </label></td>
				<td><input type="radio" name="gender" value="남자" checked>남자
					<input type="radio" name="gender" value="여자">여자</td>				
			</tr>
			<tr>
				<td><label>이메일 : </label></td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입"> <input
					type="reset" value="다시 작성"></td>
			</tr>
		</table>
	</form>
	</section>
</body>
</html>
