public class TableClass {
    int id;
    String UserName;
    float grade;

    TableClass(int id,String UserName,float grade){
        this.id = id;
        this.UserName = UserName;
        this.grade = grade;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUserName(String userName) {
       this.UserName = userName;
    }
    public void setGrade(float grade) {
        this.grade = grade;
    }
    public int getId() {
        return id;
    }
    public String getUserName() {
        return UserName;
    }
    public float getGrade() {
        return grade;
    }
    
}
