package wateon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kfmes.natelib.entity.NateGroup;

import wateon.WateOn;
import wateon.WateOnUser;

public class GroupManagementServlet extends HttpServlet {
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
		
		if ("create".equals(action)) {
			String groupName = request.getParameter("groupName");
			WateOnUser myself = WateOn.getInstance().getWateOnUser((String)request.getSession().getAttribute("id"));
			myself.getNateonMessenger().getNS().addGroup(groupName);
		}
		else if ("delete".equals(action)) {
			String groupName = request.getParameter("groupName");
			WateOnUser myself = WateOn.getInstance().getWateOnUser((String)request.getSession().getAttribute("id"));
			NateGroup group = myself.getNateonMessenger().getBuddyGroup().getGroup(groupName); 
			myself.getNateonMessenger().getNS().removeGroup(group);
		}
		else if ("modify".equals(action)) {
			String groupName = request.getParameter("groupName");
			String modifyGroupName = request.getParameter("modifyGroupName");
			WateOnUser myself = WateOn.getInstance().getWateOnUser((String)request.getSession().getAttribute("id"));
			NateGroup group = myself.getNateonMessenger().getBuddyGroup().getGroup(groupName); 
			myself.getNateonMessenger().getNS().renameGroup(group, modifyGroupName);
		}
	}
}
