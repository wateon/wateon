package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = (String)request.getSession().getAttribute("id");
		String targetId = request.getParameter("targetId");
		
		String action = request.getParameter("action");
		
		WateOnUser myself = WateOn.getInstance().getWateOnUser(id);
		PrintWriter writer = response.getWriter();
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || myself == null || myself.isLogged() == false)
			writer.println("need login");
		
		// 상대방 아이디가 있는지 확인한다.
		else if (targetId == null || targetId.equals(""))
			writer.println("need target id");
		
		// 이미, 해당 대화상대와 채팅중인지 확인한다.
		else if (myself.hasChatRoom(targetId)) {
			if (action != null && action.equals("close"))
				closeChatRoom(myself, targetId, writer);
			else
				viewChatRoomPage(targetId, request, response);
		}
		
		// 채팅중이 아니었으면, 채팅방 만들기를 시도하고, 성공했으면, 방을 보여준다.
		else if (myself.createNewChatSession(targetId))
			viewChatRoomPage(targetId, request, response);
		
		// 실패(안 만들어졌음)
		else
			writer.println("chatRoom is not created -_-");
	}

	@SuppressWarnings("unchecked")
	private void closeChatRoom(WateOnUser myself, String targetId, PrintWriter writer) {
		String result;
		String msg;
		
		System.out.println("요청받음 close : " + targetId);
		
		// 이미, 해당 대화상대와 채팅중인지 확인한다.
		if (myself.hasChatRoom(targetId)) {
			myself.getChatRoom(targetId).close();
			result = "success";
			msg = "ok";
		}
		
		// 실패 (그런 채팅방 없음.)
		else {
			result = "fail";
			msg = "not exist chat room";
		}
		
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("msg", msg);
		writer.println(json);
	}

	private void viewChatRoomPage(String targetId, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setAttribute("targetId", targetId);
		
		// view/chat.jsp 를 보여준다.
		String url = "/view/chat.jsp";
		RequestDispatcher dispatcher  = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
