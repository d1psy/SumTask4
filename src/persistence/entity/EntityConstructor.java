package persistence.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.entity.users.Role;
import persistence.entity.users.User;

/**
 * @author Golubtsov
 *
 */
public final class EntityConstructor {
    
    /**
     * Creates entities
     */
    private EntityConstructor() {
        //
    }
	
	/**
	 * Creates user entity
	 * @param rs
	 * @return User
	 * @throws SQLException
	 */
	public static User makeUser(ResultSet rs) throws SQLException {
		User user=new User();
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setPatronymic(rs.getString("patronymic"));
		user.setEmail(rs.getString("email"));
		user.setCity(rs.getString("city"));
		user.setRegion(rs.getString("region"));
		user.setSchool(rs.getString("school"));
		user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
		user.setId(Integer.parseInt(rs.getString("user_id")));
		return user;
	}
	
	public static FacultyRegister makeFacReg(ResultSet rs) throws SQLException {
	    FacultyRegister fr = new FacultyRegister();
	    System.out.println(rs.getString("id_reg") + rs.getString("user_id") + rs.getString("fac_id"));
	    fr.setId(Integer.parseInt(rs.getString("id_reg")));
	    fr.setUserId(Integer.parseInt(rs.getString("user_id")));
	    fr.setFacultyId(Integer.parseInt(rs.getString("fac_id")));
	    return fr;
	}
	
	/**
	 * Creates mark entity
	 * @param rs
	 * @return Marks
	 * @throws SQLException
	 */
	public static Marks makeUserMarks(ResultSet rs) throws SQLException {
		Marks m = new Marks();
		System.out.println(rs.getString("subname1.subject_name"));
		m.setSub1ID(rs.getString("subname1.subject_name"));
		System.out.println(rs.getString("subname2.subject_name"));
		m.setSub2ID(rs.getString("subname2.subject_name"));
		m.setSub3ID(rs.getString("subname3.subject_name"));
		m.setMark1(rs.getInt("subject1"));
		m.setMark2(rs.getInt("subject2"));
		m.setMark3(rs.getInt("subject3"));
		m.setCert(rs.getInt("certmark"));
		m.setUserId(rs.getInt("us_id"));
		m.setId(rs.getInt("marks_id"));
		return m;
	}
	
	/**
	 * Creates faculty entity
	 * @param rs
	 * @return faculty
	 * @throws SQLException
	 */
	public static Faculty makeFaculty(ResultSet rs) throws SQLException {
		Faculty faculty=new Faculty();
		faculty.setId(Integer.parseInt(rs.getString("id_faculty")));
		faculty.setName(rs.getString("name"));
		faculty.setMaxplace(Integer.parseInt(rs.getString("maxplace")));
		faculty.setBudget(Integer.parseInt(rs.getString("budget")));
		faculty.setSub1(rs.getString("subname1.subject_name"));
		faculty.setSub2(rs.getString("subname2.subject_name"));
		faculty.setSub3(rs.getString("subname3.subject_name"));
		faculty.setCount(Integer.parseInt(rs.getString("reg_count")));
		return faculty;
	}
	
	/**
	 * Creates subject entity
	 * @param rs
	 * @return subject
	 * @throws SQLException
	 */
	public static Subject makeSubjects(ResultSet rs) throws SQLException {
		Subject subject = new Subject();
		subject.setId(Integer.parseInt(rs.getString("id_subj")));
		subject.setName(rs.getString("subject_name"));
		return subject;
	}
}