package web.command.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.command.Command;

/**
 * @author Golubtsov
 * Logs out user from system
 *
 */
public class LogoutCommand extends Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		unvalidateSession(request.getSession());
		return "loginpage";
	}

	private void unvalidateSession(HttpSession session) {
		session.invalidate();
	}

}
