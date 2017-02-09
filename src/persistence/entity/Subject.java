package persistence.entity;


/**
 * @author Golbutsov
 * Stores Subject information
 *
 */
public class Subject extends BaseEntity {

	private static final long serialVersionUID = 3459117983578659990L;

	protected String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Subject(){
		
	}
	
	public Subject(int id, String name) {
		super(id);
		this.name = name;
	}
	
}
