package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kfmes.natelib.NateonMessenger;
import kfmes.natelib.SwitchBoardSession;

import wateon.WateOn;
import wateon.WateOnUser;

public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}
	
	private void call(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = (String)request.getSession().getAttribute("id");
		String sessionNoParameter = (String)request.getParameter("no");
		long sessionNo = Long.parseLong(sessionNoParameter);
		
		WateOnUser user = WateOn.getInstance().getWateOnUser(id);
		PrintWriter writer = response.getWriter();
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || user == null || user.isLogged() == false)
			writer.println("need login");
		
		// 방 번호를 매개변수로 받았는지 확인한다.
		else if (sessionNoParameter == null)
			writer.println("need chatSessionNo");
		
		// 해당 방이 아직 열려있는지 확인한다.
		else if (user.hasChatSession(sessionNo) == false)
			writer.println("the chat room was closed");
		
		// 모두 통과되면, 채팅방을 보여준다.
		else {
			// 채팅방세션 번호를 넣는다. (유저마다 별개이다.)
			request.setAttribute("chatSessionNo", sessionNo);
			
			// view/chat.jsp 를 보여준다.
			String url = "/view/chat.jsp";
			RequestDispatcher dispatcher  = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
