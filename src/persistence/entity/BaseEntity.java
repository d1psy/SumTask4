package persistence.entity;

import java.io.Serializable;

/**
 * @author Golubtsov
 *
 */
public abstract class BaseEntity implements Serializable {

	/**
	 * Base entity that's used to set and get ID
	 * @param id
	 */
	public BaseEntity(int id) {
		super();
		this.id = id;
	}

	public BaseEntity() {
	}

	private static final long serialVersionUID = 3155228971520871950L;

	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
