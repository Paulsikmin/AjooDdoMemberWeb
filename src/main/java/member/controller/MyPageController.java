package member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/member/myPage.do")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * MyPageController를 동작시키는 url은 /member/myPage.do로
		 * 정했어요. 제가 정한거에요 보통 주제와 같은 영어단어와
		 * 기능을 나타내는 영어단어를 조합해서 만들어요 나중에
		 * 시험에서도 url을 설계하는 시험이 나올거에요
		 * myPage.jsp에 출력해야하는 정보는 로그인한 사람의
		 * 정보를 출력해야하기 때문에 쿼리문이 다음과 같아요
		 * SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?
		 * 위치홀더(?)에 넘겨줄 값이 로그인한 사용자의 아이디여야
		 * 하니까. Controller에서는 해당 값을 받아서 Service로
		 * 넘겨주어야 DAO가 쿼리를 실행할 수 있어요.
		 * 즉, myPage를 보기 위해 호출하는 URL은
		 * --/member/myPage.do?memberId=admin와 같은 형태로
		 * 쿼리스트링이 필요하죠. 쿼리스트링으로 보낸 데이터를
		 * 받기 위해서는 request객체의 getParameter()메소드를
		 * 사용합니다. 이때 getParamemter() 메소드에 전달해주는
		 * 값은 내가 정한 키값(memberId)으로 일치시켜주어야 해요.
		 * Service에 로그인한 사용자의 아이디를 넘겨주고 실행결과로
		 * member객체를 받기위해서 MemberService 객체를 생성하고
		 * selectOneById() 메소드를 호출합니다. 호출할 땐 전달값으로
		 * memberId 써주어야 합니다.
		 * selectOneById() 메소드가 없다고 is undefiend 오류가
		 * 뜨고 있으니 create method 자동생성해줍시다.
		 */
		try {
			String memberId = request.getParameter("memberId");
			MemberService mService = new MemberService();
			Member member = mService.selectOneById(memberId);
			if(member != null) {
				request.setAttribute("member", member);
				// 변수안쓰고 이동할 페이지 지정 및 이동(forward() 호출)
				// 메소드 채이닝 방식, 점으로 계속 연결하기
				request.getRequestDispatcher("/WEB-INF/views/member/myPage.jsp")
				.forward(request, response);
			}else {
				// 데이터가 존재하지 않을 경우
				request.setAttribute("msg", "No Data FOUND!!!");
				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
				.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace(); -> 콘솔창에 빨간 글씨로 오류 출력해주는 메소드
			// 오류메시지를 오류페이지를 통해서 볼 수 있도록 request에
			// setAttribute하고 errorPage.jsp에서는 ${msg}로 사용
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
			.forward(request, response);
		}
	}

}

















