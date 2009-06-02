package wateon.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kfmes.natelib.NateonMessenger;
import kfmes.natelib.SwitchBoardSession;

import wateon.WateOn;
import wateon.WateOnUser;

/**
 * Servlet implementation class ChatServlet
 */
public class CreateChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateChatServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}

	private void call(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String targetId = (String)request.getParameter("targetId");
		String id = (String)request.getSession().getAttribute("id");
		
		// 내 아이디랑, 상대방 아이디가 있는지 확인한다.
		if (targetId == null || targetId.equals("") || id == null) {
			response.getWriter().println("need login");
		}
		
		else {
			// 상대방 아이디를 이용해서, 채팅방을 만들어주고,
			WateOnUser myself = WateOn.getInstance().getWateOnUser(id);
			NateonMessenger nate = myself.getNateonMessenger();
			SwitchBoardSession chatSession = nate.getNS().procOpenChat(id, targetId);
			
			System.err.println("id          : " + id);
			System.err.println("target      : " + targetId);
			
			System.err.println("count       : " + chatSession.getFriendCount());
			System.err.println("connected   : " + chatSession.isConnected());
			
			// 채팅방을 현재 아이디의 목록에 추가해준다.
			long sessionNo = myself.addChatSession(chatSession);
			
			// redirection 해준다.
			String url = "chat.do?no=" + sessionNo;
			response.sendRedirect(url);
		}
	}
}
