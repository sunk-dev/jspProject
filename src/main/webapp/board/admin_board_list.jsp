<%@page import="beans.AdminBoard"%>
<%@page import="beans.PageInfo"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>


<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<AdminBoard> articleList= new ArrayList<AdminBoard>();
	articleList=(ArrayList<AdminBoard>)request.getAttribute("articleList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>

<html>
<head>
<meta charset="UTF-8" />
<title>MVC 게시판</title>
<style type="text/css">

body{

	background-image: url("././img/bg.jpg");
	font-family: 'Noto Sans KR', sans-serif;
	color: #685449;
}
a{
	text-decoration: none;
	color: inherit;
	
}
a>div{
	position:relative;
	left:57%;
	margin-bottom:15px;
	display:inline-block;
	padding:10px;
	border:2px dashed;
	border-radius: 10PX;
}


#registForm {
	width: 500px;
	height: 600px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	border: 3px solid;
	border-color:#685449;
	margin: auto;
	width: 450px;
}

td{

	border: 1px dashed ;
	
	border-color:#685449;
	text-align: center;
}


#tr_top {

	text-align: center;
	
}

#pageList {
	margin: auto;
	width: 500px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
	height: 80%;
}
#listForm{

	margin-top: 30px;
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
	<!-- 게시판 리스트 -->
	
	<jsp:include page="../left2.jsp" />

	<section id="listForm">
		<h2>공지 목록</h2>
		<a href="AdminBoardWriteForm.bo">
		<div>게시판글쓰기</div></a>
		<table>
			<%
if(articleList != null && listCount > 0){
%>

			<tr id="tr_top">
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>

			<%
		for(int i=0;i<articleList.size();i++){
			
	%>
			<tr>
				<td><%=articleList.get(i).getBOARD_NUM()%></td>

				<td>
					
					<a href="AdminboardDetail.bo?board_num=<%=articleList.get(i).getBOARD_NUM()%>&page=<%=nowPage%>">
						<%=articleList.get(i).getBOARD_SUBJECT()%>
					</a>
				</td>

				<td><%=articleList.get(i).getBOARD_NAME() %></td>
				<td><%=articleList.get(i).getBOARD_DATE() %></td>
				<td><%=articleList.get(i).getBOARD_READCOUNT() %></td>
			</tr>
			<%} %>
		</table>
	</section>

	<section id="pageList">
		<%if(nowPage<=1){ %>
		[이전]&nbsp;
		<%}else{ %>
		<a href="AdminBoardList.bo?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
		<%} %>

		<%for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
		[<%=a %>]
		<%}else{ %>
		<a href="AdminBoardList.bo?page=<%=a %>">[<%=a %>]
		</a>&nbsp;
		<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %>
		[다음]
		<%}else{ %>
		<a href="AdminBoardList.bo?page=<%=nowPage+1 %>">[다음]</a>
		<%} %>
	</section>
	<%
    }
	else
	{
	%>
	<section id="emptyArea">등록된 글이 없습니다.</section>
	<%
	}
%>

</body>
</html>