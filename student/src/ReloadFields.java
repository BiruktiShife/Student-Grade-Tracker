public class ReloadFields {

    private String courseName;
    private String grade;
    private int creditHr;
    
    public ReloadFields(String courseName, String grade, int creditHr) {
        this.courseName = courseName;
        this.grade = grade;
        this.creditHr = creditHr;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getGrade() {
        return grade;
    }

    public int getCreditHr() {
        return creditHr;
    }
    
    @Override
    public String toString(){
        return String.format("%s %n", courseName);
    }
    
}
