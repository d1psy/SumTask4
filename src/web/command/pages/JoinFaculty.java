package web.command.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import web.command.Command;


/**
 * @author Golubtsov
 * Allows user to join faculty
 */
public class JoinFaculty extends Command{

	DBManager dbManager = DBManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("userid"));
		int user = Integer.parseInt(request.getParameter("userid"));
		int faculty = Integer.parseInt(request.getParameter("faculty"));
		int marks = Integer.parseInt(request.getParameter("marks"));
		dbManager.updateRegCount(faculty);
		if(!dbManager.facultyRegister(user, faculty, marks)){
			return "error";
		}
		return "facultyregisterpage&usrid="+user;
	}

}
