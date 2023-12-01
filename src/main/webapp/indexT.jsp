<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String pagefile=request.getParameter("page");

	if (pagefile==null){pagefile="newitem";}
%>
<!DOCTYPE html>
<html>
<head>
<!-- 폰트 링크  -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
body{
background-image: url("./img/bg.jpg");

}
a{
text-decoration: none;
color: #000000;
}
.menu{
justify-content: center;
}
img{
align-self: left;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body >
<div>
<!-- 몸통 전체  -->

<!--  상단메뉴  -->
<div class="menu">
	
	<jsp:include page="top.jsp"/>
	<!-- top.jsp추가  -->

	<jsp:include page="left.jsp"/>
	
</div>
<!-- 바디  -->

<div>

<!--  -->

<!-- 이미지 -->
<!-- <img alt="" src="./img/coffee.jpg"> -->
</div>

<img alt="" src="./img/SIDE2.PNG">

<jsp:include page='<%=pagefile+".jsp" %>'/>

</div>
<footer>
<jsp:include page="bottom.jsp"/>
</footer>
</body>
</html>