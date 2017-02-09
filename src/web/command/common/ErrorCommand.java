package web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.command.Command;

public class ErrorCommand extends Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return "error";
	}

}
