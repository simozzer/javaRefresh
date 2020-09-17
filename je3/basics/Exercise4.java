package je3.basics;

import java.io.*;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for(;;) {
            System.out.print("Reverser> ");
            String input = in.readLine();
            if (input.equals("tiuq")) break;
            for (int j = input.length()-1; j >=0; j--) {
                System.out.print(input.charAt(j));
            }
            System.out.println();
        }
    }
}