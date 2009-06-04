package wateon.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wateon.WateOn;
import wateon.WateOnUser;

import kfmes.natelib.NateonMessenger;
import kfmes.natelib.entity.NateFriend;
import kfmes.natelib.msg.InstanceMessage;

public class SendInstanceMessageServlet extends HttpServlet {
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
		
		// TODO Auto-generated method stub
		
		String targetId = request.getParameter("targetId");
		String senderId = request.getParameter("senderId");
		String message = request.getParameter("message");
		WateOnUser myself = WateOn.getInstance().getWateOnUser(senderId);
		
		
		InstanceMessage msg = new InstanceMessage(senderId, targetId, message);
		myself.getNateonMessenger().sendIMessage(msg);

		String url = "view/close.jsp";
		response.sendRedirect(url);
	}
}
