package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import kfmes.natelib.util.MsgUtil;

import wateon.WateOn;
import wateon.WateOnUser;
import wateon.DB.MessageDAO;
import wateon.DB.MessageDTO;
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
		
		Map<String, String> result = new HashMap<String, String>();
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || myself == null || myself.isLogged() == false) {
			result.put("result", "fail");
			result.put("msg", "need login");
		}
		
		else {
			// 접속기록 갱신
			myself.updateTime();
			
			
			// 상대방 아이디가 있는지 확인한다.
			if (targetId == null || targetId.equals("")) {
				result.put("result", "fail");
				result.put("msg", "need target id");
			}
			
			// 메시지 내용이 있는지 확인.
			else if (message == null || message.equals("")) {
				result.put("result", "fail");
				result.put("msg", "메시지 내용이 없습니다.");
			}
			
			// 해당 상대랑 대화하는 채팅방이 없음.
			else if (myself.hasChatRoom(targetId) == false) {
				result.put("result", "fail");
				result.put("msg", "not exist chat room");
			}
			
			// 성공
			else {
				ChatRoom room = myself.getChatRoom(targetId);
				if (room.sendMessage(message)) {
					String nick = MsgUtil.getRealString(myself.getNateonMessenger().getOwner().getNickName());
					
					result.put("result", "success");
					result.put("id", id);
					result.put("nick", nick);
					result.put("msg", message);
				}
				else {
					result.put("result", "fail");
					result.put("msg", "안보내졌음;;;");
				}
			}
		}
		
		String json = JSONValue.toJSONString(result);
		MessageDTO dto = new MessageDTO(id, targetId, message);
		new MessageDAO().insertMessage(dto);
		writer.println(json);
	}

}
