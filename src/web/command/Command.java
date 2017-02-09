package web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public abstract class Command {
	/**
	 * Executes specified command
	 * 
	 * @param request
	 * @param response
	 * @return {@link String} return action
	 */
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response);
	protected void setParamError(HttpServletRequest request){
		request.setAttribute("errorvalue", "params");
	}
}
