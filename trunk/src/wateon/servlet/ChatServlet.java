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
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
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
			
			if (chatSession == null) {
				response.getWriter().println("chatSession is null -_-");
				return;
			}
			
			// 채팅방을 현재 아이디의 목록에 추가해준다.
			long sessionNo = myself.addChatSession(chatSession);
			
			// 채팅방세션 번호를 넣는다. (유저마다 별개이다.)
			request.setAttribute("chatSessionNo", sessionNo);
			
			// view/chat.jsp 를 보여준다.
			String url = "/view/chat.jsp";
			RequestDispatcher dispatcher  = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
