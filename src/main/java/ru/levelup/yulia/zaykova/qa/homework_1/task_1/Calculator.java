package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

import java.io.*;

/**
 * Calculator
 *
 * @version 1.0 25.03.2019
 * @author Yulia Zaykova
 */
public class Calculator {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.startCalculator();
    }

    public void startCalculator() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufReaderInput objReader  = new BufReaderInput(reader);

        Addition objAdd = new Addition();
        Subtraction objSub = new Subtraction();
        Multiplication objMult = new Multiplication();
        Power objPow = new Power();
        Factorial objFact = new Factorial();

        Number arg1, arg2;
        int iVar1, iVar2;
        long lVar1, lVar2;
        double dVar1, dVar2;
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
                operation = objReader.inputStringByPattern("Choose operation ( + - * ^ ! ) : ",
                                                           "[+*^!-]");

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
                        // TODO лучше было бы использовать && instead of || до второй части как правило не доходит
                        // TODO логику операций +, -, * и ^ лучше спрятать в соответствующих классах
                        if ((arg1 instanceof Double) || (arg2 instanceof Double)) {
                            // TODO agr*.*Value() можно передавать сразу как параметры в метод
                            dVar1 = arg1.doubleValue();
                            dVar2 = arg2.doubleValue();
                            System.out.println(dVar1 + " + " + dVar2 + " = " + objAdd.add(dVar1, dVar2));
                            // TODO лучше было бы использовать && instead of || до второй части как правило не доходит
                        } else if ((arg1 instanceof Long) || (arg2 instanceof Long)) {
                            lVar1 = arg1.longValue();
                            lVar2 = arg2.longValue();
                            System.out.println(lVar1 + " + " + lVar2 + " = " + objAdd.add(lVar1, lVar2));
                        } else {
                            iVar1 = arg1.intValue();
                            iVar2 = arg2.intValue();
                            System.out.println(iVar1 + " + " + iVar2 + " = " + objAdd.add(iVar1, iVar2));
                        }
                        break;
                    case "-":
                        // TODO лучше было бы использовать && instead of || до второй части как правило не доходит
                        if ((arg1 instanceof Double) || (arg2 instanceof Double)) {
                            dVar1 = arg1.doubleValue();
                            dVar2 = arg2.doubleValue();
                            System.out.println(dVar1 + " - " + dVar2 + " = " + objSub.subtract(dVar1, dVar2));
                            // TODO лучше было бы использовать && instead of || до второй части как правило не доходит
                        } else if ((arg1 instanceof Long) || (arg2 instanceof Long)) {
                            lVar1 = arg1.longValue();
                            lVar2 = arg2.longValue();
                            System.out.println(lVar1 + " - " + lVar2 + " = " + objSub.subtract(lVar1, lVar2));
                        } else {
                            iVar1 = arg1.intValue();
                            iVar2 = arg2.intValue();
                            System.out.println(iVar1 + " - " + iVar2 + " = " + objSub.subtract(iVar1, iVar2));
                        }
                        break;
                    case "*":
                        // TODO лучше было бы использовать && instead of || до второй части как правило не доходит
                        if ((arg1 instanceof Double) || (arg2 instanceof Double)) {
                            dVar1 = arg1.doubleValue();
                            dVar2 = arg2.doubleValue();
                            System.out.println(dVar1 + " * " + dVar2 + " = " + objMult.multiply(dVar1, dVar2));
                            // TODO лучше было бы использовать && instead of || до второй части как правило не доходит
                        } else if ((arg1 instanceof Long) || (arg2 instanceof Long)) {
                            lVar1 = arg1.longValue();
                            lVar2 = arg2.longValue();
                            System.out.println(lVar1 + " * " + lVar2 + " = " + objMult.multiply(lVar1, lVar2));
                        } else {
                            iVar1 = arg1.intValue();
                            iVar2 = arg2.intValue();
                            System.out.println(iVar1 + " * " + iVar2 + " = " + objMult.multiply(iVar1, iVar2));
                        }
                        break;
                    case "^":
                        iVar2 = arg2.intValue();
                        if (arg1 instanceof Double) {
                            dVar1 = arg1.doubleValue();
                            System.out.println(dVar1 + " ^ " + iVar2 + " = " + objPow.power(dVar1, iVar2));
                        } else if (arg1 instanceof Long) {
                            lVar1 = arg1.longValue();
                            System.out.println(lVar1 + " ^ " + iVar2 + " = " + objPow.power(lVar1, iVar2));
                        } else {
                            iVar1 = arg1.intValue();
                            System.out.println(iVar1 + " ^ " + iVar2 + " = " + objPow.power(iVar1, iVar2));
                        }
                        break;
                    case "!":
                        if ((arg1 instanceof Integer) && (arg1.intValue() >= 0)) {
                            iVar1 = arg1.intValue();
                            fact = objFact.factorial(iVar1);
                            if (fact > 0) {
                                System.out.println(iVar1 + "! = " + fact);
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
                quit = objReader.inputStringByPattern("Press 'Enter' to continue or 'Q' to exit. ",
                                                      "[qQ]*");
            }
            // TODO закрытие потоков ввода/вывода лучше закрывать в блоке finally
            reader.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
            System.out.print("Bye-bye! See you later.");
    }

}
