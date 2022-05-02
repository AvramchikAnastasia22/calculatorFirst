package utils;

import java.util.Scanner;

public class Printer {
    public static void printAnswer(){
        System.out.println("Enter your expression: ");
        Scanner sc = new Scanner(System.in);
        String expr = sc.next();
        System.out.println("Result: ");
        System.out.println(expr + "=" + new Operations().getAnswerExpression(expr));
    }
}
