package org.example.lesson17juni;

import java.util.Scanner;

public class Utils {
    // подзадача для получения данных от пользователя
    public static String getInput(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("введите матем.выражение в формате 10.5*5+1-7.1 и нажмите Ввод");
        String expression = scanner.nextLine();
        return expression; // вернуть введённую пользователем строку
    }
    public static double getResult(String inputUser){
        int countOperations = getCounterOperations(inputUser); // количество операций, чтобы знать кол-во чисел в выражении
        double[] numbers = getNumbers(inputUser, countOperations + 1);
        char[] operators = getOperators(inputUser, countOperations);
        double result =numbers[0];
        for (int i=0; i< numbers.length-1; i++){
            result=calculate(result,operators[i],numbers[i+1]);
        }
        return result;
    }

    private static double calculate(double number1, char operator, double number2) {
        switch (operator){
            case '+':
                return number1+number2;
            case  '-':
                return number1-number2;
            case  '*':
                return number1*number2;
            case '/':
                if (number2==0){
                    System.out.println("деление на 0");
                    return 0;
                }
                return number1/number2;
            default:
                System.out.println("неподдерживаемая матем. операция");
                return 0;
        }
    }

    private static int getCounterOperations(String inputString){
        int counter = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (isOperation(inputString.charAt(i))) {  // проверяем является ли символ по индексу
                counter++;                              // i математич. операцией
            }
        }
        return counter;
    }

    private static char[] getOperators(String inputString, int countOperations){
        char[] operators = new char[countOperations];
        countOperations = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (isOperation(inputString.charAt(i))) {
                operators[countOperations] = inputString.charAt(i); // заполняем массив мат. операт// оров
                countOperations++;
            }
        }
        return operators;
    }

    // проверяем является ли символ по индексу i математич. операцией
    private static boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static double[] getNumbers(String inputString, int countNumbers) {
        double[] numbers = new double[countNumbers];
        int startIndex = 0;
        countNumbers = 0; // обнуляем счетчик массива для чисел
        for (int i = 0; i < inputString.length(); i++) {
            // вырезаем число до !!! матем знака
            if (isOperation(inputString.charAt(i))) {
                String stringNumber = inputString.substring(startIndex, i); // вырезаем число в виде подстроки
                double number = Double.parseDouble(stringNumber); // преобразуем подстроку в число типа Дабл
                numbers[countNumbers] = number; // заносим число в массив
                countNumbers++; // изменяем счетчик массива
                startIndex = i + 1;
            }
            if (i == inputString.length() - 1) { // это условие для последнего числа
                String stringNumber = inputString.substring(startIndex);
                double number = Double.parseDouble(stringNumber);
                numbers[countNumbers] = number;
            }
        }
        return numbers;

    }

    // подзадача : вывод результата
    public static void printResult(String inputUser, double result){
        System.out.printf("%s = %.2f", inputUser, result);
    }
}
