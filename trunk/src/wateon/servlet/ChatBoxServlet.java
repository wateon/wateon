package wateon.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wateon.DB.MessageDAO;
import wateon.DB.MessageDTO;

/**
 * Servlet implementation class ChatBoxServlet
 */
public class ChatBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private MessageDAO dao = null;
	private Vector<MessageDTO> v = null;

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
	
	private void call(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		dao = new MessageDAO();

		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String url = "/view/chatbox.jsp";
		
		if(type!=null) {
		
			if(type.equals("sendIMessages")) {
				
				v = dao.readSendIMessages(id);
				
			} else if(type.equals("receiveIMessages")) {
	
				v = dao.readReceiveIMessages(id);
				
			} else if(type.equals("messages")) {
				
				v = dao.readMessages(id);
				
			} else {
	
				v = null;
				
			}

			request.setAttribute("v", v);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
