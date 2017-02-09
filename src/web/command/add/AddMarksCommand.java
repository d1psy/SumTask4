package web.command.add;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Marks;
import utils.Validator;
import web.command.Command;

/**
 * @author Golubtsov
 * Used to add user marks to data base
 *
 */
public class AddMarksCommand extends Command{

	DBManager dbManager = DBManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String subject1 = request.getParameter("dsub1");
		String mark1 = request.getParameter("dmark1");
		String subject2 = request.getParameter("dsub2");
		String mark2 = request.getParameter("dmark2");
		String subject3 = request.getParameter("dsub3");
		String mark3 = request.getParameter("dmark3");
		String cert = request.getParameter("dcertmark");
		String userid = request.getParameter("userid");
		if (mark1 == "" | mark2 == "" | mark3 == "" | cert == "") {
			mark1 = "0";
			mark2 = "0";
			mark3 = "0";
			cert = "0";
			return "markspage&usrid=" + userid + "&error=params";
		}
		if (!Validator.validateMarks(Integer.parseInt(subject1), Integer.parseInt(mark1),
				Integer.parseInt(subject2), Integer.parseInt(mark2),
				Integer.parseInt(subject3), Integer.parseInt(mark3), Integer.parseInt(cert))) {
			System.out.println("validation failed");
			return "markspage&usrid=" + userid + "&error=params";
		}
		Marks m = new Marks(1, Integer.parseInt(userid), subject1,
				Integer.parseInt(mark1), subject2,
				Integer.parseInt(mark2), subject3, 
				Integer.parseInt(mark3), Integer.parseInt(cert));
		if(!dbManager.addMarks(m))
			return "markspage&usrid=" + userid + "&error=params";
		return "profile";
	}

}
