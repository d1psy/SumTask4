package web.command.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.users.User;
import web.command.Command;

/**
 * @author Golubtsov
 */
public class FacultyInfoPage extends Command{

	DBManager dbManager = DBManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("HELLO");
		int facultyId = 0;
		facultyId = Integer.parseInt(request.getParameter("faculty"));
		int maxPlace = dbManager.getMaxPlace(facultyId);
		int budget = dbManager.getBudget(facultyId);
		List<User> budgetUsers = dbManager.getBudgetUsers(facultyId, budget);
		List<User> nonBudgetUsers = dbManager.getNonBudgetUsers(facultyId, budget, maxPlace-budget);
		List<User> otherUsers = dbManager.getOtherUsers(facultyId, maxPlace);
		request.setAttribute("budgetusers", budgetUsers);
		request.setAttribute("nonbudgetusers", nonBudgetUsers);
		request.setAttribute("otherusers", otherUsers);
		request.setAttribute("faculty", facultyId);
		return "user/facultyinfopage";
	}

}
