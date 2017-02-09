package web.command.pages.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.users.User;
import web.command.Command;

/**
 * @author Golubtsov
 * Opens page that shows all users to admin
 */
public class ListUsers extends Command {

    DBManager dbManager = DBManager.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("method started");
		List<User> users = dbManager.getUsers();
		request.setAttribute("userlist", users);
		System.out.println("user size: " + users.size());
		return "admin/userlist";
	}
}