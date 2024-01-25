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
 * Servlet implementation class ModifyController
 */
@WebServlet("/member/modify.do")
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * --/member/modify.do?memberId=admin을 get방식으로 요청한 경우
		 * request객체의 getParameter()메소드를 통해서 memberId를 가져와
		 * service의 selectOneById() 메소드를 호출할 때 memberId를 전달값으로
		 * 전달해야 함.
		 * 
		 */
		Member member = null;
		try {
			String memberId = request.getParameter("memberId");
			MemberService mService = new MemberService();
			member = mService.selectOneById(memberId);
			if(member != null) {
				request.setAttribute("member", member);
				request.getRequestDispatcher("/WEB-INF/views/member/modify.jsp")
				.forward(request, response);
			} else {
				// 데이터가 존재하지 않을 경우
				request.setAttribute("msg", "No Data FOUND!!!");
				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
				.forward(request, response);
			}
		} catch (SQLException e) {
			// 예외가 발생한 경우
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		try {
			request.setCharacterEncoding("utf-8"); // 한글 깨지지 않게 해줌
			String memberId = request.getParameter("member-id");
			String memberPw = request.getParameter("member-pw");
			String memberEmail = request.getParameter("member-email");
			String memberPhone = request.getParameter("member-phone");
			String memberAddress = request.getParameter("member-address");
			String memberHobby = request.getParameter("member-hobby");
			Member member = new Member(memberId, memberPw, memberEmail, memberPhone, memberAddress, memberHobby);
			MemberService mService = new MemberService();
			result = mService.updateMember(member);
			if(result > 0) {
				// 성공하면 마이페이지로 이동
				response.sendRedirect("/member/myPage.do?memberId="+memberId);
			}else {
				request.setAttribute("msg", "Service Failed!!");
				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
				.forward(request, response);
			}
		} catch (SQLException e) {
			// 예외가 발생한 경우
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
			.forward(request, response);
		}
	}

}






















