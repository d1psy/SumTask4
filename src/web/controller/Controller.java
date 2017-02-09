package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import web.command.Command;
import web.command.CommandFactory;



/**
 * @author Golubtsov
 * Controller that accepts requests and opens jsp files
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Controller.class);

	/**
	 * executes requested command and does
	 * forwarding to the jsp page
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		String action = processRequest(request, response);
		forwardJsp(request, response, action);
	}

	/**
	 * executes requested command and does
	 * POST-REDIRECT-GET to the action
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		String action = processRequest(request, response);
		if (action == null || action.isEmpty()) {
			response.sendError(404);
		} else {
			response.sendRedirect("Controller?action=" + action);
		}
	}

	private String processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("process request");
		try {
			CommandFactory commandFactory = new CommandFactory();
			Command command = commandFactory.defineCommand(request);
			String action = command.execute(request, response);
			logger.debug("action " + action);
			return action;
		} catch (NullPointerException ex) {
			return null;
		}
	}

	private void forwardJsp(HttpServletRequest request,
			HttpServletResponse response, String action)
			throws ServletException, IOException {
		System.out.println("forwardJsp");
		
		request.getRequestDispatcher("WEB-INF/jsp/" + action + ".jsp").forward(
				request, response);
	}

}
