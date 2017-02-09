package web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import persistence.entity.users.Role;
import persistence.entity.users.User;
import utils.Validator;

@WebFilter("/SecurityFilter")
public class SecurityFilter implements Filter {
	final static Logger logger = Logger.getLogger(SecurityFilter.class);
	private HashMap<Role, List<String>> roleAccess = new HashMap<>();
	List<String> commonAccess = new ArrayList<String>();

	public SecurityFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String action = request.getParameter("action");
		logger.debug("security: action " + action);
		if (allowedAccess(action, req)) {
			logger.debug("allowed");
			chain.doFilter(request, response);
		} else {
			resp.sendError(403);
		}
	}

	private boolean allowedAccess(String action, HttpServletRequest request) {
		if (commonPageAccess(action))
			return true;
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return false;
		}
		Role role = user.getRole();
		if (verifyRoleAccess(role, action)) {
			return true;
		}
		return false;
	}

	private boolean verifyRoleAccess(Role role, String action) {
		List<String> access = roleAccess.get(role);
		for (String token : access) {
			if (action.equalsIgnoreCase(token))
				return true;
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		List<String> adminAccess = Arrays.asList("userlist", "blockuser", "unblockuser",
				"facultylist", "addfaculty", "editfacultypage",
				"addfacultypage", "deletefaculty", "userprofile",
				"editfaculty", "facultyinfoadmin",
				"createstatement", "sendemail");
		roleAccess.put(Role.ADMIN, adminAccess);
		commonAccess = Arrays.asList("login", "loginpage", "error", "profile",
				"logout", "register", "adduser", "uploadcommand", "markspage", "addmarks",
				"facultyregisterpage", "joinfaculty", "facultyinfopage");
	}

	private boolean commonPageAccess(String action) {
		if (Validator.nullOrEmpty(action))
			return true;
		for (String item : commonAccess) {
			if (action.equalsIgnoreCase(item))
				return true;
		}
		return false;
	}
}
