package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import persistence.DBException;
import persistence.SQLQueries;
import persistence.entity.EntityConstructor;
import persistence.entity.Faculty;
import persistence.entity.FacultyRegister;
import persistence.entity.Marks;
import persistence.entity.Subject;
import persistence.entity.users.User;


/**
 * @author Golubtsov
 *
 */
public final class DBManager {

	/**
	 * Creates an instance, so it can be used later
	 */
	private static DBManager instance;
	/**
     * Creates an instance, so it can be used later
     */
	private DataSource ds;
	/**
     * Creates an instance, so it can be used later
     */
	private static final Logger LOGGER = Logger.getLogger(DBManager.class);

	/**
     * Creates an instance, so it can be used later
     * @return instance
     */
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			try {
				instance = new DBManager();
			} catch (DBException e) {
				return null;
			}
		}
		return instance;
	}

	/**
	 * @throws DBException e
	 */
	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext
			        .lookup("java:/comp/env");
			setDs((DataSource) envContext.lookup("jdbc/committee"));
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	public List<FacultyRegister> findFacReg(int userId) {
	    PreparedStatement ps = null;
        ResultSet rs = null;
	    List<FacultyRegister> fr = new ArrayList<>();
        try (Connection con = ds.getConnection();) {
            ps = con.prepareStatement(SQLQueries.GETREGISTER);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                fr.add(EntityConstructor.makeFacReg(rs));
            }
            return fr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * Used to find user by his id
	 * @param userId user's id
	 * @return User
	 */
	public User findUserById(int userId) {
		User user = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.FINDUSERBYID);) {
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = EntityConstructor.makeUser(rs);
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Used to find faculty by it's id
	 * @param facultyId faculty id
	 * @return faculty
	 */
	public Faculty findFacultyById(int facultyId) {
		Faculty faculty = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.GETFACULTYBYID);) {
			pstmt.setLong(1, facultyId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				faculty = EntityConstructor.makeFaculty(rs);
			}
			return faculty;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addCertPhoto (String path, int userid) {
		{
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con
							.prepareStatement(SQLQueries.ADDCERTPHOTO);) {
				ps.setInt(1, userid);
				ps.setString(2, path);
				ps.execute();
				con.commit();
				return true;
			} catch (SQLException e) {
				System.out.println("add photo failed");
				return false;
			}
		}
	}
	
	/**
	 * Used to add photo's path to data base
	 * @param path path of photo
	 * @param userid user's id
	 * @return boolean
	 */
	public boolean addPhoto(String path, int userid) {
		{
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con
							.prepareStatement(SQLQueries.ADDPHOTO);) {
				ps.setString(1, path);
				ps.setInt(2, userid);
				ps.execute();
				con.commit();
				return true;
			} catch (SQLException e) {
				System.out.println("add photo failed");
				return false;
			}
		}
	}
	
	public String getCertPhoto(int userid) {
		String path = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.GETCERTPHOTO);) {
			pstmt.setLong(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				path = rs.getString(1);
			}
			return path;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Used to get photo path
	 * @param userid user's id
	 * @return photo path
	 */
	public String getPhoto(int userid) {
		String path = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.GETPHOTO);) {
			pstmt.setLong(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				path = rs.getString(1);
			}
			return path;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	/**
	 * Used to delete faculty
	 * @param facultyId
	 * @return boolean
	 */
	public boolean deleteFaculty(int facultyId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQLQueries.DROPFACULTY);
			ps.setLong(1, facultyId);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete failed");
			return false;
		} finally {
			tryToClose(con, ps);
		}
	}
	
	/**
	 * Used to block user by id
	 * @param userId
	 * @return boolean
	 */
	public boolean blockUser(int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQLQueries.BLOCKUSER);
			ps.setLong(1, userId);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Block failed");
			return false;
		} finally {
			tryToClose(con, ps);
		}
	}
	

	public boolean updateRegCount(int faculty) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQLQueries.UPDATEREGCOUNT);
			ps.setLong(1, faculty);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			tryToClose(con, ps);
		}
	}
	
	public boolean blockRegCount (int userid) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQLQueries.BLOCKREGCOUNT);
			ps.setLong(1, userid);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			tryToClose(con, ps);
		}
	}
	
	public boolean unblockRegCount (int userid) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQLQueries.UNBLOCKREGCOUNT);
			ps.setLong(1, userid);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			tryToClose(con, ps);
		}
	}
	
	/**
	 * Used to unblock user by id
	 * @param userId
	 * @return boolean
	 */
	public boolean unblockUser(int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQLQueries.UNBLOCKUSER);
			ps.setLong(1, userId);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unblock failed");
			return false;
		} finally {
			tryToClose(con, ps);
		}
	}

	public DataSource getDs() {
		return ds;
	}

	private void setDs(DataSource ds) {
		this.ds = ds;
	}


	/**
	 * Used to close connection to data base
	 * @param autoCloseables
	 */
	private void tryToClose(AutoCloseable... autoCloseables) {
		for (AutoCloseable autoCloseable : autoCloseables) {
			if (autoCloseable != null) {
				try {
					autoCloseable.close();
				} catch (Exception e) {
				    LOGGER.error("cannot close " + autoCloseable);
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Used to get faculty name by ID
	 * @param facultyId
	 * @return faculty name
	 */
	public String getFacultyById(int facultyId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String facultyName = null;
		try (
				Connection con = ds.getConnection();) {
			ps = con.prepareStatement(SQLQueries.GETFACULTYBYID);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			rs.next();
			facultyName = rs.getString("faculty.name");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		return facultyName;
	}
	
	/**
	 * Used to get max place of faculty
	 * @param facultyId
	 * @return max place
	 */
	public int getMaxPlace(int facultyId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int maxPlace = 0;
		
		try (Connection con = ds.getConnection();) {
			ps = con.prepareStatement(SQLQueries.GETMAXPLACE);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			rs.next();
			maxPlace = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} 
		
		return maxPlace;
	}
	
	public int getBudget(int facultyId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int budget = 0;
		
		try (Connection con = ds.getConnection();) {
			ps = con.prepareStatement(SQLQueries.GETBUDGET);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			rs.next();
			budget = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} 
		
		return budget;
	}
	
	public int getRegCount (int facultyId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try (Connection con = ds.getConnection();) {
			ps = con.prepareStatement(SQLQueries.GETREGCOUNT);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} 
		
		return count;
	}
	
	/**
	 * Used to add user to statement
	 * @param facultyId
	 * @param maxPlace
	 * @return List of users
	 */
	public List<User> createStatement(int facultyId, int maxPlace) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		
		try (Connection con = ds.getConnection();) {
			ps = con.prepareStatement(SQLQueries.SELECTFACULTYUSERS);
			ps.setInt(1, facultyId);
			ps.setInt(2, maxPlace);
			rs = ps.executeQuery();
			while (rs.next()) {
				addToStatement(rs.getInt("user.user_id"), facultyId);
				users.add(EntityConstructor.makeUser(rs));
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * Used to get list of users who joined faculty
	 * @param facultyID
	 * @return list of users
	 */
	public List<User> getFacultyUsers(int facultyID) {
		List<User> users = new ArrayList<>();
		PreparedStatement ps = null;
		try (Connection con = ds.getConnection();){
				ps = con.prepareStatement(SQLQueries.LISTUSERFACULTY);
				ps.setInt(1, facultyID);
				ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString("role") == "blocked") {
					continue;
				}
				users.add(EntityConstructor.makeUser(rs));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public List<User> getBudgetUsers(int facultyID, int budgetPlaces) {
		List<User> users = new ArrayList<>();
		PreparedStatement ps = null;
		try (Connection con = ds.getConnection();){
				ps = con.prepareStatement(SQLQueries.GETBUDGETUSERS);
				ps.setInt(1, facultyID);
				ps.setInt(2, budgetPlaces);
				ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString("role").equals("blocked")) {
					continue;
				}
				users.add(EntityConstructor.makeUser(rs));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public List<User> getNonBudgetUsers(int facultyID, int budgetPlaces, int places) {
		List<User> users = new ArrayList<>();
		PreparedStatement ps = null;
		try (Connection con = ds.getConnection();){
				ps = con.prepareStatement(SQLQueries.GETNONBUDGETUSERS);
				ps.setInt(1, facultyID);
				ps.setInt(2, budgetPlaces);
				ps.setInt(3, places);
				ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString("role").equals("blocked")) {
					continue;
				}
				users.add(EntityConstructor.makeUser(rs));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public List<User> getOtherUsers(int facultyID, int places) {
		List<User> users = new ArrayList<>();
		PreparedStatement ps = null;
		try (Connection con = ds.getConnection();){
				ps = con.prepareStatement(SQLQueries.GETOTHERUSERS);
				ps.setInt(1, facultyID);
				ps.setInt(2, places);
				ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString("role").equals("blocked")) {
					continue;
				}
				users.add(EntityConstructor.makeUser(rs));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public boolean addToStatement(int user, int faculty) {
		PreparedStatement ps = null;
		try (Connection con = ds.getConnection();) {
			ps = con.prepareStatement(SQLQueries.CREATESTATEMENT);
			ps.setInt(1, user);
			ps.setInt(2, faculty);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			System.out.println("register failed");
			return false;
		}
	}

	/**
	 * Used to get user's marks
	 * @param userid
	 * @return marks
	 */
	public Marks getMarks(int userid) {
		Marks m = null;
		System.out.println("START");
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.CHECK_MARKS);) {
			pstmt.setLong(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				m = EntityConstructor.makeUserMarks(rs);
			}
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Used to login user
	 * @param login
	 * @param password
	 * @return user
	 */
	public User loginUser(String login, String password) {
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		{
			try (Connection con = ds.getConnection();) {
				ps = con.prepareStatement(SQLQueries.LOGINUSER);
				ps.setString(1, login);
				ps.setString(2, password);
				rs = ps.executeQuery();
				if (rs.next()) {
					user = EntityConstructor.makeUser(rs);
				}
				con.commit();
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.info("login failed");
				return null;
			} finally {
				tryToClose(rs, ps);
			}
		}
	}
	
	/**
	 * Used to get list of subjects
	 * @return list of subjects
	 */
	public List<Subject> getSubjects() {
		List<Subject> subjects = new ArrayList<>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.SUBJECTSLIST);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				
				subjects.add(EntityConstructor.makeSubjects(rs));
			}
			return subjects;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Used to get list of users
	 * @return list users
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.LISTUSERS);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				users.add(EntityConstructor.makeUser(rs));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Used to get list of faculties
	 * @return list of faculties
	 */
	public List<Faculty> getFaculties() {
		List<Faculty> faculties = new ArrayList<>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(SQLQueries.LISTFACULTIES);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				
				faculties.add(EntityConstructor.makeFaculty(rs));
			}
			return faculties;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Used to get list of faculties by user's marks
	 * @param id
	 * @return list of faculties
	 */
	public List<Faculty> getUserFaculties(int id) {
		List<Faculty> faculties = new ArrayList<>();
		PreparedStatement ps = null;
		int sub1 = 0;
		int sub2 = 0;
		int sub3 = 0;
		ResultSet rs = null;
		try (Connection con = ds.getConnection();){
				ps = con
						.prepareStatement(SQLQueries.GETMARKSBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()) {
					sub1 = rs.getInt(1);
					sub2 = rs.getInt(2);
					sub3 = rs.getInt(3);
				}
			ps = con.prepareStatement(SQLQueries.GETUSERFACULTY);
			ps.setInt(1, sub1);
			ps.setInt(2, sub2);
			ps.setInt(3, sub3);
			ps.setInt(4, sub1);
			ps.setInt(5, sub2);
			ps.setInt(6, sub3);
			ps.setInt(7, sub1);
			ps.setInt(8, sub2);
			ps.setInt(9, sub3);
			rs = ps.executeQuery();
			while (rs.next()) {
				faculties.add(EntityConstructor.makeFaculty(rs));
			}
			return faculties;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Used to register on faculty
	 * @param user
	 * @param faculty
	 * @param marks
	 * @return boolean
	 */
	public boolean facultyRegister(int user, int faculty, int marks) {
		PreparedStatement ps = null;
		try (Connection con = ds.getConnection();) {
			ps = con.prepareStatement(SQLQueries.REGISTERONFACULTY);
			ps.setInt(1, user);
			ps.setInt(2, faculty);
			ps.setInt(3, marks);
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			System.out.println("register failed");
			return false;
		}
	}
	
	/**
	 * Used to check if login exists
	 * @param login
	 * @return boolean
	 */
	public boolean checkLogin(String login) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		{
			try (Connection con = ds.getConnection();) {
				ps = con.prepareStatement(SQLQueries.CHECK_USERS);
				ps.setString(1, login);
				rs = ps.executeQuery();
				if(rs.next()){
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return true;
			} finally {
				tryToClose(rs, ps);
			}
		}
		return true;
	}
	
	/**
	 * Used to check if faculty exists
	 * @param name
	 * @return boolean
	 */
	public boolean checkFaculty(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		{
			try (Connection con = ds.getConnection();) {
				ps = con.prepareStatement(SQLQueries.CHECK_FACULTIES);
				ps.setString(1, name);
				rs = ps.executeQuery();
				if(rs.next()){
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return true;
			} finally {
				tryToClose(rs, ps);
			}
		}
		return true;
	}
	
	/**
	 * Used to add user's marks
	 * @param m
	 * @return boolean
	 */
	public boolean addMarks(Marks m) {
		{
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con
							.prepareStatement(SQLQueries.ADDMARKS);) {
				ps.setInt(1, m.getUserId());
				ps.setString(2, m.getSub1ID());
				ps.setInt(3, m.getMark1());
				ps.setString(4, m.getSub2ID());
				ps.setInt(5, m.getMark2());
				ps.setString(6, m.getSub3ID());
				ps.setInt(7, m.getMark3());
				ps.setInt(8, m.getCert());
				ps.execute();
				con.commit();
				return true;
			} catch (SQLException e) {
				System.out.println("Addmarks failed");
				return false;
			}
		}
	}
	
	/**
	 * Used to add new faculty
	 * @param p
	 * @return boolean
	 */
	public boolean addFaculty(Faculty p) {
		{
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con
							.prepareStatement(SQLQueries.ADDFACULTY);) {
				ps.setString(1, p.getName());
				ps.setInt(2, p.getMaxplace());
				ps.setInt(3, p.getBudget());
				ps.setString(4, p.getSub1());
				ps.setString(5, p.getSub2());
				ps.setString(6, p.getSub3());
				ps.execute();
				con.commit();
				return true;
			} catch (SQLException e) {
				System.out.println("addfaculty failed");
				return false;
			}
		}
	}
	
	/** 
	 * Used to edit faculty
	 * @param p
	 * @return boolean
	 */
	public boolean editFaculty(Faculty p) {
		{
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con
							.prepareStatement(SQLQueries.EDITFACULTY);) {
				ps.setString(1, p.getName());
				ps.setInt(2, p.getMaxplace());
				ps.setInt(3, p.getBudget());
				ps.setString(4, p.getSub1());
				ps.setString(5, p.getSub2());
				ps.setString(6, p.getSub3());
				ps.setInt(7, p.getCount());
				ps.setInt(8, p.getId());
				ps.execute();
				con.commit();
				return true;
			} catch (SQLException e) {
				System.out.println("Edit faculty failed");
				return false;
			}
		}
	}
	
	/**
	 * Used to add new user to Data base
	 * @param p
	 * @return boolean
	 */
	public boolean addUser(User p) {
		{
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con
							.prepareStatement(SQLQueries.INSERT_USERS);) {
				ps.setString(1, p.getLogin());
				ps.setString(2, p.getPassword());
				ps.setString(3, p.getName());
				ps.setString(4, p.getSurname());
				ps.setString(5, p.getPatronymic());
				ps.setString(6, p.getEmail());
				ps.setString(7, p.getRegion());
				ps.setString(8, p.getCity());
				ps.setString(9, p.getSchool());
				ps.execute();
				con.commit();
				return true;
			} catch (SQLException e) {
				System.out.println("adduser failed");
				return false;
			}
		}
	}
	
	
}