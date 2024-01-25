<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	</head>
	<body>
		<!-- 
		## 마이페이지를 만들어주세요!
		- DB에서 가져온 데이터를 출력해주는 페이지로
		로그인한 사람의 아이디로 가져온 데이터여야 합니다
		딱 한 사람의 데이터만 가져옵니다.
		마이페이지에서는 정보수정 페이지로 넘어가는 버튼과
		회원을 탈퇴할 수 있는 버튼이 있어야 해요 여기서 버튼은
		a태그로 만들어서 get방식 호출을 할거에요. 그리고 이것을
		다하셨으면 회원탈퇴 버튼을 눌렀을 때 회원탈퇴할지 물어보고
		탈퇴를 하는 자바스크립트 코드를 작성해주세요. 우리
		로그아웃할 때처럼 해주면 됩니다! 
		- myPage.jsp에서는 Controller에서 request에 member 라는 키 값으로
		member 객체를 setAttribute해주면 ${member}를 통해서 받아 쓸 수 
		있어요! 그래서 jsp랑 Controller는 밀접한 관련이 있습니다.
		jsp에서 오류가 나면 항상 같이 봐야합니다!!
		- ${member}로 가져온 데이터는 memberId도 있고 memberPw도 있고
		rset에서 member로 바꿔서 가져온 데이터가 다 있습니다.
		마침표 표기법으로 필요한 값을 가져와서 사용해야 하고 마침표
		뒤에 적는 값은 Member VO클래스의 멤버변수명을 적어야 합니다.
		- c:if에서 노란줄이 보이면 jsp내에서 taglib를 적지 않은것이에요
		적어주세요!
		- 위에서 말했었던 Controller에서 request에 member라는 키값으로
		member객체를 setAttribute하러 가보도록 할까요?
		-->
		<ul>
			<li>
				<label for="member-id">아이디</label> 
				${member.memberId }
			</li>
			<li>
				<label for="member-name">이름 :</label> 
				${member.memberName }
			</li>
			<li>
				<label for="age">나이 :</label> 
				${member.memberAge }
			</li>
			<li>
				<label for="gender">성별 :</label>
				<!-- 
				member에서 가져온 memberGender는 char라서
				문자열 String과 비교가 되지 않으므로
				아스키코드표를 통해서 M에 해당하는 숫자 77
				F에 해당하는 숫자 70이랑 비교하여 해결해야한다. 
				-->
				<c:if test="${member.memberGender eq 77 }">남</c:if> 
				<c:if test="${member.memberGender eq 70 }">여</c:if> 
			</li>
			<li>
				<label for="email">이메일 :</label> 
				${member.memberEmail }
			</li>
			<li>
				<label for="phone">전화번호 :</label> 
				${member.memberPhone }
			</li>
			<li>
				<label for="address">주소 :</label> 
				${member.memberAddress }
			</li>
			<li>
				<label for="hobby">취미 :</label> 
				${member.memberHobby }
			</li>
		</ul>
		<!-- 
		마이페이지에서 정보수정하기 버튼을 누르면 정보수정할 수 있는
		페이지로 이동합니다. 이때 수정하려고 하는 사용자의 정보를 
		가져와서 출력해야하기 때문에 쿼리스트링이 필요합니다
		쿼리스트링은 ?memberId=admin과 같은 형태를 가지고 있고
		url에 만들어져서 서버로 전송이됩니다. admin은 하드코딩하지않고
		로그인한 사용자의 아이디로 작성해주어합니다. 
		ModifyController에서 doGet 메소드를 사용하여 정보 수정페이지로
		이동합니다. a태그는 url을 get방식으로 요청하기 때문에 doGet()
		메소드를 사용해야 합니다.
		doGet() 메소드에서 디비에서 가져온 값을 member로 setAttribute하여
		modify.jsp로 보내주어 화면에 출력되도록 합니다.
		-->
		<a href="/member/modify.do?memberId=${sessionScope.memberId }">정보수정</a> <br>
		<!-- 
		탈퇴하기 위해서 필요한 버튼이 만들어져 있고
		URL은 쿼리스트링으로 로그인한 사용자의 ID를 보내면서 
		서버에 요청하고 있음. 
		-->
		<a href="javascript:void(0)" onclick="deleteCheck();">회원탈퇴</a> <br>
		 <br>
	</body>
	<script>
		function deleteCheck() {
			if(confirm("탈퇴하시겠습니까?")) {
				var memberId = "${sessionScope.memberId }";
				location.href = "/member/delete.do?memberId="+memberId;
			}
		}
	</script>
</html>













