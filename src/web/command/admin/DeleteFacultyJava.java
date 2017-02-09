package web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import utils.Validator;
import web.command.Command;

/**
 * @author Golubtsov
 * Used to delete a faculty from data base
 *
 */
public class DeleteFacultyJava extends Command{
    DBManager dbManager=DBManager.getInstance();
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String facultyIdString = request.getParameter("faculty");
		if (Validator.nullOrEmpty(facultyIdString)) {
			return "error";
		}
		int facultyId = -1;
		try {
			facultyId = Integer.parseInt(facultyIdString);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "error";
		}
		if(!dbManager.deleteFaculty(facultyId)){
			System.out.println("db error");
			return "error";
		}
		return "facultylist";
	}
}
