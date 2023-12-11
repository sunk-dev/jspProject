<%@page import="beans.AdminBoard"%>
<%@page import="beans.Board"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%
	AdminBoard article = (AdminBoard)request.getAttribute("article");
    String nowPage = (String)request.getAttribute("page");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
body{

	background-image: url("././img/bg.jpg");
	font-family: 'Noto Sans KR', sans-serif;
	color: #685449;
}

a {
	text-decoration: none;
	color: #685449;
	font-weight: 900;
}
#articleForm {
	width: 500px;
	height: 800px;
	margin: auto;
}

h2 {
	text-align: center;
}

#basicInfoArea {
	height: 40px;
	text-align: center;
}

#articleContentArea {
	background-image: url("././img/boardview.jpg");
	margin-top: 20px;
	height: 650px;
	text-align: center;
	overflow: auto;
}

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
}
.logo{
	display:inline-block;
	position:absolute;
	top: 5%;
	width:200px;
	height:40px;
}

</style>
</head>

<body>

	<!-- 게시판 수정 -->
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제 목 :
			<%=article.getBOARD_SUBJECT()%>
			<br/>
			첨부파일 :
			<%if(!(article.getBOARD_FILE()==null)){ %>
			<a href="file_down?downFile=<%=article.getBOARD_FILE()%>"> <%=article.getBOARD_FILE() %>
			</a>
			<%} %>
		</section>
		<section id="articleContentArea">
			<%=article.getBOARD_CONTENT() %>
		</section>
	</section>
	<section id="commandList">
		<a href="AdminboardReplyForm.bo?board_num=<%=article.getBOARD_NUM() %>&page=<%=nowPage%>">[답변] </a>
		<a href="AdminBoardModifyForm.bo?board_num=<%=article.getBOARD_NUM() %>">[수정] </a>
		<a href="AdminboardDeleteForm.bo?board_num=<%=article.getBOARD_NUM() %>&page=<%=nowPage%>">[삭제] </a>
		<a href="adminBoardList.bo?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp;
	</section>
</body>
</html>