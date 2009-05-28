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

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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

	private void call(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession(true).getAttribute("id");
		WateOnUser user = WateOn.getInstance().getWateOnUser(id);
		
		if (user != null && user.isLogged()) {
			// TODO: 리스트를 보여준다.
			String url = "/view/main.jsp";
			
			// FIXME: 나중에 친구의 다른 정보도 포함해서 돌려주자.
			GroupList groups = user.getNateonMessenger().getBuddyGroup();
			request.setAttribute("groups", groups.getList());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("view/login_form.jsp");
		}
	}

}
