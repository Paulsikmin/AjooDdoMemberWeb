package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/member/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오류가 나도 오류 메시지가 없어서 불편하셨죠?
		// 예외가 발생하면 오류메시지를 errorPage.jsp에 출력하기 위해
		// try ~ catch로 감싼 후 메시지를 보내겠습니다.
		// try ~ catch 준비, 직접써도 되고 ctrl+space bar로 자동완성해도 되고
		try {
			// 이 안에 모든 코드를 넣읍시다.
			// 컨트롤러는 jsp와 연관이 깊음. input태그의 name값이 키값이 되어
			// 사용자가 입력한 값을 request를 통해 가져올 수 있음.
			String memberId = request.getParameter("member-id");
			String memberPw = request.getParameter("member-pw");
			// 두 개의 변수를 하나의 변수로 다루기 위해 객체 생성 후 
			// 입력받은 값으로 초기화 해줌
			Member member = new Member(memberId, memberPw);
			MemberService mService = new MemberService();
			// 입력한 값으로 데이터가 존재하면 member는 널이 아니고
			// 없으면 null임
			// selectLoginCheck만 만들어주면 로그인 완성!
			// is undefined 오류네요. service에 메소드 만들어주세요
			member = mService.selectLoginCheck(member);
			if(member != null) {
				/*
				 * 성공하면 메인페이지로 가야하는데요. 그냥 가면 안되고
				 * 로그인 성공한 정보를 session에 저장해주어야 합니다.
				 * 그래서 request에서 세션을 가져와서 세션에서 민감하지
				 * 않은 정보 몇가지만 적어줍니다. 주로 Id값이나 no값을
				 * 저장합니다.
				 * session에는 setAttribute() 메소드를 이용해서 저장하고요
				 * 저장할 수 있는 값이 여러개니까. 키값으로 구분해둡니다.
				 * 해당 키값은 jsp에서 사용되어 밸류값을 가져올 떄 필요해요
				 * memberId라는 키값으로 로그인한 아이디 값을 세션에 넣어놨구요
				 * memberName이라는 키값으로 로그인한 이름정보를 세션에 넣을게요
				 * 키값은 jsp에서 사용된다고 했는데 사용하러 index.jsp로 고고씽
				 */
				HttpSession session = request.getSession();
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				response.sendRedirect("/index.jsp");
			}else {
				// 실패하면 실패페이지
				request.setAttribute("msg", "No Data Found!!!");
				RequestDispatcher view 
				= request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
				view.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage());
			RequestDispatcher view 
			= request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

}



















