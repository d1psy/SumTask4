package web.command.pages.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.Faculty;
import persistence.entity.comparators.FacultyBudgetComparator;
import persistence.entity.comparators.FacultyNameComparator;
import persistence.entity.comparators.FacultyPlaceComparator;
import persistence.entity.comparators.FacultyCountComparator;
import utils.Validator;
import web.command.Command;

/**
 * @author Golubtsov
 * Shows list of existing faculties to admin
 */
public class ListFacultiesAdmin extends Command {

    DBManager dbManager = DBManager.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		List<Faculty> faculties = dbManager.getFaculties();
		String filterValue = request.getParameter("filtervalue");
		if (!Validator.nullOrEmpty(filterValue)) {
			filterValue = getUtf(filterValue);
			faculties = filter(faculties, filterValue);
		}
		String sorting = defineSorting(request);
		sortFaculties(faculties, sorting);
		request.setAttribute("facultylist", faculties);
		return "admin/facultylist";
	}
	
	private String getUtf(String filterValue) {
		String filterUtf = "";
		try {
			filterUtf = new String(filterValue.getBytes("iso-8859-1"), "UTF-8");
			System.out.println("utf" + filterUtf);
		} catch (UnsupportedEncodingException e) {
			return filterValue;
		}
		return filterUtf;
	}
	
	private List<Faculty> filter(List<Faculty> faculties, String filterValue) {
		List<Faculty> filteredFaculties = new ArrayList<>();
		for (int i = 0; i < faculties.size(); i++) {
			if (faculties.get(i).getName().contains(filterValue)) {
				filteredFaculties.add(faculties.get(i));
			}
		}
		return filteredFaculties;
	}
	
	private void sortFaculties(List<Faculty> faculties, String sorting) {
		switch (sorting) {
		case "namesort": {
			Collections.sort(faculties, new FacultyNameComparator());
			break;
		}
		case "placesort":
			Collections.sort(faculties, new FacultyPlaceComparator());
			break;
		case "budgetsort": 
			Collections.sort(faculties, new FacultyBudgetComparator());
			break;
		case "countsort":
			Collections.sort(faculties, new FacultyCountComparator());
			break;
		}
		return;
	}
	
	private String defineSorting(HttpServletRequest request) {
		String requestSort = request.getParameter("sort");
		if (Validator.nullOrEmpty(request.getParameter("sort"))) {
			requestSort = "";
		}
		System.out.println(requestSort);
		return requestSort;
	}
}
