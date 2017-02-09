package web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Faculty;
import utils.Validator;
import web.command.Command;
/**
 * @author Golubtsov
 * Used to edit a faculty
 *
 */
public class EditFacultyCommand extends Command{
	DBManager dbManager = DBManager.getInstance();
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("dname");
		String maxplace = request.getParameter("dplace");
		String budget = request.getParameter("dbudget");
		String idString = request.getParameter("did");
		if (budget == "" || maxplace == ""){
			return "editfacultypage&faculty=" + idString + "&error=params";
		}
		String subject1 = request.getParameter("dsub1");
		String subject2 = request.getParameter("dsub2");
		String subject3 = request.getParameter("dsub3");
		String count = request.getParameter("count");
		if (!Validator.validateEditFaculty(name, Integer.parseInt(maxplace), Integer.parseInt(budget),
				Integer.parseInt(subject1), Integer.parseInt(subject2), Integer.parseInt(subject3))) {
			System.out.println("validation failed");
			return "editfacultypage&faculty=" + idString + "&error=params";
		}
		Faculty p = new Faculty(Integer.parseInt(idString), name, Integer.parseInt(maxplace), Integer.parseInt(budget), subject1, subject2, subject3, Integer.parseInt(count));
		if (!dbManager.editFaculty(p)) {
			return "editfacultypage&faculty=" + idString + "&error=params";
		}
		return "profile";
	}
}
