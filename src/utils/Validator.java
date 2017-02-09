package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistence.dao.DBManager;

/**
 * @author Golbutsov
 *
 */
public class Validator {
    
	/**
	 * Used to validate user register
	 * @param login
	 * @param name
	 * @param email
	 * @param password
	 * @return boolean
	 */
	public static boolean validateUser(String login, String name,
	        String email, String password) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		System.out.println("CHECK: " + m);

		DBManager dbManager = DBManager.getInstance();
		if (nullOrEmpty(login, name, email, password))
			return false;
		if(!m.matches()) {
			return false;
		}
//		if (!email.contains("@")) {
//			return false;
//		}
		if (login.length() < 3 || login.length() > 20) {
			return false;
		}

		if(!dbManager.checkLogin(login)){
			return false;
		}
		
		return true;
	}
	
/**
 * Used to validate faculty creation
 * @param name
 * @param maxplace
 * @param budget
 * @param subject1
 * @param subject2
 * @param subject3
 * @return boolean
 */
public static boolean validateFaculty(String name, int maxplace,
        int budget, int subject1, int subject2, int subject3) {
		
		DBManager dbManager = DBManager.getInstance();
		if(maxplace == 0 | budget == 0) {
			System.out.println("1");
			return false;
		}
		if(nullOrEmpty(name, Integer.toString(subject1),
				Integer.toString(subject2), Integer.toString(subject3))) {
			System.out.println("2");
			return false;
		}
		if(subject1 == subject2 | subject1 == subject3 | subject2 == subject3) {
			System.out.println("3");
			return false;
		}
		if(!dbManager.checkFaculty(name)) {
			System.out.println("4");
			return false;
		}
		if(budget > maxplace)
			return false;
		return true;
	}

/**
 * Used to validate marks that user inputs
 * @param subject1
 * @param mark1
 * @param subject2
 * @param mark2
 * @param subject3
 * @param mark3
 * @param cert
 * @return
 */
public static boolean validateMarks(int subject1, int mark1,
        int subject2, int mark2, int subject3, int mark3, int cert) {
	System.out.println("HELLO!!!");
	if(nullOrEmpty(Integer.toString(subject1), Integer.toString(mark1), 
			Integer.toString(subject2), Integer.toString(mark2),
			Integer.toString(subject3), Integer.toString(mark3),
			Integer.toString(cert))) {
		System.out.println("1");
		return false;
	}
	if(subject1 == subject2 | subject1 == subject3 | subject2 == subject3) {
		System.out.println("2");
		return false;
	}
	if(mark1 > 200 | mark1 < 100 | mark2 > 200 | mark2 < 100  
	        | mark3 > 200 | mark3 < 100 | cert < 0 | cert > 60) {
		System.out.println("3");
		return false;
	}
	return true;
}

/**
 * Used to validate edited faculty
 * @param name
 * @param maxplace
 * @param budget
 * @param subject1
 * @param subject2
 * @param subject3
 * @return
 */
public static boolean validateEditFaculty(String name, int maxplace,
        int budget, int subject1, int subject2, int subject3) {
	
	if(nullOrEmpty(name, Integer.toString(maxplace), Integer.toString(budget),
			Integer.toString(subject1), Integer.toString(subject2),
			Integer.toString(subject3)))
		return false;
	if(subject1 == subject2 | subject1 == subject3 | subject2 == subject3)
		return false;
	if(budget > maxplace)
		return false;
	return true;
}

	public static boolean nullOrEmpty(String... strings) {
		for (String string : strings) {
			if (string == null || string.isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
