package persistence.entity;

public class FacultyRegister  extends BaseEntity {
    
    protected int user_id;
    protected int faculty_id;
    
    public int getUserId() {
        return user_id;
    }
    
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    
    public int getFacultyId() {
        return faculty_id;
    }
    
    public void setFacultyId(int faculty_id) {
        this.faculty_id = faculty_id;
    }
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public FacultyRegister() {
        
    }
    
    public FacultyRegister(int id, int user_id, int faculty_id) {
        super(id);
        this.user_id = user_id;
        this.faculty_id = faculty_id;
    }
}
