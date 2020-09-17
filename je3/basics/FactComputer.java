package je3.basics;

public class FactComputer {

    public static void main(String[] args) {

        try {
            int x = Integer.parseInt(args[0]);
            System.out.println(x + "! = " + Factorial4.factorial(x));
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("no arguments there");
            System.out.println("Usage: java je3.basics.FactComputer <number>");
        }

        catch (NumberFormatException e) {
            System.out.println(args[0] + " is not an integer");
        }

        catch (IllegalArgumentException e) {
            System.out.println("Bad argument: " + e.getMessage());
        }
    }
}