package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String targetId = (String)request.getParameter("targetId");
		
		WateOnUser user = WateOn.getInstance().getWateOnUser(id);
		PrintWriter writer = response.getWriter();
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || user == null || user.isLogged() == false)
			writer.println("need login");
		
		// 상대방 아이디가 있는지 확인한다.
		else if (targetId == null || targetId.equals(""))
			writer.println("need login");
		
		else {
			WateOnUser myself = WateOn.getInstance().getWateOnUser(id);
			
			// 이미, 해당 대화상대와 채팅중인지 확인한다.
			if (myself.hasChatSession(targetId)) {
				viewChatRoomPage(targetId, request, response);
			}
			
			// 채팅중이 아니었으면, 채팅방 만들기를 시도한다.
			else {
				if (myself.createNewChatSession(targetId)) {
					viewChatRoomPage(targetId, request, response);
				}
				else {
					// 실패(안 만들어졌음)
					response.getWriter().println("chatRoom is not created -_-");
					return;
				}
			}
		}
	}

	private void viewChatRoomPage(String targetId, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// view/chat.jsp 를 보여준다.
		String url = "/view/chat.jsp";
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		RequestDispatcher dispatcher  = request.getRequestDispatcher(url);
		
		request.setAttribute("targetId", targetId);
		
		dispatcher.forward(request, response);
	}
	
}
