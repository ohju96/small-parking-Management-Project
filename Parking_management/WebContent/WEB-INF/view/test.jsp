<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form name="form" method="post" enctype = "multipart/form-data" action="/test.do">
  		<br>
		
		이미지 파일 업로드 : <input type="file" name="fileUpload" />
		<br>
		<br>
		<input type="submit" value="업로드" />

	</form>

</body>
</html>