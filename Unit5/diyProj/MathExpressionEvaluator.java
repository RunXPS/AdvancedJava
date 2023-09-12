package diyProj;

import java.util.Stack;

public class MathExpressionEvaluator {
    public static void main(String[] args) {
        String mathExpression = "(18+4)*3";
        double result = evaluateMathExpression(mathExpression);
        System.out.println(mathExpression + " = " + result);
    }

    public static double evaluateMathExpression(String mathExpression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < mathExpression.length(); i++) {
            char ch = mathExpression.charAt(i);
            if (ch == ' ') {
                continue;
            } else if (Character.isDigit(ch)) {
                double number = 0;
                while (i < mathExpression.length() && Character.isDigit(mathExpression.charAt(i))) {
                    number = number * 10 + (mathExpression.charAt(i) - '0');
                    i++;
                }
                i--;
                numbers.push(number);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    double result = applyOperator(operators.pop(), numbers.pop(), numbers.pop());
                    numbers.push(result);
                }
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.empty() && hasPrecedence(ch, operators.peek())) {
                    double result = applyOperator(operators.pop(), numbers.pop(), numbers.pop());
                    numbers.push(result);
                }
                operators.push(ch);
            }
        }
        while (!operators.empty()) {
            double result = applyOperator(operators.pop(), numbers.pop(), numbers.pop());
            numbers.push(result);
        }
        return numbers.pop();
    }

    public static boolean hasPrecedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')') {
            return false;
        } else if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    public static double applyOperator(char operator, double operand2, double operand1) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return operand1 / operand2;
            default:
                throw new UnsupportedOperationException("Invalid operator");
        }
    }
}
