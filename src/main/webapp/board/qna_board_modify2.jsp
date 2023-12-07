<%@page import="beans.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	Board article = (Board)request.getAttribute("article");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<script type="text/javascript">
	
	function modifyboard(){
		modifyform.submit();
	}
</script>
<style type="text/css">

body{

	background-image: url("./img/bg.jpg");
	font-family: 'Noto Sans KR', sans-serif;
	color: #685449;

}
a {
	text-decoration: none;
	color: #685449;
	font-weight: 900;
	padding: 10px;
}

#registForm {
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

input.infoinput{

	outline: none;
	border-width: 0;
	background-color:#dcd7d3;
	font-family: 'Noto Sans KR', sans-serif;
	color: #685449;
	height: 90%;
	width: 90%;
	border-radius: 10px;
	margin-left: 30px;

}

h2 {
	text-align: center;
}


table {
	margin: auto;
	width: 800px;
	border: 2px solid;
	border-radius: 30px;

}

td{

	border-bottom: 2px solid;
	justify-content:center;
	
	height: 50px;
}
td.lasttd{

border-bottom: none;
	
}
.td_left {
	width: 350px;
	/* background: orange; */
	text-align: center;
	
}

.td_right {
	width: 450px;
	/* background: skyblue; */
	border-left: 2px solid;
}

#commandCell {
	text-align: center;
}
.textnote{

	height: 450px;
	padding: 10px;


}
 .ck-editor__editable { height: 450px;  }
</style>
</head>
<body>
	<!-- 게시판 등록 -->

	<section id="writeForm">
		<h2>게시판글수정</h2>
		<form action="boardModifyPro.bo" method="post" name = "modifyform">
		<input type = "hidden" name = "BOARD_NUM" value = "<%=article.getBOARD_NUM()%>"/>
			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input  class="infoinput" type = "text" name="BOARD_NAME" id = "BOARD_NAME" value = "<%=article.getBOARD_NAME()%>"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
					<td class="td_right">
					<input name="BOARD_PASS" class="infoinput" type="password" id = "BOARD_PASS"/>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_SUBJECT">제 목</label></td>
					<td class="td_right">
			<input class="infoinput" name="BOARD_SUBJECT" type="text" id = "BOARD_SUBJECT" value = "<%=article.getBOARD_SUBJECT()%>"/>
		</td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_CONTENT">내 용</label></td>
					<td class="textnote td_right"><textarea name="BOARD_CONTENT"  name="text" id="editor"><%=article.getBOARD_CONTENT()%></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_FILE"> 파일 첨부 </label></td>
					<td class="td_right"><input class="infoinput" name="BOARD_FILE" type="file"
						id="BOARD_FILE" /></td>
				</tr>
				
				<tr>
				
					<td  colspan="2" class="td_left lasttd"><section id = "commandCell">
	
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>
			</section></td>
				</tr>
			</table>
			
			
		</form>
	</section>
	<!-- 게시판 등록 -->


	<script
		src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
		<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
		
	<script>
	 ClassicEditor.create( document.querySelector( '#editor' ), {
		    language: "ko"
		  } );
	</script>
</body>
</html>