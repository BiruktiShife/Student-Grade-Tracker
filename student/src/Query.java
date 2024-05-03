import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Query {
    private static final EventModel EventModel = null;

    private Connection connection;

    private final String URL = "jdbc:mysql://localhost/studentGrade";

    private final String USERNAME = "root";

    private final String PASSWORD = "root";

    private PreparedStatement insert;

    
    private PreparedStatement delete;

    private PreparedStatement select;

    public Query() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void insertStudent(String userName,int id,float grade, ObservableList<ReloadFields> courses,int index) throws SQLException{
        insert = connection.prepareStatement("insert into student " + "(userName,id,grade) values(?,?,?)");
        insert.setString(1, userName);
        insert.setInt(2, id);
        insert.setFloat(3, grade);
        insert.executeUpdate();
            for(int i = 0; i < courses.size(); i++){
            insert = connection.prepareStatement("insert into courses " + "(courseId, CourseName, Grade, CreditHr) values(?,?,?,?)");
            insert.setInt(1, id);
            insert.setString(2, courses.get(i).getCourseName());
            insert.setString(3, courses.get(i).getGrade());
            insert.setInt(4, courses.get(i).getCreditHr());
            insert.executeUpdate();
        }
    }

    public ObservableList<TableClass> selectForTable()throws SQLException{
        select = connection.prepareStatement("select * from student");
        ResultSet resultSet = select.executeQuery();
        ObservableList<TableClass> list= FXCollections.observableArrayList();
        
            while (resultSet.next()) {
                list.add( new TableClass(
                resultSet.getInt("id"),
                resultSet.getString("userName"),
                resultSet.getFloat("grade")
                ));
            }
            return list;
    }

    @SuppressWarnings("unchecked")
    public ObservableList<ReloadFields> selectCourse(int id) throws SQLException{
        select = connection.prepareStatement("select * from  courses where courseId = ?");
        ObservableList list = FXCollections.observableArrayList();
        select.setInt(1, id);
        ResultSet resultSet = select.executeQuery();
            while(resultSet.next()){
                list.add(new ReloadFields(resultSet.getString("CourseName"), 
                resultSet.getString("Grade"),
                resultSet.getInt("CreditHr")));
            }
            return list;
       
    }


    public void setUpdate(String userName,int id,float grade, ObservableList<ReloadFields> courses,int index) throws SQLException{
        String query="update student set userName='"+userName+"',id='"+id+"',grade='"+grade+"'where id='"+id+"'";
        connection.createStatement().executeUpdate(query);
     }

     public void setDelete(String userName,int id) throws SQLException{
        delete = connection.prepareStatement("delete * from student where userName = ? and id = ?");
        delete.setString(1, userName);
        delete.setInt(2, id);
        delete.executeUpdate();
     }

     public void deleteStudent(int id) throws SQLException{
    
        delete = connection.prepareStatement("delete from student where id = ?");
        delete.setInt(1, id);
        delete.executeUpdate();
        
        delete = connection.prepareStatement("delete  from courses where courseId = ? ");
        delete.setInt(1, id);
        delete.executeUpdate();
         
    }
    public ObservableList<String> selectCourse(int id, String cName) throws SQLException{
        select = connection.prepareStatement("select * from  courses where courseId = ? and CourseName = ?");
        ObservableList list = FXCollections.observableArrayList();
        select.setInt(1, id);
        select.setString(2, cName);
        ResultSet resultSet = select.executeQuery();
            while(resultSet.next()){
                list.add(resultSet.getString("CourseName"));

            }
            return list;
       
    }
   

}
