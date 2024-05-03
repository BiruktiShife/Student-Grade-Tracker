import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class StudentController implements Initializable{

   ObservableList<ReloadFields> courses = FXCollections.observableArrayList();
   ObservableList<TableClass> tView = FXCollections.observableArrayList();
    private int index = 0;

    @FXML
    private Button addStudentBtn;
    

    @FXML
    private TextField courseNameTF;

    @FXML
    private TextField creditHoureTF;

    @FXML
    private TextField enterIdTF;

    @FXML
    private VBox formVBox;

    @FXML
    private TableColumn<TableClass, Float> gradeColumn;

    @FXML
    private TableColumn<TableClass, Integer> idColumn;

    @FXML
    private ListView<ReloadFields> listView;

    @FXML
    private Button nextCourseBtn;

    @FXML
    private ComboBox<Integer> numberOfCourse;

    @FXML
    private ComboBox<String> courseGrade;

    @FXML
    private Button removeInfoBtn;

    @FXML
    private TextField studentIdTF;
    static int size=1;
    @FXML
    private TextField studentNameTF;

    @FXML
    private Button submitBtn;

    @FXML
    private TableView<TableClass> tableView;

    @FXML
    private Button updateInfoBtn;

    @FXML
    private TableColumn<TableClass, String> userNameColumn;

    String copyId;
    int check = 0;
    @FXML
    void listViewHandler(MouseEvent event) {
        int x = listView.getSelectionModel().getSelectedIndex();
        courseNameTF.setText(courses.get(x).getCourseName());
        creditHoureTF.setText(String.valueOf(courses.get(x).getCreditHr()));
        courseGrade.setValue(courses.get(x).getGrade());
        numberOfCourse.setValue(courses.size());
        check++;
    }

    @FXML
    void onAddStudentHandler(ActionEvent event) {
        formVBox.setVisible(true);
      
    }

    private void updateButtonState() {
        boolean isCourseNameFilled = !courseNameTF.getText().isEmpty();
        boolean isCreditHoursFilled = !creditHoureTF.getText().isEmpty();
        boolean isCourseGradeSelected = courseGrade.getValue() != null;
        boolean isNumberOfCourseSelected = numberOfCourse.getValue() != null;

        nextCourseBtn.setDisable(!(isCourseNameFilled && isCreditHoursFilled && isCourseGradeSelected &&isNumberOfCourseSelected));
    }

    @FXML
    void onNextCourseHandler(ActionEvent event) throws SQLException {
        
        if(size <= numberOfCourse.getValue()){
            processCourses();
            if(check != 0){
            courses.set(listView.getSelectionModel().getSelectedIndex() ,new ReloadFields(courseNameTF.getText(), courseGrade.getValue(), Integer.parseInt(creditHoureTF.getText())));
            check = 0;
            }
            else{
                courses.add(new ReloadFields(courseNameTF.getText(), courseGrade.getValue(), Integer.parseInt(creditHoureTF.getText())));
            
            }
            nextCourseBtn.setDisable(true);
            submitBtn.setDisable(false);
            listView.setItems(courses);
            courseNameTF.clear();
            creditHoureTF.clear();
            size++;

        }
        else{
            submitBtn.setDisable(false);

        }    
    }

    public void processCourses() {
        Integer selectedValue = numberOfCourse.getValue();
        if (selectedValue == null) {
            return;
        }

        while (selectedValue > 0) {
            String selectedCourseGrade = courseGrade.getValue();
            String courseName = courseNameTF.getText();
            String text = creditHoureTF.getText();
            int creditHours = Integer.parseInt(text);

            selectedValue--;
        }

    }

    @FXML
    void onRemoveInfoHandler(ActionEvent event) throws SQLException {
        Query query = new Query();
        query.deleteStudent(Integer.parseInt(enterIdTF.getText()));
        tableViewShow();
    }
     float grade;
    @FXML
    void onSubmitBtnHandler(ActionEvent event) throws SQLException {
        if (submitBtn.getText().equals("Submit")) {
            Query query = new Query();
            query.insertStudent(studentNameTF.getText(), Integer.parseInt(studentIdTF.getText()), gradeReport(), courses, index);
            index = 1;
            tableViewShow();
            System.out.println("hasjhajs");
            tableViewShow();
        }else{
            submitBtn.setText("Submit");
            Query query = new Query();
            query.deleteStudent(Integer.parseInt(enterIdTF.getText()));
            query.insertStudent(studentNameTF.getText(), Integer.parseInt(studentIdTF.getText()), gradeReport(), courses, index);
            
            tableViewShow();
        }
    }
    
    @FXML
    void onUpdateInfoHandler(ActionEvent event) throws SQLException {
        if(enterIdTF.getText() != null){
            listViewSet();
            formVBox.setVisible(true);
            
            numberOfCourse.setValue(courses.size());
            courseNameTF.setText(courses.get(0).getCourseName());
            courseGrade.setValue(courses.get(0).getGrade());
            creditHoureTF.setText(String.valueOf(courses.get(0).getCreditHr()));
           
            
            for(int i = 0; i < tView.size(); i++){
                if(Integer.parseInt(enterIdTF.getText()) == tView.get(i).getId()){
                    studentNameTF.setText(tView.get(i).getUserName());
                    studentIdTF.setText(String.valueOf(tView.get(i).id));
                    
                }
            }
            submitBtn.setText("update");
            
            
            copyId = enterIdTF.getText();
         
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setNumOfCourses();
        setGrade();
        formVBox.setVisible(false);
        nextCourseBtn.setDisable(true);
        submitBtn.setDisable(true);
        
        try {
            tableViewShow();
            tableViewShow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseNameTF.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        creditHoureTF.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        courseGrade.valueProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        numberOfCourse.valueProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
    
    }

    private void setNumOfCourses(){
        ObservableList<Integer> numberOfCourses = FXCollections.observableArrayList();
        numberOfCourses.addAll(4, 5, 6, 7, 8, 9, 10);
      numberOfCourse.setItems(numberOfCourses);
    }
    private void setGrade(){
        ObservableList<String> gradeList = FXCollections.observableArrayList();
        gradeList.addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F");
        courseGrade.setItems(gradeList);
    }
    @SuppressWarnings("unused")
    private void listViewSet() throws SQLException{
        Query query = new Query();
        courses = query.selectCourse(Integer.parseInt(enterIdTF.getText()));
        listView.setItems(courses);
    }

    
    private void tableViewShow() throws SQLException{
      
        Query query = new Query();
        tView = query.selectForTable();
        userNameColumn.setCellValueFactory(new PropertyValueFactory<TableClass, String>("userName"));
        idColumn.setCellValueFactory(new PropertyValueFactory<TableClass, Integer>("id"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<TableClass, Float>("grade"));
        tableView.setItems(tView);
    }

    private float gradeReport(){
        float sum = 0;
        float grade = 1;
        int creditHoure = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for(int i = 0; i < courses.size(); i++){
            String couseGrade = courses.get(i).getGrade();
            switch (couseGrade) {
                case "A+" , "A":
                    grade = 4f;
                    break;
                case "A=" :
                    grade = 3.75f;
                    break;
                case "B+":
                    grade = 3.5f;
                    break;
                case "B":
                    grade = 3f;
                    break;
                case "B-":
                    grade = 2.75f;
                    break;
                case "C+" :
                    grade = 2.5f;
                    break;
                case "C" :
                    grade = 2f;
                    break;
                case "C-":
                    grade = 1.75f;
                    break;
                case "D":
                    grade = 1f;
                case "F" :
                    grade = 0;
                    break;
                default:
                    break;
            }
            sum += (grade * courses.get(i).getCreditHr());
            creditHoure += courses.get(i).getCreditHr();
        }

        return Float.parseFloat(decimalFormat.format(sum/creditHoure));
    }
    

}
