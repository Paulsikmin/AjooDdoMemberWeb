package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	
	// 연결과 데이터를 넘겨주면 쿼리문을 실행해줘요
	private MemberDAO mDao;
	private Connection conn;
	
	public MemberService() {
		// mDao는 멤버변수라고 하는데 그 초기화는
		// 생성자에서 해주죠
		mDao = new MemberDAO();
		// conn은 멤버변수라고 하는데 그 초기화는
		// 생성자에서 해주죠
		// getConnection 메소드는 static 메소드라
		// 객체 생성없이도 호출이 가능해서 다음과 같이 써요
		// 싱글톤이 적용되어 conn은 한번만 생성됩니다.
		// DBMS연결작업은 무거운 작업이므로 최소한으로 하는 것이
		// 부하가 적게 걸려요
		conn = JDBCTemplate.getConnection();
	}

	public Member selectLoginCheck(Member member) throws SQLException {
		// selectLoginCheck 메소드는 member 객체를 전달받아서
		// dao에 전달해주는 역할을 해요
		// 그리고 서비스에서는 DBMS연결을 만들어서
		// dao에 전달해주죠
		// 마지막으로 쿼리 성공여부에 따라 commit/rollback을
		// 하고 있습니다.(DML경우에만 합니다!)
		// DB에서 쿼리문을 실행해서 결과값을 member로 받기위해
		// MemberDAO와 관계를 맺고 메소드를 호출해야 합니다.
		// conn에 is undefined 오류가 뜨네요. 선언해주고
		// JDBCTemplate에서 연결을 만들어서 초기화해줍시다.
		// service의 selectLoginCheck 메소드를 호출한 곳에서
		// 쓸 수 있도록 mOne을 리턴해줍니다.
		// mDao의 selectLoginCheck() 메소드가 없어서 오류가 나고 있으니
		// MemberDAO에 selectLoginCheck() 메소드를 만들어줍니다.
		/*
		 * 다중주석은 이렇게 합니다.
		 * MemberDAO의 selectLoginCheck() 메소드는 SQLException을
		 * throw합니다. 그래서 호출하는 이곳에서 예외를 처리해줘야하는데
		 * try ~ catch는 controller에서 이미 하고 있기 때문에 
		 * service에서도 해당 예외를 던져줍니다. throws SQLException을
		 * 써줍니다.(전 자동완성할거임)
		 * DAO에서 넘어온 예외가 Service에 왔다가 Service에서는 다시
		 * Controller로 넘어갑니다.
		 */
		Member mOne = mDao.selectLoginCheck(conn, member);
		return mOne;
	}

	public Member selectOneById(String memberId) throws SQLException {
		/*
		 * selectOneById 메소드는 memberId를 전달받아서
		 * dao에 전달해주는 역할을 해요.
		 * 그리고 서비스에서는 DBMS 연결을 만들어서 dao 전달해줍니다.
		 * DB에서 쿼리문을 실행해서 결과값을 member로 받기위해
		 * MemberDAO와 관계를 맺고 메소드를 호출해야 합니다.
		 * conn은 이미 전에 선언해서 연결을 만들어 놨기 때문에
		 * selectOneById() 메소드만 DAO에 자동생성해주면 됩니다.
		 * 자동생성해주고 return은 null로 두지 말고 return member;해줍니다.
		 * return null로 두면 결과가 안나오거나 NULL PointerException이
		 * 발생하거나 합니다.
		 */
		Member member = mDao.selectOneById(conn, memberId);
		return member;
	}

	public int insertMember(Member member) throws SQLException {
		/*
		 * 쿼리문의 성공여부는 숫자로 result에 담긴다.
		 * 성공여부를 controller에서 알고 싶어하니까 return result;
		 * 를 적어서 값을 반환해준다.
		 * MemberDAO에 insertMember()메소드가 없어서 오류가 뜨고
		 * 있으므로 create method 해준다.
		 * Service가 하는 역할을 연결을 만들어 DAO에 전달하고
		 * DAO에 있는 쿼리문 수행 메소드를 호출하며 성공여부에
		 * 따라 커밋/롤백을 하는 역할을 하고 있다.
		 */
		int result = mDao.insertMember(conn, member);
		if(result > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		return result;
	}

	public int updateMember(Member member) throws SQLException {
		/*
		 * 쿼리문의 성공여부는 숫자로 result에 담긴다.
		 * 성공여부를 controller에서 알고 싶어하니까 return result;
		 * 를 적어서 값을 반환해준다.
		 * MemberDAO에 updateMember()메소드가 없어서 오류가 뜨고
		 * 있으므로 create method 해준다.
		 * Service가 하는 역할을 연결을 만들어 DAO에 전달하고
		 * DAO에 있는 쿼리문 수행 메소드를 호출하며 성공여부에
		 * 따라 커밋/롤백을 하는 역할을 하고 있다.
		 */
		int result = mDao.updateMember(conn, member);
		if(result > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		return result;
	}

	public int deleteMember(String memberId) throws SQLException {
		int result = mDao.deleteMember(conn, memberId);
		if(result > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		return result;
	}

}


























