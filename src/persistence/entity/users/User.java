package persistence.entity.users;

import persistence.entity.BaseEntity;

/**
 * @author Golbutsov
 * Stores User information
 *
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = 3459117983578659990L;

	protected String login;

	protected String password;

	protected String name;

	protected String surname;
	
	protected String patronymic;
	
	protected String email;
	
	protected String region;
	
	protected String city;
	
	protected String school;

	protected Role role;
	
	public String getPatronymic() {
		return patronymic;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getSchool() {
		return school;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
	}

	public User(int id, String login, String password, String name, String surname, String patronymic,
			String email, String region, String city, String school, Role role) {
		super(id);
		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.region = region;
		this.city = city;
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

}