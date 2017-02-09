package web.command.pages.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Faculty;
import persistence.entity.Subject;
import web.command.Command;

/**
 * @author Golubtsov
 * Sends admin to edit faculty page
 */
public class EditFacultyPage extends Command {

	DBManager dbManager = DBManager.getInstance();
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("faculty"));
		Faculty faculty = dbManager.findFacultyById(id);
		List<Subject> subject = dbManager.getSubjects();
		request.setAttribute("subjects", subject);
		request.setAttribute("faculty", faculty);
		

		return "admin/editfacultypage";
	}
}
