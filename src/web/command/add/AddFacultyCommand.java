package web.command.add;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Faculty;
import utils.Validator;
import web.command.Command;

/**
 * @author Golubtsov
 * Used to add new faculty to data base
 *
 */
public class AddFacultyCommand extends Command {
	DBManager dbManager = DBManager.getInstance();
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("dname");
		String maxplace = request.getParameter("dplace");
		String budget = request.getParameter("dbudget");
		String subject1 = request.getParameter("dsub1");
		String subject2 = request.getParameter("dsub2");
		String subject3 = request.getParameter("dsub3");
		if (!Validator.validateFaculty(name, Integer.parseInt(maxplace), Integer.parseInt(budget),
				Integer.parseInt(subject1), Integer.parseInt(subject2), Integer.parseInt(subject3))) {
			System.out.println("validation failed");
			return "addfacultypage&error=params";
		}
		Faculty p = new Faculty(1, name, Integer.parseInt(maxplace), Integer.parseInt(budget), subject1, subject2, subject3, 0);
		if (!dbManager.addFaculty(p)) {
			System.out.println("validation failed");
			return "addfacultypage&error=params";
		}
		return "profile";
		
	}

}
