package je3.basics;

import java.math.BigInteger;
import java.util.*;

public class Factorial4 {

    protected static ArrayList table = new ArrayList();

    static { table.add(BigInteger.valueOf(1)); }

    // declares as synchronized so that it can be safely used in multithreaded programs
    public static synchronized BigInteger factorial(int x) {

        if (x<0) throw new IllegalArgumentException("x must be non-negative");

        for (int size = table.size(); size <= x; size++) {
            BigInteger lastfact = (BigInteger)table.get(size-1);
            BigInteger nextFact = lastfact.multiply(BigInteger.valueOf(size));
            table.add(nextFact);
        }
        return (BigInteger) table.get(x);
    }

    public static void main(String[] args) {
        for (int i=0; i <=50; i++) {
            System.out.println(i + "! = " + factorial(i));
        }
    }
 }