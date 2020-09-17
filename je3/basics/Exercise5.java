package je3.basics;

import java.io.*;

public class Exercise5 {

    public static void main(String[] args) throws IOException {
        float[] table = new float[100];

        for (int i=0; i< 100; i++) {
            table[i] = (float)Math.random() * 1000.00f;
        }

        for(int i=0; i<100; i++) {
            int min = i;
            for (int j=i ; j<100;j++) {
                if (table[j] < table[min]) min = j;
            }
            float tmp;
            tmp = table[i];
            table[i] = table[min];
            table[min] = tmp;
        }

        for (int i=0; i< 100; i++) {
            System.out.println(table[i]);
        }

        BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a number> ");
        String userInput = rdr.readLine();
        try {
            float inputVal = Float.parseFloat(userInput);
            int index = findIndex(table, inputVal);
            System.out.println(table[index]);

        }
        catch (NumberFormatException e) {
            System.out.println("not a valid number");
        }
    }

    public static int findIndex(float[] list, float findVal) {
        int lo = 0;
        int hi = list.length -1;
        int pivot = -1;
        while (lo <= hi) {
            pivot = (hi + lo) / 2;
            float pivotVal = list[pivot];
            if (pivotVal == findVal) {
                return pivot;
            }
            else if (pivotVal < findVal) {
                lo = pivot + 1;
            }
            else {
                hi = pivot - 1;
            }
        }
        return pivot;
    }
}