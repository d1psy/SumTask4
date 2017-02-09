package web.command.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Faculty;
import persistence.entity.FacultyRegister;
import persistence.entity.Marks;
import web.command.Command;

/**
 * @author Golubtsov
 * Opens register on faculty page
 */
public class RegisterOnFaculty extends Command {

    DBManager dbManager = DBManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("usrid"));
		List<Faculty> faculties = dbManager.getUserFaculties(id);
		Marks marks = dbManager.getMarks(id);
		List<FacultyRegister> fr = dbManager.findFacReg(id);
		request.setAttribute("facultylist", faculties);
		request.setAttribute("marks", marks.getId());
		request.setAttribute("userid", id);
		request.setAttribute("facreg", fr);
		return "user/facultyregister";
	}

}
