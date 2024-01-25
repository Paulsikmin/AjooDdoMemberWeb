<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>아주 또 멤버웹</title>
	</head>
	<body>
		<h1>아주 또 멤버웹</h1>
		<h2>방문을 진심으로 환영합니다.</h2>
		<!-- 
		- session에 담긴 memberId값이 있으면 로그인이 된 것이고
		없으면 안된 것입니다. 로그인이 되었으면 이름을 불러 환영해주고
		로그인이 안되어 있으면 로그인 폼을 보여주어 로그인하도록 합니다.
		session에서 memberId값이 있는지 없는지 체크하기 위해서는
		if문이 필요합니다. jsp에서 if문은 jstl을 사용합니다.
		- jstl은 jsp에서 for문, if문과 같은 제어문을 사용하게 해주는
		라이브러리입니다. 라이브러리를 사용하기 위해서는 jar파일이
		필요합니다. 다이나믹 웹 프로젝트에서는 jar파일을 WEB-INF>lib
		폴더에 위치시켜야 합니다. JDBC사용할 때에도 필요한 드라이버인
		ojdbc8.jar파일도 lib에 위치시켜야 합니다. 위치시켜주시죠. 
		jstl의 경우 jar파일만 있다고 쓸 수 있는게 아니라 jstl을 쓰려고
		하는 jsp에서 taglib를 작성해주어야 합니다. 
		- jstl에는 다양한 종류의 태그가 있는데 그 중 제어문을 담당하는
		core태그를 사용하는 것이고요. 그 태그를 c라는 문자를 이용해서 
		쓰겠다는 내용을 jsp에 써주는 것이에요.
		c:if 이용해서 로그인 여부에 따라 다르게 화면 출력을 해봅시다.
		특이하게 test다음에 EL인 $를 써서 session에 저장하는 값을 불러
		오는데 sessionScope는 session에 접근하기 위해 작성해주는 내장
		객체이고요 생략이 가능합니다. 그런데 request에도 memberId가
		있다면 둘을 구별하기 위해서 꼭 써줘야 합니다. 키값이 중복되지
		않는 다면 생략해도 됩니다. memberId가 널이면 로그인 폼을 보여주고
		memberId 널이 아니면(Not Equal) 로그인을 했다는 것이고
		- 로그인 한 사용자의 이름을 세션에서 가져와서 출력해줍니다.
		이제 다 완료된거 같으니 실행해서 로그인 기능을 테스트해봅니다.
		로그인이 잘 된다면 로그아웃 기능을 추가해서 로그아웃도 해볼게요
		로그아웃 url은 /member/logout.do로 a링크를 통해 호출되므로
		get방식 호출이 됩니다.
		- 로그인하면 마이페이지로 이동하도록 a태그 get방식으로 호출
		url적을 때 쿼리스트링 누락되지 않도록 주의해야함.
		-->
		<c:if test="${sessionScope.memberId ne null }">
			${sessionScope.memberName }님, 어서오세요!
			<a href="javascript:void(0)" onclick="checkLogout();">로그아웃</a>
			<a href="/member/myPage.do?memberId=${sessionScope.memberId }">
			마이페이지</a>
		</c:if>
		<c:if test="${sessionScope.memberId eq null }">
		<fieldset>
			<legend>로그인</legend>
<!-- 			쿼리스트링 안보이는 post 메소드 선택! -->
			<form action="/member/login.do" method="post">
				<input type="text" name="member-id" placeholder="아이디를 입력해주세요"><br>
				<input type="password" name="member-pw" placeholder="비밀번호를 입력해주세요">
				<div>
					<input type="submit" value="로그인">
					<a href="/member/register.do">회원가입</a>
				</div>
			</form>
		</fieldset>
		</c:if>
		<script>
			function checkLogout() {
				if(confirm("로그아웃하시겠습니까?")) {
					location.href= "/member/logout.do";
				}
			}
		</script>
	</body>
</html>








