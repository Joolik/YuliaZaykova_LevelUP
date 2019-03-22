package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.startCalculator();
    }

    public void startCalculator() {
        Scanner scanner = new Scanner(System.in);

        ScannerInput objInput = new ScannerInput(scanner);

        Addition objAdd = new Addition();
        Subtraction objSub = new Subtraction();
        Multiplication objMult = new Multiplication();
        Power objPow = new Power();
        Factorial objFact = new Factorial();

        Number arg1, arg2;
        int iVar;
        double dVar1, dVar2;
        String line;
        String operation;
        String quit = "y";

        // Initial hint for user
        System.out.println("This calculator supports operations:");
        System.out.println(" x+y   addition");
        System.out.println(" x-y   subtraction");
        System.out.println(" x*y   multiplication");
        System.out.println(" x^y   power of a number (x - base, y - power(integer))");
        System.out.println(" x!    factorial (x>=0)");
        System.out.println("You can exit from program after each operation.");

        while (!quit.toLowerCase().equals("q")) {
            System.out.println();

            // Input first argument arg1
            arg1 = objInput.inputNumber("Enter x:");

            // Input operation
            operation = objInput.inputStringByPattern("Choose operation ( + - * ^ ! ) : ","[+\\*^!\\-]");

            // Input second argument arg2
            switch (operation) {
                case "+":
                case "-":
                case "*":
                    arg2 = objInput.inputNumber("Enter y:");
                    break;
                case "^":
                    arg2 = objInput.inputIntNumber("Enter y (integer):");
                    break;
                default:
                    arg2 = null;
                    break;
            }

            // Output result
            System.out.print(" -> RESULT: ");
            switch (operation) {
                case "+":
                    dVar1 = arg1.doubleValue();
                    dVar2 = arg2.doubleValue();
                    System.out.println(dVar1 + " + " + dVar2 + " = " + objAdd.add(dVar1, dVar2));
                    break;
                case "-":
                    dVar1 = arg1.doubleValue();
                    dVar2 = arg2.doubleValue();
                    System.out.println(dVar1 + " - " + dVar2 + " = " + objSub.subtract(dVar1, dVar2));
                    break;
                case "*":
                    dVar1 = arg1.doubleValue();
                    dVar2 = arg2.doubleValue();
                    System.out.println(dVar1 + " * " + dVar2 + " = " + objMult.multiply(dVar1, dVar2));
                    break;
                case "^":
                    dVar1 = arg1.doubleValue();
                    iVar = arg2.intValue();
                    System.out.println(dVar1 + " ^ " + iVar + " = " + objPow.power(dVar1, iVar));
                    break;
                case "!":
                    iVar = arg1.intValue();
                    if ((iVar >= 0) && (iVar < 21)) {
                        System.out.println(iVar + "! = " + objFact.factorial(iVar));
                    } else if (iVar < 0) {
                        System.out.println("(" + iVar + ")!  ERROR: Wrong negative argument");
                    } else {
                        System.out.println("Result is too big.");
                    }
                    break;
                default:
                    break;
            }
            System.out.println();

             // Input Q or q to exit
             quit = objInput.inputStringByPattern("Press 'Enter' to continue or 'Q' to exit", "[qQ]*");
        }

            scanner.close();
            System.out.print("Bye-bye! See you later.");
    }

}
