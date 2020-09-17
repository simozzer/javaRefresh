package je3.basics;

public class Factorial {

    public static int factorial(int x) {

        if (x < 0) throw new IllegalArgumentException("x must be >= 0");
        int fact = 1;
        int i = 2;
        while (i <= x) {
            fact *= i;
            i++;
        }
        return fact;
    }
}