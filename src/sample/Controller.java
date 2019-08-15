package sample;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private ResourceBundle resources;
/*

    @FXML
    private URL location;
*/

    @FXML
    private TextField inputOutputResult;
/*
    @FXML
    private Button buttonZero;

    @FXML
    private Button buttonPlus;

    @FXML
    private Button buttonOne;

    @FXML
    private Button buttonTwo;

    @FXML
    private Button buttonThree;

    @FXML
    private Button buttonMinus;

    @FXML
    private Button buttonEqual;

    @FXML
    private Button buttonFour;

    @FXML
    private Button buttonFive;

    @FXML
    private Button buttonSix;

    @FXML
    private Button buttonMultiply;

    @FXML
    private Button buttonDivision;

    @FXML
    private Button buttonSeven;

    @FXML
    private Button buttonEight;

    @FXML
    private Button buttonNine;

    @FXML
    private Button buttonReset;

    @FXML
    private Button buttonPercent;*/

    @FXML
    private TextArea buttonTextarea;

    @FXML
    private Button buttonGetLastResult;

    private Model model = new Model();
    private String operator = "";
    private double number1 = 0;
    private boolean start = true;
    private Driver driver = new Driver();

    public void processNumpad (ActionEvent event) {
        if (start){
            inputOutputResult.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        inputOutputResult.setText(inputOutputResult.getText() + value);
    }

    // вводим оператора
    public void processOperator (ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (value.equals("=")){
            if (operator.isEmpty())
                return;
            double number2 = Double.parseDouble(inputOutputResult.getText());
            // leave 2 sign after comma
            number1 = Math.round(number1 * 100.0) / 100.0;
            number2 = Math.round(number2 * 100.0) / 100.0;
            // calculate both numbers
            String amount = (model.calculate(number1, number2, operator)) + "";
            inputOutputResult.setText(amount);
            String textOut = number1 + " " + operator + " " + number2 + " = " + amount + "\n";
            // add row to DB
            driver.addLineToDB(textOut);
            // add row to right panel
            buttonTextarea.setText(textOut + buttonTextarea.getText());
            operator = "";
            start = true;
        } else if (value.equals("C")) {
            inputOutputResult.setText("");
            operator = "";
            start = true;
        } else {
            // calculate both numbers
            if (operator.isEmpty())
                number1 = Double.parseDouble(inputOutputResult.getText());
            else
                number1 = number1 + Double.parseDouble(inputOutputResult.getText());
            operator = value;
            inputOutputResult.setText("");
        }
    }

    // get last 10 rows from DB
    @FXML
    void initialize(){
        buttonGetLastResult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonTextarea.setText("");
                List<String> lines = driver.getLinesFromDB();
                for (String l : lines){
                    buttonTextarea.appendText(l);
                }
            }
        });
    }
}


