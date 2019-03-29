package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

import java.io.*;

/**
 * Calculator
 *
 * @author Yulia Zaykova
 * @version 1.2 29.03.2019
 */
public class Calculator {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.startCalculator();
    }

    public void startCalculator() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufReaderInput objReader = new BufReaderInput(reader);

        Addition objAdd = new Addition();
        Subtraction objSub = new Subtraction();
        Multiplication objMult = new Multiplication();
        Power objPow = new Power();
        Factorial objFact = new Factorial();

        Number arg1, arg2;
        double fact;  // factorial

        String operation;
        String quit = "n";

        // Initial hint for user
        System.out.println("This calculator supports operations:");
        System.out.println(" x+y   addition");
        System.out.println(" x-y   subtraction");
        System.out.println(" x*y   multiplication");
        System.out.println(" x^y   power of a number (x - base, y - power(integer))");
        System.out.println(" x!    factorial (x>=0)");
        System.out.println("You can exit from program after each operation.");

        try {
            while (!quit.toLowerCase().equals("q")) {
                System.out.println();

                // Input first argument arg1
                arg1 = objReader.inputNumber("Enter x:");

                // Input operation
                operation = objReader.inputStringByPattern("Choose operation ( + - * ^ ! ) : ", "[+*^!-]");

                // Input second argument arg2
                switch (operation) {
                    case "+":
                    case "-":
                    case "*":
                        arg2 = objReader.inputNumber("Enter y:");
                        break;
                    case "^":
                        arg2 = objReader.inputIntNumber("Enter y (integer):");
                        break;
                    default:
                        arg2 = null;
                        break;
                }

                // Result output
                System.out.print(" -> RESULT: ");
                switch (operation) {
                    case "+":
                        // TODO логику операций +, -, * и ^ лучше спрятать в соответствующих классах
                        objAdd.addResult(arg1, arg2);
                        break;
                    case "-":
                        objSub.subResult(arg1, arg2);
                        break;
                    case "*":
                        objMult.multResult(arg1, arg2);
                        break;
                    case "^":
                        objPow.powResult(arg1, arg2);
                        break;
                    case "!":
                        if ((arg1 instanceof Integer) && (arg1.intValue() >= 0)) {
                            fact = objFact.factorial(arg1.intValue());
                            if (fact > 0) {
                                System.out.println(arg1.intValue() + "! = " + fact);
                            } else if (fact == -1) {
                                System.out.println(" ERROR: Overflow.");
                            }
                        } else {
                            System.out.println(" ERROR: Argument must be integer number >= 0 !");
                        }
                        break;
                    default:
                        break;
                }
                System.out.println();

                // Input Q or q to exit
                quit = objReader.inputStringByPattern("Press 'Enter' to continue or 'Q' to exit. ", "[qQ]*");
            }
            // TODO закрытие потоков ввода/вывода лучше закрывать в блоке finally
            //reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Bye-bye! See you later.");
    }

}
