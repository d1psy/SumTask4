package web.command.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.command.Command;

/**
 * @author Golubtsov
 * Opens login page
 */
public class LoginPageCommand extends Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		if(alreadyLoggedIn(request.getSession())){
			return "profile";
		}
		return "loginpage";
	}

	private boolean alreadyLoggedIn(HttpSession session) {
		return session.getAttribute("user")!=null;
	}

}
