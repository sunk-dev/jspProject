<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String pagefile = request.getParameter("page");
System.out.print(pagefile);

if (pagefile == null) {
	pagefile = "introduce";
}
%>
<html>
<head>
<!-- 폰트 링크  -->
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<style type="text/css">
body {
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

.menu {
	position: absolute;
	top: 5%;
	left: 35%;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 16pt;
}


img {
	position: absolute;
	top: 5%;
	right: 0%;
	height: 99%;
}
.main{
	
	top:20%;
	height:100%;
	justify-content: center;
	align-content: center;

}

.main img {
	width: 72%;
	height: 81%;
	top: 15%;
	left: 10%;
}

.main.loginmain{
position:absolute;
top:30%;
}

.includepage{
	position:relative;
	left:25%;
	top:15%;
	width:50%;
	height: 70%;
	background-color: yellow;

}
.logo{
	display:inline-block;
	position:absolute;
	top: 5%;
	width:200px;
	height:40px;
}



</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<!-- 몸통 전체  -->
		<!-- 로고 -->
		
		<a href="indexT.jsp">
		<div class="logo"><img  src="./img/coffeelogo.png"></div>
		</a>
		<!--  상단메뉴  -->
		<div class="menu">

			<jsp:include page="top.jsp" />
			<!-- top.jsp추가  -->

			<jsp:include page="left.jsp" />

		</div>
		<!-- 바디  -->

		<div class="main">
		
		<div class="includepage">
		<jsp:include page='<%=pagefile+ ".jsp"%>' />
		</div>

			<!--  -->
			<!-- <img alt="" src="./img/coffee.jpg"> -->
			<!-- 이미지 -->
			<!-- <img alt="" src="./img/coffee.jpg"> -->
		</div>

		<img alt="" src="./img/SIDE2.PNG">
		<div>
		<jsp:include page="bottom.jsp"/>
		</div>

	</div>

</body>
</html>