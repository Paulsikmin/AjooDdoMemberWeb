package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.model.vo.Member;

public class MemberDAO {
	// DAO에서는 할게 별로 없어요
	// conn 연결도 있고, member 데이터도 있으니
	// 그냥 맛있게 쿼리만 실행해주면 돼요
	public Member selectLoginCheck(Connection conn, Member member) throws SQLException {
		/* 
		 * ID와 PW를 입력받았고 해당 데이터가 있는지 확인하는 
		 * query문을 작성해줍니다. SELECT로 하는거에요
		 * 물음표는 위치홀더라고 하고요 member 데이터를 넣는 부분이죠
		 * ID와 PW 두개를 이용해서 SELECT를 할 수 있도록 WHERE 조건절을
		 * 사용해줍니다.
		 */
		String query = "SELECT * FROM MEMBER_TBL "
				+ "WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		// 시험에서도 나왔는데요. prepareStatement() 메소드를 통해서
		// pstmt 객체를 만들때에는 쿼리문이 필요해요. 전달값으로 전달합니다.
		// 그리고 prepareStatement() 메소드는 Checked Exception이에요
		// 반드시 예외처리를 해줘야 합니다.
		// 예외 처리 방법은 2가지죠. 1가지는 throw 던지는거 호출한
		// 곳에서 처리하도록 하는 방법. 나머지는 try ~ catch를 사용해서
		// 그 자리에서 처리하는 방법이 있는데 여기서는 던질거에요.
		// throws SQLException을 해줍니다.
		// 위치홀더가 2개이고 여기에 값을 셋팅해주어야 입력한 값이
		// WHERE 조건절에 들어가서 쿼리문이 실행됩니다.
		// 첫번째 위치홀더는 ID이고 member에서 getter메소드
		// getMemberId()를 이용하여 꺼내서 위치홀더 셋팅해줍니다.
		// 두번째 위치홀더는 PW이고 member에서 getter메소드
		// getMemberPw()를 이용하여 꺼내서 위치홀더 셋팅해줍니다.
		// PreparedStatement에서 SELECT를 실행할 때는 executeQuery()
		// 메소들를 사용해야 하고 전달값을 전달하지 않습니다.
		// 실행한 결과값은 ResultSet Interface를 통해서 받아줍니다.
		// rset은 그대로 못쓰고 rset을 member로 바꿔주는 작업이 필요한대
		// 반복되기 때문에 모듈화를 통해서 재사용할 수 있게 합니다.
		// public void rsetToMember(ResultSet rset)을 만들어줍니다.
		// rset을 통해서 Member를 리턴해야하기 때문에 void -> Member로
		// 바꿔줍니다. 이걸 리턴타입을 Member로 바꾼다라고 합니다.
		// rset에 객체가 담기면 next() 메소드를 통해서 다음 데이터가
		// 있는지 체크합니다. 다음 데이터가 있으면 true, 없으면 false를
		// 리턴합니다. 1개만 가져오는 경우 if문, 여러개를 가져올 경우
		// while문을 써줍니다. 이번에는 1개만 가져오기 때문에 if를 씁니다.
		// rsetToMember() 메소드가 리턴하는 멤버 인스턴스를 받기 위해
		// Member타입의 mOne 변수를 선언하고 null로 초기화 합니다.
		// null로 초기화를 해놓으면 데이터가 없을 때 null이 리턴되어서
		// null체크를 할 수 있기 때문입니다.
		// 다 끝났으면 자원해제 해줍니다. 순서 상관없고 마지막에서
		// return null;로 두지 말고 return mOne;으로 변경해줍니다.
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPw());
		ResultSet rset = pstmt.executeQuery();
		Member mOne = null;
		if(rset.next()) {
			mOne = this.rsetToMember(rset);
		}
		rset.close();
		pstmt.close();
		return mOne;
	}

	public int insertMember(Connection conn, Member member) throws SQLException {
		// DAO에서는 할게 별로 없어요
		// conn 연결도 있고, member 데이터도 있으니
		// 그냥 맛있게 쿼리만 실행해주면 돼요
		/*
		 * 쿼리문을 테이블에 삽입할 수 있는 명령어로 적어주고
		 * preparedstatement를 사용할것이므로 위치홀더로 값이
		 * 들어갈 곳을 표시해주어야 함.
		 * prepareStatement()는 Checked Exception이므로 예외처리하는데
		 * 던져서 처리할거에요. Service로 던졌고 Service는 다시 Controller
		 * 로 던져서 Controller가 try ~ catch하게 해줌. try ~ catch는
		 * 언젠가는 해야하는 거임.
		 * pstmt 객체 생성되었으면 쿼리스트링에 입력값 셋팅해주어야 함.
		 * 위치홀더 9개이므로 1부터 9까지 다 셋해주어야 함.
		 * 위치홀더 4, 5번은 각각 타입이 char, int이므로 각각의 조치를
		 * 취해줘야 한다. 위치홀더 4의 경우 문자열로 만들어주고
		 * 위치홀더 5의 경우 setString이 아닌 setInt메소드를 써야한다.
		 * 쿼리문을 실행할 때는 executeUpdate() 메소드를 써야한다.
		 * 실행하려고 하는 쿼리문이 DML이기 때문이다.
		 * 자원을 해제하고 결과값을 Service가 목이 빠지게 기다리고 있으므로
		 * return result;를 작성하여 반환해준다.
		 */
		String query = "INSERT INTO MEMBER_TBL "
				+ "VALUES(?,?,?,?,?,?,?,?,?,DEFAULT, DEFAULT, 'Y')";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPw());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getMemberGender()+"");
		pstmt.setInt(5, member.getMemberAge());
		pstmt.setString(6, member.getMemberEmail());
		pstmt.setString(7, member.getMemberPhone());
		pstmt.setString(8, member.getMemberAddress());
		pstmt.setString(9, member.getMemberHobby());
		int result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	public Member rsetToMember(ResultSet rset) throws SQLException {
		/* 
		 * rset에 있는 값을 담을 member객체를 선언해주고
		 * rset.getString() 등의 메소드를 이용하여 member에 값을
		 * 넣어줍니다. 값을 넣을 땐 setter() 메소드 setMemberId()를
		 * 이용하여 member에 값을 넣어줍니다.
		 * rset.getString() 메소드는 전달값으로 컬럼명을 적어줘야하는데
		 * 오타가 나지 않도록 합니다.
		 * getString 메소드는 Chekced Exception이네요
		 * 예외처리 해줍니다. 2가지 방법 중 던지기 방법으로 처리할게요.
		 * 그리고 컬럼의 값이 숫자면 getString말고 getInt써야합니다ㅣ
		 * 이번 시험 문제였죠
		 * 그리고 이번 Member 클래스의 멤버변수 memberGender는
		 * char형입니다. rset에서 getChar는 없기 때문에 String에서 한글자
		 * 를 잘라서 char형으로 셋팅해줘야 합니다.
		 * 방법은 rset.getString("MEMBER_GENDER").charAt(0)을 씁니다.
		 * 그리고 컬럼의 타입이 Date일 경우 getDate()
		 * 컬럼의 타입이 Timestamp일 경우 getTimestamp()를 사용해야합니다.
		 * Member의 멤버변수 MEMBER_YN은 타입이 char형이므로 성별에서처럼
		 * charAt(0)메소드를 이용하여 문자열을 문자로 잘라서 만들어줍니다.
		 * 마지막으로 해야할 일은..return해줍니다. return member;
		 * 
		 */
		Member member = new Member();
		member.setMemberId(rset.getString("MEMBER_ID"));
		member.setMemberPw(rset.getString("MEMBER_PW"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setMemberGender(rset.getString("MEMBER_GENDER").charAt(0));
		member.setMemberAge(rset.getInt("MEMBER_AGE"));
		member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
		member.setMemberPhone(rset.getString("MEMBER_PHONE"));
		member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
		member.setMemberDate(rset.getDate("MEMBER_DATE"));
		member.setUpdateDate(rset.getTimestamp("UPDATE_DATE"));
		member.setMemberYn(rset.getString("MEMBER_YN").charAt(0));
		return member;
	}
}
















