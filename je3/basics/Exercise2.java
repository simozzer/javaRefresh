package je3.basics;

public class Exercise2 {

    public static void main(String[] args) {
        int n0 = 1, n1 = 1, n2 = 1, n3;

        System.out.print(n0 + " " + n1 + " " + n2 + " ");
        for(int i =0 ; i < 18; i++) {
            n3 = n1 + n0 + n2;
            System.out.print(n3 + " ");
            n0=n1;
            n1=n2;
            n2=n3;
        }
        System.out.println();
    }
}
