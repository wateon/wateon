package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kfmes.natelib.entity.NateFriend;
import kfmes.natelib.msg.InstanceMessage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import wateon.WateOn;
import wateon.WateOnUser;
import wateon.WateonUtil;

public class CheckWateOnStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		call(request, response);
	}

	@SuppressWarnings("unchecked")
	private void call(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = (String)request.getSession().getAttribute("id");
		WateOnUser myself = WateOn.getInstance().getWateOnUser(id);
		
		JSONObject results = new JSONObject();
		
		// FIXME: 지우자
		System.out.println("CheckWateOnStatusServlet");
		
		// 내 아이디가 있는지, 로그인 된 상태인지 확인한다.
		if (id == null || myself == null || myself.isLogged() == false) {
			results.put("result", "fail");
			results.put("msg", "need login");
		}
		
		// 로그인 되었으면..
		else {
			// 접속기록 갱신
			myself.updateTime();
			
			results.put("result", "success");
			results.put("updated", dataToJson(myself));
		}
		
		PrintWriter writer = response.getWriter();
		writer.println(results.toJSONString());
		
		System.out.println(id + " : " + myself);
		
		//System.out.println(results.toJSONString());
	}



	// JSON에서 타입에 담을 객체
	private static final String TYPE = "type";
	private static final String IMESSAGE = "imessage";
	private static final String NEW_CHAT = "new_chat";
	private static final String FRIEND_CHANGED = "friend_changed";

	/**
	 * 그동안 큐에 쌓였던 모든 정보들을, 리스트 하나에 담는다.
	 * @param myself
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private JSONArray dataToJson(WateOnUser myself) {
		JSONArray results = new JSONArray();

		// 그동안 큐에 쌓인 쪽지들..
		for (InstanceMessage imessage : myself.getAllInstanceMessages()) {
			JSONObject imsg = new JSONObject();
			imsg.put(TYPE, IMESSAGE);	// 종류를 쪽지라고 표시한다.
			imsg.put("name", imessage.getFriend().getRealName());
			imsg.put("from", imessage.getFrom());
			imsg.put("msg", imessage.getMessage());
			results.add(imsg);
		}

		// 그동안 새로 열린 채팅창들..
		for (String targetId : myself.getAllReceivedChatRooms()) {
			JSONObject chat = new JSONObject();
			chat.put(TYPE, NEW_CHAT);	// 종류를 새 채팅창이라고 표시한다.
			chat.put("targetId", targetId);
			results.add(chat);
		}

		// 그동안, 상태가 변경된 친구들..
		for (NateFriend friend : myself.getAllFriendModified()) {
			
			JSONObject f = new JSONObject();
			f.put(TYPE, FRIEND_CHANGED);
			f.put("group", WateonUtil.generateGroupId(friend.getGroup().getName()));			
			f.put("id", friend.getID());
			f.put("name", friend.getRealName());
			f.put("nick", friend.getNickName());
			f.put("status", friend.getStatus());
			
			
			// TODO: 등등 필요한 정보를 넘겨준다.
			
			results.add(f);
		}

		return results;
	}
}
