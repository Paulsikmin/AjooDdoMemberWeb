package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * index.jsp에서 a태그를 통해 get방식으로 /member/register.do를
		 * 호출하면 RegisterController의 doGet() 메소드가 동작해서
		 * register.jsp를 보여주도록 함. WEB-INF 아래에 있는 jsp들은
		 * RequestDispatcher를 통해서만 페이지 이동이 가능함.
		 */
		RequestDispatcher view 
		= request
		.getRequestDispatcher("/WEB-INF/views/member/register.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 *  한글은 깨지지 않도록 인코딩 셋팅을 해준다.
		 *  register.jsp 페이지를 보시면 폼태그 action에 
		 *  url(/member/reigster.do)이 적혀있고
		 *  폼태그 method에 요청방식이 적혀있는데 post로 적혀있음.
		 *  즉, form태그에 있는 submit버튼(가입하기)을 누르면 
		 *  RegisterController의 doPost() 메소드가 동작한다는 뜻이다.
		 *  그래서 지금 여기에서 입력받은 정보를 가지고 DB에 insert할
		 *  수 있도록 코드를 작성해줘야 함. 서버내려주고
		 *  Controller에서는 Service 클래스와 연관관계를 맺고
		 *  Service 메소드를 호출해야 한다. 그래서 MemberService 타입의
		 *  mService 변수를 선언한다. MemberService 생성자를 이용하여
		 *  객체를 생성해준다.
		 *  DML의 경우 성공하면 0보다 큰 수, 실패하면 아닌 수가 넘어오므로
		 *  int로 선언한 변수가 필요하다.
		 *  form태그에 입력한 데이터는 name에 적은 키 값으로 매핑되어
		 *  쿼리스트링의 형태로 서버에 넘어온다. 이것은 request의 
		 *  getParameter() 메소드를 통해 가져올 수 있다. getParameter()
		 *  메소드의 전달값은 input 태그 name 속성에 적은 값을 넣어줘야
		 *  한다.
		 *  Member형의 member 변수에 입력한 값을 모두 저장하는 것은
		 *  (setter() 메소드없이) 생성자를 통해서 초기화를 해준다.
		 *  Member(String,String,String,...)과 같은 매개변수 있는 생성자를
		 *  만들어주어야 생성자로 초기화가 가능해짐.
		 *  request.getParameter()로 가져온 값들은 모두 String이므로
		 *  Member VO의 멤버변수 중 String이 아닌 것은 타입에 맞게
		 *  변형해줘야함. ex) memberGender.charAt(0), Integer.parseInt(memberAge), ...
		 *  result는 성공하면 0보다 큰 값이 아니면 그 반대이다.
		 *  0보다 큰 수가 반환되는 이유는 쿼리문을 실행하고 성공한 행의
		 *  갯수가 반환되기 때문이다.
		 *  성공하면 메인페이지로 이동시키고, 실패하면 오류메시지를 출력할
		 *  수 있도록 try ~ catch로 감싸준다. 예외가 발생하면 예외 메시지를
		 *  가져올 수 있는데 그것을 request에 set해서 errorPage.jsp에서 쓸 수
		 *  있도록 해준다. errorPage.jsp 경로 작성시 대소문자 등 오타 주의해야함.
		 *  insertMember() 메소드에 is undefiend 오류메시지가 뜨고 있으므로
		 *  create method를 이용하여 자동생성해준다.
		 */
		
		try {
			//  이곳에 코드를 옮긴다.
			request.setCharacterEncoding("UTF-8");// 한글 깨지지 않게 해줌
			String memberId = request.getParameter("member-id");
			String memberPw = request.getParameter("member-pw");
			String memberName = request.getParameter("member-name");
			String memberGender = request.getParameter("member-gender");
			String memberAge = request.getParameter("member-age");
			String memberEmail = request.getParameter("member-email");
			String memberPhone = request.getParameter("member-phone");
			String memberAddress = request.getParameter("member-address");
			String memberHobby = request.getParameter("member-hobby");
			Member member = new Member(memberId, memberPw, memberName
					, memberGender.charAt(0)
					, Integer.parseInt(memberAge), memberEmail, memberPhone
					, memberAddress, memberHobby);
			MemberService mService = new MemberService();
			int result = mService.insertMember(member);
			if(result > 0) {
				// 성공하면 메인페이지
				response.sendRedirect("/");
			}else {
				request.setAttribute("msg", "Service Failed!!");
				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp")
				.forward(request, response);
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



























