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
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || myself == null || myself.isLogged() == false)
			writer.println("need login");
		
		// 상대방 아이디가 있는지 확인한다.
		else if (targetId == null || targetId.equals(""))
			writer.println("need target id");
		
		// 해당 대화상대와 채팅 방이 있는지 확인한다.
		else if (myself.hasChatRoom(targetId) == false)
			writer.println("chat room was closed");
		
		// 응답을 json으로 보내준다.
		else {
			String json = messagesToJson(targetId, myself.getChatRoom(targetId).getAllMessages());
			writer.println(json);
		}
	}

	@SuppressWarnings("unchecked")
	private String messagesToJson(String targetId, List<Message> messages) {
		// [
		//   { id: "아이디", nick: "닉네임", msg: "메시지내용" },
		//   { id: "아이디", nick: "닉네임", msg: "메시지내용" },
		//   ...
		// ]
		
		JSONArray results = new JSONArray();
		
		for (Message message : messages) {
			JSONObject msg = new JSONObject();
			msg.put("id", message.getId());
			msg.put("nick", message.getNick());
			msg.put("msg", message.getMessage());
			results.add(msg);
		}
		
		JSONObject json = new JSONObject();
		json.put("result", "success");
		json.put("messages", results);
		return json.toJSONString();
	}
}
