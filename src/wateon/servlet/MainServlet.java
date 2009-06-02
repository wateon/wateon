package wateon.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kfmes.natelib.entity.GroupList;

import wateon.WateOn;
import wateon.WateOnUser;

public class MainServlet extends HttpServlet {
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
		WateOnUser user = WateOn.getInstance().getWateOnUser(id);
		
		if (id != null && user != null && user.isLogged()) {
			// FIXME: 나중에 친구의 다른 정보도 포함해서 돌려주자.
			GroupList groups = user.getNateonMessenger().getBuddyGroup();
			request.setAttribute("groups", groups.getList());
			
			request.setAttribute("myself", user.getNateonMessenger().getOwner());
			
			String url = "/view/main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

}
