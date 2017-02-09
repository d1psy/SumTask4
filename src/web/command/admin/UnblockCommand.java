package web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import utils.Validator;
import web.command.Command;

/**
 * @author Golubtsov
 * Used to unblock user
 *
 */
public class UnblockCommand extends Command {
    DBManager dbManager=DBManager.getInstance();
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String userIdString = request.getParameter("user");
		if (Validator.nullOrEmpty(userIdString)) {
			return "error";
		}
		int userId = -1;
		try {
			userId = Integer.parseInt(userIdString);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "error";
		}

		dbManager.unblockRegCount(userId);
		System.out.println(userId);
		if(!dbManager.unblockUser(userId)){
			System.out.println("db error");
			return "error";
		}
		return "userlist";
	}

}