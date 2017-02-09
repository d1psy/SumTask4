package web.command.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.command.Command;

/**
 * @author Golubtsov
 * Opens profile page
 */
public class ProfilePageCommand extends Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return "profile";
	}

}
