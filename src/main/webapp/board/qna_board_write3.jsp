<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">

body{

	background-image: url("./../img/bg.jpg");
	font-family: 'Noto Sans KR', sans-serif;
	color: #685449;

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
		<h2>게시판글등록</h2>
		<form action="boardWritePro.bo" method="post"
			enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input class="infoinput" type="text" name="BOARD_NAME"
						id="BOARD_NAME" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
					<td class="td_right"><input class="infoinput" name="BOARD_PASS" type="password"
						id="BOARD_PASS" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_SUBJECT">제 목</label></td>
					<td class="td_right"><input class="infoinput" name="BOARD_SUBJECT" type="text"
						id="BOARD_SUBJECT" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_CONTENT">내 용</label></td>
					<td class="textnote td_right"><textarea name="BOARD_CONTENT"  name="text" id="editor"></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_FILE"> 파일 첨부 </label></td>
					<td class="td_right"><input class="infoinput" name="BOARD_FILE" type="file"
						id="BOARD_FILE" /></td>
				</tr>
				
				<tr>
					<td  colspan="2" class="td_left lasttd"><input type="submit" value="등록">
					<input type="reset" value="다시쓰기" /></td>
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