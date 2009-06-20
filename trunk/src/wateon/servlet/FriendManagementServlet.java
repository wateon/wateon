package wateon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kfmes.natelib.entity.NateFriend;

import wateon.WateOn;
import wateon.WateOnUser;

public class FriendManagementServlet extends HttpServlet {
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
		
		String action = request.getParameter("action");
		String friendId = request.getParameter("friendId");
		WateOnUser myself = WateOn.getInstance().getWateOnUser((String)request.getSession().getAttribute("id"));

		
		if (action.equals("add")) {
			myself.getNateonMessenger().getNS().requestAdd(friendId, friendId +"님이 친구를 요청하셨습니다.");
		}
		else if (action.equals("delete")) {
			NateFriend friend = new NateFriend(friendId);
			myself.getNateonMessenger().getNS().removeBuddy(friend);
		}
		else if (action.equals("ban")) {
			NateFriend friend = new NateFriend(friendId);
			friend.setFABR(friendId);
			friend.setUid(friendId);
			myself.getNateonMessenger().getNS().sendBlock(friend, false);
		}
		else if (action.equals("unban")) {
			// TODO: 이거 뭐야?? 차단 푸는거??
		}
	}

}
