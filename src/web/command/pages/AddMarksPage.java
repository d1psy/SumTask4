package web.command.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Marks;
import persistence.entity.Subject;
import web.command.Command;

/**
 * @author Golubtsov
 * Sends user to page where he can add his marks
 * or, if they exist, shows him his marks
 *
 */
public class AddMarksPage extends Command{

	DBManager dbManager = DBManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("usrid");
		System.out.println(id);
		List<Subject> subject = dbManager.getSubjects();
		request.setAttribute("subjects", subject);
		request.setAttribute("usrid", id);
		Marks m = dbManager.getMarks(Integer.parseInt(id));
		if (m != null) {
			request.setAttribute("marks", m);
			return "user/markspage";
		}
		return "user/addmarks";
	}
	
}
