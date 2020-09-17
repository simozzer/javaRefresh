package je3.basics;

public class FizzBuzz {

    public static void main(String[] args) {
        for (var i= 0; i<100; i++) {
            if (((i % 5) == 0) && ((i % 7) == 0)) {
                System.out.print(":fizzbuzz:");
            } else if ((i % 5) == 0) {
                System.out.print(":fizz:");
            } else if ((i % 7) == 0) {
                System.out.print(":buzz:");
            } else {
                System.out.print(i + ":");
            }
        }

        System.out.println();
    }
}