package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/member/logout.do")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 로그아웃은 세션을 파괴하는 것이다. 
		 * 세션은 new로 생성하는 것이 아니라 
		 * request라는 서블릿 객체에서 getSession() 메소드로
		 * 가져오는 것이다. 
		 * invalidate() 메소드는 세션을 파괴하며 
		 * 세션에 저장된 정보도 사라지게 한다. 세션이 파괴된 후 
		 * 다시 로그인을 하도록 하기 위해서 메인 페이지(index.jsp)로
		 * 이동하여준다.
		 */
		
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
			response.sendRedirect("/");
		}
	}


}
