package wateon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wateon.WateOn;
import wateon.WateOnUser;

public class MyStatusServlet extends HttpServlet {
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
		PrintWriter writer = response.getWriter();
		String action = (String) request.getParameter("action");
		String id = (String)request.getSession().getAttribute("id");
		String newStatus;
		String newNickName;
		WateOnUser myself;
		try {			
			
			myself = WateOn.getInstance().getWateOnUser(id);
			
			//닉네임 병경시
			if ("nickName".equals(action)) {
				newNickName = (String) request.getParameter("newNickName");
				myself.getNateonMessenger().setMyNickName(newNickName);
			//현재 상태 변경시
			} else if ("status".equals(action)) {
				newStatus = (String) request.getParameter("newStatus");
				if(newStatus.length()!= 1)
					throw new Exception();
				/*
				public final static String Online = "O";
				public final static String Away = "A";
				public final static String Busy = "B"; 
				public final static String Phone = "P";
				public final static String Meeting = "M";
				public final static String Invisible = "X";
				public final static String Offline = "F";
				 */
				if("OABPMXF".indexOf(newStatus) < 0)
					throw new Exception();				
				myself.getNateonMessenger().setMyStatus(newStatus);
			//두가지 액션이 아닐경우 예외
			}else{
				throw new Exception();
			}			
		} catch (Exception e) {
			writer.println("illegal accessing~!!!");
		} finally {
			
		}
	
	}
}
