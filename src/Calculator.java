import java.util.Scanner;

public class Calculator {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Консольный калькулятор ===");
        System.out.println("Команды: C/c — сброс, S/s — выход.");
        System.out.println("Введите первое число:");

        double result = readDouble();

        while (true) {
            System.out.println("Текущий результат: " + result);
            System.out.println("Введите операцию (+, -, *, /) или команду (C/c, S/s):");

            String input = SCANNER.next().trim();

            if (input.equalsIgnoreCase("s")) {
                System.out.println("Завершение работы. До свидания!");
                return;
            }

            if (input.equalsIgnoreCase("c")) {
                result = 0;
                System.out.println("Результат сброшен. Введите первое число:");
                result = readDouble();
                continue;
            }

            char operator = input.charAt(0);

            if (!isSupportedOperator(operator)) {
                System.out.println("Ошибка: неподдерживаемая операция '" + operator + "'. Повторите ввод.");
                continue;
            }

            System.out.println("Введите второй операнд:");
            double secondOperand = readDouble();

            if (operator == '/' && secondOperand == 0) {
                System.out.println("Ошибка: деление на ноль. Результат не изменён.");
                continue;
            }

            result = calculate(result, secondOperand, operator);
            System.out.println("Результат: " + result);
        }
    }

    private static boolean isSupportedOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    private static double calculate(double firstOperand, double secondOperand, char operator) {
        switch (operator) {
            case '+':
                return add(firstOperand, secondOperand);
            case '-':
                return subtract(firstOperand, secondOperand);
            case '*':
                return multiply(firstOperand, secondOperand);
            case '/':
                return divide(firstOperand, secondOperand);
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) {
        return a / b;
    }

    private static double readDouble() {
        while (true) {
            if (SCANNER.hasNextDouble()) {
                return SCANNER.nextDouble();
            }
            String invalid = SCANNER.next();
            if (invalid.equalsIgnoreCase("s")) {
                System.out.println("Завершение работы. До свидания!");
                System.exit(0);
            }
            if (invalid.equalsIgnoreCase("c")) {
                System.out.println("Сброс невозможен во время ввода числа. Введите число:");
                continue;
            }
            System.out.println("Ошибка: '" + invalid + "' не является числом. Повторите ввод:");
        }
    }
}
