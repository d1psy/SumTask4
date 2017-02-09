package persistence;

/**
 * @author Golubtsov
 *
 */
public final class SQLQueries {
    /**
     * 
     */
    private SQLQueries() {
        //
    }

	/**
	 * Used to Insert new users in DB
	 */
	public static final String INSERT_USERS = "INSERT INTO `user` "
	        + "(`login`, `password`,`name`, `surname`, `patronymic`,"
	        + " `email`, `region`, `city`, `school`)" 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	/**
	 * Used to check if user has registered for specific faculty
	 */
	public static final String GETREGISTER = "SELECT * "
	        + "FROM faculty_register "
	        + "where user_id = ? ";
	
	/**
	 * Used to check if user exists
	 */
	public static final String CHECK_USERS = "SELECT * from user"
	        + " where `login` = ? LIMIT 1";
	/**
	 * Used to check if faculty exists
	 */
	public static final String CHECK_FACULTIES = "SELECT * from "
	        + "faculty where `name` = ? LIMIT 1";
	
	/**
	 * Used to check user's marks
	 */
	public static final String CHECK_MARKS = "Select marks_id, "
	        + "us_id, subject1, subject2,"
			+ " subject3, certmark, subname1.subject_name,"
			+ "subname2.subject_name, subname3.subject_name " + 
			"FROM user_marks " + 
			"JOIN subjects as subname1 " + 
			"ON subname1.id_subj = subject1_id " + 
			"JOIN subjects as subname2 " + 
			"ON subname2.id_subj = subject2_id " + 
			"JOIN subjects as subname3 " + 
			"ON subname3.id_subj = subject3_id " + 
			"WHERE us_id = ? LIMIT 1";
	/**
	 * Used to add new faculty
	 */
	public static final String ADDFACULTY = "INSERT INTO `faculty`"
	        + " (`name`, `maxplace`, `budget`, `subject1`, `subject2`,"
	        + " `subject3`, `reg_count`) VALUES (?,?,?,?,?,?,0);";
	
	/**
	 * Used to list all users of faculty
	 */
	public static final String LISTUSERFACULTY = "SELECT * " + 
			"FROM user" + 
			"	join faculty_register" + 
			"    on faculty_register.user_id = user.user_id" + 
			"		join faculty" + 
			"        on faculty_register.fac_id = faculty.id_faculty" + 
			"        and faculty.id_faculty = ?";
	
	/**
	 * Used to select users that have passed to faculty
	 */
	public static final String LISTOFPASSED = "SELECT name, surname,"
	        + " patronymic, email, "
			+ "SUM(user_marks.subject1 + user_marks.subject2 +"
			+ " user_marks.subject3 + user_marks.certmark) " 
			+ "FROM user" 
			+ "	JOIN user_marks " 
			+ "    ON user_marks.us_id = user.user_id" 
			+ "    JOIN faculty_register" 
			+ "    ON faculty_register.user_id = user.user_id" 
			+ "    AND faculty_register.fac_id = ?" 
			+ "group by login "
			+ "ORDER BY MAX(user_marks.subject1 + user_marks.subject2"
			+ " + user_marks.subject3 + user_marks.certmark) DESC, id_reg desc "
			+ "LIMIT ?";
	
	/**
	 * Used to get user's marks
	 */
	public static final String GETMARKSBYID = "SELECT subject1_id,"
	        + " subject2_id, subject3_id " + 
			"FROM user_marks " + 
			"WHERE us_id = ?";

	/**
	 * used to get max place of faculty
	 */
	public static final String GETMAXPLACE = "SELECT maxplace "
			+ "FROM faculty "
			+ "WHERE id_faculty = ?";
	
	public static final String GETBUDGET = "SELECT budget "
			+ "FROM faculty "
			+ "WHERE id_faculty = ?";
	
	/**
	 * Used to select users of current faculty
	 */
	public static final String SELECTFACULTYUSERS = "SELECT user.* " 
			+"FROM user" 
			+"	JOIN user_marks" 
			+"    ON user_marks.us_id = user.user_id" 
			+"    JOIN faculty_register" 
			+"    ON faculty_register.user_id = user.user_id " 
			+"    AND faculty_register.fac_id = ? " 
			+"WHERE role = 'user' "
			+"group by id_reg " 
			+"ORDER BY MAX(user_marks.subject1 + user_marks.subject2"
			+ " + user_marks.subject3 + user_marks.certmark) DESC, id_reg " 
			+"LIMIT ?";
	
	/**
	 * Used to create statement
	 */
	public static final String CREATESTATEMENT = "INSERT INTO "
	        + "`statement` (`user_id`, `faculty_id`) VALUES (?,?)";
	
	/**
	 * Used to register on faculty
	 */
	public static final String REGISTERONFACULTY = "INSERT INTO "
	        + "`faculty_register` (`user_id`, `fac_id`, `marks_id`)"
			+ " VALUES (?,?,?)";
	
	public static final String INCREASEREGCOUNT = "";

	/**
	 * Used to get user's faculty
	 */
	public static final String GETUSERFACULTY = "Select id_faculty, name,"
	        + " maxplace, budget, subname1.subject_name, " 
			+"subname2.subject_name, subname3.subject_name, reg_count " 
			+"FROM faculty" 
			+"	JOIN subjects as subname1" 
			+"		ON subname1.id_subj = subject1 " 
			+"		JOIN subjects as subname2" 
			+"		ON subname2.id_subj = subject2" 
			+"		JOIN subjects as subname3" 
			+"		ON subname3.id_subj = subject3 " 
			+"	WHERE subject1 in (?,?,?)" 
			+"    and subject2 in (?,?,?)" 
			+"    and subject3 in (?,?,?)";
	
	/**
	 * Used to delete faculty
	 */
	public static final String DROPFACULTY = "DELETE FROM faculty WHERE id_faculty=?";
	
	/**
	 * Used to add marks to Data base
	 */
	public static final String ADDMARKS = "INSERT INTO `user_marks` "
	        + "(`us_id`, `subject1_id`, `subject1`, "
			+ "`subject2_id`, `subject2`, `subject3_id`, "
			+ "`subject3`, `certmark`) VALUES (?,?,?,?,?,?,?,?);";
	
	/**
	 * Used to get faculties
	 */
	public static final String GETFACULTYBYID = "Select id_faculty, name,"
	        + " maxplace, budget, subname1.subject_name, " 
			+"	subname2.subject_name, subname3.subject_name, reg_count "
			+ " FROM faculty" 
			+"			JOIN subjects as subname1" 
			+"			ON subname1.id_subj = subject1" 
			+"			JOIN subjects as subname2" 
			+"			ON subname2.id_subj = subject2" 
			+"			JOIN subjects as subname3" 
			+"			ON subname3.id_subj = subject3 WHERE id_faculty = ?;";
	
	/**
	 * Used to add user's photo
	 */
	public static final String ADDPHOTO = "INSERT INTO `user_photo` (`photo`, `us_id`) " + 
			"VALUES (?, ?);";
	
	public static final String ADDCERTPHOTO = "INSERT INTO `user_certmark` "
			+ "(`us_id`, `certmark`) VALUES (?, ?);";
	
	/**
	 * Used to get subject names
	 */
	public static final String SUBJECTSLIST = "SELECT id_subj, subject_name"
			+ " FROM subjects;";
	
	/**
	 * Used to get photo's path
	 */
	public static final String GETPHOTO = "Select `photo` FROM user_photo WHERE us_id = ?;";
	
	public static final String GETCERTPHOTO = "SELECT `certmark` FROM user_certmark"
			+ " WHERE us_id = ?;";
	
	/**
	 * Used to edit faculty
	 */
	public static final String EDITFACULTY = "UPDATE faculty"
			+ " SET name = ?, maxplace = ?, budget = ?, subject1 = ?, "
			+ "subject2 = ?, subject3 = ?, reg_count = ? "
			+ " WHERE id_faculty=?";
	
	/**
	 * Used to list users
	 */
	public static final String LISTUSERS = "SELECT user_id, login, "
	        + "password, name, surname, patronymic, email, region, city, school, role"
			+ " FROM user"
			+ " where role='user'"
			+ "or role ='blocked';";
	/**
	 * Used to list faculties
	 */
	public static final String LISTFACULTIES = "SELECT id_faculty,"
	        + " name, maxplace, budget, subname1.subject_name, "
			+ "subname2.subject_name, subname3.subject_name, reg_count" 
			+" FROM faculty" 
			+"  JOIN subjects as subname1" 
			+"    ON subname1.id_subj = subject1" 
			+"  JOIN subjects as subname2" 
			+"    ON subname2.id_subj = subject2" 
			+"  JOIN subjects as subname3" 
			+"    ON subname3.id_subj = subject3;";
	/**
	 * Used to find user by ID
	 */
	public static final String FINDUSERBYID = "SELECT *"
			+ " FROM user"
			+ " where user_id=? LIMIT 1";
	
	/**
	 * Used to block user
	 */
	public static final String BLOCKUSER = "update user set role='blocked' where user_id=?";
	/**
	 * Used to unblock user
	 */
	public static final String UNBLOCKUSER = "update user set role='user' where user_id=?";
	/**
	 * Used to login user
	 */
	public static final String LOGINUSER = "SELECT * from user where login=? "
			+ " and password= BINARY ? LIMIT 1;";
	
	public static final String  GETBUDGETUSERS = "SELECT user.* " 
			+"FROM user"
			+ "	JOIN user_marks "
			+ "	ON user_marks.us_id = user.user_id" 
			+"	JOIN faculty_register" 
			+"		on faculty_register.user_id = user.user_id" 
			+"	JOIN faculty" 
			+"		on faculty.id_faculty = fac_id " 
			+"WHERE faculty.id_faculty = ? " 
			+"AND role = 'user' "
			+"GROUP BY login "
			+"ORDER BY MAX(user_marks.subject1 + user_marks.subject2 + user_marks.subject3 + user_marks.certmark) desc,id_reg " 
			+"LIMIT ?";
	
	public static final String  GETNONBUDGETUSERS = "SELECT user.* "
			+"FROM user" 
			+ "	JOIN user_marks "
			+ "	ON user_marks.us_id = user.user_id" 
			+"	JOIN faculty_register" 
			+"		on faculty_register.user_id = user.user_id" 
			+"	JOIN faculty" 
			+"		on faculty.id_faculty = fac_id " 
			+"WHERE fac_id = ? " 
			+"AND role = 'user' "
			+"GROUP BY login "
			+"ORDER BY MAX(user_marks.subject1 + user_marks.subject2 + user_marks.subject3 + user_marks.certmark) desc,id_reg " 
			+"LIMIT ?,?";
	
	public static final String  GETOTHERUSERS = "SELECT user.* "
			+"FROM user" 
			+ "	JOIN user_marks "
			+ "	ON user_marks.us_id = user.user_id" 
			+"	JOIN faculty_register" 
			+"		on faculty_register.user_id = user.user_id" 
			+"	JOIN faculty" 
			+"		on faculty.id_faculty = fac_id " 
			+"WHERE fac_id = ? " 
			+"AND role = 'user' "
			+"GROUP BY login "
			+"ORDER BY MAX(user_marks.subject1 + user_marks.subject2 + user_marks.subject3 + user_marks.certmark) desc,id_reg " 
			+"LIMIT ?,1000";
	
	public static final String GETREGCOUNT = "select reg_count "
			+ " from faculty "
			+ " join faculty_register "
			+ " on fac_id = id_faculty "
			+ " join user "
			+ " on user.user_id = faculty_register.user_id "
			+ " and role = 'user' "
			+ " where fac_id = ? "
			+ " group by fac_id";
	
	public static final String UPDATEREGCOUNT = " update faculty "
			+ " set reg_count = reg_count + 1 "
			+ " where id_faculty = ?;";
	
	public static final String BLOCKREGCOUNT = " update faculty "
			+ " join faculty_register "
	        + " on fac_id = id_faculty "
	        + " join user "
	        + " on user.user_id = faculty_register.user_id "
	        + " set reg_count = reg_count - 1 "
	        + " where user.user_id = ?";
	
	public static final String UNBLOCKREGCOUNT = " update faculty "
			+ " join faculty_register "
	        + " on fac_id = id_faculty "
	        + " join user "
	        + " on user.user_id = faculty_register.user_id "
	        + " set reg_count = reg_count + 1 "
	        + " where user.user_id = ?";
}
