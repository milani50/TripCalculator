package TripCalculator;

import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the starting point for the program. 
 * It provides the GUI for the application. 
 * The user can enter one student name at a time along with their expenses.
 * 
 * @author Ali Milaninia  Dec 26, 2017
 */
public class RunTripCalculator extends Application {
    
    TextField studentTextField = new TextField();
    TextArea expensesTextBox = new TextArea();
    TextArea resultsTextBox = new TextArea();
    List<Student> studentList = new LinkedList();

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Trip Calculator");
        
        GridPane resultsGrid = new GridPane();
        resultsGrid.setAlignment(Pos.CENTER);
        resultsGrid.setHgap(10);
        resultsGrid.setVgap(10);
        resultsGrid.setPadding(new Insets(25, 25, 25, 25));
        
        
        Text resultsSceneTitle = new Text("Results");
        resultsSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        resultsGrid.add(resultsSceneTitle, 0, 0, 2, 1);
        
        resultsTextBox.setWrapText(true);
        resultsTextBox.setEditable(false);
        resultsGrid.add(resultsTextBox, 1, 5);

        Button btnClose= new Button("Close");
        HBox hbBtnResults = new HBox(10);
        hbBtnResults.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnResults.getChildren().addAll(btnClose);
        resultsGrid.add(hbBtnResults, 1, 8);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Enter student expenses one student at a time.");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        Label studentNameLabel = new Label("Student Name:");
        grid.add(studentNameLabel, 0, 1);
        
        grid.add(studentTextField, 1, 1);
        
        Text nameHint = new Text("Enter one student name, example: David");
        nameHint.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(nameHint, 1, 0, 1, 6);
        
        Label expenses = new Label("Expenses:");
        grid.add(expenses, 0, 5);
        
        expensesTextBox.setWrapText(true);
        grid.add(expensesTextBox, 1, 5);
        
        Text expenseHint1 = new Text("Enter student expenses, separated by spaces");
        Text expenseHint2 = new Text("example: 10.00 20 38.41 45.00");
        expenseHint1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(expenseHint1, 1, 6, 1, 1);
        expenseHint2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(expenseHint2, 1, 7, 1, 1);
        
        Button btnSubmit= new Button("Submit");
        Button btnNext= new Button("Next Student");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnNext, btnSubmit);
        grid.add(hbBtn, 1, 8);
                
        // Next button handler
        btnNext.setOnAction((event) -> {
            readInputFields();
        });

        // Submit button handler
        btnSubmit.setOnAction((event) -> {
            
            String studentName = studentTextField.getText();
            String expensesText = expensesTextBox.getText();
            
            boolean inputValid = true;
            
            if (! studentName.isEmpty() &&
                    ! expensesText.isEmpty())  {
                inputValid = readInputFields();
            }
            
            if (inputValid) {
                String resultsString = new TripCalculator(studentList).processStudentExpenses();
                Scene resultScene = new Scene(resultsGrid, 600, 275);
                resultsTextBox.setText(resultsString);
                primaryStage.setScene(resultScene);
            }
            
        });

        // Close button handler
        btnClose.setOnAction((event) -> {
            System.exit(0);
            
        });


        Scene scene = new Scene(grid, 600, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Reads the contents of input fields.
     * If a field is empty, an error message is displayed.
     * The expenses field is checked for valid values.
     * The name field and the expenses field contents are used to create
     * a Student object that gets added to the list of students.
     * 
     * @return true/false fields are valid or not.
     */
    private boolean readInputFields() {
        String studentName = studentTextField.getText();
        String expensesText = expensesTextBox.getText();
        double studentExpenses = 0;
        
        if (studentName.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Encountered");
            alert.setHeaderText("Student name field is empty.");
            alert.setContentText("Enter one student name, example: David");
            
            alert.showAndWait();
            studentTextField.requestFocus();
            
            
        } else if (expensesText.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Encountered");
            alert.setHeaderText("Expenses field is empty.");
            alert.setContentText("Enter student expenses, separated by spaces"
                    + "example: 10.00 20.00 38.41 45.00");
            
            alert.showAndWait();
            expensesTextBox.requestFocus();
            
        } else {
            
            try {
                String[] tokenize = expensesText.split("\\s+");
                for (String token : tokenize) {
                    studentExpenses += (Double.parseDouble(token));
                }
                
                Student tempObj = new Student(studentName, studentExpenses);
                
                // add to array list
                studentList.add(tempObj);
                
                studentTextField.clear();
                expensesTextBox.clear();
                studentTextField.requestFocus();
                
            } catch(Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Encountered");
                alert.setHeaderText("Incorrect value entered in Expenses field.");
                alert.setContentText("Expenses must be entered as space-separated numbers.\n"
                        + "Example: \n"
                        + "12.34 45.56 67.78\n\n"
                        + "Please revise the values in Expenses field.");
                
                alert.showAndWait();
                return false;
            }
        }
        
        return true;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
