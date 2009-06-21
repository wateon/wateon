package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import wateon.WateOn;
import wateon.WateOnUser;
import wateon.entity.ChatRoom;
import wateon.entity.Message;

public class CheckMessageServlet extends HttpServlet {
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
		
		WateOnUser myself = WateOn.getInstance().getWateOnUser(id);
		PrintWriter writer = response.getWriter();
		
		String result = null;
		String msg = "";
		
		JSONArray messages = null;
		ChatRoom room = null;
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || myself == null || myself.isLogged() == false)
			msg = "need login";
		
		// 로그인 되어있으면,
		else {
			// 접속기록 갱신
			myself.updateTime();
			
			// 상대방 아이디가 있는지 확인한다.
			if (targetId == null || targetId.equals(""))
				msg = "need target id";
			
			// 해당 대화상대와 채팅 방이 있는지 확인한다.
			else if (myself.hasChatRoom(targetId) == false)
				// TODO: 채팅방을 새로 만들어준다??
				msg = "chat room was closed";
			
			// 응답을 json으로 보내준다.
			else {
				// 채팅방 접속기록 갱신
				room = myself.getChatRoom(targetId);
				room.updateTime();
				messages = messagesToJson(room);
				result = "success";
				msg = "";
			}
		}
		
		if (result == null)
			result = "fail";
		
		JSONObject jsonResults = generateJSONResults(room, result, msg, messages);
		writer.println(jsonResults.toJSONString());
	}

	@SuppressWarnings("unchecked")
	private JSONObject generateJSONResults(ChatRoom room, String result, String msg, JSONArray messages) {
		JSONObject jsonResults = new JSONObject();
		jsonResults.put("result", result);
		jsonResults.put("msg", msg);
		jsonResults.put("messages", messages);

		if (room != null) {
			// 타이핑 여부
			jsonResults.put("typing", room.getTypingMessage());

			// // 채팅방 닫혔는지 여부
			// if (room.isConnected() == false)
			// jsonResults.put("closed", true);
		}

		return jsonResults;
	}

	@SuppressWarnings("unchecked")
	private JSONArray messagesToJson(ChatRoom room) {
		// [
		//   { id: "아이디", nick: "닉네임", msg: "메시지내용" },
		//   { id: "아이디", nick: "닉네임", msg: "메시지내용" },
		//   ...
		// ]
		
		JSONArray results = new JSONArray();
		List<Message> messages = room.getAllMessages();
		
		for (Message message : messages) {
			JSONObject msg = new JSONObject();
			msg.put("id", message.getId());
			msg.put("nick", message.getNick());
			msg.put("msg", message.getMessage());
			results.add(msg);
		}
		
		return results;
	}
}
