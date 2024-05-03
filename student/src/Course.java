public class Course {

    private String courseName;
    private String grade;
    private int creditHr;
    
    public Course(String courseName, String courseGrade, int creditHr) {
        this.courseName = courseName;
        this.grade = courseGrade;
        this.creditHr = creditHr;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseGrade() {
        return grade;
    }

    public int getCreditHr() {
        return creditHr;
    }
    
    
    
}
