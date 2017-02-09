package persistence.entity.users;


public class Admin extends User {

	private static final long serialVersionUID = 7693700587566222849L;

	public Admin(int id, String login, String password, String email,
			String name, String surname, String patronymic, String region, String city, String school, Role role) {
		super(id, login, password, email, name, surname, patronymic, region, city, school, role);
	}
}
