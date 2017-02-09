package web.command.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.command.Command;

/**
 * @author Golubtsov
 * Sends user to register page
 *
 */
public class RegisterPageCommand  extends Command{
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return "register";
	}
}
