<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		1. LoginController에서 데이터가 없거나 예외 발생시
		request에 메시지를 set해주므로 그것을 사용.
		2. 예외 중에서 NullPointer Exception 발생하면 msg는 null로
		넘어오는데 이것을 처리하기 위해서 if문을 쓴다.
		jsp에서 if문을 쓰려면 jstl이 필요하다. jstl을 쓰기 위해선
		jar파일(4개)이 필요한대 이것은 이미 lib에 위치시켰고
		jsp파일 내에 taglib 구문을 이용하여 core Tag를 사용할 수 있도록
		한다.
 -->
 		<c:if test="${msg eq null }">
 			<h2>NullPointerException 발생! Null인 곳을 찾아주세요!</h2>
 		</c:if>
		<h2>${msg }</h2>		
	</body>
</html>