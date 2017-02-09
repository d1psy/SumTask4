package persistence.entity;

/**
 * @author Golbutsov
 *
 */
public class Marks extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	protected int userid;
	protected String subject1ID;
	protected int mark1;
	protected String subject2ID;
	protected int mark2;
	protected String subject3ID;
	protected int mark3;
	protected int certificate;
	
	public int getUserId() {
		return userid;
	}
	
	public void setUserId(int userid) {
		this.userid = userid;
	}
	
	public String getSub1ID() {
		return subject1ID;
	}
	
	public void setSub1ID(String subjectID) {
		this.subject1ID = subjectID;
	}
	
	public int getMark1() {
		return mark1;
	}
	
	public void setMark1(int mark) {
		this.mark1 = mark;
	}
	
	public String getSub2ID() {
		return subject2ID;
	}
	
	public void setSub2ID(String subjectID) {
		this.subject2ID = subjectID;
	}
	
	public int getMark2() {
		return mark2;
	}
	
	public void setMark2(int mark) {
		this.mark2 = mark;
	}
	public String getSub3ID() {
		return subject3ID;
	}
	
	public void setSub3ID(String subjectID) {
		this.subject3ID = subjectID;
	}
	
	public int getMark3() {
		return mark3;
	}
	
	public void setMark3(int mark) {
		this.mark3 = mark;
	}
	
	public int getCert() {
		return certificate;
	}
	
	public void setCert(int certificate) {
		this.certificate = certificate;
	}
	
	public Marks() {
		
	}
	
	public Marks(int id, int userid, String subject1, int mark1, String subject2, int mark2, String subject3, int mark3, int certificate) {
		super(id);
		this.userid = userid;
		this.subject1ID = subject1;
		this.mark1 = mark1;
		this.subject2ID=subject2;
		this.mark2 = mark2;
		this.subject3ID = subject3;
		this.mark3 = mark3;
		this.certificate = certificate;
	}
}
