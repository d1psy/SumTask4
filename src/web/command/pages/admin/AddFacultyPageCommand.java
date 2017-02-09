package web.command.pages.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Subject;
import web.command.Command;

/**
 * @author Golubtsov
 * Sends admin to add faculty page
 */
public class AddFacultyPageCommand extends Command{

	DBManager dbManager = DBManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		List<Subject> subject = dbManager.getSubjects();
			request.setAttribute("subjects", subject);
		return "admin/addfacultypage";
	}

}
