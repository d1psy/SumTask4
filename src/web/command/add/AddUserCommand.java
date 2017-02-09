package web.command.add;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.users.User;
import persistence.entity.users.Role;
import utils.Validator;
import web.command.Command;

/**
 * @author Golubtsov
 * Used to add new user to data base
 *
 */
public class AddUserCommand extends Command {
	DBManager dbManager = DBManager.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("HERE!");
		String name = request.getParameter("pname");
		String surname = request.getParameter("psurname");
		String patronymic = request.getParameter("ppatronymic");
		String region = request.getParameter("pregion");
		String city = request.getParameter("pcity");
		String school = request.getParameter("pschool");
		String login = request.getParameter("plogin");
		String email = request.getParameter("pemail");
		String password = request.getParameter("ppassword");
		//login, password, name, surname, patronymic, email, region, city, school
		if (!Validator.validateUser(login, name, email, password)) {
			setParamError(request);
			return "register&error=params";
		}
		User p = new User(1, login, password, name, surname, patronymic, email, region, city, school, Role.USER);
		if (!dbManager.addUser(p)) {
			return "error";
		}
		return "loginpage";
		
	}

}