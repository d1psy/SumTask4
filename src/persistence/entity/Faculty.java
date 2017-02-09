package persistence.entity;

import persistence.entity.BaseEntity;

/**
 * @author Golbutsov
 *
 */
public class Faculty extends BaseEntity {

	private static final long serialVersionUID = 3459117983578659990L;
	
	/**
	 * Name of the faculty
	 */
	protected String name;
	
	/**
	 * max place of the faculty
	 */
	protected int maxplace;
	
	/**
	 * budget of the faculty
	 */
	protected int budget;

	protected String subject1;

	protected String subject2;

	protected String subject3;
	
	protected int regCount;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxplace() {
		return maxplace;
	}
	
	public void setMaxplace(int maxplace) {
		this.maxplace = maxplace;
	}
	
	public int getBudget() {
		return budget;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public String getSub1() {
		return subject1;
	}
	
	public void setSub1(String subject1) {
		this.subject1 = subject1;
	}
	
	public String getSub2() {
		return subject2;
	}
	
	public void setSub2(String subject2) {
		this.subject2 = subject2;
	}
	
	public String getSub3() {
		return subject3;
	}
	
	public void setSub3(String subject3) {
		this.subject3 = subject3;
	}
	
	public int getCount() {
		return regCount;
	}
	
	public void setCount(int regCount) {
		this.regCount = regCount;
	}
	
	public Faculty(){
		
	}
	
	public Faculty(int id, String name, int maxplace, int budget, String subject1, String subject2,
			String subject3, int regCount) {
		super(id);
		this.name = name;
		this.maxplace = maxplace;
		this.budget = budget;
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
		this.regCount = regCount;
	}
	
}
