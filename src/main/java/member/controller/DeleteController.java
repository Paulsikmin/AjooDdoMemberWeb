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
 * Servlet implementation class DeleteController
 */
@WebServlet("/member/delete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		/*
		 * 삭제할 때 로그인한 사용자가 자신의 아이디만 탈퇴하도록
		 * 체크하는 코드를 작성해야 악의적으로 이용되지 않게 할 수 있다.
		 */
		MemberService mService = new MemberService();
		int result = 0;
		try {
			result = mService.deleteMember(memberId);
			if(result > 0) {
				// 성공하면 로그아웃해주기
				// 로그아웃 기능은 이미 완료되었으므로 만들필요 없음
				response.sendRedirect("/member/logout.do");
			}else {
				// 실패
				request.setAttribute("msg", "No Data FOUND!!!");
				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
				.forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
			.forward(request, response);
		}
	}

}













