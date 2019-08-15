package sample;

public class Model {

    public double calculate(double number1, double number2, String operator) {
        switch (operator){
            case "+":
                return Math.round((number1+number2)*100)/100.0;
            case "-":
                return Math.round((number1-number2)*100)/100.0;
            case "*":
                return Math.round((number1*number2)*100)/100.0;
            case "/":
                if (number2 == 0){
                    return 0;
                }
                return Math.round((number1/number2)*100)/100.0;
            case "%":
                return Math.round(((number1*100)/number2)*100)/100.0;
        }

        System.out.println("Unknown operator " + operator);
        return 0;
    }
}
