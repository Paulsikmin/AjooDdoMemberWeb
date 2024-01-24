<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>에러페이지</title>
	</head>
	<body>
		<h1>에러 메시지</h1>
		<h2>서비스 실패! 관리자에게 문의바랍니다.</h2>
<!-- 		
		LoginController에서 데이터가 없거나 예외 발생시
		request에 메시지를 set해주므로 그것을 사용
 -->
		<h2>${msg }</h2>
	</body>
</html>