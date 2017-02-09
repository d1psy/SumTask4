package web.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import web.command.common.DefaultCommand;

public class CommandFactory {
	final static Logger LOGGER = Logger.getLogger(CommandFactory.class);
	public Command defineCommand(HttpServletRequest request) {
		Command current = null;
		String action = request.getParameter("action");
		LOGGER.debug("commandfactory action: "+action);
		if (action == null || action.isEmpty()) {
			return new DefaultCommand();
		}
		current = CommandStorage.getCommand(action.toUpperCase());
		LOGGER.info("CURRENT COMMAND"+current);
		return current;
	}
}
