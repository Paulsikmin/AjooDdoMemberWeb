<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<!-- 
		회원정보 수정 페이지에서는 비밀번호, 이메일, 전화번호,
		주소, 취미만 수정합니다.
		c if에서 노란줄이 나면은 jsp파일에 taglib를 적지 않아서
		그런거니까 taglib를 파일 상단에 적어주세요. 
		회원정보수정은 post메소드를 사용하고 있으므로 ModifyController의
		doPost() 메소드에서 회원정보수정을 하도록 해야합니다.
		-->
		<form action="/member/modify.do" method="post">
			<fieldset>
				<legend>회원정보수정</legend>
				<ul>
					<li>
						<label for="member-id">아이디</label> 
						<input type="text" name="member-id" id="member-id" 
						value="${member.memberId }" readonly>
					</li>
					<li>
						<label for="member-pw">비밀번호</label> 
						<input type="password" name="member-pw" id="member-pw"
						value="${member.memberPw }">
					</li>
					<li>
						<label for="member-name">이름</label> 
						<input type="text" name="member-name" id="member-name" 
						value="${member.memberName }" readonly>
					</li>
					<li>
						<label for="age">나이</label> 
						<input type="text" name="member-age" id="age"
						value="${member.memberAge }" readonly>
					</li>
					<li>
						<label for="gender">성별</label> 
						<c:if test="${member.memberGender eq 77 }">남</c:if>
						<c:if test="${member.memberGender eq 70 }">여</c:if>
					</li>
					<li>
						<label for="email">이메일</label> 
						<input type="text" name="member-email" id="email"
						value="${member.memberEmail }">
					</li>
					<li>
						<label for="phone">전화번호</label> 
						<input type="text" name="member-phone" id="phone"
						value="${member.memberPhone }">
					</li>
					<li>
						<label for="address">주소</label> 
						<input type="text" name="member-address" id="address"
						value="${member.memberAddress }">
					</li>
					<li>
						<label for="hobby">취미</label> 
						<input type="text" name="member-hobby" id="hobby"
						value="${member.memberHobby }">
					</li>
				</ul>
			</fieldset>
			<input type="submit" value="수정하기">
			<input type="reset" value="초기화">
		</form>
	</body>
</html>