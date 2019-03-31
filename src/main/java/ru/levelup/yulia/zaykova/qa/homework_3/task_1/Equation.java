package ru.levelup.yulia.zaykova.qa.homework_3.task_1;

import java.io.*;
import java.util.Locale;

/**
 * Квадратное уравнение
 */
public class Equation {

    public static void main(String[] args) {
        Equation eq = new Equation();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        NumberInput in = new NumberInput();

        double a=1, b=1, c=1;

        System.out.println("Enter coefficients of quadratic equation  \"a*x^2 + b*x + c = 0\"");
        try {
            a = in.inputDouble(" a = ", reader);
            b = in.inputDouble(" b = ", reader);
            c = in.inputDouble(" c = ", reader);
            System.out.println();
            eq.quadratic(a, b, c);
        } catch (IOException e) {
            System.out.println("IO Error!");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Решение квадратного уравнения
    public void quadratic (double a, double b, double c) {
        double x1, x2;
        // TODO Можно проверить в одном условии что a, b и c не равно 0
        if (a != 0) {
            if ((b != 0) && (c !=0)) {
                fullQuadraticEquation(a, b, c);
            } else {
                System.out.print("Non-full quadratic equation\n\nResult: ");
                // TODO Можно проверить в одном условии что b и c равно 0
                if (b == 0) {
                    if ( c == 0) {
                        x1 = 0;
                        System.out.println(String.format(Locale.US, "x = %.2f" , x1));
                    } else {
                        // TODO Это может быть заменено на else - if
                        if ( -c / a > 0) {
                            x1 = Math.sqrt(-c / a);
                            x2 = -Math.sqrt(-c / a);
                            System.out.println(String.format(Locale.US, "x1 = %.2f  x2 = %.2f", x1, x2));
                        } else {
                            System.out.println("Equation has no solution!");
                        }
                    }
                } else if (c == 0) {
                    x1 = 0;
                    x2 = -b / a;
                    System.out.println(String.format(Locale.US, "x1 = %.2f  x2 = %.2f", x1, x2));
                }
            }
        } else {
            System.out.println("This isn't a quadratic equation!");
        }
        System.out.println();

    }

    // Решение полного квадратного уравнения
    private void fullQuadraticEquation  (double a, double b, double c) {
        double D = discriminant(a, b, c);
        double x1, x2;

        System.out.print("Full quadratic equation\nDiscriminant = " + D +"\n\nResult: ");
        if (D > 0) {
            x1 = (-b + Math.sqrt(D)) / (2 * a);
            x2 = (-b - Math.sqrt(D)) / (2 * a);
            System.out.println(String.format(Locale.US, "x1 = %.2f  x2 = %.2f", x1, x2));
        } else if (D == 0) {
            x1 = -b / (2 * a);
            System.out.println(String.format(Locale.US, "x1 = x2 = %.2f", x1));
        } else {
            System.out.println("Equation has no solution!");
        }

    }

    // Вычисление дискриминанта
    private double discriminant(double a, double b, double c) {
        return (b * b - 4 * a * c);
    }
}
