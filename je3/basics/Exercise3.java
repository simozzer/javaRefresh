package je3.basics;

public class Exercise3 {

    public static void main(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("invalid number of arguments");
        }

        String text = args[0];
        int len = text.length();

        int start, amt;

        try {
            start = Integer.parseInt(args[1]);
            if ((start <0) || (start >= len -1)) {
                throw new IllegalArgumentException("2nd out of range");
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("2nd argument is not an integer");
        }

        try {
            amt = Integer.parseInt(args[2]);
            if (((amt + start) > len) || (amt <1)) {
                throw new IllegalArgumentException("3rd arg out of range");
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("3rd argument is not an integer");
        }

        for (int i= start; i < start+amt; i++) {
            System.out.print(text.charAt(i));
        }

        System.out.println();



    }
}
