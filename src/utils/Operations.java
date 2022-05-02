package utils;

import java.util.Stack;

public class Operations {
    public static int getOperationPriority(char symbol) {
        switch (symbol) {
            case '*':
            case '/':
                return 4;
            case '+':
            case '-':
                return 3;
            case '(':
                return 2;
            case ')':
                return 1;
            default:
                return 0;
        }

    }

    public String processingExpression(String expr) {
        String processExpr = "";
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '-') {
                if (i == 0) {
                    processExpr += '0';
                } else if (expr.charAt(i - 1) == '(') {
                    processExpr += '0';
                }
            }
            processExpr += expr.charAt(i);
        }
        return processExpr;
    }

    public static String convertExpr(String expr) {
        String str = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {

                if (expr.charAt(i) >= 58 && expr.charAt(i) <= 128 || expr.charAt(i) >= 33 && expr.charAt(i) <= 39) {
                       System.out.println("Oops... incorrect expression");
                       throw new NumberFormatException("Oops... incorrect expression " + expr.charAt(i));
                }

            switch (getOperationPriority(expr.charAt(i))) {
                case 0:
                    str += expr.charAt(i);
                    break;
                case 2:
                    stack.push(expr.charAt(i));
                    break;
                case 1:
                    if (getOperationPriority(expr.charAt(i)) == 1) {
                        str += ' ';
                        while (getOperationPriority(stack.peek()) != 2) {
                            str += stack.pop();
                        }
                        stack.pop();
                    }
                    break;
                default:
                    if (getOperationPriority(expr.charAt(i)) > 2) {
                        str += ' ';
                        while (!stack.empty()) {
                            if (getOperationPriority(stack.peek()) >= getOperationPriority(expr.charAt(i))) {
                                str += stack.pop();
                            } else {
                                break;
                            }
                        }
                        stack.push(expr.charAt(i));
                    }
                    break;
            }
        }
        while (!stack.empty()) {
            str += stack.pop();
        }
        return str;
    }

    public static double calculateConvertExpr(String convExpr) {
        String symbol = "";
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < convExpr.length(); i++) {
            if (convExpr.charAt(i) == ' ') continue;
            if (getOperationPriority(convExpr.charAt(i)) == 0) {
                while (getOperationPriority(convExpr.charAt(i)) == 0 && convExpr.charAt(i) != ' ') {
                    symbol += convExpr.charAt(i++);
                    if (i == convExpr.length()) break;
                }
                stack.push(Double.parseDouble(symbol));
                symbol = "";
            }
            if (getOperationPriority(convExpr.charAt(i)) > 2) {
                double a = stack.pop();
                double b = stack.pop();
                switch (convExpr.charAt(i)) {
                    case '+':
                        stack.push(b + a);
                        break;
                    case '-':
                        stack.push(b - a);
                        break;
                    case '*':
                        stack.push(b * a);
                        break;
                    case '/':
                        stack.push(b / a);
                        break;
                }

            }
        }
        return stack.pop();
    }

    public double getAnswerExpression(String expr) {
        return calculateConvertExpr(convertExpr(processingExpression(expr)));
    }
}
