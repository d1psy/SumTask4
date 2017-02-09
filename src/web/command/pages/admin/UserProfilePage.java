package web.command.pages.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Marks;
import persistence.entity.users.User;
import web.command.Command;

/**
 * @author Golubtsov
 * Opens page that shows user info to admin
 */
public class UserProfilePage extends Command{

	DBManager dbManager = DBManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("user"));
		Marks m = dbManager.getMarks(id);
		String photoPath = dbManager.getPhoto(id);
		User user = dbManager.findUserById(id);
		String certPath = dbManager.getCertPhoto(id);
		request.setAttribute("user", user);
		request.setAttribute("path", photoPath);
		request.setAttribute("cert", certPath);
		request.setAttribute("marks", m);
		System.out.println("HELLO!!: " + photoPath);
		return "admin/userprofile";
	}
	
}
