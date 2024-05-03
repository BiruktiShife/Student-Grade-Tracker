
public class EventModel {
  private String username;
  private int Id;
  private int numberofcourse;
  private String nameofcourses ;
  private float coursegrade;
  private int credithoure;

//   public EventModel(){
           
//   }

  public EventModel(int Id,String nameofcourses,float coursegrade,int credithoure){
    // this.username = username;
    this.Id = Id;
    this.nameofcourses = nameofcourses;
    // this.numberofcourse = numberofcourse;
    this.coursegrade = coursegrade;
    this.credithoure =credithoure;
  }

  public String getusername(){return username;}

  public int getId(){return Id;}

  public String getnameofcourses(){return nameofcourses;}

  public int getnumberofcourse(){return numberofcourse;}

  public float getcoursegrade(){return coursegrade;}

  public int getcredithoure(){return credithoure;}
}
