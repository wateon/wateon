package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wateon.WateOn;
import wateon.WateOnUser;
import wateon.entity.ChatRoom;

public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}

	private void call(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = (String)request.getSession().getAttribute("id");
		String targetId = (String)request.getParameter("targetId");
		String message =  (String) request.getParameter("message");
		
		WateOnUser myself = WateOn.getInstance().getWateOnUser(id);
		PrintWriter writer = response.getWriter();
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || myself == null || myself.isLogged() == false)
			writer.println("{ result: 'fail', msg: 'need login' }");
		
		// 상대방 아이디가 있는지 확인한다.
		else if (targetId == null || targetId.equals(""))
			writer.println("{ result: 'fail', msg: 'need target id' }");
		
		// 메시지 내용이 있는지 확인.
		else if (message == null || message.equals(""))
			writer.println("{ result: 'fail', msg: '메시지 내용이 없습니다.' }");
		
		// 해당 상대랑 대화하는 채팅방이 없음.
		else if (myself.hasChatSession(targetId) == false) {
			writer.println("{ result: 'fail', msg: 'not exist chat room' }");
		}
		
		// 성공
		else {
			ChatRoom room = myself.getChatRoom(targetId);
			if (room.sendMessage(message)) {
				// TODO: message 에 URL encoding 이 필요함!!!
				String json = "{ result: 'success', msg: '" + message + "' }";
				writer.println(json);
			}
			else {
				writer.println("{ result: 'fail', msg: '안보내졌음;;;' }");
			}
		}
	}

}
