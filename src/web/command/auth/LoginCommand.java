package web.command.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.dao.DBManager;
import persistence.entity.users.Role;
import persistence.entity.users.User;
import web.command.Command;

/**
 * @author Golubtsov
 * Used to login user to system
 *
 */
public class LoginCommand extends Command {

    DBManager dbManager = DBManager.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		User user = dbManager.loginUser(login, password);
		if (user == null) {
			return "loginpage&error=invalid";
		}
		System.out.println(user.getRole());
		if(user.getRole() == Role.USER) {
			String certPath = dbManager.getCertPhoto(user.getId());
			setSessionCertPhoto(request.getSession(), certPath);
		}
		String path = dbManager.getPhoto(user.getId());
		setSessionUser(request.getSession(), user);
		setSessionPhoto(request.getSession(), path);
		return "profile";
	}

	/**
	 * Sets photo path, if exists
	 * @param session
	 * @param path
	 */
	private void setSessionPhoto(HttpSession session, String path) {
		session.setAttribute("photo", path);
	}
	
	private void setSessionCertPhoto(HttpSession session, String path) {
		session.setAttribute("certPhoto", path);
	}
	
	private void setSessionUser(HttpSession session, User user) {
		session.setAttribute("user", user);
	}
}
